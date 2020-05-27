package spendingPotential.util;

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
	private String line;

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
		line = reader.readLine();
	}

	/**
	 *polls the each input element from the file
	 * @return
	 * @throws IOException
	 */
	public String poll() throws IOException {
		if (null == line) return null;
		String newValue = line.trim();
		line = reader.readLine();
		return newValue;
	}

	/**
	 *close used to close the resources
	 * @throws IOException
	 */
	public void close() throws IOException {
		try {
			reader.close();
			line = null;
		} catch (IOException e) {
			throw new IOException("failed to close file", e);
		}
	}

	/**
	 * check the pattern for input file
	 * @param itemstr
	 * @return voolean
	 */
	public boolean checkItemOrMonneypattern(String itemstr){
		String [] pattern=itemstr.split(":");
		if(pattern.length !=2){
			System.out.println("Pattern not matchning. please format the inputfile");
			return false;
		}
		if(((pattern[0].equals("item") ))){
			if(!(pattern[1].toLowerCase().matches("[a-zA-Z]+?"))){
				System.out.println(itemstr);
				System.out.println("Items Pattern is not matching item:jetPlain. please format the inputfile");
				return false;
			}}
		else if(pattern[0].equals("money")){
			if(!(pattern[1].toLowerCase().matches("[0-9]*"))){
				System.out.println(itemstr);
				System.out.println("Money Pattern is not matching money:40000. please format the inputfile");
				return false;
			}
		}
		else{
			return false;
		}
		return true;
	}

	/**
	 * check the pattern for avaliable item file
	 * @param itemstr
	 * @return boolean
	 */
	public boolean checkAvailablepattern(String itemstr){
		String [] pattern=itemstr.split(":");
		if(pattern.length !=2){
			System.out.println("Pattern not matchning. please format the inputfile");
			return false;
		}
		if(((pattern[0].equals("basic")|| pattern[0].equals("moderatelyExpensive") || pattern[0].equals("superExpensive")))){
			if(!(pattern[1].toLowerCase().matches("[a-zA-Z]+?"))){
				System.out.println(itemstr);
				System.out.println("AvailableItems Pattern is not matchning. please format the inputfile");
				return false;
			}}
		else{
			return false;
		}
		return true;
	}

	/**
	 *
	 * @return string it returns the class details
	 */
	@Override
	public String toString() {
		return String.format("FileProcessor[FileReader: %s, bufferedReader: %s]", line, reader);
	}
	@Override
	public int hashCode() {
		return (line.hashCode()) *13;
	}
}
