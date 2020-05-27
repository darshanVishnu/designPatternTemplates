//package persisterServiceClient;

import java.util.ArrayList;

public class Results {
    public ArrayList<Integer> listResult = null;

    public Results() {
        listResult = new ArrayList<Integer>();
        MyLogger.writeMessage("Constructor called - " + this.toString(), MyLogger.DebugLevel.CONSTRUCTOR);
    }

    public void addDataToList(Integer numberIn) {
        MyLogger.writeMessage("DATA_STRUCTURE_ENTRY is called " + this.toString(), MyLogger.DebugLevel.DATA_STRUCTURE_ENTRY);
        listResult.add(numberIn);
        System.out.println(numberIn + " is  added");
    }

    public void sumListResults() {
        Integer sum = 0;
        MyLogger.writeMessage("DATA_STRUCTURE_SUM is called " + this.toString(), MyLogger.DebugLevel.DATA_STRUCTURE_SUM);
        for (Integer result : listResult) {
            sum = sum +result;
            MyLogger.writeMessage("DATA_STRUCTURE_CONTENT is called - " + this.toString(), MyLogger.DebugLevel.DATA_STRUCTURE_CONTENT);

        }
        System.out.println(sum);
    }


}
