@ECHO OFF

REM compile the code into the class-files folder
javac -cp ..\src\main\java -Xlint:none -d .\ ..\src\main\java\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM make the jar file
jar cmvf META-INF\MANIFEST.MF Main.jar *.class