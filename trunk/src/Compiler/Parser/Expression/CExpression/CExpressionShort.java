package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.*;
import Compiler.Parser.Parser;

/**
 * Eine Zahl in C
 * @author Coolo
 */
public class CExpressionShort extends ExpressionShort {
    public CExpressionShort(Parser p, short number) {
        super(p, number);
    }

    @Override
    public String generate() {
        return "((short)"+getNumber()+")";
    }
}
