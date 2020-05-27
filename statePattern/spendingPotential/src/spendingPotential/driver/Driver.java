package spendingPotential.driver;

/**
 * @author Darshan
 * TODO update the author name.
 */

import spendingPotential.spendingPotentialStates.AvailableItems;
import spendingPotential.util.FileDisplayInterface;
import spendingPotential.util.CmdChecker;
import spendingPotential.util.FileProcessor;
import spendingPotential.util.Results;
import spendingPotential.spendingPotentialStates.SpendingPotentialContext;
import spendingPotential.spendingPotentialStates.ContextI;
import spendingPotential.util.IncorrectFileFormatException;
import java.io.IOException;
public class Driver {
    public static void main(String[] args) {
        try {
            CmdChecker cmd = new CmdChecker(args);
            FileProcessor fileProcessorInputFile = new FileProcessor(args[0]);
            FileProcessor fileProcessorAvailableItemFile = new FileProcessor(args[1]);
            FileDisplayInterface result = new Results(args[3]);

            AvailableItems availableItems = new AvailableItems(Integer.parseInt(args[2]));
            AvailableItemProcessor.run(fileProcessorAvailableItemFile, availableItems);
            fileProcessorAvailableItemFile.close();

            ContextI spendingPotentialContext = new SpendingPotentialContext(result, availableItems);

            InputProcessor.run(fileProcessorInputFile, spendingPotentialContext);
            fileProcessorInputFile.close();

            result.resourceCloser();
        }catch(IOException exception){
            exception.printStackTrace();
            System.exit(0);
        }
        catch (IncorrectFileFormatException e){
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
     * @param must be of type FileProcessor
     * @return void
     */
    public static void run(FileProcessor fileProcessorIn,ContextI spendingPotentialContextIn) {
        try {
            String itemormoneystr = "";
            while ((itemormoneystr = fileProcessorIn.poll()) != null) {
                if(fileProcessorIn.checkItemOrMonneypattern(itemormoneystr))
                    spendingPotentialContextIn.yesOrNoItem(itemormoneystr);
                else
                    throw new IncorrectFileFormatException("Please format the input file  properlly error at item "+ itemormoneystr);
            }
        } catch (IncorrectFileFormatException | IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

} /*
     AvailableItemProcessor class is used to process the input file
     */

final class AvailableItemProcessor {
    /**
     * process the availableItemFIle.txt
     *
     * @param must be of type FileProcessor
     * @return void
     */
    public static void run(FileProcessor fileProcessorIn, AvailableItems availableItemsProcessorIn) {
        try {
            String itemstr = "";
            while ((itemstr = fileProcessorIn.poll()) != null) {
                if(fileProcessorIn.checkAvailablepattern(itemstr))
                    availableItemsProcessorIn.addavailableItems(itemstr);
                else
                    throw new IncorrectFileFormatException("Please format the AvailableItems File properlly error at item "+ itemstr);
            }
        } catch (IncorrectFileFormatException | IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}

