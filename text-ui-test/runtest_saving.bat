@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT
if exist savedata.txt del savedata.txt

REM compile the code into the bin folder
javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\cuboyd\Cuboyd.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/1_todo.txt     > ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/2_deadline.txt >> ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/3_event.txt    >> ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/4_mark.txt     >> ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/5_unmark.txt   >> ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/6_delete.txt   >> ACTUAL.TXT
java -classpath ..\bin\ cuboyd.Cuboyd < input_saving/end.txt        >> ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED_SAVING.TXT

REM delete savedata from new run
if exist savedata.txt del savedata.txt

REM delete compilation outputs
rmdir /q /s ..\bin