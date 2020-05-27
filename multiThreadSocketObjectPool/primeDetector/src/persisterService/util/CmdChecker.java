package persisterService.util;

import persisterService.util.ValidatorUtil;
import java.util.Arrays;
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
        public static final int REQUIRED_NUMBER_OF_ARGS = 2;

        public static Validator validateArgslength(CmdChecker checkLength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if ((checkLength.args.length != REQUIRED_NUMBER_OF_ARGS) ||
                            (checkLength.args[0].equals("${port}")) ||
                            (checkLength.args[1].equals("${outputFile}"))) {
                        throw new Exception("Error: Incorrect number of arguments. Program accepts " + REQUIRED_NUMBER_OF_ARGS + " arguments.");
                    }
                }
            };
        }

        public static Validator validatePortNumber(CmdChecker checkLength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (checkLength.args[0].equals("") || checkLength.args[0] == null) {
                        throw new Exception("Error: Input the port number");
                    } else if (checkLength.args[0] != null) {
                        try {
                            Integer port = Integer.parseInt(checkLength.args[0]);
                            if (port < 32678 || port > 50000) {
                                throw new Exception("Error: Port number should be between 32678 and 50000 only.");
                            }
                        } catch (NumberFormatException error) {
                            throw new Exception("Error: Given port number is not a number");
                        }
                    }
                }
            };
        }

        public static Validator validateOutputFile(CmdChecker checkLength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if (checkLength.args[1].equals("") || checkLength.args[1] == null) {
                        throw new Exception("Error: Input file is missing.Please give path of the inputfile");    
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
    public CmdChecker(String[] args) throws Exception {
        this.args = args;
        ValidatorUtil.validate("failed", ValidatorFetcher.validateArgslength(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validateOutputFile(this));
        ValidatorUtil.validate("failed", ValidatorFetcher.validatePortNumber(this));
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
