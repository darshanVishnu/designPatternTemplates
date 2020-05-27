package visitorPattern.visitor;

import visitorPattern.results.Results;
import visitorPattern.util.Iterator;
import visitorPattern.util.MyArrayList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;

public class TopKMostFreqAnalyzer implements Visitor {
    private Integer kMostFreqCount;
    private Results topKFreqWordsResults;

    /**
     * Constructor of the TopKMostFreqAnalyzer
     * @param kMostFreqCountIn
     * @param topKFreqWordsResultsIn
     */
    public TopKMostFreqAnalyzer(String kMostFreqCountIn, Results topKFreqWordsResultsIn) {
        kMostFreqCount = Integer.parseInt(kMostFreqCountIn);
        topKFreqWordsResults = topKFreqWordsResultsIn;
    }

/**
 * vist method which the logic to find TopKMostFreq words
 */
    @Override
    public void visit(MyArrayList myArrayList) {
        Iterator iterator = myArrayList.getIterator();
        String sentence = null;
        while (iterator.hasNext()) {
            Map<String, Integer> list = new HashMap<>();
            sentence = (String) iterator.next();
            if(sentence.length()>1){
          //  System.out.println(sentence.trim());
            String eachWords[] = sentence.trim().split(" ");
            for (String eachWord : eachWords)
                list.put(eachWord, list.getOrDefault(eachWord, 0) + 1);
            List<String> words = new ArrayList<>(list.keySet());
            Collections.sort(words, (word1, word2) -> list.get(word1).equals(list.get(word2)) ?
                    word1.compareTo(word2) : list.get(word2) - list.get(word1));
           // System.out.println(aword);
            topKFreqWordsResults.addElement(words.subList(0, kMostFreqCount).toString());
        }}
    }

    @Override
    public String toString() {
        return String.format("TopKMostFreqAnalyzer[kMostFreqCount: %s", kMostFreqCount);
    }
}
