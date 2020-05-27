# CSX42: Assignment 2
## Name: 
Darshan Doddaghatta
-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in numberPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

```commandline
ant -buildfile numberPlay/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

```commandline
ant -buildfile numberPlay/src/build.xml all
```

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

#### Use the below command to run the program.

```commandline
ant run -buildfile numberPlay/src/build.xml \
-DinputNumStream="<input file path>" \
-DrunAvgWindowSize="<size of the window for running average calculations>" \
-DrunAvgOutFile="<output file path to which running averages are written>" \
-Dk="<max size of the list containing the top K numbers>" \
-DtopKNumOutFile="<path of output file to which the top K numbers are written>" \
-DnumPeaksOutFile="<path of output file to which the peaks in the number stream are written>"
```

-----------------------------------------------------------------------
## Description:
FileProcessor class is used to read and process the inputfile.

NumberPeaksData class is used to calculate the peak values. 

RunningAverageData  class is used to calculate Running Average.

TopKNumbersData class is used to calculate Top K values. 

IntEventFilter class is used as filter to alert all the obsevers when numberprocessed is int.

FloatEventFilter class is used as filter to alert all the obsevers when number processed is float.

ProcessingEventFilter class is used as filter to alert all the obsevers to trigger process complete event.

NumberProcessor class is  subject which is used to trigger events to observers and register all the observers.

CmdChecker class is used validate input cmd line aruguments. 

There are three observers NumberPeaksObserver,RunnningAverageObserver,TopKNumbersObserver.
NumberPeaksObserver class is used to maintain Observers of number peak.

RunnningAverageObserver class is used to maintain Observers of runningAverageData.

TopKNumbersObserver class is used to maintain Observers of top k number.

For reading the file each input number was read and checked for valid input ,on error an valid exception is thrown. Each number are processed at one time checked for a int or float and based on the filter check the observers are alerted.
One to many relation is maintained between filter and observers


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [02/23/2020]


