/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Compiler;

/**
 *
 * @author Coolo
 */
public class Operator {
    private String op;
    private int prio;
    private int ret = -1;
    private int[] datatypes;
    /**
     * Erstellt einen neuen Operator
     * @param op Wie der Operator im source steht
     * @param prio Die Priorität
     * @param datatypes Die Datentypen, den dieser Operator verarbeiten kann
     */
    public Operator(String op, int prio, int[] datatypes) {
        this.op = op;
        this.prio = prio;
        this.datatypes = datatypes;
    }

    public Operator(String op,int prio,int[] datatypes,int ret) {
        this(op,prio,datatypes);
        this.ret = ret;
    }
    /**
     * Liefert die Priorität
     * @return
     */
    public int getPrio() {
        return prio;
    }
    /**
     * Liefert den Namen
     * @return
     */
    public String getName() {
        return op;
    }
    /**
     * Liefert alle möglichen Datentypen
     */
    public int[] getPossibleDatatypes() {
        return datatypes;
    }

    /**
     * Lieert den rückgabedatentyp (falls vorhanden)
     */
     public int getReturn() {
         return ret;
     }
}
