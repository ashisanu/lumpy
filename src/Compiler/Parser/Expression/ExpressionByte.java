package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;

/**
 * Eine 1 Byte Ganzzahl
 * @author Coolo
 */
public abstract class ExpressionByte extends ExpressionIdentifier {
    private byte number;

    /**
     * Konstruktor
     * @param p
     * @param num
     */
    public ExpressionByte(Parser p, byte num) {
        super(p,new Datatype(Datatype.BYTE_DATATYPE,0,null));

        this.number = num;
    }

    /**
     * Die Nummer/der Wert der gehalten wird
     * @return
     */
    public byte getNumber() {
        return number;
    }
}
