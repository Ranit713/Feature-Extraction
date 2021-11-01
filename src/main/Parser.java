package main;

import java.io.File;

import parsers.tokenizer.FileParser;

class Parser {

    /** Creates the circuit graph. */
    void createGraph(File file) {
        FileParser fileParser = new FileParser();
        fileParser.parse(file);
        System.out.println(file.getName() + ": Graph created.");
    }
}
