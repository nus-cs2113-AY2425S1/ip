# AlyBot - User Guide

## Introduction
AlyBot is a task management application that allows users to create, manage, and track tasks efficiently. Users can add various types of tasks, filter tasks by date, and find specific tasks by keywords. The application provides a user-friendly interface to ensure an enjoyable experience.

## Table of Contents
* [Set up](#set-up)
* [Features](#features)
   * [Adding Todo tasks](#adding-todo-tasks-todo)
   * [Adding Deadline tasks](#adding-deadline-tasks-deadline)
   * [Adding Event tasks](#adding-event-tasks-event)
   * [Listing all tasks](#listing-all-tasks-list)
   * [Finding tasks by keyword](#finding-tasks-by-keyword-find)
   * [Filtering tasks by date](#filtering-tasks-by-date-filter)
   * [Marking a task as done or undone](#marking-a-task-as-done-or-undone-markunmark)
   * [Deleting tasks](#deleting-tasks-delete)
   * [Exiting program](#exiting-program-exit)
   * [Saving/Loading of task data](#savingloading-of-task-data)
* [Command Summary](#command-summary)
* [FAQs](#faqs)
* [Contact and Support](#contact-and-support)

## Set up
1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest .jar file from [here](#https://github.com/LTK-1606/ip/releases)
3. Copy the file into any folder
4. Open a new terminal in the folder you copied the .jar file into, and enter the following command:

         `java -jar Aly.jar`
5. Upon running the command, the following messages should be printed in your terminal:
```
Hello, my name is
_      _     _   _
/ \    | |   \ \ / /
/ _ \   | |    \ V /
/ ___ \  | |__   | |  
/_/   \_\ |____|  |_|

================================================================================================================
Searching for data/taskdata.txt...
File already exists, I will edit that!
================================================================================================================
I'm hungry, what do you want?
================================================================================================================
Possible commands:
1. 'echo' followed by input to receive echo
2. 'list' to see your list of tasks
3. 'todo/deadline/event' to add that type of task
4. 'mark/unmark' with a number to toggle that task's status
5. 'delete' with a number to delete that task
6. 'filter' with a date to see all tasks scheduled on that date
7. 'find' with a description to see all tasks containing that description
8. 'help' to see the command list
9. 'exit' to exit
   ================================================================================================================
```

## Features
Notes about the command format:

1. Words in **UPPER_CASE** are the parameters to be supplied by the user.

   e.g. in `todo DESC`, `DESC` is a parameter, like `todo go to school`.


2. Command words are **case-sensitive**.

   e.g `list` is valid, but `LIST` is invalid.


3. **Whitespaces** are required between every word in the input.

   e.g. `list1` will not execute `list` command.


4. Extraneous parameters for commands that do not take in parameters (such as help, list, exit) will be ignored.

   e.g. `list 1-10` will be treated the same as `list`.

### Adding ToDo tasks: `todo`
Adds a `ToDo` task to the task list.

Format: `todo DESCRIPTION`

**Examples:**

* `todo go school` adds a `ToDo` task with description `go school`.

### Adding Deadline tasks: `deadline`
Adds a `Deadline` task to the task list.

Format: `deadline DESCRIPTION by DUE-DATE`

* The input for `DUE-DATE` has to be entered in the format `yyyy-MM-dd HHmm`.

**Examples:**

* `deadline finish homework by 2024-10-10 1200` adds a Deadline task with description `finish homework` and
due-date `2024-10-10 1200`.

### Adding Event tasks: `event`
Adds an `Event` task to the task list.

Format: `event DESCRIPTION from FROM-DATE to TO-DATE`

* The inputs for `FROM-DATE` and `TO-DATE` have to be entered in the format `yyyy-MM-dd HHmm`.

**Examples:**

* `event finish homework from 2024-10-10 0600 to 2024-10-10 1000` adds an `Event` task with description `finish homework`,
from-date `2024-10-10 0600` and to-date `2024-10-10 1000`.

### Listing all tasks: `list`
Shows a list of all tasks in the task list currently.
```
Your task list:
1.[T][ ] eat lunch
2.[D][ ] finish homework (by: Oct-10-2024 10:00)
Wah shag, good luck with your tasks bro!
```
   Format: `list`

### Finding tasks by keyword: `find`
Finds and displays a list of all the tasks that contains a specific keyword.

```
Your task list:
1.[D][ ] finish homework (by: Oct-10-2024 10:00)
2.[E][ ] finish cleaning (from: Oct-9-2024 08:00 to: Oct-9-2024 10:00)
3.[T][ ] FINISH LUNCH
Wah shag, good luck with your tasks bro!
```

Format: `find KEYWORD`

* This method is **case-insensitive**, e.g `finish` will match `FINISH`.

* `KEYWORD` can consist of multiple words. 

* Only tasks that contain the entire `KEYWORD` will be found.

**Examples:**

* `find finish` returns all tasks that contain the word `finish`.

**Note**

* The indexes of the tasks in the shown list **don't correspond** to their indexes in the complete task list.

### Filtering tasks by date: `filter`
Finds and displays a list of all the tasks that occur on a particular date.

```
Your task list:
1.[D][ ] finish homework (by: Oct-10-2024 10:00)
Wah shag, good luck with your tasks bro!
```

Format: `filter DATE`

* The value of `DATE` has to be entered in the format `yyyy-mm-dd`.

**Examples:**

* `filter 2024-10-10` shows a list of tasks that occur on `2024-10-10`.

**Note**

* The indexes of the tasks in the shown list **don't correspond** to their indexes in the complete task list.

### Marking a task as done or undone: `mark`/`unmark`
Marks an existing task as done/undone.

Format: `mark INDEX`/`unmark INDEX`

* Marks the task at `INDEX` as done/undone. 

* The value of `INDEX` is the same as the one shown in the task list.

* The value of `INDEX` must be a positive integer (1, 2, 3,…).

* If the value of `INDEX` is not within the task list, the program prints the following message:

```
Index out of bounds lah bro
```

**Example:**

`mark 2` marks the task with an **'X'** at index 2 with an in the task list to indicate it is done.

```
Your task list:
1.[T][ ] eat lunch
2.[D][X] finish homework (by: Oct-10-2024 10:00)
Wah shag, good luck with your tasks bro!
```


### Deleting tasks: `delete`
Deletes a task from the list.

Format: `delete INDEX`

* Deletes the task at `INDEX` from the task list. The value of `INDEX` is the same as the one shown in the task list. 

* The value of `INDEX` must be a positive integer (1, 2, 3,…).

* If the value of `INDEX` is not within the task list, the program prints the following message:
```
Index out of bounds lah bro
```

**Example:**

* `delete 1` deletes the task at index 1 from the task list.

### Exiting program: `exit`
Exits the program. Upon exiting, the program shows the following message:

```
Tasks recorded successfully to data/taskdata.txt
Bye! Time for MacDonalds!
```

Format: `exit`

### Saving/Loading of task data
**Saving:**

The program saves the data in the task list to the hard disk after every command. There is no need to save anything manually.

**Loading:**

When the program is initialised, it loads the data from the saved file into the task list.
If the save file does not
exist, the program will create an empty file and a directory at the required file path for you.

**Filepath:**

The filepath for the save file is `[JAR file location]/data/taskdata.txt`.

## Command Summary

| **Action**      | **Format**                        | **Example**                                                |
|-----------------|-----------------------------------|------------------------------------------------------------|
| Add `Todo`      | `todo DESCRIPTION`                | `todo finish homework`                                     |
| Add `Deadline`  | `deadline DESCRIPTION by DUE-DATE` | `deadline finish homework by 2024-10-10 1000`              |
| Add `Event`     | `event DESCRIPTION from FROM-DATE to TO-DATE `| `event do homework from 2024-10-09 0800 to 2024-10-09 1000`|
| `List`          |`list`                             | -                                                          |
| `Find`            |`find KEYWORD`| `find finish`                                              |
| `Filter`          |`filter DATE`| `filter 2024-10-10`                                        |
| `Mark` done/undone|`mark INDEX`/`unmark INDEX`| `mark 2`/`unmark 2`                                        |
|`Exit`|`exit`| -                                                          |

## FAQs
**1. What should I do if I encounter an error?**

Carefully read the error message provided. It will often guide you on how to correct your input.

**2. Can I undo an action?**

Currently, the application does not support undoing actions. Be sure to check your input before executing a command.

**3. How do I exit the application?**

Use the exit command.

## Contact and Support
For further assistance, please contact me at [e1122153@u.nus.edu](#e1122153@u.nus.edu)!