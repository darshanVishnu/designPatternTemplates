package numberPlay.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TopKNumbersData used to maintain data form TopKNumbersResultsI
 */
public class TopKNumbersData implements PersisterI, TopKNumbersResultsI {
	public BufferedWriter bufferedWriter;
	private FileWriter fileWriter;
	public ArrayList<Double> ktopnumber = new ArrayList<Double>();
	public List<List<Double>> ktoplist = new ArrayList<List<Double>>();
	int windowSize = 0;
	int windowcounter = 0;

	/**
	 * constructors creates filewiter to write the data
	 * @param fileName path of the file
	 * @param windowSizeIn must be of int
	 */
	public TopKNumbersData(int windowSizeIn,String fileName){
		this.windowSize =windowSizeIn;
		try {
			fileWriter = new FileWriter(fileName);
		} catch (IOException e) {
			System.err.println("Error :fail to read the file please provide proper path" + fileName);
			e.printStackTrace();
		}
	}
	/**
	 * topKcalculator used to calculate topK values
	 * @param number type Integer
	 */

	public void topKcalculator(double numberIn) {
		numberIn=rounder(numberIn);
		if (windowcounter < windowSize) {
			ktopnumber.add(numberIn);
			Collections.sort(ktopnumber);
			windowcounter++;
		}
		// 1 2 3 4
		else if (windowSize == windowcounter) {
			if (ktopnumber.get(0) < numberIn) {
				ktopnumber.remove(0);
				ktopnumber.add(numberIn);
				Collections.sort(ktopnumber);
			}
		}
		store(ktopnumber);

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
	 * @param inputIn must be of type List<Dobule>
	 */
	@Override
	public void store(List<Double> topK) {
		List<Double> lists = new ArrayList<Double>();
		for (int i = (topK.size()-1); i >=0 ; i--) {
			lists.add(topK.get(i));
		}
		ktoplist.add((ArrayList<Double>) lists);
	}

	/**
	 * used to write the file and close resource after all the input is read

	 */
	@Override
	public void writeToFile() {
		try {
			bufferedWriter = new BufferedWriter(fileWriter);
			for (int i = 0; i < ktoplist.size(); i++) {
				bufferedWriter.write(String.valueOf(ktoplist.get(i))+"\n");
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
	 * writeToFile used to write the output to a file
	 */
	@Override
	public void close() {
		try {
			fileWriter.close();
		}
		catch (IOException e) {
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
		return String.format("TopKNumbersData[ktopnumber: %s, ktoplist: %s ,windowSize: %s,bufferedWriter %s ,fileWriter : %s]",ktopnumber,ktoplist,windowSize,fileWriter, bufferedWriter);
	}
	@Override
	public int hashCode() {
		return ("TopKNumbersData".hashCode()) *13;
	}
}
