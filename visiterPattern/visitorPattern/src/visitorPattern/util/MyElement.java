package visitorPattern.util;

import visitorPattern.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class MyElement {

    private List<String> element;

    /**
     * Constructor to Myelement
     */
    public MyElement() {
        element = new ArrayList<String>();
    }

    /**
     * used to add a element to list
     * @param input
     */
    public void add(String input) {
        element.add(input);
    }

    /**
     * returns element list
     * @return list
     */
    public List<String> getElement() {
        return element;
    }
    @Override
    public String toString() {
        return String.format("MyElement[element: %s", element);
    }
}
