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
        if (value != null) {
            str += prop.getSet().generateFuncName()+"("+self.generate()+", "+value.generate()+")";
        } else {
            str += prop.getGet().generateFuncName()+"("+self.generate()+")";
        }
        return str;
    }
}
