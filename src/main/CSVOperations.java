package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class CSVOperations {

    private Features features;

    CSVOperations() {
        features = new Features();
    }

    /* Returns file name without extension. */
    private String getFileName(String fileName) {
        return fileName.substring(0, fileName.indexOf("."));
    }

    /** Stores feature values in a csv file. */
    void write(File vfile) {
        String CSVFileName = getFileName(vfile.getName()).concat(".csv");

        try (Writer CSVWriter = new FileWriter("../features/" + CSVFileName)) {

            // Writes the header in CSV file.
            CSVWriter.write(features.getHeader());

            // Iterate through each data and store them as CSV rows.
            for (Map.Entry<String, List<Integer>> entry : features.extract().entrySet()) {

                // String storing the row.
                StringBuilder tuple = new StringBuilder();

                // Store Net name.
                tuple.append(entry.getKey()).append(",");

                // Store respective net features.
                Iterator<Integer> iterator = entry.getValue().iterator();
                while (iterator.hasNext()) {
                    tuple.append(iterator.next());
                    if (iterator.hasNext())
                        tuple.append(",");
                }
                
                tuple.append(System.getProperty("line.separator"));
                CSVWriter.write(tuple.toString());
            }
            System.out.println(vfile.getName() + ": Features added to file succesfully!");
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
