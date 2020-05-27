package spendingPotential.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
Used for outputing result
File writing
 */
public class Results implements FileDisplayInterface {
    public BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    /**
     * constructor for class Results
     * initialses a file writer
     *
     * @param must the absolute path of the file
     */
    public Results(String fileName) {
        try {
            fileWriter = new FileWriter(fileName);
        } catch (IOException e) {
            System.err.println("Error :fail  to read the file please provide proper path" + fileName);
            e.printStackTrace();
        }
    }

    /**
     * writes the the file line by line .
     *
     * @param content must to a String
     * @return void.
     */
    @Override
    public void writeSingleLine(String content) {
        try {
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println("Error :fail  to write the fileline");
            e.printStackTrace();
        }
    }

    /*
    used to close the file object and bufferReader object
    @return void
     */
    @Override
    public void resourceCloser() {
        try {
            if (fileWriter != null)
                fileWriter.close();
            if (bufferedWriter != null)
                bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error :fail  to close the Resources");
            e.printStackTrace();
        }
    }
        @Override
        public String toString () {
            return String.format("Results:[fileWriter: %s, bufferedWriter: %s]", fileWriter, bufferedWriter);
        }

    }

