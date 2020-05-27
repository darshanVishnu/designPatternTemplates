package wordPlay.driver;

import wordPlay.util.FileProcessor;
import wordPlay.util.Results;
import wordPlay.util.SentenceCalculator;
import wordPlay.util.CmdChecker;

/**
 * @author darshan
 * Class Driver used to get the cmd input files and process the file
 * with the help of FileProcessor,Sentencecalculator,Results class files
 * and writes the output to the metrix and output file
 */
public class Driver {
    public static void main(String[] args) {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         */

        String line = "";
        try {
            CmdChecker cmd = new CmdChecker(args);
            FileProcessor inputFileProcessor = new FileProcessor(args[0]);
            Results outputFileProcessor = new Results(args[1]);
            Results metrixFileProcessor = new Results(args[2]);

            SentenceCalculator resultprocessor = new SentenceCalculator();
            inputFileProcessor.processSentence(outputFileProcessor, resultprocessor, args[1]);

            inputFileProcessor.resourceCloser();
            outputFileProcessor.resourceCloser();

            metrixFileProcessor.metrixFileWriter(resultprocessor);
            FileProcessor displayer = new FileProcessor(args[1]);
            while ((line = displayer.readSingleLine()) != "") {
                metrixFileProcessor.stddisplay(line);
            }
            displayer.resourceCloser();
            FileProcessor displayermetrix = new FileProcessor(args[2]);
            while ((line = displayermetrix.readSingleLine()) != "") {
                metrixFileProcessor.stddisplay(line);
            }
            displayermetrix.resourceCloser();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Please check the message");
            System.exit(0);
        }
    }

}

