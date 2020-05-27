package numberPlay.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * NumberPeaksData used to maintain data form peak values
 */
public class NumberPeaksData implements PersisterI, NumberPeaksResultsI {

	private double previousNumber = 0;
	private double currentNumber = 0;
	private long count = 0;
	private BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	private   ArrayList<Double> peaklist = new ArrayList<Double>();

	/**
	 * constructors creates filewiter to write the data
	 * @param fileName path of the file
	 */
	public NumberPeaksData(String fileName) {
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			System.err.println("Error :fail to read the file please provide proper path" + fileName);
			e.printStackTrace();
		}
	}

	/**
	 * peakcalculator used to calculate peak
	 * @param number type double
	 */
	public void peakcalculator(Double number) {
		currentNumber = number;
		if (count > 0) {
			if (currentNumber < previousNumber) {
				store(rounder(previousNumber));
			}
		}
		count++;
		previousNumber = currentNumber;
	}
	/**
	 * returns input value rounded up to 2 decimal points.
	 *
	 * @param input must be a type double
	 * @return type double .
	 */
	public double rounder(double input) {
		long roundedInt = Math.round(input * 100);
		return (double) roundedInt / 100;
	}

	/**
	 * used to store peaklist data
	 * @param inputIn must be of type double
	 */
	@Override
	public void store(Double inputIn) {
		peaklist.add(inputIn);

	}

	/**
	 * writeToFile used to write the output to a file
	 */
	@Override
	public void writeToFile() {
		try {
			bufferedWriter = new BufferedWriter(fileWriter);
			for (Double item : peaklist) {
				bufferedWriter.write(String.valueOf(item) + "\n");
			}
			bufferedWriter.flush();
			bufferedWriter.close();
			close();
		} catch (IOException e) {
			System.err.println("Error :fail  to write the fileline");
			e.printStackTrace();
		}
	}

	/**
	 * used to close the opened resources
	 */
	@Override
	public void close() {
		try {
			fileWriter.close();
		} catch (IOException e) {
			System.err.println("Error :fail to close the fileline");
			e.printStackTrace();
			System.exit(0);
		}
	}
	/**
	 *
	 * @return string it returns the class details
	 */
	 @Override
	public String toString() {
		return String.format("NumberPeaksData[previousNumber: %s, currentNumber: %s ,count: %s,bufferedWriter %s ,fileWriter : %s]",previousNumber,currentNumber,count,fileWriter, bufferedWriter);
	}

	@Override
	public int hashCode() {
		return (int)(currentNumber) *31;
	}
}