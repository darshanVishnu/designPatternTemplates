package spendingPotential.util;
/*
Used to build custom validators
 */
interface Validator {
    /*
    run is used to validate the input and throw exception on failure to meet condition
    @return void
     */
    void run() throws IncorrectFileFormatException;
}
