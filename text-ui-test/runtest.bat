@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp .\src\main\java -Xlint:none -d ..\bin .\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM Check if compilation succeeded
echo Compilation succeeded

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Sirius < .\text-ui-test\input.txt > .\text-ui-test\ACTUAL.TXT

REM Remove trailing blank lines from ACTUAL.TXT
powershell -Command "Get-Content .\text-ui-test\ACTUAL.TXT | Where-Object { $_.Trim() -ne '' } | Set-Content .\text-ui-test\ACTUAL_NO_TRAILING_BLANKS.TXT"
powershell -Command "Get-Content .\text-ui-test\EXPECTED.txt | Where-Object { $_.Trim() -ne '' } | Set-Content .\text-ui-test\EXPECTED_NO_TRAILING_BLANKS.TXT"

REM Check if ACTUAL.TXT was created
if exist ACTUAL.TXT (
    echo ACTUAL.TXT created successfully
) else (
    echo Failed to create ACTUAL.TXT
)

REM compare the output to the expected output
FC .\text-ui-test\ACTUAL_NO_TRAILING_BLANKS.TXT .\text-ui-test\EXPECTED_NO_TRAILING_BLANKS.TXT
IF ERRORLEVEL 1 (
    echo Test result: FAILED
) ELSE (
    echo Test result: PASSED
)


REM Clean up temporary files
del .\text-ui-test\ACTUAL_NO_TRAILING_BLANKS.TXT
del .\text-ui-test\EXPECTED_NO_TRAILING_BLANKS.TXT
