package Compiler.Parser;

import Compiler.Lexer.Lexer;
import Compiler.*;
import Compiler.Class;
import Compiler.Parser.Expression.*;
import Compiler.Parser.Expression.CExpression.CExpressionAssignment;
import Compiler.Parser.Expression.CExpression.CExpressionNew;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import lumpy.Main;

/**
 * Parst und generiert C Code
 * @author Coolo
 */
public class CParser extends Parser {
    private String headerCode = "";
    private String functionCode = "";
    private String importCode = "";
    private String template;
    private Import myImport;
    
    public CParser(Lexer analyser, ExpressionManager man, String filePath) throws SyntaxException {
        super(analyser,man,filePath);
    }
    public void generate(String template, boolean isMain) {
        //if (isMain) {
        for (Import imp: Main.imports) {
            if (imp.getPath().endsWith("h"))
                importCode += "#include \""+imp.getPath()+"\""+newLine();
        }
        for (Import imp: Main.imports) {
            if (imp.getParser() != null)
                importCode += "#include \"file"+imp.getID()+".h\""+newLine();
        }
        //}
        String mainCode = "";
        mainCode = template.substring(template.indexOf("$MAIN_CODE"), template.indexOf("$ENDMAIN_CODE")+"$ENDMAIN_CODE".length());
        for (Import imp: Main.imports) {
            if (this.getPath().equals(imp.getPath())) {
                myImport = imp;
                break;
            }
        }
        if (SyntaxException.wasError) return;
        headerCode += "#ifndef boolean"+newLine();
        headerCode += "#define boolean char"+newLine();
        headerCode += "#endif"+newLine();
        //klassen definieren
        for (Class c: getClasses()) {
            headerCode += "#define TYP_"+c.getName().toUpperCase()+" "+c.getUnsafeID()+newLine();
            identUp();
            headerCode += "typedef struct __"+c.getName()+"__ {"+newLine();
            if (c.getInherit() != null) {
                headerCode +="//Inherit from: "+c.getInherit().getName()+newLine();
            }
            //headerCode += "VFunction* vtable;"+newLine();
            headerCode += "int typid;"+newLine();
            headerCode += "int superclass;"+newLine();
            for (Variable attribute: c.getAttibutes()) {
                if (attribute.isUsed()) {
                    headerCode += CExpressionAssignment.getDatatype(attribute.getDatatype())+" _"+attribute.getName()+"_;"+newLine();
                }
            }

            identDown();

            headerCode += newLine();
            headerCode += "} "+c.getName()+";"+newLine();

            headerCode += "GCNode* cast2"+c.getName()+"(GCNode* node);"+newLine();
            /*
            functionCode += "VFunction vtable_"+c.getName()+"[] = {";
            boolean start = false;
            for (Function func: c.getMethods()) {
                if (start) functionCode += ", ";

                functionCode += "(VFunction)"+func.generateFuncName();

                start = true;
            }
            functionCode += "};"+newLine();*/

            identUp();
            functionCode += "GCNode* cast2"+c.getName()+"(GCNode* node) {"+newLine();
            functionCode += "";
            //functionCode += "(("+c.getName()+"*)node) -> vtable = vtable_"+c.getName()+";"+newLine();
            functionCode +=  "node -> typid = TYP_"+c.getName().toUpperCase()+";"+newLine();
            functionCode += "return node;"+newLine();
            identDown();
            functionCode += newLine();
            functionCode += "}"+newLine();

            identUp();


            functionCode +="GCNode* new_"+c.getName()+"() {"+newLine();

            functionCode += "GCNode* obj = gc_malloc(sizeof("+c.getName()+"), &standardTrace);"+newLine();
            functionCode += "(("+c.toString()+"*)obj -> data) -> typid = TYP_"+c.getName().toUpperCase()+";"+newLine();
            if (c.getInherit() != null) functionCode += "(("+c.toString()+"*)obj -> data) -> superclass = TYP_"+c.getInherit().getName().toUpperCase()+";"+newLine();
            functionCode += "obj -> typid = TYP_"+c.getName().toUpperCase()+";"+newLine();

            Class tmpC = c;

            while (tmpC != null) {
                for (ExpressionDeclaration dec: tmpC.getStartDecs()) {
                    int i = 0;
                    for (Variable att: dec.getDecVar()) {
                        String init = "";
                        if (dec.getValues().get(i) instanceof ExpressionEmpty) {
                            init = att.getDatatype().createDatatypeExpression(getManager()).generate();
                        } else {
                            init = dec.getValues().get(i).generate();
                        }
                        if (att.isUsed()) functionCode += "(("+c.toString()+"*)obj -> data) -> "+CExpressionAssignment.getAccess(att)+" = " + init+ ";"+newLine();
                        i++;
                    }
                }
                tmpC = tmpC.getInherit();
            }
            functionCode += "return obj;"+newLine();

            identDown();
            functionCode += newLine();

            functionCode += "}"+newLine();

            headerCode +="GCNode* new_"+c.getName()+"();"+newLine();
        }

        Scope scope = getMainScope();
         //funktionheaders machen
        for (Function func:scope.getFunctions()) {
            if (func.isUsed() && !func.isDefined()) { // && ((func instanceof CodeFunction && ((CodeFunction)func).getScope().getParser() != this) || !(func instanceof CodeFunction))) {
                func.define();
                if (func instanceof CodeFunction) {
                    for (ExpressionFunctionDeclaration expr: getFunctionDeclaration()) {
                        if (expr.getFunction() == func && expr.getFunction().getScope().getParser() == this && expr.getFunction().isDefined()) {
                            functionCode += expr.generate();
                            break;
                        }
                    }
                    CodeFunction cfunc = (CodeFunction)func;
                    if (cfunc.getTyp() == CodeFunction.IS_ANONYME) {
                        //anonyme => erzeuge alias
                        LinkedList<Datatype> pars = new LinkedList<Datatype>();
                        for (Variable par: cfunc.getParameter()) {
                            pars.add(par.getDatatype());
                        }
                        Datatype tmp = new Datatype(cfunc.getDatatype(),cfunc.getDatatype().getDimensions(),pars);
                        String tmpStr = "";
                        tmpStr += "#ifndef _"+tmp.toString()+"_"+newLine();
                        tmpStr += "#define _"+tmp.toString()+"_"+newLine();
                        tmpStr += "typedef "+CExpressionAssignment.getDatatype(cfunc.getDatatype())+"(*"+tmp.toString()+")(";
                        
                        boolean start = false;
                        for (Variable par: cfunc.getParameter()) {
                            if (start) tmpStr += ", ";
                            tmpStr+=CExpressionAssignment.getDatatype(par.getDatatype());
                            start = true;

                        }
                        tmpStr +=");"+newLine();
                        tmpStr += "#endif"+newLine();
                        headerCode = tmpStr + headerCode;
                    }
                }
                headerCode += CExpressionAssignment.getDatatype(func.getDatatype()) + " "+func.generateFuncName()+"(";
                boolean start = false;
                if (func instanceof CodeFunction) {
                    CodeFunction cfunc = (CodeFunction)func;

                    if (cfunc.getScope().getOwnerClass() != null) {
                        headerCode += CExpressionAssignment.getDatatype(cfunc.getScope().getOwnerClass())+" _this_";
                        start = true;
                    }
                    
                }
                for (Variable param: func.getParameter()) {
                    if (start) headerCode += ", ";
                    headerCode += CExpressionAssignment.getDatatype(param.getDatatype())+" "+param.getName();

                    start = true;
                }
                headerCode += ");"+newLine();
            }
        }
        
        //Nun die fehlenden Array Initalasatoren implementieren
        if (isMain) {
            for (int i = 1; i<=CExpressionNew.maxArraySize;i++) {
                String head = "";
                head += "GCNode* allocarray_"+i+"_(int size";
                for (int j = 0; j<i;j++) {
                    head += ", int param"+j;
                }
                head += ")";

                headerCode += head+";"+newLine();

                identUp();
                functionCode += head+" {"+newLine();
                String c = "*";
                for (int j = 1 ;j<i;j++) c += "*";
                functionCode += "int"+c+" arr = malloc(size*param0);"+newLine();
                for (int j = 1; j<i;j++) {
                    functionCode += "int var"+j+";"+newLine();
                    identUp();
                    functionCode += "for (var"+j+" = 0;var"+j+" < param"+j+"; var"+j+"++) {"+newLine();
                    functionCode += "arr";
                    for (int k = 0;k<j;k++) {
                        functionCode += "[var"+(k+1)+"]";
                    }
                    functionCode +=" = malloc(size*param"+j+");"+newLine();
                }

                for (int j = 1; j<i;j++) {
                    identDown();
                    functionCode += newLine();
                    functionCode += "}";
                    functionCode += newLine();
                }
                functionCode += "GCNode* node = gc_malloc(0,&standardTrace);"+newLine();
                functionCode += "node -> data = arr;"+newLine();
                functionCode += "return node;"+newLine();
                identDown();
                functionCode += newLine();
                functionCode += "}";
                functionCode += newLine();
            }
        }

        /*
        for (Import imp: getImports()) {
            if (imp.getPath().endsWith("ly")) {
                importCode += "#include \"file"+imp.getID()+".h\""+newLine();
            }
        }

        importCode += "#include \"file"+myImport.getID()+".h\""+newLine();
        */
        if (isMain) {
            template = template.replace("$MAIN_CODE","");
            template = template.replace("$ENDMAIN_CODE","");
        } else {
            template = template.replace(mainCode, "");
        }

        template = template.replace("$IMPORT_CODE", importCode);
        template = template.replace("$FUNCTION_CODE", functionCode);
        this.template = template;

        //headerCode = "#define INCLUDEFILE"+myImport.getID() + newLine() + headerCode;
        //headerCode = "#ifndef INCLUDEFILE"+myImport.getID() + newLine() + headerCode;
        
        //headerCode += "#endif"+newLine();
    }


    public void backend() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output/file"+myImport.getID()+".c"));
            writer.write(template);
            writer.close();
            
            writer = new BufferedWriter(new FileWriter("output/file"+myImport.getID()+".h"));
            writer.write(headerCode);
            writer.close();

            String userDir = System.getProperty("user.dir");
            String command = "gcc -c "+userDir+"/output/file"+myImport.getID()+".c";
            
            InputStream strm = Runtime.getRuntime().exec(command).getErrorStream();
            int i = 0;
            while ((i = strm.read()) != -1 ) {
                System.out.print((char)i);
            }
        } catch (IOException ex) {
            System.err.println("Error while executing backend.");
        }
        
    }

    public void link(LinkedList<Import> other) {
        
        try {
            String userDir = System.getProperty("user.dir");
            String links = "";
            for (Import imp: other) {
                if (imp.getParser() != null)
                    links += "\""+userDir+"/output/file"+imp.getID()+".c\" ";
                else if (imp.getPath().endsWith("o"))
                    links += "\""+imp.getPath()+"\" ";
            }
            String command = "gcc -O3 -o \"program.exe\" "+links;
            System.out.println(command);
            System.out.println(command);
            InputStream strm = Runtime.getRuntime().exec(command).getErrorStream();
            int i = 0;
            while ((i = strm.read()) != -1 ) {
                System.out.print((char)i);
            }
        } catch (IOException ex) {
            System.err.println("error.");
        }
    }

    public String getGeneratedCode() {
        return template;
    }

    public void execute() {
        try {
            String userDir = System.getProperty("user.dir");
            InputStream strm = Runtime.getRuntime().exec("program.exe").getInputStream();
            int i = 0;
            while ((i = strm.read()) != -1 ) {
                System.out.print((char)i);
            }
        } catch (IOException ex) {
            System.err.println("error");
        }
    }

    /**
     * Der Sprachenname
     */
    public String getLanguageName() {
        return "c";
    }
}
