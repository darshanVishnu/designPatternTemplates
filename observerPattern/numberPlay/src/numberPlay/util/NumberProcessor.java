package numberPlay.util;
import observer.ObserverI;
import subject.SubjectI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import numberPlay.filter.*;
/*
NumberProcessor is  subject which is used to trigger events to observers
 */
public class NumberProcessor implements SubjectI {

    private Map<FilterI, List<ObserverI>> observers;

    /**
     *Constructor is used create a hasp map of one to many filter to observer relation ship
     */
    public NumberProcessor() {
        observers = new HashMap<FilterI, List<ObserverI>>();
    }

    /**
     *used to notify observers based on the filter check and enum of the filter
     * @param numberIn type String
     * @throws Exception when numberIn is not of type int or float
     */
    @Override
    public void notifyEvent(String numberIn) throws Exception {
        for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
            if (entry.getKey().eventDecider(numberIn)) {
                if (entry.getKey().getDecision() == FilterI.Decision.INTEGER) {
                    for (ObserverI o : entry.getValue()) {
                        o.numberPassed(Integer.parseInt(numberIn));
                    }
                }
                if (entry.getKey().getDecision() == FilterI.Decision.FLOAT) {
                    for (ObserverI o : entry.getValue()) {
                        o.numberPassed(Float.parseFloat(numberIn));
                    }
                }
            }
        }
    }

    /**
     *it is used to register each of the observers under a filter
     * @param o must a observer
     * @param f must be filter
     */
    @Override
    public void register(ObserverI o, FilterI f) {
        if (!observers.containsKey(f)) {
            observers.put(f, new ArrayList<ObserverI>());
        }
        observers.get(f).add(o);
    }

    /**
     *used to notify all obervers
     * @param numberIn type string
     */
    @Override
    public void notifyAll(String numberIn) {
        for (Map.Entry<FilterI, List<ObserverI>> entry : observers.entrySet()) {
            if (entry.getKey().getDecision() == FilterI.Decision.PROCESSINGCOMPLETE) {
                for (ObserverI o : entry.getValue()) {
                    o.processComplete(numberIn);
                }
            }
        }
    }

    /**
     *
     * @return string it returns the class details
     */
    @Override
    public String toString() {
        return String.format("NumberProcessor[observers: %s",observers);
    }
}
