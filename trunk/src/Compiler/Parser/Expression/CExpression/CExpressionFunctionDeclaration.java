package Compiler.Parser.Expression.CExpression;

import Compiler.*;
import Compiler.Class;
import Compiler.Parser.Expression.ExpressionBlock;
import Compiler.Parser.Expression.ExpressionFunctionDeclaration;
import Compiler.Parser.Parser;
import java.util.*;

/**
 * Eine Codefunction deklaration
 * @author Coolo
 */
public class CExpressionFunctionDeclaration extends ExpressionFunctionDeclaration {
    public CExpressionFunctionDeclaration(Parser p, ExpressionBlock block, CodeFunction func) {
        super(p,block,func);
    }

    @Override
    public String generate() {
        String str = CExpressionAssignment.getDatatype(func.getDatatype())+" "+func.generateFuncName()+"(";
        boolean start = false;
        if (func.getScope().getOwnerClass() != null) {
            str += "GCNode* _this_";
            start = true;
        }
        for (Variable par: func.getParameter()) {
            if (start) str += ", ";
            str += CExpressionAssignment.getDatatype(par.getDatatype())+" "+CExpressionAssignment.getAccess(par);
            start = true;
        }
        str += ") ";
        //schauen, ob dies eine methode ist
        if (getFunction().getScope().getOwnerClass() != null && getFunction().getTyp() == Class.IS_CLASS) {
            Class myc = getFunction().getScope().getOwnerClass();
            getParser().identUp();
            str +="{"+getParser().newLine();
            str += "GCNode* _super_ = NULL;"+getParser().newLine();
            str += "int tmpid = _this_ -> typid;"+getParser().newLine();

            if (myc.getInherit() != null) {
                str += "_super_ = _this_;//(("+getFunction().getScope().getOwnerClass().getName()+"*)_this_ -> data) -> superclass;"+getParser().newLine();
            }
            str += "_this_ -> typid = ((object*)_this_->data) -> typid; //grauslicher hack fuers casten..."+getParser().newLine();
            getParser().identUp();
            str += "switch (tmpid) {"+getParser().newLine();
            //Alle überladenen funktionen suchen
            LinkedList<Function> functions = new LinkedList<Function>(myc.getMethods());
            Stack<Class> classes = new Stack<Class>();
            for (Class c: myc.getInheriting()) classes.add(c);
            while (!classes.isEmpty()) {
                Class c = classes.pop();
                functions.addAll(c.getMethods());
                for (Class tmp: c.getInheriting()) classes.add(tmp);
            }
            LinkedHashSet<Function> tmp = new LinkedHashSet<Function>();
            tmp.addAll(functions);
            for (Function func: tmp) {
                if (func.getName().equals(getFunction().getName()) && func.getParameter().size() == getFunction().getParameter().size()) {
                    boolean equ = true;
                    int i = 0;
                    for (Variable param: func.getParameter()) {
                        if (!param.getDatatype().match(getFunction().getParameter().get(i).getDatatype())) {
                            equ = false;
                            break;
                        }
                        i++;
                    }
                    if (equ) {
                        CodeFunction f = (CodeFunction)func;
                        getParser().identUp();
                        str += "case TYP_"+f.getScope().getOwnerClass().getName().toUpperCase()+": "+getParser().newLine();
                        if (f.getBlock() != null) str += f.getBlock().generate();
                        str += getParser().newLine();
                        str += "break;";
                        getParser().identDown();
                        str += getParser().newLine();
                    }
                }
            }
            getParser().identUp();
            str += "default:"+getParser().newLine();
            str += block.generate()+getParser().newLine();
            getParser().identDown();
            getParser().identDown();
            str += getParser().newLine();
            str += "}"+getParser().newLine();
            str += "return;"+getParser().newLine();
            getParser().identDown();
            str += getParser().newLine();
            str+= "}"+getParser().newLine();
        } else {
            str += block.generate()+getParser().newLine();
        }
        return str;
    }
}
