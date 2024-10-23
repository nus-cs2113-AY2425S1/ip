@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac -d "..\bin" ..\src\main\java\app\*.java ..\src\main\java\exceptions\*.java ..\src\main\java\taskmanager\*.java ..\src\main\java\tasks\*.java

IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)

REM no error here, errorlevel == 0

REM sets classpath to bin folder
set CLASSPATH=..\bin

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin app.Terri < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM Check the error level to determine if differences were found
IF ERRORLEVEL 1 (
    echo Differences found between ACTUAL.TXT and EXPECTED.TXT.
) ELSE (
    echo No differences found. Output matches.
)

pause
