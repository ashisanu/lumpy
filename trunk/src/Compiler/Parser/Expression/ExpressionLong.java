package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;

/**
 * Eine 8 Byte Ganzzahl
 * @author Coolo
 */
public abstract class ExpressionLong extends Expression {
    private long number;

    /**
     * Konstruktor
     * @param p
     * @param num
     */
    public ExpressionLong(Parser p, long num) {
        super(p,new Datatype(Datatype.LONG_DATATYPE,0,null));

        this.number = num;
    }


    /**
     * Die Nummer/der Wert der gehalten wird
     * @return
     */
    public long getNumber() {
        return number;
    }
}
