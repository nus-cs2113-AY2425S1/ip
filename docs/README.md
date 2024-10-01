# Aether

**Aether** is a personal task management application designed to help you manage your todos, deadlines, and events via a simple command-line interface.

---

## Table of Contents

- [Quick Start](#quick-start)
- [Using Aether](#using-aether)
- [Features](#features)
  - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
  - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an Event Task: `event`](#adding-an-event-task-event)
  - [Listing All Tasks: `list`](#listing-all-tasks-list)
  - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
  - [Unmarking a Task: `unmark`](#unmarking-a-task-unmark)
  - [Deleting a Task: `delete`](#deleting-a-task-delete)
  - [Finding Tasks: `find`](#finding-tasks-find)
  - [Exiting the Application: `bye`](#exiting-the-application-bye)
- [Command Summary](#command-summary)
- [Saving Data](#saving-data)

---
## Quick Start

**Prerequisites**: JDK 17 or higher installed on your computer.

1. **Open Command Line Interface**:

   - On **Windows**: Press `Win + R`, type `cmd`, and press Enter.
   - On **macOS**: Open the `Terminal` from Applications or use Spotlight search.
   - On **Linux**: Open the terminal from the applications menu or press `Ctrl + Alt + T`.

2. **Navigate to the directory where the Aether JAR file is located**:

   Use the `cd` command to navigate. For example:

   `cd path/to/your/aether/folder`

3. **Run the Aether application**:

Execute the following command to start the Aether task manager:

   `java -jar aether.jar`

4. **Verify the setup**:

If the setup is correct, you should see output similar to the following in the terminal:

![image](https://github.com/user-attachments/assets/999edfcb-9a79-4ea7-9d04-f49224020c40)

5. **Start managing your tasks**:

You can now start typing commands, for example:
- `todo Read book` to add a todo task named "Read book".
- `deadline Submit assignment /by 15/09/2023 23:59` to add a deadline task.
- `event Team meeting /from 20/09/2023 14:00 /to 20/09/2023 16:00` to add an event task.
- `list` to view all tasks.
- `bye` to exit the application.

6. **Tasks will be saved automatically** to `data/aether.txt` in the current folder after any command that changes the list.

---
## Using Aether

You can start typing commands into the console to manage your tasks. Here are some example commands you can try:

- list: Lists all tasks.
- todo Read book: Adds a todo task named 'Read book' to Aether.
- deadline Submit assignment /by 15/09/2023 23:59: Adds a deadline task.
- event Team meeting /from 20/09/2023 14:00 /to 20/09/2023 16:00: Adds an event task.
- mark 1: Marks the first task as completed.
- unmark 1: Marks the first task as not completed.
- delete 1: Deletes the first task in the list.
- find book: Finds tasks containing the keyword 'book'.
- bye: Exits the application.

Refer to the **Features** below for details of each command.

---

## Features

:information_source: **Notes about the command format**:

- Words in **UPPER_CASE** are placeholders to be supplied by the user.
  - e.g., in `todo DESCRIPTION`, **DESCRIPTION** is a placeholder that can be replaced with the actual task, such as `todo Read book`.

- If you are using a **PDF version** of this document, be careful when copying and pasting commands that span multiple lines, as space characters surrounding line-breaks may be omitted when copied into the application.

### Adding a Todo Task: `todo`

Adds a general task without a deadline.

**Format**: 
```todo DESCRIPTION```

**Examples**:
- `todo Read book` Adds a todo task named "Read book" to the list.
- `todo Buy groceries` Adds "Buy groceries" as a todo task to the list.

  

### Adding a Deadline Task: `deadline`

Adds a task with a specific deadline. Useful for time-sensitive tasks.

**Format**: 
```deadline DESCRIPTION /by DATE_TIME```

**Examples**:
- `deadline Submit assignment /by 15/09/2023 23:59` Adds a task "Submit assignment" with a deadline of 15th September 2023 at 11:59 PM.
- `deadline Pay bills /by 10/10/2023 1700` Adds "Pay bills" with a deadline of 10th October 2023 at 5:00 PM.

  

### Adding an Event Task: `event`

Adds a task with both a start and end time. Ideal for scheduling events like meetings.

**Format**: 
```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```

**Examples**:
- `event Team meeting /from 20/09/2023 14:00 /to 20/09/2023 16:00` Adds "Team meeting" from 2:00 PM to 4:00 PM on 20th September 2023.
- `event Dinner with friends /from 25/12/2023 18:00 /to 25/12/2023 21:00` Adds "Dinner with friends" from 6:00 PM to 9:00 PM on 25th December 2023.

  

### Listing All Tasks: `list`

Displays a list of all tasks currently saved.

**Format**: 
```list```

**Example**:
- `list` Lists all tasks in your task list, showing their status (done or pending).

  

### Marking a Task as Done: `mark`

Marks a task as completed.

**Format**: 
```mark TASK_NUMBER```

**Examples**:
- `mark 1` Marks the 1st task in the list as done.
- `mark 2` Marks the 2nd task as done.

  

### Unmarking a Task: `unmark`

Marks a task as not completed (undoes the 'done' status).

**Format**: 
```unmark TASK_NUMBER```

**Examples**:
- `unmark 1` Unmarks the 1st task in the list as not done.
- `unmark 3` Unmarks the 3rd task in the list.

  

### Deleting a Task: `delete`

Removes a task from the task list.

**Format**: 
```delete TASK_NUMBER```

**Examples**:
- `list` followed by `delete 2` deletes the 2nd task in the list.
- `find report` followed by `delete 1` deletes the 1st task from the results of the `find` command.

  

### Finding Tasks: `find`

Searches for tasks that contain a specific keyword.

**Format**: 
```find KEYWORD```

**Examples**:
- `find book` Searches for all tasks that contain the word "book" and displays the results.
- `find meeting` Searches for tasks that contain the word "meeting".

  

### Exiting the Application: `bye`

Exits the Aether application.

**Format**: 
```bye```

**Example**:
- `bye` Closes the application and saves the task list.

  

### Saving Data

Aether automatically saves your tasks to the `data/aether.txt` file after any changes. There is no need for manual saving.

---

## Command Summary

| **Action**       | **Format, Examples**                                                                                            |
|------------------|-----------------------------------------------------------------------------------------------------------------|
| **Add Todo**     | ```todo DESCRIPTION```<br>e.g., ```todo Read book```                                                            |
| **Add Deadline** | ```deadline DESCRIPTION /by DATE_TIME```<br>e.g., ```deadline Submit report /by 15/09/2023 23:59```             |
| **Add Event**    | ```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```<br>e.g., ```event Team meeting /from 2pm /to 4pm``` |
| **List**         | ```list```                                                                                                      |
| **Mark**         | ```mark TASK_NUMBER```<br>e.g., ```mark 1```                                                                    |
| **Unmark**       | ```unmark TASK_NUMBER```<br>e.g., ```unmark 1```                                                                |
| **Delete**       | ```delete TASK_NUMBER```<br>e.g., ```delete 2```                                                                |
| **Find**         | ```find KEYWORD```<br>e.g., ```find book```                                                                     |
| **Exit**         | ```bye```                                                                                                       |

---

