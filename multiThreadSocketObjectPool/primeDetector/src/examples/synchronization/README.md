This example code illustrates:
wait(), notify() and notifyAll() methods useful for synchronizing threads.

NOTES:
1. While synchronising with wait(), notify() and notifyAll() to synchronize threads, make sure there is no IllegalMonitorStateException.
2. IllegalMonitorStateException means an object which has no permission to call wait/notify/notifyAll on another object(i.e; on an object which it doesn't own) tries to wait/notify/notifyAll on it.
3. A thread becomes the owner of an object’s monitor in one of the following ways: (from https://examples.javacodegeeks.com/java-basics/exceptions/java-lang-illegalmonitorstateexception-how-to-solve-illegalmonitorstateexception/)
    By executing a synchronized instance method of that object.
    By executing the body of a synchronized statement that synchronizes on the object.
    For objects of type Class, by executing a synchronized static method of that class.

Commands:
Compile: javac Driver.java Consumer.java Producer.java
Run: java Driver
