@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\erika\*.java ..\src\main\java\erika\task\*.java ..\src\main\java\erika\exception\*.java ..\src\main\java\erika\command\*.java ..\src\main\java\erika\command\addcommand\*.java ..\src\main\java\erika\console\*.java ..\src\main\java\erika\filesystem\*.java ..\src\main\java\erika\parser\*.java ..\src\main\java\erika\settings\*.java ..\src\main\java\erika\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin erika.Erika < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
