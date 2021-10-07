package parsers.tokenizer;

import parsers.parser.CircuitryToken;
import parsers.parser.NetlistToken;

class Tokenizer {

    private boolean isNetlistToken = true;

    /*
     * Parses a token and decides whether it is a netlist definition or circuitry.
     */
    void parse(String token) {
        if (isNetlistToken) // token is a netlist defintion
            isNetlistToken = new NetlistToken().parse(token);

        if (!isNetlistToken) { // token is a circuitry defintion
            new CircuitryToken().parse(token);
        }
    }
}
