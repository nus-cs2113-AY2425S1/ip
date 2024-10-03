# Apsea User Guide

## Quick start
1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest `.jar` file from [here](github.com/glenn-chew/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Apsea.
4. Open a command terminal, `cd` into the folder you put the jar file in,
and use the `java -jar apsea.jar` command to run the application. 
The message below should be printed in your terminal:
```
    ___________________________________________________________________________
    Hello! I'm Apsea!
    What can I do for you?
    ______________________________________________________________________________  
```

## Listing all tasks :`list`
Shows a list of all tasks in the task list.

Format: `list`

## Adding a todo task :`todo`
Adds a todo task to the task list.

Format :`todo NAME`

Example:
- `todo read a book`

## Adding a deadline task :`deadline`
Adds a deadline task to the task list.

Format :`deadline NAME /by TIME`

Example:
-  `deadline complete assignment /by 2pm`

## Adding an event task :`event`
Adds an event task to the task list.

Format :`event NAME /from START_TIME /to END_TIME`

Example:
 -  `event lecture /from 2pm /to 4pm`

## Marking task as complete :`mark`
Marks a task in the task list as complete.

Format :`mark TASK_NUMBER`
- `TASK_NUMBER` follows the number shown in the task list.
- The value of `TASK_NUMBER` must be a positive integer (1, 2, 3...)

Example:
- `mark 2`

## Marking a task to incomplete :`unmark`
Marks a task in the task list as incomplete.

Format :`unmark TASK_NUMBER`
- `TASK_NUMBER` follows the number shown in the task list.
- The value of `TASK_NUMBER` must be a positive integer (1, 2, 3...)

Example:
- `unmark 1`

## Locating a task by name: `find`
Finds tasks whose names contain any of the given keywords.

Format: `find KEYWORD`

- The search is case-insensitive. e.g `book` will match `Book`
- Only the name is searched.
- Parts of a word will be matched e.g. `book` will match `books`
- Phrases can be matched e.g. `read book` will match `read books`
- Phrases must be in the exact order e.g `read book` will not match `read a book`

Example:
- `find read book`

## Exiting the programme :`bye`
Exits the program.

Format :`bye`

## Saving the data
The data in the task list are saved in the hard disk automatically after any command that changes the data. 
There is no need to save manually.