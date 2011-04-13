package Compiler.Parser;

import Compiler.Lexer.Lexer;
import Compiler.*;
import Compiler.Class;
import Compiler.Parser.Expression.*;
import java.io.*;
import java.util.*;
import lumpy.Main;

/**
 * Parst und erstellt den AST
 * @author Coolo
 */
public abstract class Parser {
    private static int anonFunc = 0;
    private static String[] scopeKeyword = {
        "if",
        "loop",
        "function",
        "select",
        "class",
        "elseif",
        "else",
        "language",
        "generator",
        "property",
        "get",
        "set"
    };
    private Operator[] operators;
    private int maxPrio;
    private Token previous;
    private Token current;
    private ListIterator<Token> iterator;
    private LinkedList<Token> tokens;
    private ExpressionManager man;
    private Lexer lexer;
    private Scope currentScope = null;
    private Scope mainScope;
    private int ident = 0; //einrückung
    private Function currentFunction; //Die aktuelle Funktion, für die kompiliert wird
    private Class currentClass; //Die aktuelle Klasse
    private String filePath;
    private LinkedList<Class> classes;
    private boolean noDoubleDeclaration = true; // in "var" keine doppelten deklarationen
    private boolean inExtern = false; //In einem extern block
    private boolean inGenerator = false; //in einem generator block
    private boolean inLoop = false; // in einer schleife
    private boolean inSelect = false; //In einem select
    private boolean alreadyCompiled = false;
    private CodeFunction lastFunc = null;
    private LinkedList<ExpressionFunctionDeclaration> funcDecs;
    private HashMap<String,String> replacer;
    private LinkedList<Datatype> currentTemplates;

    /**
     * Parst eine Datei mit dem gegebenen Analyser
     * @param analyser
     */
    public Parser(Lexer lexer, ExpressionManager man, String filePath) throws SyntaxException {
        SyntaxException.wasError = false;

        int[] add = { //alle addierbaren
            Datatype.BYTE_DATATYPE,
            Datatype.DOUBLE_DATATYPE,
            Datatype.FLOAT_DATATYPE,
            Datatype.INT_DATATYPE,
            Datatype.LONG_DATATYPE,
            Datatype.SHORT_DATATYPE,
            Datatype.STRING_DATATYPE
        };
        int all[] = { //der rest
            Datatype.BYTE_DATATYPE,
            Datatype.DOUBLE_DATATYPE,
            Datatype.FLOAT_DATATYPE,
            Datatype.INT_DATATYPE,
            Datatype.LONG_DATATYPE,
            Datatype.SHORT_DATATYPE
        };
        Operator[] o = {
            new Operator("<", 2, add,Datatype.BOOLEAN_DATATYPE),
            new Operator(">", 2, add,Datatype.BOOLEAN_DATATYPE),
            new Operator("!=", 2, add,Datatype.BOOLEAN_DATATYPE),
            new Operator("+", 5, add),
            new Operator("-", 5, all),
            new Operator("*", 10, all),
            new Operator("/", 10, all),
            new Operator("<=", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator(">=", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator("!=", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator("==", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator("and", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator("or", 2, all,Datatype.BOOLEAN_DATATYPE),
            new Operator("xor", 2, all,Datatype.BOOLEAN_DATATYPE),
        };

        operators = o;
        maxPrio = 10;

        this.classes = new LinkedList<Class>();
        this.funcDecs = new LinkedList<ExpressionFunctionDeclaration>();

        this.lexer = lexer;
        this.man = man;
        this.filePath = filePath;
        man.setParser(this);

        tokens = lexer.getTokens();
        iterator = tokens.listIterator();
        while (iterator.hasNext()) {
            Token tok = iterator.next();
            if (tok.getText().equals("class")) {
                Token t = tok;
                tok = iterator.next();
                if (tok.getText().equals("<")) {
                    while (!tok.getText().equals(">") && iterator.hasNext()) tok = iterator.next();
                    tok = iterator.next();
                }
                Class.newClass(new Class(tok.getText(),t,this));
            }
        }
        iterator = tokens.listIterator();


        boolean dont = false;
        for (Import imp: Main.imports) {
            if (imp.getPath().equals(filePath)) {
                dont = true;
                break;
            }
        }
        if (!dont) {
            Import imp = newImport(filePath);
            imp.setParser(this);
        }
        

        getNext();
        block(false);
    }

    /**
     * Kompiliert alle Funktion
     */
    public void compile() throws SyntaxException {
        alreadyCompiled = true;
        //marke alle überladene funktionen als überladen
        for (Function func1 : currentScope.getFunctions()) {
            for (Function func2 : currentScope.getFunctions()) {
                if (func1 != func2 && func1.getName().equals(func2.getName())) {
                    if (func1.getSynonym().equals(func2.getSynonym())) {
                        func1.overloadSynonym();
                        func2.overloadSynonym();
                    }
                }
            }
        }

        //program kompilieren
        for (Function func: currentScope.getFunctions()) {
            if (func.getName().equals("program")) {
                func.use();
                
                compileFunction(func);
                break;
            }
        }

        //nun alle unbenutzen Funktionen löschen
        ListIterator<Function> it = currentScope.getFunctions().listIterator();
        while (it.hasNext()) {
            if (!it.next().isUsed()) it.remove();
        }

        for (Variable var: Variable.getVariables()) {
            if (var.getDatatype() == null || (var.getDatatype().isVoid() && !(var instanceof Function)) || var.getDatatype().isNull()) {
                current = tokens.getFirst();
                error("Invalid datatype '"+var.getDatatype()+"' for variable "+var.getName());
            }
        }
    }
    /**
     * sucht den nächsten Token, der in ordnung ist
     * @return Wenn es nicht fixbar ist, dann wird true zurückgegeben
     */
    private void fixError() throws SyntaxException {
        getNext();
        while (!isToken("if") && !isToken("\n") && !isToken("loop") && !isToken("var") && !isToken("function") && !isToken("else") && !isToken("select") && !isToken("case") && !isToken("default") && !isToken("class")) {
            try {
                getNext();
            } catch (SyntaxException ex) {
                throw ex;
            }
        }
    }

    /**
     * Parst einen do - end block.
     * @return
     */
    private ExpressionBlock block(boolean inGen) throws SyntaxException {
        Scope oldScope = currentScope;
        currentScope = new Scope("",this, currentScope);
        boolean isMain = false;
        if (oldScope == null) {
            isMain = true;
            mainScope = currentScope;
        }
        ExpressionBlock block = getManager().getBlockExpression(inGen);

        
        if (isToken("do")) {
             match("do");
        } else {
            getNext();
        }
        while (!getCurrent().getText().equals("end")) {
            while (isToken("\n")) match("\n");
            try {
                int line = getCurrent().getLine();
                Expression ex = line(isMain);
                ex.setLine(line);
                block.addLine(ex);
            } catch (SyntaxException ex) {
                System.out.println(ex.genError());
                fixError();
            } catch (Exception ex) {
                System.out.println(new SyntaxException("Unknown error",getCurrent()).genError());
                ex.printStackTrace(System.out);
            }
            while (isToken("\n")) match("\n");
        }
        match("end");
        while (isToken("\n")) match("\n");

        if (oldScope != null)
            currentScope = oldScope;
        return block;
    }
    /**
     * Kompiliert eine Funktion
     */
    public void compileFunction(Function func) throws SyntaxException {
        if (func.isCompiled()) return;
        
        Token safe = getCurrent();
        func.use();

        Scope tmp = currentScope;
        Class tmpClass = currentClass;
        boolean tmpGen = inGenerator;
        if (func instanceof CodeFunction) {
            CodeFunction codeFunc = (CodeFunction)func;
            currentClass = codeFunc.getScope().getOwnerClass();
            if (codeFunc.getScope().getParser() != this) {
                codeFunc.getScope().getParser().compileFunction(func);
            } else {
                //CodeFunction => kompiliere
                currentScope = codeFunc.getScope();
                
                for (Variable par: codeFunc.getParameter()) {
                    if (!codeFunc.getScope().varExist(par.getName()))
                        currentScope.newVariable(par);
                }

                currentScope.getFunctions().addAll(getMainScope().getFunctions());

                if (codeFunc.getStartToken() != null) {
                    iterator = tokens.listIterator( tokens.indexOf(codeFunc.getStartToken()) );
                    getNext();
                    Function tmpF = currentFunction;
                    currentFunction = codeFunc;

                    boolean generator = false;
                    if (codeFunc.isGenerator()) {
                        inGenerator = true;
                        generator = true;
                    }
                    HashMap<String, String> tmpReplacer = replacer;
                    if (currentClass != null) {
                        tmpReplacer = currentClass.getReplacer();
                    }

                    func.compile();
                    ExpressionBlock funcBlock = block(generator); //nun den block parsen
                    replacer = tmpReplacer;
                    

                    codeFunc.setBlock(funcBlock);
                    for (Variable par: codeFunc.getParameter()) {
                        funcBlock.addVariable(par);
                    }

                    if (codeFunc.getDatatype() == null) {
                        codeFunc.setDatatype(new Datatype(Datatype.VOID_DATATYPE,0,null));
                    }

                    ExpressionReturn exprRet = null;
                    if (codeFunc.getScope().getOwnerClass() != null && codeFunc.getName().equals("new")) {
                        exprRet = getManager().getReturnExpression(getManager().getVariableExpression(codeFunc.getScope().getVariable("this")));
                    } else {
                        exprRet = getManager().getReturnExpression(codeFunc.getDatatype().createDatatypeExpression(getManager()));
                    }

                    
                    funcBlock.addLine(exprRet);

                    currentFunction = tmpF;
                    getFunctionDeclaration().add(getManager().getFunctionDeclarationExpression(funcBlock, codeFunc));
                    
                }


            }
        }

        current = safe;
        iterator = tokens.listIterator(tokens.indexOf(safe));
        getNext();

        currentClass = tmpClass;
        currentScope = tmp;
        inGenerator = tmpGen;
    }
    /**
     * Parst eine Zeile, MUSS mit \n enden
     * @return
     */
    private Expression line(boolean isMain) throws SyntaxException {
        Expression expr;
        expr = keyWord();
        if (expr != null) {
            return expr;
        }
        expr = variableExpression();
        if (expr != null) {
            if (isMain) {
                error("No function call in main scope.");
            }
            return expr;
        }

        expr = factor();
        if (expr != null) {
            if (isMain) {
                error("No factor in main scope.");
            }
            if (expr instanceof ExpressionNew || expr instanceof ExpressionCast || expr instanceof ExpressionAccess) {
                return expr;
            }
            expr = null;
        }

        error("Unknown/Unexpected expression.");
        
        //match("\n");
        return expr;
    }

    /**
     * Berechnet einen mathematischen term aus +,-,* und /
     */
    private Expression operator(int prio) throws SyntaxException {
        if (prio > maxPrio) {
            return factor();
        } else {
            boolean found = false;
            Expression expr1, retExpr;
            expr1 = operator(prio + 1);
            retExpr = expr1;
            do {
                found = false;
                Expression expr2;

                for (Operator op : operators) {
                    if (op.getPrio() == prio && op.getName().equals(getCurrent().getText())) {
                        getNext();
                        expr2 = operator(prio + 1);

                        Datatype data1 = null, data2 = null;
                        boolean err = SyntaxException.wasError;
                        try {
                            data1 = expr1.getDatatype();
                        } catch(SyntaxException ex) {
                            data1 = null;
                        }

                        try {
                            data2 = expr2.getDatatype();
                        } catch(SyntaxException ex) {
                            data2 = null;
                        }
                        SyntaxException.wasError = err;

                        if (data1 == null || data2 == null) {
                            if (data1 == null && data2 == null) {
                                error("Cannot resolve datatype. Two unknown datatypes");
                            } else {
                                if (data1 == null) {
                                    if (expr1 instanceof ExpressionIdentifier) {
                                        ExpressionIdentifier ident = (ExpressionIdentifier)expr1;
                                        if (ident.getVariable() != null) {
                                            ident.getVariable().setDatatype(data2);
                                        } else {
                                            error("Identifier has no variable.");
                                        }
                                    } else {
                                        error("Cannot set datatype.");
                                    }
                                } else {
                                    if (expr2 instanceof ExpressionIdentifier) {
                                        ExpressionIdentifier ident = (ExpressionIdentifier)expr2;
                                        if (ident.getVariable() != null) {
                                            ident.getVariable().setDatatype(data1);
                                        } else {
                                            error("Identifier has no variable.");
                                        }
                                    } else {
                                        error("Cannot set datatype.");
                                    }
                                }
                            }
                        }


                        found = true;
                        if (retExpr.getDatatype() == null || expr2.getDatatype() == null) error("Variable datatype unknown.");

                        retExpr = getManager().getOperatorExpression(op, retExpr, expr2);

                        break;
                    }
                }
            } while (found);
            return retExpr;
        }
    }

    /**
     * Ein Identifier, Zahl, String,...
     */
    private Expression factor() throws SyntaxException {
        Expression expr;
        if (isToken("null")) {
            match("null");
            return getManager().getNullExpression();
        }

        if (isToken("(")) {
            match("(");
            expr = operator(0);
            match(")");
            return expr;
        }
        
        if (getCurrent().getText().length()> 1 && getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("b")) {
            String t = getCurrent().getText().substring(0, getCurrent().getText().length() - 1);
            try { //Byte
                expr = getManager().getByteExpression(new Byte(t));
                getNext();
                return expr;
            } catch (NumberFormatException ex) {
            }
        }
        if (getCurrent().getText().length()> 1 && getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("s")) {
            String t = getCurrent().getText().substring(0, getCurrent().getText().length() - 1);
            try { //Short
                expr = getManager().getShortExpression(new Short(t));
                getNext();
                return expr;
            } catch (NumberFormatException ex) {
            }
        }

        if (!getCurrent().getText().contains(".")) {
            try { //integer
                expr = getManager().getIntegerExpression(new Integer(getCurrent().getText()));
                getNext();
                return expr;
            } catch (NumberFormatException ex) {
            }
        }


        if (getCurrent().getText().length()> 1 && getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("l")) {
            String t = getCurrent().getText().substring(0, getCurrent().getText().length() - 1);
            try { //Long
                expr = getManager().getLongExpression(new Long(t));
                getNext();
                return expr;
            } catch (NumberFormatException ex) {
            }
        }

        try { //float
            expr = getManager().getFloatExpression(new Float(getCurrent().getText()));
            getNext();
            return expr;
        } catch (NumberFormatException ex) {
        }
        if (getCurrent().getText().length()> 1 && getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("d")) {
            String t = getCurrent().getText().substring(0, getCurrent().getText().length() - 1);
            try { //double
                expr = getManager().getDoubleExpression(new Double(t));
                getNext();
                return expr;
            } catch (NumberFormatException ex) {
                if (getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("d")) {
                }
            }
        }
        if (getCurrent().getText().equals("true")) {
            match("true");
            return getManager().getBooleanExpression(true);
        }
        if (getCurrent().getText().equals("false")) {
            match("false");
            return getManager().getBooleanExpression(false);
        }

        if (getCurrent().getText().length() > 1 && getCurrent().getText().substring(0, 1).equals("\"") && getCurrent().getText().substring(getCurrent().getText().length() - 1).equals("\"")) {
            expr = getManager().getStringExpression(getCurrent().getText());
            getNext();
            return expr;
        }
        expr = variableExpression();
        if (expr != null) {
            return expr;
        }
        
        error("Unknown/Unexpected token.");
        return null; //kommt hier nie an.
    }

    /**
     * Erstellt einen Funktionsaufruf
     */
    private ExpressionFunctionCall functionCall(String funcName, LinkedList<Function> functions, LinkedList<Expression> parameters) throws SyntaxException {
        Function call = null;
        //nun passende funktion suchen
        for (Function func : functions) {
            if (func.getName().equals(funcName) && !func.isCompiled() && func instanceof CodeFunction) {
                //möglich dass es aufgerufen wird => kompiliere
                compileFunction(func);
            }
            if (func.match(funcName, parameters)) {
                call = func;
                break;
            }
        }

        if (call != null) {
            call.use();
            //aufrufen
            if (!call.isPublic()) {
                if (currentClass == null || !currentClass.getMethods().contains(call)) {
                    error("Try to call private class method.");
                }
            }
            int i = 0;
            for (Variable par : call.getParameter()) {
                par.setDatatype(parameters.get(i).getDatatype());
                i++;
            }

            return getManager().getFunctionCallExpression(call, parameters,currentClass);
        } else {

            String paramString = "";
            for (Expression param : parameters) {
                paramString += " " + param.getDatatype().getName();
            }
            error("There is no function named '" + funcName + "' with parameter: '" + paramString + " '");
        }
        return null;
    }

    /**
     * Eine Variable Expression
     * Eine VExpr ist jede Expression die mit einem Identifier beginnt
     * @return
     * @throws SyntaxException
     */
    private ExpressionIdentifier variableExpression() throws SyntaxException {
        ExpressionIdentifier var = null;
        boolean existInClass = false;
        boolean isAttribute = false;
        if (currentClass != null) {
            for (Variable v: currentClass.getAttibutes()) {
                if (isToken(v.getName())) {
                    existInClass = true;
                    isAttribute = true;
                    break;
                }
            }
            if (!existInClass) {
                for (Function f: currentClass.getMethods()) {
                    if (isToken(f.getName())) {
                        existInClass = true;
                        break;
                    }
                }
            }
            if (isToken("new")) existInClass = false;
        }
        if (getScope().getVariable(getCurrent().getText())!=null || getScope().getFunction(getCurrent().getText()) != null || Datatype.Name2Int(getCurrent().getText()) != -1 || isToken("new") || isToken("function")) {
            if (var == null && getScope().getVariable(getCurrent().getText()) != null) {
                var = getManager().getVariableExpression(getScope().getVariable(getCurrent().getText()));
            } else if (existInClass) {
                if (isAttribute) {
                    Variable vari = null;
                    for (Variable v: currentClass.getAttibutes()) {
                        if (isToken(v.getName())) {
                            vari = v;
                            break;
                        }
                    }

                    var = getManager().getVariableExpression(currentClass.getScope().getVariable("this"));
                    var = getManager().getAccessExpression(var, getManager().getVariableExpression(vari));
                } else {
                    error("Methods need 'this'");
                }
            } else if (isToken("new")) {
                match("new");
                String typ = getCurrent().getText();
                getNext();
                LinkedList<Datatype> temps = null;
                if (isToken("<")) {
                    temps = new LinkedList<Datatype>();
                    match("<");
                    boolean start = false;
                    while (!isToken(">")) {
                        if (start) match(",");
                        Datatype d = null;


                        if (!(Datatype.Name2Int(getCurrent().getText()) == -1 || getCurrent().getText().equals("null"))) {
                            d = new Datatype(Datatype.Name2Int(getCurrent().getText()),0,null);
                            getNext();
                        } else {
                            d = variableExpression().getDatatype();
                        }
                        temps.add(d);
                        start = true;
                        
                    }
                    match(">");
                    Class c = Class.getClassByName(typ);
                    typ = compileGenericClass(c, temps);
                }
                if (isToken("(")) {
                    Datatype data = new Datatype(Datatype.Name2Int(typ),0,null);
                    Class c = Class.getClassByName(data.getName());


                    match("(");
                    LinkedList<Expression> parameter = new LinkedList<Expression>();
                    boolean start = false;
                    while (!isToken(")")) {
                        if (start) match(",");
                        parameter.add(operator(0));
                        start = true;
                    }
                    match(")");
                    LinkedList<Function> functions = new LinkedList<Function>();
                    if (c == null) error("Unknown class: "+typ);
                    for (Function func: c.getMethods()) {
                        if (func.getName().equals("new")) {
                            functions.add(func);
                        }
                    }
                    if (functions.size() == 0) error("Cannot find constructor.");

                    ExpressionFunctionCall call = functionCall("new", functions, parameter);

                    //schauen ob zugreifbar
                    if (!call.getFunction().isPublic() && currentClass != c) {
                        error("Cant access private contructor in non private scope.");
                    }

                    var = call;
                    call.getParameter().addFirst(getManager().getNewExpression(data));
                } else if (isToken("[")) {
                    int dim = 0;
                    LinkedList<Expression> exprs = new LinkedList<Expression>();
                    do {
                        match("[");
                        dim++;
                        exprs.add(operator(0));
                        match("]");
                    } while (isToken("["));
                    Datatype data = new Datatype(Datatype.Name2Int(typ),dim,null);
                    var = getManager().getNewExpression(data, exprs);
                } else {
                    Datatype data = new Datatype(Datatype.Name2Int(typ),0,null);
                    Class c = Class.getClassByName(data.getName());
                    LinkedList<Expression> parameter = new LinkedList<Expression>();

                    LinkedList<Function> functions = new LinkedList<Function>();
                    if (c == null) error("Unknown class: "+typ);
                    for (Function func: c.getMethods()) {
                        if (func.getName().equals("new")) {
                            functions.add(func);
                        }
                    }
                    if (functions.size() == 0) error("Cannot find constructor.");

                    ExpressionFunctionCall call = functionCall("new", functions, parameter);

                    //schauen ob zugreifbar
                    if (!call.getFunction().isPublic() && currentClass != c) {
                        error("Cant access private contructor in non private scope.");
                    }

                    var = call;
                    call.getParameter().addFirst(getManager().getNewExpression(data));

                }
                
            } else if (isToken("function")) {
                Expression expr = keyWord();
                if (expr instanceof ExpressionAnonymousFunc) {
                    var = (ExpressionAnonymousFunc)expr;
                } else {
                    error("Internal error.");
                }
            } else if (Datatype.Name2Int(getCurrent().getText()) != -1) {
                Datatype data = datatype(false,true);
                match("(");
                ExpressionIdentifier expr = variableExpression();
                if (!isToken(")")) match(")");
                if (data.match(expr.getDatatype())) {
                    var = expr;
                } else {
                    boolean cantCast = false;
                    if (expr.getDatatype().isClass() || data.isClass()) {
                        if (expr.getDatatype().isClass() || data.isClass()); else {
                            cantCast = true;
                        }
                    }

                    if (expr.getDatatype().isClass()) {
                        //schauen ob dorthingecastet werden kann
                        Class c = (Class)Class.getClassByName(expr.getDatatype().getName());
                        boolean find = false;
                        while (c != null) {
                            if (c.getName().equals(data.getName())) {
                                find = true;
                                break;
                            }
                            //nicht sicher ob richtig:
                            for (Class c2: c.getInheriting()) {
                                if (c2.getName().equals(expr.getDatatype().getName())) {
                                    find = true;
                                    break;
                                }
                            }
                            c = c.getInherit();
                        }
                        if (!find) cantCast = true;
                    }

                    if (cantCast) {
                        error("Cannot cast '"+expr.getDatatype().toString()+"' to '"+data.toString()+"'");
                    } else {
                        var = getManager().getCastExpression(expr, expr.getDatatype(), data);
                    }
                }
            } else {
                //funktionsaufruf
                var = null;
            }
            LinkedList<ExpressionProperty> props = new LinkedList<ExpressionProperty>(); // um zu schauen, ob das property get implementiert
            if (!isToken("\n")) getNext();
            while (isToken("(") || isToken(".") || isToken("[") || var == null || var instanceof ExpressionMethod) {
                if (isToken(".") && var != null) {
                    match(".");
                    //omg es ist ein zugriff
                    //schauen ob Variable
                    
                    Datatype curData = var.getDatatype();
                    if (curData.match(new Datatype(curData,0,null)) && curData.isClass()) {
                        //okay, nun schauen ob es sich um eine methode oder attribut handelt.
                        Class c = Class.getClassByName(curData.getName());
                        boolean find = false;
                        //attribut:
                        for (Variable attribute:c.getAttibutes()) {
                            if (isToken(attribute.getName())) {
                                //schauen ob zugreifbar
                                if (!attribute.isPublic() && currentClass != c) {
                                    error("Cant access private attribute in non private scope.");
                                }
                                //yeah \o/
                                var = getManager().getAccessExpression(var, getManager().getVariableExpression(attribute));

                                getNext();
                                find = true;
                                break;
                            }
                        }
                        if (!find) {
                            for (Function func: c.getMethods()) {
                                if (isToken(func.getName())) {
                                    //schauen ob zugreifbar
                                    if (!func.isPublic() && currentClass != c) {
                                        error("Cant access private method in non private scope.");
                                    }
                                    if (!func.isCompiled()) {
                                        compileFunction(func);
                                    }
                                    var = getManager().getMethodExpression(func.getDatatype(),var,c);

                                    getNext();
                                    find = true;
                                    break;
                                }
                            }
                            if (!find) {
                                //schauen ob property
                                for (Property p:c.getPropertys()) {
                                    if (isToken(p.getName())) {
                                        var = getManager().getPropertyExpression(p, var);
                                        find = true;
                                        props.add((ExpressionProperty)var);
                                        getNext();
                                        break;
                                    }
                                }
                                if (!find) {
                                    error("Unknown member '"+getCurrent().getText()+"' of class '"+c.toString()+"'");
                                }
                            }
                        }
                    } else {
                        error("Cannot find identifier of datatype '"+curData.toString()+"'");
                    }
                } else if (isToken("[") && var != null) {
                    LinkedList<Expression> arrs = new LinkedList<Expression>();
                    while (isToken("[")) {
                        match("[");
                        arrs.add(operator(0));
                        match("]");
                    }
                    if (var instanceof ExpressionProperty) {
                        Property p = ((ExpressionProperty)var).getProperty();
                        if (arrs.size() == p.getIndexer().size()) {
                            int i = 0;
                            for (Datatype d: p.getIndexer()) {
                                if (!d.match(arrs.get(i).getDatatype())) {
                                    error("Indexer datatype does not match." + d.generateErrorMsg(arrs.get(i).getDatatype()));
                                }
                                i++;
                            }
                        }
                        ((ExpressionProperty)var).setIndexer(arrs);
                    } else {
                        var = getManager().getArrayExpression(var, arrs);
                    }
                } else {
                    LinkedList<Function> functions = null;
                    String funcName = getPrevious().getText();
                    //schauen ob funktionsname
                    boolean isFunc = false;
                    boolean isAnonyme = false;
                    if (var != null) {
                        if (var.getVariable() != null && var.getDatatype() != null && var.getDatatype().getDimensions() == 0 && var.getDatatype().getParameters() == null && var instanceof ExpressionMethod) {
                            Class c = ((ExpressionMethod)var).getOwnerClass();
                            functions = new LinkedList<Function>();
                            for (Function func: c.getMethods()) {
                                if (funcName.equals(func.getName())) {
                                    isFunc = true;
                                    functions.add(func);
                                }
                            }
                        } else {
                            //schauen ob anonym.
                            if (var != null && ((var instanceof ExpressionAnonymousFuncCall && var.getDatatype().getParameters()!=null) || var.getVariable().getDatatype().getParameters() != null)) {
                                isAnonyme = true;
                            } else {
                                isFunc = false;
                            }
                        }
                    } else {
                        
                        isFunc = getScope().getFunction(getCurrent().getText()) != null;
                        functions = getScope().getFunction(funcName);
                    }
                    if (isFunc || isAnonyme) {
                        boolean wasBracket = false;
                        if (isToken("(")) {
                            match("(");
                            wasBracket = true;
                        }
                        boolean start = false;
                        LinkedList<Expression> parameters = new LinkedList<Expression>();
                        while (!isToken(")") && !isToken("\n")) {
                            if (start) match(",");
                            parameters.add(operator(0));
                            start = true;
                        }
                        if (isToken(")") && !wasBracket) {
                            error("Missing beginning '('");
                        }
                        if (isToken("\n") && wasBracket) {
                            error("Missing ')'");
                        }
                        if (isToken(")")) getNext();
                        

                        if (funcName.equals("new")) {
                            //Hier habe ich || isnew weggegeben.
                            if (currentFunction instanceof CodeFunction && ((CodeFunction)currentFunction).getTyp() == CodeFunction.IS_CONSTRUCT); else {
                                error("Don't call Constructor within non constructor.");
                            }
                        }
                        
                        if (isFunc) {
                            ExpressionFunctionCall call = functionCall(funcName, functions, parameters);
                            if (var != null) {
                                call.getParameter().addFirst(var);
                            }
                            var = call;
                        } else if (isAnonyme) {
                            var = getManager().getAnonymousFuncCallExpression(var.getDatatype(),var,parameters);
                        }

                        
                    } else {
                        error("Unknown function/method: "+funcName);
                    }
                }
                existInClass = false;
            }

            //schauen ob set
            if (isToken("=")) {
                match("=");
                if (var.getVariable() == null) {
                    error("Cannot assign non variable.");
                }
                Expression expr = operator(0);
                if (var instanceof ExpressionProperty) {
                    if (((ExpressionProperty)var).getProperty().getSet() == null) error("Variable '"+((ExpressionProperty)var).getProperty().getName()+"' does not implement 'set'.");
                    ((ExpressionProperty)var).setValue(expr);
                }
                if (var.getVariable().getDatatype() == null) {
                    var.getVariable().setDatatype(expr.getDatatype());
                } else if (expr instanceof ExpressionIdentifier) {
                    ((ExpressionIdentifier)expr).getVariable().setDatatype(var.getVariable().getDatatype());
                }
                

                
                if (expr.getDatatype().isVoid()) {
                    error("Variable cannot contain void datatype.");
                }
                
                if (!var.getDatatype().match(expr.getDatatype())) { //ist weil, variable einen generic hat, aber "new" eins ohne generic zurückgibt
                    error(var.getVariable().getDatatype().generateErrorMsg(expr.getDatatype()));
                }
                if (var instanceof ExpressionProperty);
                else {
                    var = getManager().getAssignmentExpression(var.getVariable(), var, expr);
                }
                
            }

            for (ExpressionProperty p: props) {
                if (p.getProperty().getGet() == null && p.getValue() == null) {
                    error("Property '"+p.getProperty().getName()+"' does not implement 'get'.");
                }
            }

            return var;
        } else {
            return null;
        }
    }

    /**
     * Importieren
     * @param path
     */
    private Import newImport(String path) {
        Import imp = new Import(path);
        Main.imports.addLast(imp);
        return imp;
    }
    /**
     * importieren
     */
    private void importParser(Import imp) throws SyntaxException {
        if (imp.getParser() != null) {
            for (Function func: imp.getParser().getMainScope().getFunctions()) {
                getMainScope().newFunction(func);
            }
        }
    }
    /**
     * Der Wrapper, um before = null zu setzen
     */
    public Datatype datatype(boolean match,boolean funcDatatype) throws SyntaxException {
        return datatype(match,funcDatatype,null);
    }
    /**
     * Parst einen Datentyp
     */
    @SuppressWarnings("empty-statement")
    private Datatype datatype(boolean match,boolean funcDatatype,Datatype before) throws SyntaxException {
        LinkedList<Datatype> parameters = null;
        String name = "";
        if (before == null) {
            if (match) match(":");
            name = getCurrent().getText();
            isValidDatatype(getCurrent());
            getNext();
        }
        boolean good = true;
        if (isToken("(")) {
            //Schauen ob danach eine "()", wenn funcdatatype
            Token tmp = getCurrent();
            parameters = new LinkedList<Datatype>();
            
            match("(");
            boolean start = false;
            while (!isToken(")")) {

                boolean wasError = SyntaxException.wasError;
                try {
                    if (start) match(",");
                    parameters.add(datatype(false,false));
                } catch (SyntaxException ex) {
                    if (funcDatatype) {
                        SyntaxException.wasError = wasError;
                        good = false;
                        break;
                    } else {
                        throw ex;
                    }
                }
                start = true;
            }
            if (good) match(")");
            if (funcDatatype) {
                if (!isToken("(")) { //|| good war hier, ka ob falsch
                    //zurück gehen
                    while (getPrev() != tmp);
                    getNext();
                    parameters = null;
                }
            }
        }

        if (isToken("<")) {
            Class c = Class.getClassByName(name);
            name += "_templates_";
            match("<");
            boolean start = false;
            LinkedList<Datatype> data = new LinkedList<Datatype>();
            while (!isToken(">")) {
                if (start) match(",");
                name += "_"+getCurrent().getText();
                data.add(new Datatype(Datatype.Name2Int(getCurrent().getText()),0,null));
                getNext();
                start = true;
            }
            match(">");

            if (c != null && Datatype.Name2Int(name) == -1) {
                name = compileGenericClass(c, data);
            }
        }
        int dim = 0;
        while (isToken("[")) {
            dim++;
            match("[");
            match("]");
        }
        int id =  Datatype.Name2Int(name);
        if (id == -1) {
            error("Unknown datatype name: '"+name+"'");
        }
        return new Datatype(id,dim,parameters);
    }

    private void overJumpBlock() throws SyntaxException {
        int step = 0;
        if (isToken("end")) {
            match("end");
        } else {
            while (!isToken("end") || step >= 0) {
                
                for (String key: scopeKeyword) {
                    if (getCurrent().getText().equals(key)) {
                        if (key.equals("inline")) {
                            while (!isToken("end")) {
                                getNext();
                            }
                            match("end");
                        } else
                            step++;
                        break;
                    }
                }
                getNext();
                if (getCurrent().getText().equals("end")) {
                    step--;
                }
                
            }
            match("end");
        }
    }
    /**
     * kompiliert eine generische klasse, und zwar in den notwendigen datentyp
     */
    public String compileGenericClass(Class c, LinkedList<Datatype> data) throws SyntaxException {
        if (c.getParser() != this) {
            return c.getParser().compileGenericClass(c, data);
        } else {
            String name = c.getName() + "_templates_";
            for (Datatype d: data) {
                name += "_"+d.toString();
            }

            //schauen ob es die klasse bereits gibt
            boolean alreadyExist = false;
            Class findClass = null;
            for (Class testC: Class.getClasses()) {
                if (name.equals(testC.getName())) {
                    //generics prüfen
                    findClass = testC;
                    alreadyExist = true;
                }
            }
            if (!alreadyExist) {
                Token tmp = getCurrent();
                ListIterator tmp2 = iterator;
                iterator = tokens.listIterator(tokens.indexOf( c.getStartToken() ));
                getNext();
                currentTemplates = data;
                keyWord();
                currentTemplates = null;

                iterator = tmp2;
                current = tmp;

                return name;
            } else {
                return findClass.getName();
            }
        }
     }

    /**
     * Schlüsselwort
     * @return Die Expression mit dem Schlüsselwort
     */
    @SuppressWarnings("empty-statement")
    private Expression keyWord() throws SyntaxException {
        if (isToken("var")) {
            match("var");
            Datatype standardDatatype = null;
            if (isToken(":")) {
                standardDatatype = datatype(true,false);
            }
            LinkedList<Expression> exprs = new LinkedList<Expression>();
            LinkedList<Variable> vars = new LinkedList<Variable>();
            boolean start = false;
            while (!isToken("\n") && (!isToken(",") || noDoubleDeclaration) && !isToken("to") && !isToken("in")) {
                if (start) {
                    match(",");
                }

                String name = getCurrent().getText();
                Datatype datatype = standardDatatype;
                getNext();
                if (isToken(":")) {
                    datatype = datatype(true,false);
                }
                
                
                Variable vari = new Variable(name, datatype);
                if (inGenerator) {
                    if (currentClass.getAttibutes() != null) {
                        for (Variable att: currentClass.getAttibutes()) {
                            if (att.getName().equals(vari.getName()) && !att.getDatatype().match(vari.getDatatype())) {
                                error("Generator variable '"+vari.getName()+"' does not match with '"+att.getName()+"'.");
                            }
                        }
                    }
                    currentClass.newAttribute(false, vari);
                    vari.use();
                } else {
                    getScope().newVariable(vari);
                }
                vars.add(vari);
                Expression setExpr = getManager().getEmptyExpression();
                if (isToken("=")) {
                    match("=");
                    vari.use();
                    setExpr = operator(0);
                    if (setExpr.getDatatype().isVoid()) {
                        error("Variable cannot contain void datatype.");
                    }
                    if (setExpr instanceof ExpressionFunctionCall && vari.getDatatype() != null) {
                        ((ExpressionFunctionCall) setExpr).getFunction().setDatatype(vari.getDatatype());
                    }
                    if (datatype == null) {
                        vari.setDatatype(setExpr.getDatatype());
                    }
                    if (!vari.getDatatype().match(setExpr.getDatatype())) {
                        error(vari.getDatatype().generateErrorMsg(setExpr.getDatatype()));
                    }
                }
                exprs.add(setExpr);
                start = true;
            }
            return getManager().getDeclarationExpression(vars, exprs,inGenerator);
        } else if (isToken("if")) {
            match("if");
            Expression expr = operator(0);
            if (!expr.getDatatype().match(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null))) {
                error(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null).generateErrorMsg(expr.getDatatype()));
            }

            ExpressionBlock block = block(false);
            //match("\n");
            LinkedList<ExpressionIf> list = new LinkedList<ExpressionIf>();
            while (isToken("elseif")) {
                match("elseif");
                Expression elseExpr = operator(0);
                if (!elseExpr.getDatatype().match(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null))) {
                    error(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null).generateErrorMsg(elseExpr.getDatatype()));
                }
                ExpressionBlock elseIfBlock = block(false);
                //match("\n");
                list.add(getManager().getIfExpression(elseExpr, elseIfBlock,  null, new LinkedList<ExpressionIf>()));
            }
            ExpressionBlock elseBlock = null;
            if (isToken("else")) {
                match("else");
                elseBlock = block(false);
                //match("\n");
            }

            return getManager().getIfExpression(expr, block, elseBlock, list);
        } else if (isToken("select")) {
            match("select");
            boolean tmp = inSelect;
            inSelect = true;
            Expression start = operator(0);
            LinkedList<Expression> conds = new LinkedList<Expression>();
            LinkedList<ExpressionBlock> blocks = new LinkedList<ExpressionBlock>();
            ExpressionBlock def = null;
            while (!isToken("end")) {
                boolean inDef = false;
                while (isToken("\n")) getNext();
                if (isToken("default")) {
                    inDef = true;
                    match("default");
                } else {
                    match("case");
                }
                
                Expression cond = null;
                if (!inDef) cond = operator(0);
                ExpressionBlock block = getManager().getBlockExpression(false);
                while (!isToken("end") && !isToken("case") && !isToken("default")) {
                    while (isToken("\n")) getNext();
                    block.addLine(line(false));
                    while (isToken("\n")) getNext();
                }
                if (!inDef) {
                    conds.add(cond);
                    blocks.add(block);
                } else {
                    def = block;
                }
                while (isToken("\n")) getNext();
            }

            inSelect = tmp;
            match("end");
            return getManager().getSelectExpression(start,conds,blocks,def);
        } else if (isToken("exit")) {
            if (inLoop) {
                match("exit");
                return getManager().getLoopEventExpression(false);
            } else {
                error("Exit must appear in loop.");
            }
        } else if (isToken("continue")) {
            if (inLoop) {
                match("continue");
                return getManager().getLoopEventExpression(true);
            } else {
                error("Loop must appear in loop.");
            }
        } else if (isToken("loop")) {
            match("loop");
            Expression start= null;
            boolean bef = inLoop;
            inLoop = true;
            Token last = getCurrent();
            if (isToken("var")) {
                noDoubleDeclaration = false;
                start = keyWord();
                noDoubleDeclaration = true;
            } else {
                start = operator(0);
            }
            
            if (start instanceof ExpressionIdentifier || start instanceof ExpressionDeclaration) {
                //for
                if (isToken(",")) {
                    match(",");
                    Expression cond = operator(0);
                    match(",");
                    Expression inc = operator(0);
                    ExpressionBlock block = block(false);
                    inLoop = bef;
                    return getManager().getForExpression(start, cond, inc, block);
                } else if (isToken("to")) {
                    match("to");
                    Expression to = operator(0);
                    Expression step = getManager().getIntegerExpression(1);
                    if (isToken("step")) {
                        match("step");
                        step = operator(0);
                    }
                    ExpressionBlock block = block(false);
                    Token tmp = getCurrent();
                    while (getPrev() != last);
                    getNext();
                    
                    if (isToken("var")) getNext();
                    //braucht den expressionidentifier der variable...
                    //es ist so ein böser hack... das sollte verboten werden
                    ExpressionIdentifier var = null;
                    var = variableExpression();
                    //zurück
                    while (getNext() != tmp);
                    getNext();
                    inLoop = bef;
                    //zusammensetzen
                    return getManager().getSimpleForExpression(var,start, to,step, block);
                } else if (isToken("in")) {
                    match("in");
                    ExpressionIdentifier in = variableExpression();
                    if (in.getDatatype() != null) {
                        Class c = Class.getClassByName(in.getDatatype().getName());
                        if (c != null) {
                            //schauen ob es eine invoke methode gibt
                            int find = 0;
                            Function invFunc = null;
                            for (Function func: c.getMethods()) {
                                if (func.getName().equals("invoke") && func.getParameter().size() == 0) {
                                    find ++;
                                    invFunc = func;
                                    func.use();

                                    compileFunction(func);
                                }
                                if (func.getName().equals("hasnext") && func.getParameter().size() == 0 && func.getDatatype().match(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null))) {
                                    find ++;
                                    func.use();
                                }
                                if (func.getName().equals("start") && func.getParameter().size() == 0) {
                                    find ++;
                                    func.use();
                                }
                            }
                            if (find == 3) {
                                Token tmp = getCurrent();
                                while (getPrev() != last);
                                getNext();
                                if (isToken("var")) getNext();
                                //braucht den expressionidentifier der variable...
                                //es ist so ein böser hack... das sollte verboten werden

                                ExpressionIdentifier var = null;
                                var = variableExpression();
                                var.getVariable().setDatatype(invFunc.getDatatype());
                                //zurück
                                while (getNext() != tmp);
                                //getNext();
                                ExpressionBlock block = block(false);
                                inLoop = bef;
                                return getManager().getEachInExpression(c,block, var, in, start);
                            } else {
                                error("Could not find method 'invoke', 'hasnext' or 'start'.");
                            }
                        } else {
                            error("Cannot iterate through primitve datatype.");
                        }
                    } else {
                        error("Cannot specify datatype.");
                    }
                } else {
                    ExpressionBlock block = block(false);
                    inLoop = bef;
                    return getManager().getWhileExpression(start, block);
                }
            } else {
                if (!start.getDatatype().match(new Datatype(Datatype.BOOLEAN_DATATYPE,0,null))) {
                    error("loop expression must be boolean.");
                }
                ExpressionBlock block = block(false);
                return getManager().getWhileExpression(start, block);
            }
        } else if (isToken("import")) {
            match("import");
            String name = getCurrent().getText();
            getNext();
            String path = "";
            if (isToken(".")) {
                match(".");
                //schaue in den Projekordner
                path = filePath.substring(0,filePath.lastIndexOf("/"))+name+"."+getCurrent().getText();
            } else if (name.endsWith("\"") && name.startsWith("\"")) {
                path = filePath.substring(0,filePath.lastIndexOf("/"))+"/"+name.replace("\"", "");
            } else {
                //schaue in den lumpy ordner
                path = System.getProperty("user.dir")+"/modules/"+name+".ly";
            }
            Import imp = null;
            for (Import imp2: Main.imports) {
                if (imp2.getPath().equals(path)) {
                    imp = imp2;
                    break;
                }
            }
            if (imp == null) {
                imp = newImport(path);
                if (imp.getPath().endsWith(".ly")) {
                    imp.setText(Parser.readFile(imp.getPath()));
                    Parser parser = new CParser(new Lexer(imp.getText()),new ExpressionManagerC(),imp.getPath());
                    imp.setParser(parser);
                    //if (!dont) {
                    //    Import imp2 = new Import(parser.getPath());
                    //    imp2.setParser(this);
                    //    Main.imports.add(imp2);
                    //}
                    //importParser(imp2);
                }
            }
            importParser(imp);
            return getManager().getEmptyExpression();
        } else if (isToken("class")) {
            match("class");
            Token startToken = getCurrent();
            boolean baseClass = false;
            String name = "";
            HashMap<String, String> tmpReplacer = replacer;
            Class tmpClass = currentClass;
            boolean tmpGenerator = inGenerator;
            inGenerator = false;
            replacer = null;
            Class c = null;
            if (isToken("<")) {
                match("<");
                boolean start = false;
                LinkedList<String> types = new LinkedList<String>();
                while (!isToken(">")) {
                    if (start) match(",");
                    types.add(getCurrent().getText());
                    getNext();
                }
                match(">");
                c = Class.getClassByName(getCurrent().getText());
                //template
                if (currentTemplates == null) {
                    baseClass = true;
                    name = getCurrent().getText();
                } else {
                    replacer = new HashMap<String,String>();
                    baseClass = false;
                    int i = 0;
                    for (String str: types) {
                        replacer.put(str, currentTemplates.get(i).toString());
                        i++;
                    }
                    
                    String n ="_templates_";
                    for (String data: replacer.values()) {
                        n += "_"+data;
                    }
                    


                    name = getCurrent().getText()+n;

                    Class.newClass(new Class(name,startToken,this));
                }
            } else {
                name = getCurrent().getText();
            }
            //name = getCurrent().getText() + name;
            c = Class.getClassByName(name);
            if (!baseClass) {
                c.setReplacer(replacer);
            }

            currentClass = c;
            classes.add(c);
            getNext();
            if (isToken("<")) { //vererbung
                match("<");

                do {
                    
                    String ex = getCurrent().getText();
                    Class exClass = Class.getClassByName(ex);
                    if (exClass == null) error("Expecting Class name");
                    c.inherits(exClass);
                } while (isToken(","));
                getNext();
            }
            match("\n");
            Scope tmp = currentScope;
            currentScope = new Scope(c.getName(),this, null);
            Variable thisVar = new Variable("this",c);
            Variable superVar =  null;
            currentScope.newVariable(thisVar);
            if (c.getInherit() != null) {
                superVar = new Variable("super",c.getInherit());
                currentScope.newVariable(superVar);
            }
            c.setScope(currentScope);
            for (Function func: tmp.getFunctions()) {
                currentScope.getFunctions().add(func);
            }
            if (baseClass) {
                overJumpBlock();
            }
            while (!isToken("end") && !baseClass) {
                boolean isPublic = false;
                boolean gen = false;
                if (isToken("private")) {
                    match("private");
                } else if (isToken("public")) {
                    match("public");
                    isPublic = true;
                } else {
                    error("Expecting public/private.");
                }

                
                if (isToken("var")) {
                    ExpressionDeclaration dec = (ExpressionDeclaration)keyWord();
                    for (Variable var: dec.getDecVar()) {
                        c.newAttribute(isPublic, var);
                    }
                    c.getStartDecs().add(dec);
                } else if (isToken("property")) {
                    match("property");
                    
                    LinkedList<Variable> indexer = null;
                    if (isToken("[")) {
                        indexer = new LinkedList<Variable>();
                        while (isToken("[")) {
                            match("[");
                            String indexerName = getCurrent().getText();
                            getNext();
                            Datatype indexerData = null;
                            if (isToken(":")) {
                                indexerData = datatype(true, false);
                            }
                            indexer.add(new Variable(indexerName,indexerData));
                            match("]");
                        }
                    }
                    String propName = getCurrent().getText();
                    getNext();
                    
                    Datatype data = null;
                    if (isToken(":")) {
                        data = datatype(true,false);
                    }

                    CodeFunction set = null, get = null;
                    
                    
                    
                    while (!isToken("end")) {                        
                        if (isToken("get")) {
                            match("get");

                            

                            if (get != null) error("Duplicate get property.");
                            
                            get = new CodeFunction("get_"+propName,data,new Scope("",this,null));
                            get.setStartToken(getCurrent());
                            get.setTyp(CodeFunction.IS_PROPERTY_GET);
                            if (indexer != null) {
                                for (Variable index:indexer) {
                                    get.getParameter().add(index);
                                }
                            }
                            get.use();
                            c.newMethod(isPublic, get, thisVar, superVar);
                            getMainScope().newFunction(get);

                            compileFunction(get);

                            if (get.getDatatype() != null) {
                                data = get.getDatatype();
                            }

                            overJumpBlock();
                        } else if (isToken("set")) {
                            match("set");
                            
                            

                            if (set != null) error("Duplicate set property.");

                            set = new CodeFunction("set_"+propName,new Datatype(Datatype.VOID_DATATYPE,0,null),new Scope("",this,null));
                            Variable v = set.newParameter("value", null);
                            set.setStartToken(getCurrent());
                            set.setTyp(CodeFunction.IS_PROPERTY_SET);
                            set.use();
                            if (indexer != null) {
                                for (Variable index:indexer) {
                                    set.getParameter().add(index);
                                }
                            }
                            c.newMethod(isPublic, set, thisVar, superVar);
                            getMainScope().newFunction(set);

                            compileFunction(set);
                            overJumpBlock();

                            if (data == null) data = v.getDatatype();
                            if (v.getDatatype() == null) {
                                error("Cannot resolve variable datatype for set property "+propName);
                            }
                            if (!v.getDatatype().match(data)) {
                                error(v.getDatatype().generateErrorMsg(data));
                            }
                        } else {
                            match("\n");
                        }
                    }
                    LinkedList<Datatype> datas = new LinkedList<Datatype>();
                    for (Variable v: indexer) {
                        datas.add(v.getDatatype());
                    }
                    c.newProperty(new Property(propName,data,set,get,datas));
                    match("end");
                } else if (isToken("generator")) {
                    match("generator");
                    if (gen) error("Class can only have maximal one generator.");
                    gen = true;
                    Variable v = new Variable("status",new Datatype(Datatype.INT_DATATYPE,0,null));
                    v.use();
                    c.newAttribute(false, v);
                    LinkedList<Variable> tmpVar = new LinkedList<Variable>();
                    tmpVar.add(v);
                    LinkedList<Expression> tmpDec = new LinkedList<Expression>();
                    tmpDec.add(getManager().getIntegerExpression(-1));
                    
                    c.getStartDecs().add(getManager().getDeclarationExpression(tmpVar, tmpDec, false));


                    Scope s = new Scope("",this,null);
                    CodeFunction func = new CodeFunction("start",new Datatype(Datatype.VOID_DATATYPE,0,null),s);
                    ExpressionBlock block = getManager().getBlockExpression(false);
                    block.addLine(getManager().getAssignmentExpression(v, getManager().getAccessExpression(getManager().getVariableExpression(thisVar), getManager().getVariableExpression(v)), getManager().getIntegerExpression(0)));
                    func.setBlock(block);
                    c.newMethod(isPublic, func, thisVar, superVar);
                    funcDecs.add(getManager().getFunctionDeclarationExpression(block, func));
                    getMainScope().newFunction(func);


                    s = new Scope("",this,null);
                    func = new CodeFunction("hasnext",new Datatype(Datatype.BOOLEAN_DATATYPE,0,null),s);
                    block = getManager().getBlockExpression(false);
                    Operator o = null;
                    for (Operator op: operators) {
                        if (op.getName().equals("!=")) {
                            o = op;
                            break;
                        }
                    }
                    block.addLine(getManager().getReturnExpression(getManager().getOperatorExpression(o, getManager().getAccessExpression(getManager().getVariableExpression(thisVar), getManager().getVariableExpression(v)), getManager().getIntegerExpression(-1))));
                    func.setBlock(block);
                    c.newMethod(isPublic, func, thisVar, superVar);
                    funcDecs.add(getManager().getFunctionDeclarationExpression(block, func));
                    getMainScope().newFunction(func);


                    Datatype data = datatype(true,true);
                    s = new Scope("",this,null);
                    s.setClass(c);
                    func= new CodeFunction("invoke",data,s);
                    func.setAsGenerator();
                    func.use();
                    match("(");
                    boolean start = false;
                    while (!isToken(")")) {
                        if (start) {
                            match(",");
                        }
                        name = getCurrent().getText();
                        data = null;
                        getNext(); //name
                        if (isToken(":")) {
                            data = datatype(true,false);
                        }
                        func.newParameter(name, data);
                        start = true;
                    }
                    
                    match(")");
                    func.setStartToken(getCurrent());
                    c.newMethod(isPublic, func,thisVar,superVar);
                    getMainScope().newFunction(func);
                    func.notCallable();

                    
                    overJumpBlock();
                    
                } else if (isToken("function")) {
                    Expression expr = keyWord();
                    //getMainScope().getFunctions().removeLast();

                    expr.setLine(getCurrent().getLine());
                    
                    c.newMethod(isPublic, lastFunc,thisVar,superVar);


                    String tmpName = lastFunc.getName();
                    lastFunc.setName(c.getName()+"_"+name);
                    getMainScope().newFunction(lastFunc);
                    lastFunc.setName(tmpName);

                } else {
                    error("Expecting var, function, generator or property.");
                }
                match("\n");
                while (isToken("\n")) getNext();
            }
            currentScope = tmp;
            if (!baseClass) {
                match("end");
                match("\n");
            }
            currentClass = tmpClass;
            replacer = tmpReplacer;
            inGenerator = tmpGenerator;
            
            return getManager().getEmptyExpression();
        } else if (isToken("function")) {
            match("function");
            boolean anonymous = false;
            if (isToken(":")) {
                anonymous = true;
            } else if (getScope().getSuperScope() != null) {
                error("Functions only allowed in main scope.");
            }
            String name = "";
            if (anonymous) {
                name ="__anofunc__"+anonFunc+"_";
                anonFunc++;
            } else {
                name = getCurrent().getText();
                getNext(); //name
            }
            Datatype data = null;
            if (currentClass != null && name.equals("new")) {
                if (isToken(":")) {
                    error("Constructor has no datatype.");
                }
                data = new Datatype(currentClass,0,null);
            } else {
                if (isToken(":")) data = datatype(true,true);
            }
            Function func;
            lastFunc = null;
            if (!inExtern) {
                func = new CodeFunction(name,data, new Scope(name,this,null));
                func.setSynonym("function_"+func.getName());
                lastFunc = (CodeFunction)func;
                if (anonymous) lastFunc.setTyp(CodeFunction.IS_ANONYME);
            } else {
                func = new Function(name,data);
            }
            if (anonymous) func.use();
            match("(");
            boolean start = false;
            while (!isToken(")")) {
                if (start) {
                    match(",");
                }
                name = getCurrent().getText();
                data = null;
                getNext(); //name
                if (isToken(":")) {
                    data = datatype(true,false);
                }
                func.newParameter(name, data);
                start = true;
            }
            match(")");
            if (isToken("=")) {
                match("=");
                func.setSynonym(getCurrent().getText().replace("\"", ""));
                getNext();
            }
            if (isToken("force")) {
                match("force");
                func.use();
                
            }
            if (!inExtern) ((CodeFunction)func).setStartToken(getCurrent());

            match("\n");
            
            if (!inExtern) {
                overJumpBlock();
            }
            if (currentClass == null && !anonymous) getMainScope().newFunction(func);
            if (anonymous) {
                getMainScope().getFunctions().add(func);
                compileFunction(func);
                return getManager().getAnonymousFuncExpression((CodeFunction)func);
            } else {
                return getManager().getEmptyExpression();
            }
        } else if (isToken("yield")) {
            if (inGenerator) {
                match("yield");
                if (isToken("break")) {
                    match("break");
                    return getManager().getYieldExpression();
                } else {
                    Expression ret = keyWord();
                    return getManager().getYieldExpression(ret);
                }
            } else {
                error("Cannot call 'yield' in non generator.");
            }
        } else if (isToken("return")) {
            if (getScope().getSuperScope() == null) {
                error("Return only allowed in function.");
            }
            match("return");

            Datatype data = currentFunction.getDatatype();
            Expression expr = null;
            if (data == null || !data.isVoid()) {
                expr = operator(0);
                if (data == null) {
                    currentFunction.setDatatype(expr.getDatatype());
                    data = expr.getDatatype();
                }
                if (!data.match(expr.getDatatype())) {
                    error(data.generateErrorMsg(expr.getDatatype()));
                }
            }
            match("\n");

            return getManager().getReturnExpression(expr);
        } else if (isToken("language")) {
            match("language");
            if (isToken(getLanguageName())) {
                getNext();
                return block(false);
            } else {
                int step = 0;
                while (!getCurrent().getText().equals("end") || step >= 0) {
                    getNext();
                    for (String key: scopeKeyword) {
                        if (getCurrent().getText().equals(key)) {
                            step++;
                            break;
                        }
                    }
                    if (getCurrent().getText().equals("end")) {
                        step--;
                    }
                }
                return getManager().getEmptyExpression();
            }
        } else if (isToken("extern")) {
            if (getScope().getSuperScope() != null) {
                error("Extern only allowed in main scope.");
            }
            inExtern = true;
            match("extern");
            if (isToken("do")) {
                match("do");
            }
            while (!isToken("end")) {
                if (isToken("function")) {
                    Expression expr = keyWord();
                } else {
                    getNext();
                }
            }
            match("end");
            inExtern = false;
            return getManager().getEmptyExpression();
        }
        return null;
    }
    /**
     * Gültiger Datentyp?
     */
    public void isValidDatatype(Token tok) throws SyntaxException {
        if (Datatype.Name2Int(tok.getText()) == -1 || tok.getText().equals("null")) {
            error("Invalid Datatype '" + tok.getText() + "'"); //Datentyp
        }
    }
    /**
     * Das aktuelle token
     */
    public Token getCurrent() {
        if (replacer != null) {
            if (replacer.containsKey(current.getText())) {
                return new Token(replacer.get(current.getText()),current.getWholeLine(),current.getLine(),current.getPos());
            }
        }
        return current;
    }
    /**
     * Vorige token
     * @return
     */
    public Token getPrevious() {
        return previous;
    }
    /**
     * Stimmt der aktuelle Token mit dem gegebenen String überein
     */
    public void match(String token) throws SyntaxException {
        if (!current.getText().equals(token)) {
            error("Unexpected token, expecting '" + token + "'");
        }
        getNext();
    }
    /**
     * Das vorige Token
     * @return das Token
     */
    public Token getPrev() throws SyntaxException {
        if (iterator.hasPrevious()) {
            previous = current;
            current = iterator.previous();
        } else {
            error("Unexpected begin of file.");
        }
        return getCurrent();
    }

    /**
     * Das nächste Token
     * @return das Token
     */
    public Token getNext() throws SyntaxException {
        if (iterator.hasNext()) {
            previous = current;
            current = iterator.next();
        } else {
            error("Unexpected end of file.");
        }
        return getCurrent();
    }

    /**
     * Ist das aktuelle Token ident mit dem übergegebenen Parameter
     */
    public boolean isToken(String param) {
        return current.getText().equals(param);
    }

    /**
     * Wirft eine Fehlermeldung
     */
    public void error(String msg) throws SyntaxException {
        SyntaxException ex = new SyntaxException(msg, current);
        System.out.print(ex.toString());

        SyntaxException.wasError = true;
        throw ex;
    }

    /**
     * Gibt den ExpressionManager zurück
     */
    public ExpressionManager getManager() {
        return man;
    }

    /**
     * Gibt alle Funktionsdeklarationen zurück
     */
    public LinkedList<ExpressionFunctionDeclaration> getFunctionDeclaration() {
        return funcDecs;
    }

    /**
     * Gibt den Lexer zurück
     */
    public Lexer getLexer() {
        return lexer;
    }

    /**
     * Generiert den Code
     */
    public abstract void generate(String template, boolean isMain);

    /**
     * Führt das backend aus
     */
    public abstract void backend();
    /**
     * Linkt das Programm
     */
    public abstract void link(LinkedList<Import> other);
    /**
     * Der generierte Code
     */
    public abstract String getGeneratedCode();
    /**
     * ausführen
     */
    public abstract void execute();
    /**
     * Name
     */
    public abstract String getLanguageName();
    /**
     * Aufrücken
     */
    public void identUp() {
        ident++;
    }

    /**
     * Einrücken
     */
    public void identDown() {
        ident--;
    }

    /**
     * Neue Zeile
     */
    public String newLine() {
        String str = "\n";
        for (int i = 0; i < ident; i++) {
            str += "\t";
        }
        return str;
    }

    /**
     * Gibt den aktuellen Scope zurück
     */
    public Scope getScope() {
        return currentScope;
    }

    /**
     * gibt den hauptscope zurück
     */
    public Scope getMainScope() {
        return mainScope;
    }

    /**
     * Setzt den aktuellen Token (achtung, vorsichtig verwenden)
     */
    public void setToken(Token tok) {
        this.current = tok;
    }
    /**
     * Bereits kompiliert?
     */
    public boolean isAlreadyCompiled() {
        return alreadyCompiled;
    }
    /**
     * Der Dateipfad
     * @return
     */
    public String getPath() {
        return filePath;
    }
    /**
     * Alle im Skript definiete variablen
     * @return
     */
     public LinkedList<Class> getClasses() {
         return classes;
     }
    /**
     * liest einen string aus einer datei
     * @param path
     * @return
     */
    public static String readFile(String path) {
        String input = "";
        BufferedReader reader = null;
        try {
           reader = new BufferedReader(new FileReader(path));
           String tmp = reader.readLine()+"\n";
           while (tmp != null) {
               input += tmp+"\n";
               tmp = reader.readLine();
           }
           reader.close();
        } catch (IOException ex) {
            System.err.println("File not found. "+path);
            ex.printStackTrace();
        }
        return input;
    }

    /**
     * Gibt die aktuelle Klasse zurück
     */
    public Class getCurrentClass() {
        return currentClass;
    }

    /**
     * Ist es im generator
     */
    public boolean isInGenerator() {
        return inGenerator;
    }
}
