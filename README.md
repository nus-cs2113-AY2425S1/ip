# AnBot User Guide

This is the User Guide for AnBot, a basic task management chatbot that allows you to track your todos, deadlines and events

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from AnBot 
   ```
## Basic commands for the chatbot

### $List$ 

Function: List all the tasks in the storage file

Format: $list$ 

Example: $list$ 

### $Todo$ 

Function: Add a todo task

Format: $todo DESCRIPTION$ 

Example: $todo read books$ 

### $Deadline$ 

Function: Add a deadline task

Format: $deadline DESCRIPTION /by DUE TIME$ 

Example: $deadline return books /by 6pm$ 

### $Event$ 

Function: Add an event task

Format: $event DESCRIPTION /from START TIME /to END TIME$ 

Example: $event book fair /from 6pm /to /8pm$ 

### $Mark$ 

Function: Mark the completion status of the i-th task

Format: $mark INDEX$ 

Example: $mark 1$

### $Unmark$ 

Function: Unmark the completion status of the i-th task

Format: $unmark INDEX$ 

Example: $unmark 1$

### $Delete$ 

Function: Delete the i-th task

Format: $delete INDEX$ 

Example: $delete 1$

### $Find$ 

Function: Find all the tasks containing the specified keyword 

Format: $find KEYWORD$ 

Example: $find BOOKS$

## Acknowledgement

This code is created in the CS2113 NUS course, with the assistance of ChatGPT in fixing code bugs and templates from the coure's Coursemology exercises. 
