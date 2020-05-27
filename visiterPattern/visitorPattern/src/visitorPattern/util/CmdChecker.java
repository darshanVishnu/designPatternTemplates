package visitorPattern.util;

import java.util.Arrays;
import java.io.File;
/*
Used for checking input command line arguments
 */
public class CmdChecker {
    private String[] args;

    private static class ValidatorFetcher {
        /*
        validateArgslength check for the args lenght
        @return Validator
        @params CmdChecker
         */
       public static final int REQUIRED_NUMBER_OF_ARGS = 5;

        public static Validator validateArgslength(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws IncorrectFileFormatException {
                    if ((checklength.args.length != REQUIRED_NUMBER_OF_ARGS) ||
                            (checklength.args[0].equals("${input}")) ||
                            (checklength.args[1].equals("${acceptableWordsFile}")) ||
                            (checklength.args[2].equals("${k}")) ||
                            (checklength.args[3].equals("${topKOutputFile}"))||
                            (checklength.args[4].equals("${spellcheck_output.txt}"))) {
                        System.err.printf("Error: Incorrect number of arguments. Program accepts %d arguments.", REQUIRED_NUMBER_OF_ARGS);
                        System.exit(0);
                    }
                }
            };
        }
        public static Validator validateInputFile(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws IncorrectFileFormatException {
                    if (checklength.args[0].equals("") || checklength.args[0] == null) {
                        System.err.printf("Error: Input file is missing.Please give path of the inputfile");
                        System.exit(0);
                    }
                    checkfile(checklength.args[0]);
                }
            };
        }
        public static Validator validateAcceptableWordsFile(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws IncorrectFileFormatException {
                    if (checklength.args[1].equals("") || checklength.args[1] == null) {
                        System.err.printf("Error: Available Item file is missing.Please give path of the inputfile");
                        System.exit(0);
                    }
                    checkfile(checklength.args[1]);
                }
            };
        }
        public static Validator validateKWindowSize(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws IncorrectFileFormatException {
                    if (checklength.args[1] != null) {
                        try {
                            Integer dk= Integer.parseInt(checklength.args[2]);
                            if(dk <=0){
                                System.err.printf("Error: AverageK Window Size must be greater than 0.Please give +ve integer value");
                                System.exit(0);
                            }
                        } catch (NumberFormatException error) {
                            System.err.printf("Error: AverageK Window Size  is missing.Please give +ve integer value  greater than 0");
                            System.exit(0);
                        }
                    }
                    else{
                        System.err.printf("Error: AverageK Window Size  is missing.Please give +ve integer value  greater than 0");
                        System.exit(0);
                    }
                }
            };
        }
    }

    /*
    Constructor of class CmdChecker
    used to valiadate cmd agrs
    @exection when failed to validate error
     */
    public CmdChecker(String[] args) throws IncorrectFileFormatException {
        this.args = args;
        ValidatorUtil.validate("failed", ValidatorFetcher.validateArgslength(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateInputFile(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateAcceptableWordsFile(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateKWindowSize(this));
    }

    /**
     * used to check input file for empty file,valid file
     * @param input
     */
    public static void  checkfile(String input ){
        File file = new File(input);
        if (!file.exists()) {
            System.err.printf("File " +input +"does not exist!");
            System.exit(0);
        }
        if(!file.isFile()) {
            System.err.printf("Given  " +input +"is not a file!");
            System.exit(0);
        }
        if(!file.canRead()) {
            System.err.printf("No permission to access file"+input );
            System.exit(0);
        }
        if(file.length() == 0) {
            System.err.printf(" "+input+" file is empty");
            System.exit(0);
        }
    }

    @Override
    public String toString() {
        return String.format("CmdChecker[args:%s]",Arrays.toString(args));
    }

    @Override
    public int hashCode() {
        return (args.hashCode()) *13;
    }
}
