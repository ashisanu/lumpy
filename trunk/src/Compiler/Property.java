package Compiler;

/**
 * Ein Property, mit ausgewählter set/get Methode
 * @author Coolo
 */
public class Property extends Variable {
    private CodeFunction set,get;

    public Property(String name, Datatype data,CodeFunction set, CodeFunction get) {
        super(name,data);
        this.set = set;
        this.get = get;
    }


    public CodeFunction getSet() {
        return set;
    }

    public CodeFunction getGet() {
        return get;
    }
}
