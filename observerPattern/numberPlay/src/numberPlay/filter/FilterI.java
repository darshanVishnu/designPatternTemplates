package numberPlay.filter;

public interface FilterI {
    enum Decision {
        INTEGER, FLOAT, PROCESSINGCOMPLETE
    }
    boolean eventDecider(String numberIn) throws Exception;

    Decision getDecision();
}
