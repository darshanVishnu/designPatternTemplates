package primeDetector.util;

import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.InvalidPathException;

import java.util.List;
import primeDetector.util.MyLogger;

/**
 * This class deals with the input file reading.
 */
public final class FileProcessor {
	private BufferedReader reader;
	private String line;

	public FileProcessor(String inputFilePath) 
		throws InvalidPathException, SecurityException, FileNotFoundException, IOException {
		MyLogger.writeMessage("FileProcessor's Constructor is called" , MyLogger.DebugLevel.CONSTRUCTOR);
		if (!Files.exists(Paths.get(inputFilePath))) {
			throw new FileNotFoundException("invalid input file or input file in incorrect location");
		}

		reader = new BufferedReader(new FileReader(new File(inputFilePath)));
		line = reader.readLine();
	}

	/**
	 * Read one line which contains only a number from the input file.
	 * @return String - one number read from the inout file
	 * @throws IOException
	 */
	public synchronized String poll() throws IOException {
		if (null == line) return null;
	
		String newValue = line.trim();
		line = reader.readLine();
		return newValue;
	}

	/**
	 * Close the open reader reference to the file.
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
}
