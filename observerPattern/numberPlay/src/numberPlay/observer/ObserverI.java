package observer;

/**
 * ObserverI is an interface used to maintain observers
 */
public interface ObserverI
{
     /**
      *
      * @param message must be string
      */
     void processComplete(String message);

     /**
      *
      * @param intergerNumber must be a string
      */
     void numberPassed(Number intergerNumber);


}


