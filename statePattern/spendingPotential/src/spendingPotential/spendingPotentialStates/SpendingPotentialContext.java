package spendingPotential.spendingPotentialStates;

import spendingPotential.util.FileDisplayInterface;

public class SpendingPotentialContext implements ContextI{
    private StateI basic;
    private StateI extravagant;
    private StateI luxurious;
    private StateI state;
    public FileDisplayInterface results;
    public AvailableItems availableItems;
    public SpendingPotentialContext(FileDisplayInterface resultsIn,AvailableItems availableItemsIn){
        basic=new Basic(this);
        extravagant=new Extravagant(this);
        luxurious=new Luxurious(this);
        setState(basic);
        results=resultsIn;
        availableItems=availableItemsIn;
    }

    /**
     * setter to set the current state
     * @param currentState
     */
    public void setState(StateI currentState) {
        state = currentState;
    }

    /**
     * Used agree and disagree a purchase of items based on context state
     * @param itemorMoney
     */
    public void yesOrNoItem(String itemorMoney){
        state.yesOrNoItem(itemorMoney);
    }

    /**
     * getter to return basic state
     * @return basic state
     */
    public StateI getBasicState(){
        return basic;
    }

    /**
     * getter to return extravagant state
     * @return extravagant state
     */
    public StateI getExtravagantState(){
        return extravagant;
    }

    /**
     * getter to return luxurious state
     * @return luxurious state
     */
    public StateI getLuxuriousState(){
        return luxurious;
    }
    @Override
    public String toString() {
        return String.format("SpendingPotentialContext[availableItems: %s, state: %s]", availableItems,state);
    }

}