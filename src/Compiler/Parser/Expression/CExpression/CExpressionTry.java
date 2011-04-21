package Compiler.Parser.Expression.CExpression;

import Compiler.Datatype;
import Compiler.Parser.Expression.ExpressionBlock;
import Compiler.Parser.Expression.ExpressionTry;
import Compiler.Parser.Parser;
import java.util.LinkedList;

/**
 * Ein Try Block in C
 * @author Coolo
 */
public class CExpressionTry extends ExpressionTry {
    public static int TRYCOUNT = 0;
    public static int currentTry = -1;
    private int id;
    public CExpressionTry(Parser p, ExpressionBlock mainBlock, LinkedList<ExpressionBlock> catches, LinkedList<Datatype> datas, ExpressionBlock finallyBlock, String variableName) {
        super(p,mainBlock,catches,datas, finallyBlock,variableName);
        id = TRYCOUNT++;
    }

    @Override
    public String generate() {
        String str = "";
        str += "//begin try: "+name+getParser().newLine();
        str += "int jmp_env_"+id+" = setjmp(exc_env_"+id+");"+getParser().newLine();
        getParser().identUp();
        str += "if (jmp_env_"+id+") {"+getParser().newLine();
        //exception raised
        int i = 0;
        for (Datatype data: datas) {
            getParser().identUp();
            str += "if (jmp_env_"+id+" == TYP_"+data.getName().toUpperCase()+") {" + getParser().newLine() ;
            String tmpName = data.getName();
            if (data.isGC()) tmpName="obj";
            str += CExpressionAssignment.getDatatype(data)+" _"+this.name+"_ = exc_holder_" +tmpName+";"+getParser().newLine();
            
            str += catches.get(i).generate();


            getParser().identDown();
            str += getParser().newLine();
            str += "}"+getParser().newLine();
            i++;
        }
        if (finallyBlock != null) {
            str += "else "+finallyBlock.generate();
        }
        getParser().identDown();
        str += getParser().newLine();
        str += "} else {";
        getParser().identUp();
        str += getParser().newLine();
        int excCount = 0;
        String possibleExceptions = "";
        boolean start = false;
        for (Datatype data: datas) {
            if (start) possibleExceptions +=", ";
            possibleExceptions += "TYP_"+data.getName().toUpperCase();
            excCount ++;
            start = true;
        }
        str += "stack_enter(0, newException(&exc_env_"+id+", "+excCount+", "+possibleExceptions+"));"+getParser().newLine();
        int tmp = currentTry;
        currentTry = id;
        str += mainBlock.generate();
        currentTry = tmp;
        str += getParser().newLine();
        str += "stack_leave();"+getParser().newLine();
        getParser().identDown();
        str += getParser().newLine();
        str += "}"+getParser().newLine();
        str += "//end try"+getParser().newLine();
        return str;
    }
}
