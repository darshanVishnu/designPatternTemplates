# CSX42: Assignment 4
## Name: Darshan Doddaghatta

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in spendingPotential/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile spendingPotential/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile spendingPotential/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile spendingPotential/src/build.xml run -DinputFile="input.txt" -DavailableItemsFile="availableItemFile.txt" -DrunningAverageWindowSize="2" -DoutputFile="output.txt"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
FileProcessor class is used to read and process the inputfile.
CmdChecker class is used validate input cmd line aruguments.
queue dataStructure used for calculating for running average.
ContextI class is a context Inferface.
StateI class is a state Inferface.
SpendingPotentialContext class is a which implements Context and holds contexts for state.
AvaliableItems Class is to maintain availableItems.Used hasmap datastructure to store  Avaliable items  .



-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [20-04-2020]


