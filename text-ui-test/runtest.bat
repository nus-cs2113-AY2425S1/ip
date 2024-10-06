@ECHO OFF

REM delete the data folder and its contents if it exists
if exist data (
    rmdir /S /Q data
)

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder, adjusting for package structure
javac -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\aether\*.java ..\src\main\java\aether\task\*.java ..\src\main\java\aether\ui\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, adjust the classpath to include the aether package and run the Aether class
java -classpath ..\bin aether.Aether < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT
IF ERRORLEVEL 1 (
    echo ********** TEST FAILURE **********
    pause
    exit /b 1
) ELSE (
    echo Test passed!
)

REM pause to prevent the window from closing immediately
pause
