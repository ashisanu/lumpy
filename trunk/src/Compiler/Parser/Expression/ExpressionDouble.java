package Compiler.Parser.Expression;

import Compiler.Datatype;
import Compiler.Parser.Parser;

/**
 * Eine 8 Byte Kommazahl
 * @author Coolo
 */
public abstract class ExpressionDouble extends ExpressionIdentifier {
    private double number;


    /**
     * Konstruktor
     * @param p
     * @param num
     */
    public ExpressionDouble(Parser p, double num) {
        super(p,new Datatype(Datatype.DOUBLE_DATATYPE,0,null));

        this.number = num;
    }


    /**
     * Die Nummer/der Wert der gehalten wird
     * @return
     */
    public double getNumber() {
        return number;
    }
}
