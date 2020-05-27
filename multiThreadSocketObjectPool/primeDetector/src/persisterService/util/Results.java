package persisterService.util;


import java.io.*;
import java.io.DataInputStream;
import java.io.IOException;

/*
Used for outputing result
File writing ,STD displaying and metrix file are written usingS this class
 */
public class Results {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;

    /**
     * Constructor creates a new fileWriter and BufferWritter objects
     * @param fileNameIn is the absolute path of the file to which result is stored
     */
    public Results(String fileNameIn) {
        try {
            fileWriter = new FileWriter(fileNameIn);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {
            System.err.println("Error :fail  to read the file please provide proper path" + fileNameIn);
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * writes the the file line by line .
     *
     * @param content must to a String
     * @return void.
     */
    public void writeSingleLine(String content) {
        try {
            bufferedWriter.write(content);
            bufferedWriter.write("\n");
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
    public void resourceCloser() throws IOException {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.flush();
                bufferedWriter.close();
            }
            if (fileWriter != null)
                fileWriter.close();
        } catch (IOException exception) {
            System.err.println("Error :failed to close the resources");
            System.err.println(exception.getMessage());
        }
    }

    @Override
    public String toString() {
        return String.format("Results:[fileWriter: %s, bufferedWriter: %s]", fileWriter, bufferedWriter);
    }
}