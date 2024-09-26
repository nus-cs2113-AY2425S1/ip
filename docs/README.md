# V User Guide

V is an application for managing a task list. It is optimized for use via a Command Line Interface (CLI).

V can add 3 different types of tasks i.e a to-do, a deadline or an event

A to-do only has a task description. A deadline has a description and a set deadline. An event has a description, a starting time and an ending time


## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file from [here](https://github.com/KHsienKit/ip/releases/tag/A-Release).
3. Open a command terminal and use the `java -jar PATH_TO_JAR_FILE` command to run the application.
4. Refer to features below for list of commands


## Notes About Command Format

- Words in `SCREAMING_SNAKE_CASE` are parameters that needs to be inputted by the user

eg. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter
- Parameters must be in order

eg. The input from the user must be `event DESCRIPTION /from DATE /to DATE` and **NOT** `event DESCRIPTION /to DATE /from DATE`


## Features

## Adding To-Dos: `todo`

Adds a todo to the list

Format: `todo DESCRIPTION`

Examples: 

- `todo homework`

Expected Output:

```
todo homework
____________________________________________________________
Got it. Task added
 [T][ ] homework
____________________________________________________________
```


## Adding Deadlines: `deadline`

Adds a task with a deadline to the list

Format: `deadline DESCRIPTION /by TIME`

-  The parameter `TIME` is stored as a String, any date and time format can be used 

Examples: 
- `deadline report /by 12th November`
- `deadline assignment /by 23/5`

Expected Output:

```
deadline report /by 12th November
____________________________________________________________
Got it. Task added
 [D][ ] report (by: 12th November)
____________________________________________________________
deadline assignment /by 23/5
____________________________________________________________
Got it. Task added
 [D][ ] assignment (by: 23/5)
____________________________________________________________
```


## Adding Events: `event`

Adds an event with a start and end time to the list

Format: `event DESCRIPTION /from STARTING_TIME /to ENDING_TIME`

- The parameters `STARTING_TIME` and `ENDING_TIME` are stored as a String, any date and time format can be used
Examples:
- `event concert /from 12pm /to 4pm`
- `event family holiday /from 12 August 6pm /to 15 August 7pm`

Expected Output:

```
event concert /from 12pm /to 4pm
____________________________________________________________
Got it. Task added
 [E][ ] concert (from: 12pm to: 4pm)
____________________________________________________________
event family holiday /from 12 August 6pm /to 15 August 7pm
____________________________________________________________
Got it. Task added
 [E][ ] family holiday (from: 12 August 6pm to: 15 August 7pm)
____________________________________________________________
```


## Marking a task as completed: `mark`

Marks a task as completed

Format: `mark POSITION`
- Tasks are numbered in the list. You can use the command `list` to see which positions in the list the tasks are in
- `POSITION` must be a positive integer, else an exception is thrown
- Value of `POSITION` must not be more than the number of tasks in the list, else an exception is thrown

Examples:

- `mark 1`

Expected Output:

```
mark 1
____________________________________________________________
1.[T][X] homework
2.[D][ ] report (by: 12th November)
3.[D][ ] assignment (by: 23/5)
4.[E][ ] concert (from: 12pm to: 4pm)
5.[E][ ] family holiday (from: 12 August 6pm to: 15 August 7pm)
____________________________________________________________
```


## Deleting a task: `delete`

Deletes a task from the list

Format: `delete POSITION`

- `POSITION` must be a positive integer, else an exception is thrown
- Value of `POSITION` must not be more than the number of tasks in the list, else an exception is thrown

Examples:

- `delete 2`

Expected output:

```
delete 2
____________________________________________________________
Got it. The task below was removed:
[D][ ] report (by: 12th November)
Now you have 4 left
____________________________________________________________
```


## Finding a task: `find`

Finds all task that contains a specified description and prints them to the terminal

Format: `find DESCRIPTION`

- Search is case insensitive
- Partial words will be matched eg. `Te` will match with `test 234`

Examples:

- `find assignment`

Expected output:

```
find assignment
____________________________________________________________
Here are the matching tasks in your list
1.[D][ ] assignment (by: 23/5)
____________________________________________________________
```


## Listing all the tasks: `list`

Displays all task in the list along with their type and status

Format: `list`

Expected Output:

```
list
____________________________________________________________
1.[T][X] homework
2.[D][ ] assignment (by: 23/5)
3.[E][ ] concert (from: 12pm to: 4pm)
4.[E][ ] family holiday (from: 12 August 6pm to: 15 August 7pm)
____________________________________________________________
```


## Exiting the programme: `bye`

Exits the programme and saves all tasks that can be loaded again in the future

Format: `bye`

Expected Output:

```
bye
____________________________________________________________
See Ya
____________________________________________________________
```


## Saving the data

Data is saved only when the programme is properly exited(i.e the command `bye` is used)

If the programme is not properly exited, the currnet tasks in the list will not be saved and the previous save
will be used in the future


## Editing the data

All tasks are saved in the file `V.txt` and is stored in the current directory

Users are free to edit the save file directly