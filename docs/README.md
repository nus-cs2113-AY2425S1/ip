# Medea User Guide

Medea is a desktop app for managing tasks, optimized for use via a CLI.

- [QuickStart](#QuickStart)
- [Features](#Features)
    - [Listing Tasks](#listing-tasks-list)
    - [Adding a Todo](#adding-a-todo-todo)
    - [Adding a Deadline](#adding-a-deadline-deadline)
    - [Adding an Event](#adding-a-event-event)
    - [Mark Done Task](#mark-done-task-mark)
    - [Unmark Done Task](#unmark-done-task-unmark-)
    - [Deleting Tasks](#deleting-tasks-delete-)
    - [Finding Tasks](#finding-tasks-find)
    - [Exiting the App](#exiting-the-app-bye-)
- [Saving Data](#saving-data)
- [Editing the Data File](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quickstart

1. Ensure you have Java `17` or above installed in your Computer.
2. Grab the latest `.jar` file from [here](https://github.com/TVageesan/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Medea.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar medea.jar command to run the application.
5. Once you see the welcome message, you may begin entering commands. Refer to [features](#features) for full list of commands.

## Features

> **Caution:**  Command Format Notes
> - **Words in UPPER_CASE** are the parameters to be supplied by the user.  
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`.
> - **Parameters must be input in the specified order.**  
    e.g. if the command specifies `/from START /to END`, `/to END /from START` will cause data inaccuracy.
> - **Extraneous parameters** for commands that do not take in parameters (such as help, list, exit, and clear) will be ignored.  
    e.g. if the command specifies `help 123`, it will be interpreted as `help`.

### Listing Tasks: `list`
Displays all the tasks currently in the list of tasks.   
Format: `list`    
Example: `list`

### Adding a Todo: `todo`
Adds a todo task into the list of tasks.  
Format:  `todo DESCRIPTION`  
Example: `todo read book`

### Adding a Deadline: `deadline`
Adds a deadline task into the list of tasks.  
Format:  `deadline DESCRIPTION /by DEADLINE`  
Example: `todo read book /by Tomorrow`

### Adding a Event: `event`
Adds a event task into the list of tasks.
Format:  `todo DESCRIPTION /from START /to END`  
Example: `todo read book /from 2pm /to 4pm`

### Mark Done Task: `mark`
Using the task's index, marks a task as completed.
Format: `mark INDEX`    
Example: `mark 1`

### Unmark Done Task: `unmark`
Using the task's index, marks a task as incomplete.  
Format: `unmark INDEX`    
Example: `unmark 1`

### Deleting Tasks: `delete`
Using the task's index, delete a task from the list of tasks.  
Format: `delete INDEX`    
Example: `delete 2`

### Finding Tasks: `find`
Display all tasks that match a given keyword.  
Format: `find KEYWORD`    
Example: `find homework`

### Exiting the App: `bye`
Exits the application.  
Format: `bye`    
Example: `bye`

## Saving Data
Task data is saved automatically to the hard disk when the application is exited. No manual saving is required.

## Editing the Data File
Task data is saved as a CSV file at the designated file path. Advanced users can update data directly by editing that file.

> **Caution:** If your changes to the data file make its format invalid, the application may discard all data and start with an empty file at the next run. Therefore, it is recommended to take a backup of the file before editing it. Certain edits can cause the app to behave unexpectedly, so proceed with caution.

## Command Summary

| Command              | Description                                                       | Format                              | Example                          |
|----------------------|-------------------------------------------------------------------|-------------------------------------|----------------------------------|
| **List Tasks**        | Displays all the tasks in the list.                               | `list`                              | `list`                           |
| **Add Todo**          | Adds a todo task.                                                 | `todo DESCRIPTION`                  | `todo read book`                 |
| **Add Deadline**      | Adds a deadline task.                                             | `deadline DESCRIPTION /by DEADLINE` | `deadline read book /by Tomorrow`|
| **Add Event**         | Adds an event task.                                               | `event DESCRIPTION /from START /to END`| `event read book /from 2pm /to 4pm` |
| **Mark Task as Done** | Marks a task as completed using its index.                        | `mark INDEX`                        | `mark 1`                         |
| **Unmark Task**       | Marks a task as incomplete using its index.                       | `unmark INDEX`                      | `unmark 1`                       |
| **Delete Task**       | Deletes a task using its index.                                   | `delete INDEX`                      | `delete 2`                       |
| **Find Task**         | Displays tasks matching a keyword.                                | `find KEYWORD`                      | `find homework`                  |
| **Exit**              | Exits the application.                                            | `bye`                               | `bye`                            |