package visitorPattern.results;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckResults implements Results {
    public BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    List<String> spellCheckResultsList = new ArrayList<String>();

    public SpellCheckResults(String spellCheckOutputFilename) throws IOException {
        fileWriter = new FileWriter(spellCheckOutputFilename);
        bufferedWriter = new BufferedWriter(fileWriter);

    }

    /**
     * Used to write the file
     */

    @Override
    public void writeToFile() {
        try {
            for (String alist : spellCheckResultsList) {
                bufferedWriter.write(alist + "\n");
                bufferedWriter.flush();
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error :fail  to write the fileline");
            e.printStackTrace();
        }
    }

    /**
     * Used to add element to spellCheckResultlist
     */
    @Override
    public void addElement(String input) {
        // System.out.println(input);
        spellCheckResultsList.add(input);
    }

    @Override
    public String toString() {
        return String.format("SpellCheckResults[spellCheckResultsList: %s", spellCheckResultsList);
    }

}
