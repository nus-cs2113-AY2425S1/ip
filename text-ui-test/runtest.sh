#!/usr/bin/env bash

# Create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# Delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# Compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/AlyBot/Aly.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# Run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -cp ../bin Aly < input.txt > ACTUAL.TXT

# Convert to UNIX format (if needed)
if command -v dos2unix > /dev/null; then
    dos2unix ACTUAL.TXT EXPECTED.TXT
else
    echo "dos2unix command not found, skipping line ending conversion."
fi

# Compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
