@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java\nell -Xlint:none -d ..\bin ..\src\main\java\nell\*.java ..\src\main\java\nell\tasks\*.java^
 ..\src\main\java\nell\storage\*.java ..\src\main\java\nell\common\*.java ..\src\main\java\nell\parser\*.java ^
 ..\src\main\java\nell\ui\*.java ..\src\main\java\nell\command\*.java ..\src\main\java\nell\list\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin nell.Nell < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
