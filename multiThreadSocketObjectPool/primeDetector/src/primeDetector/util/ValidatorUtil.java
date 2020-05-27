package primeDetector.util;

/** 
 * This class is used from the lecture given by Pradyumna Kaushik on the use of validators.
 * This class runs all the validators of type ValidatorI
 * @author Srinidhi Raghavendra
 */
public final class ValidatorUtil {
	/**
	 * Validate method. Calls the run method of all validators of type ValidatorI.
	 * @param - String baseErrMsg - custom message to be appended to the generated execption message.
	 * @param - Var args of validators of type ValidatorI
	 */
	public static void validate(String baseErrMsg, ValidatorI... validators) throws Exception {
		for (ValidatorI v : validators) {
			try {
				v.run();
			} catch (Exception e) {
				throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
			}
		}
	}
}