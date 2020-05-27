package visitorPattern.util;

import java.util.List;

public class MyArrayListIterator implements Iterator {

    private List<String> myArrayList;
    private Integer count = 0;

    public MyArrayListIterator(List<String> myArrayListIn) {
        myArrayList = myArrayListIn;
        count = 0;
    }

    /**
     * used to check the next object
     * @return boolean
     */
    @Override
    public boolean hasNext() {
        if (count < myArrayList.size() && myArrayList.get(count) !=null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * used to get the next object
     * @return Object
     */
    @Override
    public Object next() {
        if (hasNext()) {
            return myArrayList.get(count++);
        } else
            return null;
    }
    @Override
    public String toString() {
        return String.format("myArrayListIterator[myArrayList: %s", myArrayList);
    }
}
