#!/usr/bin/env bash

LOGFILE="test_log.txt"

# Create or clear the log file
echo "Running tests..." > "$LOGFILE"

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java -Xlint:none -d ../bin ../src/main/java/task/*.java ../src/main/java/exception/*.java ../src/main/java/datahandling/*.java ../src/main/java/JerChatBot.java >> "$LOGFILE" 2>&1
then
    echo "********** BUILD FAILURE **********" | tee -a "$LOGFILE"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ../bin < input.txt > ACTUAL.TXT 2>> "$LOGFILE"

# convert to UNIX format
cp EXPECTED.TXT EXPECTED-UNIX.TXT
dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT >> "$LOGFILE" 2>&1

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED-UNIX.TXT >> "$LOGFILE" 2>&1
if [ $? -eq 0 ]
then
    echo "Test result: PASSED" | tee -a "$LOGFILE"
    exit 0
else
    echo "Test result: FAILED" | tee -a "$LOGFILE"
    exit 1
fi
