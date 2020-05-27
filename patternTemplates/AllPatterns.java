//state
public class AllPatterns {

    public static void main(String[] args) {
        ContextI connectionContext = new ConnectionContext(result, availableItems);
    }

}

public interface StateI {
    public void switchTurn();

}

public interface ContextI {
    public void setState(StateI currentState);

    public void switchTurn(String itemOrMoney);

}

class On implements StateI {
    public void switchTurn() {
    }
}

class Off implements StateI {
    public void switchTurn() {
        currentStatePredictor();
    };

    currentStatePredictor(){
        this.setState(getOffState);
    };
}

public class ConnectionContext implements ContextI {
    private StateI on;
    private StateI Off;
    public AvailableItems availableItems;

    public ConnectionContext() {
        on = new On(this);
        off = new Off(this);
    }

    public void setState(StateI currentState) {
        state = currentState;
    }

    public void switchTurn() {
        state.switchTurn(itemorMoney);
    };

    public StateI getOnState() {
        return On;
    }

}

// strategy

public interface CheckStrategyI {

    public boolean check(String s);

}

public class SortAlgorithm implements CheckStrategyI {

    public boolean check(String s){
    
    if(s.equals(”Selection”))
    
    return true;
    
    else
    
    return false;
    
    }

}

// Driver code

public class Driver {

    List<Integer> alist = new ArrayList<Integer>();

    String useAlgorithm =”Selection”;

    public String getuseAlgorithm() {

        return useAlgorithm;

    }

    void selectionSort(List<Integer> alsist) {

        // use selestion sort to sort number

    }

    void mergeSort(List<Integer> alsist) {

        // use mergeSort sort to sort number

    }

    public void sortNumber(List<Integer> listNumber, CheckStrategy strategy) {

        while ((listNumber) != null) {

            String newWord = getuseAlgorithm();

            if (strategy.check(newWord)) {

                SelectionSort(listNumber);

            }

            else {

                mergeSort(listNumber);

            }

        }

    }

    public static void main(String[] args) {

        CheckStrategy designPrefix = new SortAlgorithm();

        sortNumber(alist, designPrefix);

    }

}

//Visitor pattern

public interface VisitorI {

    public void visit();

}

public class Optimization implements VisitorI {

    public void visit(AST ast) {

        // method implementation

    }
}

public class AST {

    public AST() {
    };

    public void accept(Visitor visitor) {

        visitor.visit(this);

    }

}

public class Driver {

    public static void main(String[] args) {
        // create the

        VisitorI constfoldingOptimization = new Optimization();

        AST ast = new AST();

        // visit the elements
        for (Visitor visitor : visitors) {
            AST.accept(visitor);
        }

    }
}

// Decorator

public abstract class Beverage {
    protected String description = “Placeholder”;

    public String getDescription() {
        return description;
    }

    public abstract double cost();
}

public class Expresso extends Beverage {
    public Expresso() {
		description = “Expresso”;
	}

    public double cost() {
        return 2.19;
    }
}

public class DarkRoast extends Beverage {
    public DarkRoast() {
		description = “Expresso”;
	}

    public double cost() {
        return 2.40;
    }
}

public abstract class CondimentDecorator extends Beverage {
    public abstract String getDescription();
}

public class Mocha extends CondimentDecorator {
    // note the superclass type is used here to delegate
    // and implement the wrapper/decorator
    private Beverage beverage;

    // add constructor

    public String getDescription() {
        // obtain description of object being wrapped
        // add own description
    }

    public double cost() {
        // obtain cost of object being wrapped
        // add own cost
    }
}

public class Mocha extends CondimentDecorator {
    // note the type used here to delegate
    // and implement the wrapper/decorator
    Beverage beverage;

    public Mocha(Beverage beverageIn) {
        beverage = beverageIn;
    }

   public String getDescription() {
       return beverage.getDescription() + “ Mocha”;
   }

    public double cost() {
        return .50 + beverage.cost();
    }}

    Beverage bev = new DarkRoast();
    // Now add a Mocha decorator
    // Add another Mocha decorator
    // Add a Whip decorator
    ;bev=new Mocha(bev);bev=new Mocha(bev);bev=new Whip(bev);




    // Cor

    class Chain {
        DuesProcessor chain;

        public Chain() {
            buildChain();
        }

        private void buildChain() {
            chain = new LibraryDues(new LabDues(new AcountDeptDues(null)));
        }

        public void process(Student request) {
            chain.process(request);
        }
    }

    abstract class DuesProcessor {
        private DuesProcessor processorDues;

        public DuesProcessor(DuesProcessor processor) {
            this.processorDues = processor;
        };

        public void process(Student request) {
            if (processorDues != null)
                processorDues.process(request);
        };
    }

    class Student {
        private String bunumber;

        public Student(String bunumberIn) {
            bunumber = bunumberIn;
        }

        public String getBuNumber() {
            return bunumber;
        }
    }

    class LibraryDues extends DuesProcessor {
        public LibraryDues(DuesProcessor processor) {
            super(processor);
        }

        public void process(Student request) {
            System.out.println("Library cleared of all dues " + request.getBuNumber());
            super.process(request);
        }
    }

    class LabDues extends DuesProcessor {
        public LabDues(DuesProcessor processor) {
            super(processor);
        }

        public void process(Student request) {
            System.out.println("LabDues cleared of all dues " + request.getBuNumber());
            super.process(request);
        }
    }

    class AcountDeptDues extends DuesProcessor {
        public AcountDeptDues(DuesProcessor processor) {
            super(processor);
        }

        public void process(Student request) {
            System.out.println("AcountDeptDues cleared of all dues " + request.getBuNumber());
            super.process(request);
        }
    }

    class Main {
        public static void main(String[] args) {
            Chain chain = new Chain();
            chain.process(new Student("B00815064"));
        }
}

// Refelection

String clsName = "StudentRecord";

String methodName ="getGPAforMinor";

String minorName="Jhon";

Class clsStudentRecord = Class.forName(clsName);

Class[] signature = new Class[1];

signature[0]=String.class;

Method methgetGPAforMinor = clsStudentRecord.getMethod(methodName, signature);

Object obj = clsStudentRecord.newInstance();

Object[] params = new Object[1];

params[0]=new String(minorName);

System.out.println(methgetGPAforMinor.invoke(obj,params));

double result = (double) methgetGPAforMinor.invoke(obj, params);

String methodName2 =”isDesignEnthusiat”;

Object[] params2 = new Object[1];

Class[] signature1 = new Class[1];

Method methDesignEnthusiat = clsStudentRecord.getMethod(methodName2, signature1);

System.out.println(methDesignEnthusiat.invoke(obj,params2));

boolean ans=(boolean) methDesignEnthusiat .invoke(obj, params2);


Class chatMessageClass= objectInstance.getClass();
  Field[] fieldList =  chatMessageClass.getFields();
  for (int j=0; j<fieldList.length; j++) {
                Class fieldClass = fieldList[j].getType();
                String fieldName = fieldList[j].getName();
                Object fieldObject =
				 fieldList[j].get(objectInstance);
}


String className = "TestObject"
String methodName="getX"

Class clsTestObject = Class.forName(className);


Class[] signature = new Class[1];

signature[0]=String.class;

Method methGetX = clsStudentRecord.getMethod(methodName, signature);

Object obj = clsStudentRecord.newInstance();

Object[] params = new Object[1];

params[0]=new String(minorName);

System.out.println(methGetX.invoke(obj,params));

String result = (String) methGetX.invoke(obj, params);



//Command pattern
public interface Command {
	public void execute();
}


public class PdfCommand  implements Command {
	private Doc doc;
	public PdfCommand(Doc docIn) {
		doc = docIn;
	}
	// execute calls specific methods on 
    //  the receiving object
	public void execute() {
		doc.ConvertToPdf();
   }
}

public class SimpleRemoteControl {
	private Command slot;
	public SimpleRemoteControl() {..}
	public void setCommand(Command commandIn) {
		slot = commandIn;
	}
	// Note how the remote control is decoupled from
	// the object that performs the action. Execute is 
	// called for all the devices that are contoled
	public void buttonWasPressed() {
		slot.execute();
	}
}

public class RemoteControlTest {
    public static void main(String[] args){
        SimpleRemoteControl remote =new SimpleRemoteControl();
        Doc doc = new Doc();
        PdfCommand pdfCommand = new PdfCommand(doc);
        remote.setCommand(pdfCommand);
        remote.buttonWasPressed();
    }
}