package Compiler.Parser.Expression.CExpression;

import Compiler.Datatype;
import Compiler.Parser.Expression.Expression;
import Compiler.Parser.Expression.ExpressionReturn;
import Compiler.Parser.Parser;
import Compiler.SyntaxException;

/**
 * Ein Return aus einer Funktion in C.
 * @author Coolo
 */
public class CExpressionReturn extends ExpressionReturn {
    public CExpressionReturn(Parser p, Expression expr) {
        super(p, expr);
    }

    @Override
    public String generate() {
        try {
            String prefix = "stack_leave();"+getParser().newLine();
            if (CExpressionBlock.varCount == 0) prefix = "";
            if (expr != null && !expr.getDatatype().isVoid())
                return prefix+"return "+expr.generate();
            else
                return prefix+"return";
        } catch (SyntaxException ex) {}
        return "ERROR";
    }
}
