# CSX42: Assignment 1
## Name: Darshan Doddaghatta

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in wordPlay/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile wordPlay/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile wordPlay/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile wordPlay/src/build.xml run -Darg0="input.txt" -Darg1="output.txt" -Darg2="metrics.txt"

Note: Arguments accept the absolute path of the files.


-----------------------------------------------------------------------
## Description:
FileProcessor class is used to read and process the inputfile.
SentenceCalculator class is used to calculate the metrix parameters.
Result class is used to write the reverse content and metrix data to the output files and metrix file respectivelly.
CmdChecker class is used validate input cmd line aruguments.
For reading the file each char was read and checked for valid input ,on error an valid exception is thrown.
Each sentence are processed a one time and is written to the output file.
For frequent world caculation used hasp map data structure and found the highest key value through iteration.

## References:
https://stackabuse.com/reading-and-writing-files-in-java/

-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: [02-06-2020]


