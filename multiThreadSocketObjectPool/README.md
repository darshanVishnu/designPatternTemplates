# CSX42: Assignment 3 
## Team Members: 
Darshan Doddaghatta 
<br>Srinidhi Raghavendra
-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in primeDetector/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile primeDetector/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile primeDetector/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.
1.First the run the persisterService and then the primeDetector.
```commandline

ant -buildfile primeDetector/src/build.xml  persisterService-run -Dport="<port number>"  -DoutputFile="<path of the output file>"

ant -buildfile primeDetector/src/build.xml  primeDetector-run -DinputFile="<path of inputfile>" -DnumThreads=5 -Dcapacity=5 -DpersistorServiceIp=<Enter persisterService Ip> -DpersistorServicePort=<Enter persisterService port> -DdebugValue=0

```

-----------------------------------------------------------------------
## Description:
Justification for the Data Structures used

1.To Store Prime Numbers in Result Class.

Abstract type: Queue
<br>Concrete type: LinkedList(because LinkList has non blocking put and get method since we use wait() and noitfyAll() to synchronize we only need non  blocking implementation of the Queue ) 

2.Thread Pool Management.
<br>
ArrayList is used to for dynamic storage of Thread.

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [03/31/2020]

-----------------------------------------------------------------------
### Slack Days Used:
-----------------------------------------------------------------------

2 Days
