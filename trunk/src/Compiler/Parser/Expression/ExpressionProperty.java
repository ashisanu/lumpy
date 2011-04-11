package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;
import Compiler.Property;
import Compiler.Variable;

/**
 * Ein Property Zugriff. Muss dynamisch entscheiden, ob es set/get ist
 * @author Coolo
 */
public abstract class ExpressionProperty extends ExpressionIdentifier {
    protected Property prop;
    protected ExpressionIdentifier self;
    protected Expression value;

    public ExpressionProperty(Parser p,Property prop,ExpressionIdentifier self) {
        super(p,new Datatype(Datatype.VOID_DATATYPE,0,null,null));
        this.prop = prop;
        this.self = self;
    }

    public void setValue(Expression value) {
        this.value = value;
    }

    public Expression getValue() {
        return value;
    }

    @Override
    public Datatype getDatatype() {
        return prop.getDatatype();
    }

    @Override
    public Variable getVariable() {
        if (value != null || prop.getGet() == null) {
            return prop.getSet();
        } else {
            return prop.getGet();
        }
    }

    public Property getProperty() {
        return prop;
    }
}
