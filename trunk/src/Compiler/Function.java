package Compiler;
import Compiler.Parser.Expression.Expression;
import java.util.*;
/**
 * Eine festdefinierte Funktion. Diese Funktion hat einen eindeutigen globalen Namen
 * @author Coolo
 */
public class Function extends Variable {
    private LinkedList<Variable> parameters;
    
    private boolean synonymOverload = false;
    private boolean defined = false;
    private boolean callable = true;
    private boolean compiled = false;

    /**
     * Konstruktor
     * @param name
     */
    public Function(String name,Datatype data) {
        super(name,data);
        this.name = name;
        setDatatype(data);
        this.synonym = name;
        parameters = new LinkedList<Variable>();
    }
    /**
     * Fügt einen Parameter hinzu
     * @param name der name des parameters
     * @param data der datentyp des parameters
     */
    public Variable newParameter(String name, Datatype data) {
        Variable var = new Variable(name,data);
        var.setSynonym("param_"+parameters.size());
        parameters.add(var);

        return var;
    }

    /**
     * Der Name
     */
    public String getName() {
        return name;
    }
    /**
     * Schaut ob die gegebenen Informationen reichen, dass die Funktion gerufen werden kann
     */
    public boolean match(String name, LinkedList<Expression> parameters) throws SyntaxException {
        if (getName().equals(name) && parameters.size() == this.parameters.size()) {
            int i = 0;            
            for (Expression par:parameters) {
                Datatype data = this.parameters.get(i).getDatatype();
                
                if (par.getDatatype() != null && !par.getDatatype().match(data)) {
                    return false;
                }
                i++;
            }
            
            return true;
        } else {
            return false;
        }
    }
    /**
     * Generiert den Namen (anhand den parametern) => name juggling
     */
    public String generateFuncName() {
        String name = synonym;
        if (this.synonymOverload) {
            for (Variable param: parameters) {
                name += "_"+param.getDatatype().toString();
                
            }
        }
        return name;
    }

    /**
     * Gibt die Parameter zurück
     */
    public LinkedList<Variable> getParameter() {
        return parameters;
    }

    

    /**
     * Überlade Synonym
     */
    public void overloadSynonym() {
        this.synonymOverload = true;
    }

    /**
     * definiere
     */
    public void define() {
        this.defined = true;
    }
    /**
     * definiert?
     */
    public boolean isDefined() {
        return defined;
    }
    /**
     * Einfach so aufrufbar
     */
    public void notCallable() {
        callable = false;
    }

    /**
     * Ist es aufrufbar?
     */
    public boolean isCallable() {
        return callable;
    }

    /**
     * wurde es schon kompiliert?
     */
    public boolean isCompiled() {
        return compiled;
    }

    /**
     * kompiliere
     */
    public void compile() {
        this.compiled = true;
    }
}
