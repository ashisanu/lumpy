package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.*;
import Compiler.Parser.Parser;

/**
 * Eine Zahl in C
 * @author Coolo
 */
public class CExpressionLong extends ExpressionLong {
    public CExpressionLong(Parser p, long number) {
        super(p, number);
    }

    @Override
    public String generate() {
        return "((long)"+getNumber()+")";
    }
}
