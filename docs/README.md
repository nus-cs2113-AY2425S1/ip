# Rizzler User Guide

Rizzler is a CLI-based task management application designed to help you organize and manage your tasks efficiently.
With Rizzler, you can add various types of tasks, mark them as completed, delete them, and more. This guide will walk
you through all the features of Rizzler.

## Table of Contents
- [Quickstart](#quickstart)
- [Features](#features)
    - [Listing Tasks](#listing-tasks)
    - [Adding a Todo](#adding-a-todo)
    - [Adding a Deadline](#adding-a-deadline)
    - [Adding an Event](#adding-an-event)
    - [Marking a Task as Done](#marking-a-task-as-done)
    - [Unmarking a Task](#unmarking-a-task)
    - [Deleting a Task](#deleting-a-task)
    - [Finding Tasks](#finding-tasks)
    - [Exiting the App](#exiting-the-app)
- [Saving Data](#saving-data)
- [Editing the Data File](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quickstart
1. Ensure Java 17 or above is installed on your computer.
2. Download the latest Rizzler `.jar` file from [here](https://github.com/nirala-ts/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Rizzler.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar Rizzler.jar command to run the application.
5. Once you see the welcome message, you may begin entering commands. Refer to [features](#features) for full list of commands.

## Features
> **Caution:**  Command Format Notes
> - **Words in UPPER_CASE** are the parameters to be supplied by the user.  
    e.g. in `todo TASKNAME`, `TASKNAME` is a parameter which can be used as `todo recite a poem`.
> - **Parameters must be input in the specified order.**  
    e.g. if the command specifies `/from START /to END`, `/to END /from START` will cause data inaccuracy.
> - **Extraneous parameters** for commands that do not take in parameters will be ignored.  
    e.g. if the command specifies `list 123`, it will be interpreted as `list`.

### Listing Tasks: `list`
Displays all the tasks currently the task list.

Format: `list`    
Example: `list`

### Adding a Todo: `todo`
Adds a todo task into the task list.

Format:  `todo TASKNAME`  
Example: `todo buy groceries`

### Adding a Deadline: `deadline`
Adds a deadline task into the task list.

Format:  `deadline TASKNAME /by YYYY-MM-DD HH:mm`  
Example: `deadline submit CS2113 iP /by 2024-10-11 23:59`

### Adding a Event: `event`
Adds an event task into the task list.

Format:  `event TASKNAME /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm`  
Example: `event GESS1005 team meeting /from 2024-10-15 09:00 /to 2024-10-15 11:00`

### Mark Done Task: `mark`
Marks the task corresponding to the index given as completed.

Format: `mark INDEX`    
Example: `mark 1`

### Unmark Done Task: `unmark`
Marks the task corresponding to the index given as incomplete.

Format: `unmark INDEX`    
Example: `unmark 2`

### Deleting Tasks: `delete`
Deletes the task corresponding to the index given from the task list.

Format: `delete INDEX`    
Example: `delete 3`

### Finding Tasks: `find`
Searches for tasks containing a specific keyword and displays them.

Format: `find KEYWORD`    
Example: `find groceries`

### Exiting the App: `bye`
Exits the Rizzler application.

Format: `bye`    
Example: `bye`

## Saving Data
Rizzler automatically saves your task data to a file upon exiting. You do not need to manually save your tasks.

## Editing the Data File
Task data is stored as a CSV file in the designated storage path. You can edit this file manually, but be cautious:
an incorrect format may lead to data loss. It is recommended to back up the file before making edits.

> **Caution:** Editing the data file directly can lead to unexpected behavior and loss of data. Proceed with caution.


## Command Summary

| Command          | Description                                                         | Format                                                       | Example                                                                   |
|------------------|---------------------------------------------------------------------|--------------------------------------------------------------|---------------------------------------------------------------------------|
| **List Tasks**   | Displays all the tasks in the task list.                            | `list`                                                       | `list`                                                                    |
| **Add Todo**     | Adds a todo task to the task list.                                  | `todo TASKNAME`                                              | `todo buy groceries`                                                      |
| **Add Deadline** | Adds a deadline task to the task list.                              | `deadline TASKNAME /by YYYY-MM-DD HH:mm`                     | `deadline submit CS2113 iP /by 2024-10-11 23:59`                          |
| **Add Event**    | Adds an event task to the task list.                                | `event TASKNAME /from YYYY-MM-DD HH:mm /to YYYY-MM-DD HH:mm` | `event GESS1005 team meeting /from 2024-10-15 09:00 /to 2024-10-15 11:00` |
| **Mark Task**    | Marks the specified task as completed using its index.              | `mark INDEX`                                                 | `mark 1`                                                                  |
| **Unmark Task**  | Marks the specified task as incomplete using its index.             | `unmark INDEX`                                               | `unmark 2`                                                                |
| **Delete Task**  | Deletes the specified task from the task list using its index.      | `delete INDEX`                                               | `delete 3`                                                                |
| **Find Task**    | Searches for tasks containing a specific keyword and displays them. | `find KEYWORD`                                               | `find groceries`                                                          |
| **Exit**         | Exits the Rizzler application.                                      | `bye`                                                        | `bye`                                                                     |
