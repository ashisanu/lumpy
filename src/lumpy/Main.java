package lumpy;

import Compiler.Import;
import Compiler.Lexer.*;
import Compiler.Parser.*;
import Compiler.Parser.Expression.ExpressionManagerC;
import Compiler.SyntaxException;
import java.util.*;

/**
 * Mainprogramm
 * @author Coolo
 */
public class Main {
    public static LinkedList<Import> imports = new LinkedList<Import>();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input = Parser.readFile("file.ly");
        String template = Parser.readFile("data/template_c");
        try {
            System.out.flush();
            Parser mainParser = new CParser(new Lexer(input), new ExpressionManagerC(),"/");

            mainParser.compile();
            boolean notCompiled = true;
            while (notCompiled) {
                notCompiled = false;
                for (Import imp2: Main.imports) {
                    if (imp2.getParser()!= null && !imp2.getParser().isAlreadyCompiled()) {
                        notCompiled = true;
                        imp2.getParser().compile();
                    }
                }
            }
            
            if (!SyntaxException.wasError) {
                mainParser.generate(template,true);
                
                System.out.println(mainParser.getGeneratedCode());
                
                for (Import imp2: imports) {
                    if (imp2.getParser() != null && imp2.getParser()!= mainParser) imp2.getParser().generate(template,false);
                }


                for (Import imp2: imports) {
                    if (imp2.getParser() != null) imp2.getParser().backend();
                }
                

                mainParser.link(imports);

                mainParser.execute();
            } else {
                throw new SyntaxException("",null,false);
            }
        } catch (SyntaxException ex) {
            System.out.println(ex.genError());
            //ex.printStackTrace();
        }
    }

    
}
