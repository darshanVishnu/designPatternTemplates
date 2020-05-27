package visitorPattern.driver;

/**
 * @author Darshan
 * TODO update the author name.
 */

import visitorPattern.results.Results;
import visitorPattern.results.SpellCheckResults;
import visitorPattern.results.TopKFreqWordsResults;
import visitorPattern.util.CmdChecker;
import visitorPattern.util.FileProcessor;
import visitorPattern.util.MyArrayList;
import visitorPattern.util.IncorrectFileFormatException;
import visitorPattern.util.Element;
import visitorPattern.visitor.SpellCheckAnalyzer;
import visitorPattern.visitor.TopKMostFreqAnalyzer;
import visitorPattern.visitor.Visitor;

import java.io.IOException;

public class Driver {
    private static void runAnalysis(FileProcessor fileProcessor, Visitor... visitors) throws IOException {
        Element myArrayList = new MyArrayList.Builder().withFileProcessor(fileProcessor).build();
        for (Visitor visitor : visitors) {
            myArrayList.accept(visitor);
        }
    }

    private static void persistResults(Results... analysisResults) {
        for (Results results : analysisResults) {
            results.writeToFile();
        }
    }

    public static void main(String[] args) throws IOException, IncorrectFileFormatException {
        // TODO command-line args validation.
        // TODO command-line parsing and initialization of following variables.
        // 	1. inputFilename.
        //	2. acceptableWordsFilename.
        // 	3. k.
        // 	4. topKOutputFilename.
        // 	5. spellCheckOutputFilename.

        CmdChecker cmd = new CmdChecker(args);
        String inputFilename = args[0];
        String acceptableWordsFilename = args[1];
        String k = args[2];
        String topKOutputFilename = args[3];
        String spellCheckOutputFilename = args[4];

        FileProcessor fileProcessor = new FileProcessor(inputFilename);

        Results topKFreqWordsResults = new TopKFreqWordsResults(topKOutputFilename);
        Visitor topKMostFreqAnalyzer = new TopKMostFreqAnalyzer(k, topKFreqWordsResults);

        Results spellCheckResults = new SpellCheckResults(spellCheckOutputFilename);
        Visitor spellCheckAnalyzer = new SpellCheckAnalyzer(acceptableWordsFilename, spellCheckResults);

        runAnalysis(fileProcessor, topKMostFreqAnalyzer, spellCheckAnalyzer);

        persistResults(topKFreqWordsResults, spellCheckResults);
    }
}
