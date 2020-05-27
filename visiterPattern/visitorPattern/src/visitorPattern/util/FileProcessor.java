package visitorPattern.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

public final class FileProcessor {
	/**
	 * FileProcessor used to read input file
	 */
	private BufferedReader reader;

	/**
	 * constructor used to read the file throws ex when file is not directory put in as path
	 * @param inputFilePath
	 * @throws InvalidPathException
	 * @throws SecurityException
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public FileProcessor(String inputFilePath)
		throws InvalidPathException, SecurityException, FileNotFoundException, IOException {

		if (!Files.exists(Paths.get(inputFilePath))) {
			throw new FileNotFoundException("invalid input file or input file in incorrect location");
		}

		reader = new BufferedReader(new FileReader(new File(inputFilePath)));

	}

	/**
	 * read a single char in a file
	 * @return char
	 * @throws Exception
	 */
	public char readSingleChar(){
		char fileContent = '\0';
		try {
		int c = 0;
		if ((c = reader.read()) != -1) {
			fileContent = (char) c;
			if (!charFormatChecker(fileContent)) {
				throw new IncorrectFileFormatException("Error :fail to read the file please check the file format");
			}
		}}
		catch(IOException  | IncorrectFileFormatException exception){
			exception.printStackTrace();
			System.exit(0);
		}
		return fileContent;
	}
	/**
	 * check for the input char is in [[a-zA-Z\s\.\n]].
	 *
	 * @param must be of type char
	 * @return boolean true is the condition is satisified else not.
	 */
	public boolean charFormatChecker(char inputChar) {
		if(String.valueOf(inputChar).equals("\n")){
			System.err.println("Error:please input the formated  document remove the  newline char from file");
			return false;
		}
		if (!((String.valueOf(inputChar)).matches("[a-zA-Z\\s\\.]*$"))) {
			System.err.println("Error:please input the formated  document "+inputChar);
			return false;
		}
		return true;
	}

 /**
  * used to read a sentence
  * @return String
  */
	public String readEachSentence(){
	String line = "";
	char singleChar;
        try {
			while ((singleChar = readSingleChar()) != '\0') {
				if (singleChar == '.') {
					break;
				} else {
					line += singleChar;
				}
			}
        }catch (Exception e){
        	e.printStackTrace();
		}
		return line;
	}
	/**
	 * used to read a line 
	 * @returnString
	 */
	public String readLine(){
		String line = "";
		try {
			if ((line = reader.readLine()) != null) {
				return line.trim();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return line;
	}
	/**
	 *close used to close the resources
	 * @throws IOException
	 */
	public void close() throws IOException {
		try {
			reader.close();
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}

	/**
	 *
	 * @return string it returns the class details
	 */
	@Override
	public String toString() {
		return String.format("FileProcessor[FileReader: %s, bufferedReader: %s]", reader);
	}
	@Override
	public int hashCode() {
		return ("line".hashCode()) *13;
	}
}
