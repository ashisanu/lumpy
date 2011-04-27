package Compiler;
/**
 * Ein Syntaxfehler in der Datei
 * @author Coolo
 */
public class SyntaxException extends Exception {
    public static boolean wasError = false;
    private String text;
    private Token token;
    private boolean duck;

    /**
     * Erstellt eine neue Syntax Exception.
     * @param text
     * @param token
     */
    public SyntaxException(String text, Token token, boolean duck) {
        this.text = text;
        this.token = token;
        this.duck = duck;
    }

    public String toString() {
        return "";
    }
    /**
     * Die Fehlermeldung
     * @return Meldung als String
     */
    public String genError() {
        String str = "";

        str += "Syntax error: -------------------------------------------------------\n";
        str += "\tMessage: '"+text.replace("\n", "NEWLINE").trim()+"'\n";
        if (token != null) str += "\tLine: '"+token.getLine()+"' Character: '"+token.getPos()+"' at token '"+token.getText().replace("\n", "NEWLINE")+"'\n";
        if (token != null) str += "\tContent: '"+token.getWholeLine()+"'\n";
        str += "---------------------------------------------------------------------\n";

        return str;
    }
    /**
     * Die Fehlermeldung
     * @return
     */
    public String getText() {
        return text;
    }

    /**
     * Ist es ein ducktype?
     */
    public boolean isDuck() {
        return duck;
    }

    /**
     * Setzt es als Ducktype
     */
    public void setDuck() {
        this.duck = true;
    }
}
