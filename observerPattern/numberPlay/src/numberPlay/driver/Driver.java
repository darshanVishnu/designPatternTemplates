package numberPlay.driver;

/**
 * @author Darshan
 * TODO update the author name.
 */

import observer.ObserverI;
import subject.SubjectI;
import numberPlay.util.*;
import numberPlay.filter.*;

import java.io.IOException;

/*
 * Class Driver used to get the cmd input files and process the input file
 * 3 observer are create and are register with filters
 * 3 filter are used and one to many relation is used between fiter and observer
 *Intputprocessor class is used to process the input file
 */
public class Driver {
    public static void main(String[] args) {

        /*
         * As the build.xml specifies the arguments as argX, in case the
         * argument value is not given java takes the default value specified in
         * build.xml. To avoid that, below condition is used
         * FIXME Refactor commandline validation using the validation design taught in class.
         */
        try {
            CmdChecker cmd = new CmdChecker(args);

            FileProcessor fileProcessor = new FileProcessor(args[0]);
            ObserverI runningAverageObserver = new RunnningAverageObserver(args);
            ObserverI topkNumberObserver = new TopKNumbersObserver(args);
            ObserverI numberpeakObserver = new NumberPeaksObserver(args);
            //subject object
            SubjectI numberProcessor = new NumberProcessor();

            FilterI intEventFilter = new IntEventFilter();
            FilterI floatEventFilter = new FloatEventFilter();
            FilterI processingEventFilter = new ProcessingEventFilter();
            //FilterOne intEventFilter : notify all observer
            numberProcessor.register(runningAverageObserver, intEventFilter);
            numberProcessor.register(topkNumberObserver, intEventFilter);
            numberProcessor.register(numberpeakObserver, intEventFilter);
            //Filtertwo intEventFilter : notify only selected observer
            numberProcessor.register(topkNumberObserver, floatEventFilter);
            numberProcessor.register(numberpeakObserver, floatEventFilter);
            //Filterthree  : notify all observer
            numberProcessor.register(runningAverageObserver, processingEventFilter);
            numberProcessor.register(topkNumberObserver, processingEventFilter);
            numberProcessor.register(numberpeakObserver, processingEventFilter);

            InputProcessor.run(fileProcessor, numberProcessor);
            numberProcessor.notifyAll("processescomplete");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }
}

/*
 Intputprocessor class is used to process the input file
 */
final class InputProcessor {
     /**
     * process the input file
     *
     * @param must be of type FileProcessor, @param must be of type SubjectI
     * @return void
     */
    public static void run(FileProcessor fileProcessorIn, SubjectI subjectIn) {
        try {
            String numberstr = "";
            while ((numberstr = fileProcessorIn.poll()) != null) {
                subjectIn.notifyEvent(numberstr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}
