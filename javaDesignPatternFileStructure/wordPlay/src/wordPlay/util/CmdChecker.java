package wordPlay.util;

import wordPlay.util.ValidatorUtil;
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
        public static Validator validateArgslength(CmdChecker checklength) {
            return new Validator() {
                @Override
                public void run() throws Exception {
                    if ((checklength.args.length != 3) || (checklength.args[0].equals("${arg0}")) || (checklength.args[1].equals("${arg1}")) || (checklength.args[2].equals("${arg2}"))) {
                        throw new Exception("Error: Incorrect number of arguments. Program accepts 3 arguments.");
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
    }
    @Override
    public String toString() {
        return String.format("CmdChecker[args:%s]",Arrays.toString(args));
    }

}
