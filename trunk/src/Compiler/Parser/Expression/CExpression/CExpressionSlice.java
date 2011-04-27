package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.Expression;
import Compiler.Parser.Expression.ExpressionIdentifier;
import Compiler.Parser.Expression.ExpressionSlice;
import Compiler.Parser.Parser;
import Compiler.SyntaxException;

/**
 * Slicing in C
 * @author Coolo
 */
public class CExpressionSlice extends ExpressionSlice {
    public CExpressionSlice(Parser p, ExpressionIdentifier self, Expression start, Expression end) throws SyntaxException {
        super(p,self,start,end);
    }

    @Override
    public String generate() {
        String str = "";
        str += "sliceArray("+self.generate() + ",";
        if (start != null && end != null) {
            str += start.generate()+", "+end.generate();
        } else if (start == null && end != null) {
            str += "0, "+end.generate();
        } else if (end == null && start != null) {
            str += start.generate()+", 0";
        } else {
            str += "0, 0";
        }
        try {
            str += ",sizeof(" + CExpressionAssignment.getArrayDatatype(self.getDatatype())+ ")";
        } catch(SyntaxException ex) {}
        str += ")";
        
        return str;
    }
}
