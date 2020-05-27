package wordPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
Used for outputing result
File writing ,STD displaying and metrix file are written usingS this class
 */
public class Results implements FileDisplayInterface, StdoutDisplayInterface {
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
            bufferedWriter.flush();
        } catch (IOException e) {
            System.err.println("Error :fail  to write the fileline");
            e.printStackTrace();
        }
    }

    /**
     * writes results from the SentenceCalculator object to the metrix file.
     *
     * @param fileData must be of class Sentencecalculator
     * @return void.
     */
    public void metrixFileWriter(SentenceCalculator fileData) {
        try {
            writeSingleLine("AVG_NUMBER_WORDS_PER_SENTENCE = " + fileData.gettotalwords() + "\n");
            writeSingleLine("AVG_NUM_CHARS_PER_SENTENCE = " + fileData.gettotalchars() + "\n");
            writeSingleLine("LONGEST_WORD  = " + fileData.getLongestWord() + "\n");
            writeSingleLine("MAX_FREQ_WORD = " + fileData.maxFreqWord() + "\n");
        } catch (Exception e) {
            System.err.println("Error :fail  to write the metrix file");
            e.printStackTrace();

        }
    }

    /**
     * Displays the contents to the std .
     *
     * @param display must to a String
     * @return void.
     */
    @Override
    public void stddisplay(String display) {
        System.out.println(display);
    }

    /**
     * Used to empty the file
     *
     * @param fileName name of the file to be cleared
     * @return void
     */
    public void writeEmpty(String fileName) {
        try {
            FileWriter fileClear = new FileWriter(fileName);
            PrintWriter clearer = new PrintWriter(fileClear, false);
            fileClear.flush();
            fileClear.close();
        } catch (IOException e) {
            System.err.println("Error :fail  to clear the file");
            e.printStackTrace();
        }
    }

    /*
    used to close the file object and bufferReader object
    @return void
     */
    public void resourceCloser() throws IOException {
        if (fileWriter != null)
            fileWriter.close();
        if (bufferedWriter != null)
            bufferedWriter.close();

    }
    @Override
    public String toString() {
        return String.format("Results:[fileWriter: %s, bufferedWriter: %s]", fileWriter, bufferedWriter);
    }

}

