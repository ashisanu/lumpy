package Compiler.Parser.Expression.CExpression;

import Compiler.Datatype;
import Compiler.Parser.Expression.Expression;
import Compiler.Parser.Expression.ExpressionArray;
import Compiler.Parser.Expression.ExpressionIdentifier;
import Compiler.Parser.Parser;
import Compiler.SyntaxException;
import java.util.LinkedList;

/**
 * Ein Arrayzugriff in C
 * @author coolo
 */
public class CExpressionArray extends ExpressionArray {
    public CExpressionArray(Parser p, ExpressionIdentifier var, LinkedList<Expression> arr) throws SyntaxException {
        super(p,var,arr);
    }

    @Override
    public String generate() {
        try {
            String str = "";
            Datatype d = new Datatype(var.getDatatype(),arrs.size(),null,null);
            str += "(("+CExpressionAssignment.getArrayDatatype(d)+"*)("+var.generate()+" -> data))";
            for (Expression e: arrs) {
                str += "["+e.generate()+"]";
            }
            return str;
        } catch (SyntaxException ex) {}
        return "ERROR";
    }
}
