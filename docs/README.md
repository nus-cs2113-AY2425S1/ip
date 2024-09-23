# Iris User Guide

Iris is a desktop app for managing tasks, optimized for use via a 
Command Line Interface (CLI), as it is faster and easier than traditional 
Graphical User Interface (GUI) apps for fast-typers.

## Quick Start
1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file from [here](https://github.com/TPH777/ip/releases).
3. Open a command terminal, cd into the folder the jar file is in, and use the `java -jar iris.jar` command to run the application.

## Features
Note: Words in `UPPER_CASE` are compulsory parameters, unless it is in `[SQUARE_BRACKETS]`

Command Summary: 
[`todo, event, deadline`](#1-adding-a-task-todo-deadline-event-), [`mark`](#2-marking-a-task-as-completed-mark), 
[`unmark`](#3-unmarking-a-task-from-completed-unmark), [`list`](#4-listing-the-tasks-list),
[`find`](#5-finding-a-task-find), [`date`](#6-split-tasks-by-date-date),
[`delete`](#7-delete-a-task-delete), [`bye`](#8-end-application-bye)

<br>

### 1. Adding a task: `todo`, `event`, `deadline`
Adds a task to the list of tasks

Format: `TASK_TYPE DESCRIPTION [/TIME_PREPOSITION] [DATE]`

Examples:
- `todo reading`
- `event concert /on 2024-10-10`
- `deadline assignment /by 2024-12-31`

Expected Output:
```
Got it. I've added this task:
[D][ ] assignment (by: 2024-12-31)
Now you have 3 tasks in the list
```

### 2. Marking a task as completed: `mark`
Set a task as completed

Format: `mark INDEX_OF_TASK`

Example: `mark 1`

Expected Output:
```
Nice! I've marked this task as done:
[T][X] reading
```

### 3. Unmarking a task from completed: `unmark`
Set a task as uncompleted

Format: `unmark INDEX_OF_TASK`

Example: `unmark 1`

Expected Output:
```
OK, I've marked this task as not done yet:
[T][ ] reading
```

### 4. Listing the tasks: `list`
Print all the tasks in the list of tasks

Format: `list`

Expected Output:
```
Here are the tasks in your list:
1.[T][ ] reading
2.[E][ ] concert (on: 2024-10-10)
3.[D][ ] assignment (by: 2024-12-31)
```

### 5. Finding a task: `find`
To find tasks with description that contain a certain keyword

Format: `find KEYWORD`

Example: `find rea`

Expected Output:
```
Here are the matching tasks in your list:
[T][ ] reading
```

### 6. Split tasks by date: `date`
To find tasks that are before and after a certain date

Format `date DATE_TO_SPLIT`

Example: `date 2024-11-11`

Expected Output:
```
Here are the tasks before 2024-11-11:
[E][ ] concert (on: 2024-10-10)
---------------------------------------------
Here are the tasks after 2024-11-11:
[D][ ] assignment (by: 2024-12-31)
```

### 7. Delete a task: `delete`
To remove a task from the list of tasks

Format: `delete INDEX_OF_TASK`

Example: `delete 1`

Expected Output:
```
Noted. I've removed this task:
[T][ ] reading
Now you have 2 tasks in the list
```

### 8. End application: `bye`
To save the current list of tasks before closing the application

Format: `bye`

Expected Output:
```
Saved task list successfully!
Bye! Hope to see you again soon!
```