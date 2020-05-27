package numberPlay.util;

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
	 *polls the each input element from the fiel
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
