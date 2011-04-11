package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.*;
import Compiler.Parser.Parser;

/**
 * Eine Zahl in C
 * @author Coolo
 */
public class CExpressionDouble extends ExpressionDouble {
    public CExpressionDouble(Parser p, double number) {
        super(p, number);
    }

    @Override
    public String generate() {
        return "((double)"+getNumber()+")";
    }
}
