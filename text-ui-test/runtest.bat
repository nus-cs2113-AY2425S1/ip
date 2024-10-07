@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac -cp C:\Users\Lucas\Desktop\CS2113\ip\src\main\java -Xlint:none -d ..\bin ..\src\main\java\command\*.java ..\src\main\java\exception\*.java ..\src\main\java\monday\*.java ..\src\main\java\parser\*.java ..\src\main\java\storage\*.java ..\src\main\java\tasklist\*.java ..\src\main\java\tasktypes\*.java ..\src\main\java\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ..\bin Monday < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
