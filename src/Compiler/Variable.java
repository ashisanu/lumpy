package Compiler;

import java.util.LinkedList;

/**
 * Eine Variable
 * @author Coolo
 */
public class Variable {
    private static LinkedList<Variable> variables = new LinkedList<Variable>();
    public static LinkedList<Variable> getVariables() {
        return variables;
    }


    protected String name;
    protected Datatype datatype;
    protected boolean isPub = true;
    protected String synonym;
    private boolean used = false;


    /**
     * Konstruktor
     */
    public Variable(String name, Datatype datatype) {
        this.name = name;
        this.datatype = datatype;
        this.synonym = name;
    }

    /**
     * Name
     */
    public String getName() {
        return name;
    }

    
    /**
     * Gibt den Datentyp aus, ohne die Überprüfung ob null
     */
    public Datatype getDatatype() {
        return datatype;
    }
    /**
     * Setzt den Datentyp
     */
    public void setDatatype(Datatype data) {
        if (this.datatype == null)
            this.datatype = data;
    }

    /**
     * benutze es
     */
    public void use() {
        this.used = true;
        variables.add(this);
    }
    /**
     * wurde es schon benutzt?
     */
    public boolean isUsed() {
        return used;
    }
    /**
     * Setzt es als public
     */
    public void setPublic(boolean isPub) {
        this.isPub = isPub;
    }

    /**
     * Ist es public?
     */
     public boolean isPublic() {
         return isPub;
     }

     /**
      * Vorsichtig verwenden...
      */
     public void setName(String name) {
         this.name = name;
     }


     /**
     * Setzt ein Synonym
     */
    public void setSynonym(String syn) {
        this.synonym = syn;
    }

    /**
     * Das synonym
     */
    public String getSynonym() {
        return synonym;
    }
}
