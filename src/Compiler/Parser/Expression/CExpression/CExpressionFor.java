package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.Expression;
import Compiler.Parser.Expression.ExpressionBlock;
import Compiler.Parser.Expression.ExpressionFor;
import Compiler.Parser.Expression.ExpressionIdentifier;
import Compiler.Parser.Parser;

/**
 * For Schleife in C
 * @author Coolo
 */
public class CExpressionFor extends ExpressionFor {
    public CExpressionFor(Parser p, Expression init, Expression cond, Expression increment,ExpressionBlock block) {
        super(p,init,cond,increment,block);
    }

    @Override
    public String generate() {
        String str = "";
        str += init.generate()+";"+getParser().newLine();
        str += "for(;"+cond.generate()+";"+increment.generate()+") ";
        str += block.generate();
        return str;
    }
}
