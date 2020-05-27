package numberPlay.util;

/*
Helper class validate the  custom validators
*/


final class ValidatorUtil {
    /*
    method is used to run all the chain of valiadator
    @params baseErrMsg valid error message, validators is the array of validator
     @return void
     */
    public static void validate(String baseErrMsg, Validator... validators) throws Exception {
        for (Validator valid : validators) {
            try {
                valid.run();
            } catch (Exception e) {
                throw new Exception(baseErrMsg.concat(": " + e.getMessage()), e);
            }
        }

    }
}