This example code illustrates:
1. Creation of a thread pool.
2. Borrowing threads from the pool.
3. Returning threads to the pool.

NOTE: Since threads dont behave like other objects, a thread that is run once and terminated cannot be returned to the pool to be reused. Hence based on the description given in the assignment to be able to start and join on threads from the thread pool, we need to create new threads as and when used threads are returned to the pool to give the user an experience of an object pool. A thread pool can be implemented in the correct manner by making the thread long running and passing the runnable task to be run to the pool and the pooluses a queue to schedule those tasks.
Compile: javac Driver.java MyThreadPool.java WorkerThread.java ThreadPoolInterface.java
Run: java Driver
