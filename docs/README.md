# AnBot User Guide

```
         ___________________________
         Hello! Welcome to AnBot
         What can I do for you? 
         ___________________________
```
AnBot, a basic task management chatbot that allows you to track your todos, deadlines and events. 

## How to run the jar file 

Step 1: Download the `.jar` file from the lastest release

Step 2: Put the `.jar` file into an empty folder. 

Step 3: Open a command window in that folder.

Step 4: Run the command java -jar {filename}.jar (i.e., run the command in the same folder as the jar file). 

## Basic commands for the chatbot

### `List` 

Function: List all the tasks in the storage file

Format: `list` 

Example: `list`

### `Todo`

Function: Add a todo task

Format: `todo DESCRIPTION` 

Example: `todo read books` 

### `Deadline` 

Function: Add a deadline task

Format: `deadline DESCRIPTION /by DUE TIME` 

Example: `deadline return books /by 6pm` 

### `Event` 

Function: Add an event task

Format: `event DESCRIPTION /from START TIME /to END TIME`

Example: `event book fair /from 6pm /to /8pm` 

### `Mark` 

Function: Mark the completion status of the i-th task

Format: `mark INDEX` 

Example: `mark 1`

### `Unmark` 

Function: Unmark the completion status of the i-th task

Format: `unmark INDEX`

Example: `unmark 1`

### `Delete`

Function: Delete the i-th task

Format: `delete INDEX` 

Example: `delete 1`

### `Find` 

Function: Find all the tasks containing the specified keyword 

Format: `find KEYWORD` 

Example: `find BOOKS`

### `Bye` 

Function: Exit the chatbot 

Format: `bye` 

Example: `bye`