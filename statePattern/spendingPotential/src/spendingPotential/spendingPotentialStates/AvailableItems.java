package spendingPotential.spendingPotentialStates;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class AvailableItems {
    /**
     * Used to store the set of abvaliable items
     */
    private Map<String, String> availableItems;
    private Queue<Integer> runningAverageDataq = new LinkedList<>();
    private Integer windowSize;

    public AvailableItems(Integer windowSizeIn) {
        windowSize = windowSizeIn;
        availableItems = new HashMap<>();
    }

    /**
     * puts the items to the haspmap in the order item ,categories
     * @param addavailableItemsIn
     */
    public void addavailableItems(String addavailableItemsIn) {
        String[] addavailableItems = addavailableItemsIn.split(":");
        availableItems.put(addavailableItems[1], addavailableItems[0]);
    }

    /**
     * get the avaliable items from the map
     * @param avaItem
     * @return
     */
    public String getavailableItems(String avaItem){
        return availableItems.get(avaItem);
    }

    /**
     * used to caculate the ruuning average
     * @param input must be a interger
     * @return eunning of the data queue average
     */
    public int runningAverageCalculator(Integer input) {
        Integer average = 0;
        if (runningAverageDataq.size() < windowSize) {
            runningAverageDataq.add(input);
        } else if (runningAverageDataq.size() == windowSize) {
            runningAverageDataq.remove();
            runningAverageDataq.add(input);
        }
        for (Integer item : runningAverageDataq) {
            average = average + item;
        }
        return average / runningAverageDataq.size();
    }
    @Override
    public String toString() {
        return String.format("AvailableItems[availableItems: %s, windowSize: %s ,runningAverageDataq %s]", availableItems, windowSize,runningAverageDataq);
    }


}
