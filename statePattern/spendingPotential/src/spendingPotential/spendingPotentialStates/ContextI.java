package spendingPotential.spendingPotentialStates;

public interface ContextI {
    public  void  yesOrNoItem(String itemOrMoney);
    public void setState(StateI currentState);
}