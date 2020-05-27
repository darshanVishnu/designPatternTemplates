package visitorPattern.util;

import visitorPattern.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class MyArrayList implements Element{
    /**
     * Myelement is used to store the element
     */
    private static MyElement myElement;

    private MyArrayList(){
        myElement=new MyElement();
    }
/**
 * Used to call the visitor method
 */
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

/**
 * used to get iterator 
 * @return Iterartor
 */

    public Iterator getIterator() {
    return new MyArrayListIterator(myElement.getElement());
    }



    public static class Builder {
        FileProcessor fileProcessor;
        /**
         * Used to hold the reference of the file processor
         * @param fileProcessorIn
         * @return
         */
        public Builder withFileProcessor(FileProcessor fileProcessorIn) {
            Builder builder=new Builder();
            builder.fileProcessor=fileProcessorIn;
            return builder;
        }
        /**
         * Used to process the file into elements
         * @return
         */
        public Element build() {
            String input="";
            Element myArrayList=new MyArrayList();
               try {
                   while((input= fileProcessor.readEachSentence()) !=""){
                       myElement.add(input.toLowerCase());
                   }
           }
           catch (Exception ex){
               ex.printStackTrace();
           }
            return myArrayList;
        }
    }
}
