package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.*;
import Compiler.Parser.Parser;

/**
 * Eine Zahl in C
 * @author Coolo
 */
public class CExpressionByte extends ExpressionByte {
    public CExpressionByte(Parser p, byte number) {
        super(p, number);
    }

    @Override
    public String generate() {
       return "((byte)"+getNumber()+")";
    }
}
