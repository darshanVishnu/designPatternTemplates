package spendingPotential.util;

/*
Helper class validate the  custom validators
*/


final class ValidatorUtil {
    /*
    method is used to run all the chain of valiadator
    @params baseErrMsg valid error message, validators is the array of validator
     @return void
     */
    public static void validate(String baseErrMsg, Validator... validators) throws IncorrectFileFormatException {
        for (Validator valid : validators) {
            try {
                valid.run();
            } catch (IncorrectFileFormatException e) {
                throw new IncorrectFileFormatException(baseErrMsg.concat(": " + e.getMessage()));
            }
        }

    }
}