package spendingPotential.spendingPotentialStates;
public class Luxurious implements StateI {
    private SpendingPotentialContext spendingPotentialContext;

    public Luxurious(SpendingPotentialContext spendingPotentialContextIn) {
        spendingPotentialContext = spendingPotentialContextIn;
    }
    private final String state = "LUXURIOUS";
    private final String stateCategories = "superExpensive";

    /**
     * used to agrre or dis agree items and write to result
     * @param itemorMoney String
     */
    @Override
    public void yesOrNoItem(String itemorMoney) {
        String agrees = "NO";
        String[] itemList = itemorMoney.split(":");
        if (itemList[0].equals("money")) {
            currentStatePredictor(spendingPotentialContext.availableItems.runningAverageCalculator(Integer.parseInt(itemList[1])));
        } else if (itemList[0].equals("item")) {
            if(!spendingPotentialContext.availableItems.getavailableItems(itemList[1]).equals(stateCategories)) {
                agrees = "YES";
            }
            spendingPotentialContext.results.writeSingleLine(state + "::" + itemList[1] + "--" + agrees);
        }
    }

    /**
     * Used to set the the current state
     * @param currentAverage
     */
    public void currentStatePredictor(Integer currentAverage) {
        if (currentAverage < 10000 && currentAverage > 0) {
            spendingPotentialContext.setState(spendingPotentialContext.getBasicState());
        } else if (currentAverage < 50000 && currentAverage >= 10000) {
            spendingPotentialContext.setState(spendingPotentialContext.getLuxuriousState());
        } else if (currentAverage >= 50000) {
            spendingPotentialContext.setState(spendingPotentialContext.getExtravagantState());
        }
    }
}