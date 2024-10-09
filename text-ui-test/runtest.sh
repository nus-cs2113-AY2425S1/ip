#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ];
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ];
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/grok/*.java ../src/main/java/grok/tasks/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin grok.Grok < input.txt > ACTUAL.TXT

# ensure there's a newline at the end of ACTUAL.TXT
echo "" >> ACTUAL.TXT

# compare the output to the expected output
diff -q ACTUAL.TXT EXPECTED.TXT

# check if the diff command encountered differences
if [ $? -eq 0 ]; then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
