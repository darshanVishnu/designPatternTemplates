package wordPlay.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Used to Calculate the metrix paramets of a sentence .
 */

public class SentenceCalculator {
    /*
    totalWords is of type long used to stores the total number of words in the input file
    totalChars is of type long used to stores the total number of charcters in the input file
    totalSentences is of type long used to stores the total number of Sentence in the input file
    maxChar is of type long used to stores the longest charchter count word in input file
    longestWord is of type String used to stores the longest charchter word in input file
    frqWordCount is of type HashMap used to stores the most occuring of all the word in input file
    INITIALVALUE is of type long used to set the values to  0
    INITIALWORD is of type String used to set the String to empty.
     */

    final long INITIALVALUE = 0;
    final String INITIALWORD = "";
    private long totalWords, totalChars, totalSentences, maxChar;
    private String longestWord;
    private HashMap<String, Integer> frqWordCount = new HashMap<String, Integer>();

    /**
     * constructor for class Sentencecalculator
     * initialse the initial values fo the metrix parameters
     */
    public SentenceCalculator() {
        this.totalChars = INITIALVALUE;
        this.longestWord = INITIALWORD;
        this.totalChars = INITIALVALUE;
        this.totalSentences = INITIALVALUE;
        this.maxChar = INITIALVALUE;
    }

    /**
     * returns the longest word in the input file .
     *
     * @return type String .
     */
    public String getLongestWord() {
        return this.longestWord;
    }

    /**
     * returns the total words in a input file.
     *
     * @return type double .
     */
    public double gettotalwords() {
        return rounder((double) this.totalWords / this.totalSentences);
    }

    /**
     * returns the total charcters in a input file.
     *
     * @return type double .
     */
    public double gettotalchars() {
        return rounder((double) (this.totalChars) / this.totalSentences);
    }

    /**
     * returns input value rounded up to 2 decimal points.
     *
     * @param input must be a type double
     * @return type double .
     */
    public double rounder(double input) {
        long roundedInt = Math.round(input * 100);
        return (double) roundedInt / 100;
    }

    /**
     * Used to reverse a Sentence.
     *
     * @param eachSentence must be a sentence from the input file
     * @return String which is reversed.
     */
    public String reverseEachSentence(String eachSentence) {
        String reverseSentence = "";
        String word = "";
        if (eachSentence == "" || eachSentence == null) return "";
        for (int i = 0; i < eachSentence.length(); i++) {
            if (eachSentence.charAt(i) == ' ' || eachSentence.charAt(i) == '.' || eachSentence.charAt(i) == '\n') {
                if (eachSentence.charAt(i) == '.') {
                    reverseSentence = reverseSentence + newReversedWord(word) + ".";
                    totalSentences++;
                    totalChars++;
                } else if (eachSentence.charAt(i) == '\n') {
                    reverseSentence = reverseSentence + newReversedWord(word) + "\n";
                } else {
                    reverseSentence = reverseSentence + newReversedWord(word) + " ";
                    totalChars++;
                }
                if (maxChar < word.length()) {
                    maxChar = word.length();
                    longestWord = word;
                }
                word = "";
            } else {
                totalChars++;
                word += eachSentence.charAt(i);
            }
        }
        if (word != "")
            reverseSentence = reverseSentence + newReversedWord(word);
        return reverseSentence;
    }

    /**
     * returns reversed input String rounded and
     * stores the input word in a hasp map as key along with its repetitiveness as value.
     *
     * @param input must be a type String
     * @return String .
     */
    public String newReversedWord(String rword) {
        String reversedWord = "";
        if (rword == "" || rword == null) return "";
        if (frqWordCount.containsKey(rword)) {
            frqWordCount.put(rword, frqWordCount.get(rword) + 1);
        } else {
            frqWordCount.put(rword, 1);
        }
        for (int j = rword.length() - 1; j >= 0; j--) {
            reversedWord += rword.charAt(j);
        }
        totalWords++;
        return reversedWord;
    }

    /**
     * used to find max occures of word in the file
     * checks  hasp map for the max value of a key and returns the key with max value among all.
     *
     * @return String .
     */
    public String maxFreqWord() {
        String wordFound = "";
        int frqcount = 0;
        for (Map.Entry<String, Integer> frqword : frqWordCount.entrySet()) {
            if (frqcount < frqword.getValue()) {
                frqcount = frqword.getValue();
                wordFound = frqword.getKey();
            }
        }
        return wordFound;
    }
    @Override
    public String toString() {
        return String.format("SentenceCalculator[ longestWord =%s,totalChars=%d,totalChars=%d,maxChar=%d,totalSentences =%d]",longestWord,totalChars, totalChars,maxChar,totalSentences);
    }
}
