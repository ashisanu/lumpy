package Compiler.Parser.Expression.CExpression;

import Compiler.Parser.Expression.Expression;
import Compiler.Parser.Expression.ExpressionIdentifier;
import Compiler.Parser.Expression.ExpressionProperty;
import Compiler.Parser.Parser;
import Compiler.Property;

/**
 * Ein Zugriff auf Propertys in C
 * @author Coolo
 */
public class CExpressionProperty extends ExpressionProperty {
    public CExpressionProperty(Parser p,Property prop,ExpressionIdentifier self) {
        super(p,prop,self);
    }


    public String generate() {
        String str = "";
        String indexer = "";
        for (Expression expr: super.indexer) {
            indexer += ", ";
            indexer += expr.generate();
        }
        if (value != null) {
            str += prop.getSet().generateFuncName()+"("+self.generate()+", "+value.generate()+indexer+")";
        } else {
            str += prop.getGet().generateFuncName()+"("+self.generate()+indexer+")";
        }
        return str;
    }
}
