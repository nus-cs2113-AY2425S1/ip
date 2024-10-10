@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\ryan -Xlint:none -d ..\bin ..\src\main\java\ryan\*.java ..\src\main\java\ryan\commands\*.java ..\src\main\java\ryan\exceptions\*.java ..\src\main\java\ryan\tasks\*.java ..\src\main\java\ryan\utility\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed ryan.commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin ryan.Ryan < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
