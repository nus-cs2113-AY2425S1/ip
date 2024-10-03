@ECHO OFF

REM Create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM Delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM Compile the code into the bin folder
javac -cp "..\lib\gson-2.11.0.jar;..\src\main\java" -Xlint:none -d ..\bin ..\src\main\java\*.java ..\src\main\java\wildpeace\Storage\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM Run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath "..\bin;..\lib\gson-2.11.0.jar" Wildpeace < input.txt > ACTUAL.TXT

REM Compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
