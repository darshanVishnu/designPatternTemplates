package visitorPattern.results;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TopKFreqWordsResults implements Results {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private List<String> topKFreqWordsResultsList = new ArrayList<String>();

    public TopKFreqWordsResults(String topKOutputFileName) throws IOException {
        fileWriter = new FileWriter(topKOutputFileName);
        bufferedWriter = new BufferedWriter(fileWriter);
    }


    /**
     * Used to write the file
     */
    @Override
    public void writeToFile() {
        try {
            for (String list : topKFreqWordsResultsList) {
                bufferedWriter.write(list + "\n");
                bufferedWriter.flush();
               // System.out.println(alist);
            }
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            System.err.println("Error :fail  to write the fileline");
            e.printStackTrace();
        }
    }

    /**
     * Used to add element to topKFreqWordsResultslist
     */
    @Override
    public void addElement(String input) {
        topKFreqWordsResultsList.add(input);
        // System.out.println(input);
    }
    @Override
    public String toString() {
        return String.format("TopKFreqWordsResults[topKFreqWordsResultsList: %s", topKFreqWordsResultsList);
    }
}

