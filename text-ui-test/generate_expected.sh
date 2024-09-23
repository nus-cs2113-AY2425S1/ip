#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./EXPECTED.TXT" ]
then
    rm EXPECTED.TXT
fi

# delete data folder from previous run
if [ -e "./data" ]
then
	rm -rf data
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/*.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# generate EXPECTED.TXT
java -classpath ../bin Main < input.txt > EXPECTED.TXT

# exit program
echo "EXPECTED.TXT generated"
exit 0

