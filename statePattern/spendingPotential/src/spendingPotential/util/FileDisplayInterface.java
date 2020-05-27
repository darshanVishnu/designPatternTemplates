package spendingPotential.util;

/*
Used for File writing
 */
public interface FileDisplayInterface {

    /**
     * writes the the file line by line .
     *
     * @return void.
     */
    public void writeSingleLine(String content);
    public void resourceCloser();
}
