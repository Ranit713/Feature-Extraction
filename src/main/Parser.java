package main;

import java.io.File;

import parsers.tokenizer.FileParser;

class Parser {

    /** Opens input file. */
    private File openFile(String fileName) {
        return new File("../resources/" + fileName);
    }

    /** Creates the circuit graph. */
    private void createGraph(File file) {
        FileParser fileParser = new FileParser();
        fileParser.parse(file);
        System.out.println(file.getName() + ": Graph created.");
    }

    void run(String fileName) {
        File file = openFile(fileName);
        createGraph(file);
    }
}
