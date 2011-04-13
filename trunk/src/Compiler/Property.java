package Compiler;

import java.util.LinkedList;

/**
 * Ein Property, mit ausgewählter set/get Methode
 * @author Coolo
 */
public class Property extends Variable {
    private CodeFunction set,get;
    private LinkedList<Datatype> indexer;
    public Property(String name, Datatype data,CodeFunction set, CodeFunction get,LinkedList<Datatype> indexer) {
        super(name,data);
        this.set = set;
        this.get = get;
        this.indexer = indexer;
        set.setDatatype(data);
    }


    public CodeFunction getSet() {
        return set;
    }

    public CodeFunction getGet() {
        return get;
    }
    public LinkedList<Datatype> getIndexer() {
        return indexer;
    }
}
