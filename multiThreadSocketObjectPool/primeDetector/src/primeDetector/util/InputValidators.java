package primeDetector.util;

import java.io.File;
import primeDetector.util.ValidatorI;
import primeDetector.util.StandardException;

/**
 * @author Srinidhi Raghavendra
 * Collection of different types of validators
 */
public class InputValidators {
    /**
     * Takes in a file path and checks 
     * 1. if the file is present, 
     * 2. is in fact a file,
     * 3. can be read and 
     * 4. is not empty.
     * @param filePath
     * @return
     */
    public static ValidatorI inputFileValidator(String filePath) {
        return new ValidatorI() {
            @Override
            public void run() throws SecurityException, StandardException {
                File file = new File(filePath);
                if (!file.exists()) {
                    throw new StandardException("File does not exist!");
                }
                if(!file.isFile()) {
                    throw new StandardException("Given input is not a file!");
                }
                if(!file.canRead()) {
                    throw new StandardException("No permission to access file");
                }
                if(file.length() == 0) {
                    throw new StandardException("Input file is empty");
                }
            }
        };
    }

    /**
     * Takes in a integer number and checks if it its between 1 and 5.
     * @param int numThreads
     * @return
     */
    public static ValidatorI numThreadsValidator(int numThreads) {
        return new ValidatorI() {
            @Override
            public void run() throws StandardException {
                if (numThreads < 1 || numThreads > 5) {
                    throw new StandardException("Number of threads has to be between 1 and 5 included.");
                }
            }
        };
    }

    /**
     * Takes in a integer number and checks if it its between 0 and 4.
     * @param int level
     * @return
     */
    public static ValidatorI debugLevelValidator(int level) {
        return new ValidatorI() {
            @Override
            public void run() throws StandardException {
                if (!(level >= 0 && level <= 4)) {
                    throw new StandardException("Debug level has to be between 0 and 4 included.");
                }
            }
        };
    }
}