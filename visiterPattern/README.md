# csx42-spring-2020-assign5-darshanVishnu
# CSX42: Assignment 5
## Name: Darshan Doddaghatta

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in visitorPattern/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command:  ant -buildfile visitorPattern/src/build.xml  clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile spendingPotential/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:


####Command:
```
ant -buildfile visitorPattern/src/build.xml run -Dinput="./input.txt" -DacceptableWordsFile="./acceptable_words.txt" -Dk=2 -DtopKOutputFile="./topk_output.txt" -DspellCheckOutputFile="spellcheck_output.txt"
 ```
Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
FileProcessor class is used to read and process the inputfile.
CmdChecker class is used validate input cmd line aruguments.
SpellCheckResults class to used to store and write the spell check result
TopKFreqResults class is used to store and write the Topk  result
SpellCheckAnalyzer and  TopKMostFreqAnalyzer are one vistor class
input file and acceptable are processed case insensative 


-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [03-04-2020]

### Slack days
2 days
