package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;
import java.util.LinkedList;

/**
 * Erstellt eine neue Instanz/dimensioniert ein array
 * @author Coolo
 */
public abstract class ExpressionNew extends ExpressionIdentifier {
    protected LinkedList<Expression> exprs = null;
    public ExpressionNew(Parser p, Datatype data) {
        super(p, data);
    }
    public ExpressionNew(Parser p, Datatype data, LinkedList<Expression> exprs) {
        super(p,data);
        this.exprs = exprs;
    }
    
}
