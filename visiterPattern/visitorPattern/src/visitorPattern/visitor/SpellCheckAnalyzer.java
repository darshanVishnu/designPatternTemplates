package visitorPattern.visitor;

import visitorPattern.results.Results;
import visitorPattern.util.FileProcessor;
import visitorPattern.util.Iterator;
import visitorPattern.util.MyArrayList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SpellCheckAnalyzer implements Visitor {
    private Results spellCheckResults;
    private String acceptableWordsFileName;
    private List<String> spellCheckWordList;

    /**
     * Constructor for SpellCheckAnalyzer class
     * @param acceptableWordsFileNameIn
     * @param spellCheckResultsIn
     */
    public SpellCheckAnalyzer(String acceptableWordsFileNameIn, Results spellCheckResultsIn) {
        acceptableWordsFileName = acceptableWordsFileNameIn;
        spellCheckResults = spellCheckResultsIn;
        spellCheckWordList = new ArrayList<>();
        readacceptableWordsFileName();
    }

    /**
     * Used as vist method to implement Spellcheck Logic
     * @param myArrayList
     */
    @Override
    public void visit(MyArrayList myArrayList) {
     //   System.out.println("SpellCheckAnalyzer");
        Iterator iterator = myArrayList.getIterator();
        String sentence = null;
        while (iterator.hasNext()) {
            sentence = (String) iterator.next();
          //  System.out.println(sentence);
            String[] wordSen = sentence.split(" ");
            for (int index = 0; index < wordSen.length; index++) {
                char[] word1 = wordSen[index].toCharArray();
                for (int listWordCount = 0; listWordCount < spellCheckWordList.size(); listWordCount++) {
                    String listWord = spellCheckWordList.get(listWordCount);
                    char[] word2 = listWord.toCharArray();
                    if (word1.length == word2.length && word1.length >2) {
                        int count = 0;
                            for (int counter = 0; counter < word1.length; counter++) {
                            if (count > 1)
                                break;
                            if (word1[counter] != word2[counter]) {
                                count++;
                            }
                            if (count == 1 && counter == word1.length - 1) {
                                spellCheckResults.addElement(wordSen[index] + "::[" + listWord + "]");
                            }
                        }
                    }
                }
            }
        }
    }
/**
 * Used to process the acceptableWordsFilename list
 */
    private void readacceptableWordsFileName() {
        try {
            FileProcessor fileProcessor = new FileProcessor(acceptableWordsFileName);
            String word = "";
            while ((word = fileProcessor.readLine()) != null) {
                spellCheckWordList.add(word);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
    @Override
    public String toString() {
        return String.format("SpellCheckAnalyzer[spellCheckWordList: %s", spellCheckWordList);
    }
}
