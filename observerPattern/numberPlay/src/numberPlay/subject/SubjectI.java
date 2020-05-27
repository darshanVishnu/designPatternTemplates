package subject;
import observer.ObserverI;
import numberPlay.filter.FilterI;


/**
 * SubjectI is an interface used to maintain Subject
 */
public interface SubjectI {

     /**
      * notifyEvent used to notify only filtered the obserservs
      * @param numberIn must be of type string
      * @throws Exception if filter is not present
      */
     void notifyEvent(String number) throws Exception;

     /**
      * notifyAll used to notify all the obserservs
      * @param numberIn must be of type string
      * @throws Exception
      */
     void notifyAll(String numberIn)throws Exception;

     /**
      * register used to register the observer under a filter
      * @param o must be of type ObseverI
      * @param f must be of type FilterI
      */
     void register(ObserverI o, FilterI f);

}