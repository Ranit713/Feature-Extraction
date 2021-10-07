package parsers.tokenizer;

import java.io.File;
import java.util.Scanner;

public class FileParser {

    // Breaks file into tokens and removes useless tokens
    public void parse(File file) {
        try (Scanner sc = new Scanner(file).useDelimiter(";")) {
            Tokenizer tokenizer = new Tokenizer();
            while (sc.hasNext()) {
                String token = sc.next();
                token = token.trim();

                // If token is a comment line
                if (token.startsWith("//"))
                    token = token.split("\\n", 2)[1].trim(); // remove everything before newline

                // Ignore "module" and "endmodule" and parse token
                if (!token.startsWith("module") && !token.startsWith("endmodule"))
                    tokenizer.parse(token);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
        }
    }
}