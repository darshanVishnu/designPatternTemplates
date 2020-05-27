package primeDetector.driver;

import java.io.FileNotFoundException;
import java.io.IOException;

import primeDetector.util.FileProcessor;
import primeDetector.util.IsPrime;
import primeDetector.util.Results;
import primeDetector.worker.CreateWorkers;
import primeDetector.util.StandardException;
import primeDetector.util.InputValidators;
import primeDetector.util.ValidatorUtil;
import primeDetector.util.MyLogger;

public class Driver {
    private static String inputFilePath;
	private static String numThreads;
	private static String capacity;
	private static String persisterServiceIp;
	private static String persisterServicePort;
    private static String debugValue;

    public static void main(String[] args) {
        final int REQUIRED_NUMBER_OF_ARGS = 6;
		if ((args.length != REQUIRED_NUMBER_OF_ARGS) || 
				(args[0].equals("${inputFile}")) || 
				(args[1].equals("${numThreads}")) || 
				(args[2].equals("${capacity}")) ||
				(args[3].equals("${persisterServiceIp}")) ||
				(args[4].equals("${persisterServicePort}")) ||
				(args[5].equals("${debugValue}"))) {

			System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
			System.exit(0);
		}

		inputFilePath = args[0];
		numThreads = args[1];
		capacity = args[2];
		persisterServiceIp = args[3];
		persisterServicePort = args[4];
		debugValue = args[5];

		try {
			ValidatorUtil.validate("Error in input validation: "
			, InputValidators.inputFileValidator(inputFilePath)
			, InputValidators.numThreadsValidator(Integer.parseInt(numThreads))
			, InputValidators.debugLevelValidator(Integer.parseInt(debugValue))
            );
		} catch(SecurityException secE) {
			System.err.println("Unable to validate path with security exception: " + secE.getMessage());
			System.exit(0);
		} catch(NumberFormatException nfe) {
			System.err.println("Expected integer, given something else. Error: " + nfe.getMessage());
			System.exit(0);
		} catch(StandardException se) {
			System.err.println("Custom exception generated:  " + se.getMessage());
			System.exit(0);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(0);
        }
        startProcessing();
    }

    public static void startProcessing() {
        try {
			MyLogger.setDebugValue(Integer.parseInt(debugValue));
			FileProcessor fileProc = new FileProcessor(inputFilePath);
            IsPrime primeIdentifier = new IsPrime();
            Results results = new Results(Integer.parseInt(capacity), persisterServiceIp, Integer.parseInt(persisterServicePort),Integer.parseInt(numThreads));
            CreateWorkers workerManager = new CreateWorkers(fileProc, results, primeIdentifier);
            workerManager.startWorkers(Integer.parseInt(numThreads));
        } catch(FileNotFoundException fnfe) {
            System.out.println("Input file not found.");
            System.exit(0);
        } catch(IOException ioe) {
            System.out.println(ioe.getMessage());
            System.exit(0);
        }
    }
}