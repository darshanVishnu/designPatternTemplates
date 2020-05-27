package primeDetector.util;

/** 
 * This interface is used from the lecture given by Pradyumna Kaushik on the use of validators.
 * Specifies the expected functionality of a validator.
 * @author Srinidhi Raghavendra
 */
public interface ValidatorI {
    /** 
     * Run a validator test and throw Exception is any is encountered.
     */
	public void run() throws Exception;
}