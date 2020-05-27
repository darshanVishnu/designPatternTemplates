package wordPlay.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

/**
 * Used to read a  File .
 * And the read file is processed to the rquired format.
 */
public class FileProcessor {
    /*
    file used to store the file.
    bufferedReader used as buffer reader.
     */
    private FileReader file;
    public BufferedReader bufferedReader;

    /**
     * constructor for class FileProcessor
     * check for a empty file and initials a reader for the file
     *
     * @param must the absolute path of the file
     */
    public FileProcessor(String fileName) {
        try {
            file = new FileReader(fileName);
            File chechEmpty = new File(fileName);
            if (chechEmpty.length() == 0) {
                throw new Exception("File" + fileName + " is empty: please provide input in file");
            } else {
                bufferedReader = new BufferedReader(file);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * reads the the file line by line .
     *
     * @return String of the read line.
     */
    public String readSingleLine() {
        String fileContent = "";
        try {
            String line = "";
            if ((line = bufferedReader.readLine()) != null) {
                fileContent = line;
            }

        } catch (IOException e) {
            System.err.println("Error :failread to read the fileline");
            e.printStackTrace();
        }
        return fileContent;
    }

    /**
     * reads the the file char by char
     * the exception must be handled in the method which consumses it
     *
     * @return char which is  read .
     * @throws exception when the read char is not in required format
     */
    public char readSingleChar() throws Exception {
        char fileContent = '\0';
        int c = 0;
        if ((c = bufferedReader.read()) != -1) {
            fileContent = (char) c;
            if (!charFormatChecker(fileContent)) {
                throw new Exception("Error :fail to read the file please check the file format");
            }
        }
        return fileContent;
    }

    /**
     * check for the input char is in [[a-zA-Z0-9\s\.\n]].
     *
     * @param must be of type char
     * @return boolean true is the condition is satisified else not.
     */
    public boolean charFormatChecker(char inputChar) {
        if (!((String.valueOf(inputChar)).matches("^[a-zA-Z0-9\\s\\.\\n]*$"))) {
            System.err.println("Error:please input the formated  document");
            return false;
        }
        return true;
    }

    /**
     * It processes each senctences and calculates the metrics of the senctence and alos reverse the word
     * with help of readSingleChar,reverseEachSentence and writes each sentence after processing it.
     *
     * @param outputFileProcessor must be of type Result
     * @param resultprocessor     must be of type SentenceCalculator
     * @param outPutFilename      the absolute path of output
     * @return void.
     * @throws check for the sentence which does not have period and throws invalid input
     */
    public void processSentence(Results outputFileProcessor, SentenceCalculator resultprocessor, String outPutFilename) throws Exception {
        String line = "";
        String lineChar = "";
        char singleChar;
        try {
            while ((singleChar = readSingleChar()) != '\0') {
                if (singleChar == '.') {
                    lineChar += singleChar;
                    if ((line = resultprocessor.reverseEachSentence(lineChar)) != "") {
                        outputFileProcessor.writeSingleLine((line));
                    }
                    lineChar = "";

                } else {
                    lineChar += singleChar;
                }
            }
            if (singleChar == '\0' && lineChar.length() != 0 && !lineChar.equals("\n")) {
                throw new Exception("Input file is not well formatted");
            }
        } catch (Exception e) {
            outputFileProcessor.writeEmpty(outPutFilename);
            System.err.println("Error :failread to read the file");
            e.printStackTrace();
            System.exit(0);
        }
    }

    /*
    used to close the file object and bufferReader object
    @return void
     */
    public void resourceCloser() throws IOException {
        if (file != null)
            file.close();
        if (bufferedReader != null)
            bufferedReader.close();

    }
    @Override
    public String toString() {
        return String.format("FileProcessor[FileReader: %s, bufferedReader: %s]", file, bufferedReader);
    }
}
