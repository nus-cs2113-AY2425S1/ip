@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Main < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

REM Check the result of the comparison
IF ERRORLEVEL 1 (
    echo ********** TEST FAILED **********
    echo Updating EXPECTED.TXT with the new ACTUAL.TXT content.
    echo.

    REM Replace EXPECTED.TXT with ACTUAL.TXT
    copy /y ACTUAL.TXT EXPECTED.TXT

    echo Please review the changes if necessary.
    exit /b 1
) ELSE (
    echo ********** TEST PASSED **********
)
