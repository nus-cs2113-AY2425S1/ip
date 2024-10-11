# Jarvis User Guide

**Jarvis** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI). If you can type fast, Jarvis can help you manage your tasks faster than traditional Graphical User Interface (GUI) apps.

---

## Table of Contents

- [Jarvis User Guide](#jarvis-user-guide)
  - [Table of Contents](#table-of-contents)
  - [Quick Start](#quick-start)
  - [Features](#features)
    - [Adding a Todo Task: `todo`](#adding-a-todo-task-todo)
    - [Adding a Deadline Task: `deadline`](#adding-a-deadline-task-deadline)
    - [Adding an Event Task: `event`](#adding-an-event-task-event)
    - [Listing All Tasks: `list`](#listing-all-tasks-list)
    - [Marking a Task as Done: `mark`](#marking-a-task-as-done-mark)
    - [Unmarking a Task: `unmark`](#unmarking-a-task-unmark)
    - [Finding Tasks: `find`](#finding-tasks-find)
    - [Deleting a Task: `delete`](#deleting-a-task-delete)
    - [Exiting the Application: `bye` or `exit`](#exiting-the-application-bye-or-exit)
    - [Saving Data](#saving-data)
  - [FAQ](#faq)
    - [Q: How do I transfer my data to another computer?](#q-how-do-i-transfer-my-data-to-another-computer)
  - [Editing the Data File](#editing-the-data-file)
  - [Command Summary](#command-summary)

---
## Quick Start

Ensure you have `Java 17` or above installed on your computer. You may download `Java 17` for your respective system [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

1. Download the latest Jarvis JAR file from the releases page [here](https://github.com/AjayShanker-geek/ip/releases).

2. Copy the file to the folder you want to use as the home folder for Jarvis.

3. Locate Your JAR File and copy the file path. Example:`C:\Users\YourUsername\Downloads`

4. Open a command terminal
    - Windows: Click on the Start button and type `cmd`, then press `Enter` to open the Command Prompt.
    - macOS/Linux: Press `Command + Space`, type `Terminal`, and hit `Enter` to open the Terminal.

5. Use `cd` to go to the folder where your JAR file is located. Example:

   `cd C:\Users\YourUsername\Downloads`

6. Run the application using the following command:

   `java -jar jarvis.jar`

7. After a few seconds, the application will launch in your terminal. You should see the user interface output for Jarvis as shown below: Some example commands you can try:

![Jarvis User-Interface](/ip/assets/ui.png)

1. Type the command in the terminal and press Enter to execute it. e.g. typing **`help`** and pressing Enter will print the list of commands available:
    - *list* : Lists all tasks.
    - *todo* Read book : Adds a task named "Read book" to your task list.
    - *deadline* Submit report /by Aug 20 11pm : Adds a deadline task for "Submit report".
    - *event* Team meeting /from Sept 1 2pm /to 4pm : Adds an event named "Team meeting" with start and end times.
    - *delete* 2 : Deletes the 2rd task shown in the current list.
    - *bye* or *exit* : Exits the application.

2.  Refer to the Features section below for more details about each command.

---

## Features
<div markdown="block" class="alert alert-info">

**ℹ️: Notes about the command format:**<br>

* Parameters cannot be in any order for `event`.<br>
  e.g. For event, the `/from` is require **comes before** `/to`. Improper ordering will result in an error. Will improve in future versions.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Adding a Todo Task: `todo`

Adds a general task.

**Format**:
```todo DESCRIPTION```

**Examples**:
- `todo Read book` Adds a todo task named "Read book" to the list.
- `todo buy groceries` Adds "buy groceries" as a todo task to the list.


### Adding a Deadline Task: `deadline`

Adds a task with a specific deadline. Useful for time-sensitive tasks.

**Format**:
```deadline DESCRIPTION /by DATE_TIME```

**Examples**:
- `deadline Submit CG2113 assignment /by Oct 10 6pm` Adds a task "Submit CS2113 assignment" with a deadline of 10th October 2024 at 6PM.
- `deadline pay rent /by Dec 1` Adds "pay rent" with a deadline of Dec 1.


### Adding an Event Task: `event`

Adds a task with both a start and end time. Ideal for scheduling events like meetings.

**Format**:
```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```

- Note: Ensure that the /from is require **comes before** /to.

**Examples**:
- `event Team lunch /from Dec 13 2pm 4pm` Adds "Team lunch" from 2PM on 13th December 2024 to 4PM on same day.
- `event dinner with friends /from 6pm /to 9pm` Adds "dinner with friends" from 6PM to 9PM.



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
- `mark 3` Marks the 3nd task in the list as done.



### Unmarking a Task: `unmark`

Marks a task as not completed (undoes the 'done' status).

**Format**:
```unmark TASK_NUMBER```

**Examples**:
- `unmark 1` Unmarks the 1st task in the list as not done.
- `unmark 2` Unmarks the 2rd task in the list as not done.

### Finding Tasks: `find`

Searches for tasks that contain a specific keyword. The number shown on the result corresponds to the original list.

**Format**:
```find KEYWORD```

- Note: The search is case-sensitive. For example, `book` will not match `Book` and `BOOK`.

**Examples**:
- `find book` Searches for all tasks that contain the word "book" and displays the results.
- `find meeting` Searches for tasks that contain the word "meeting".


### Deleting a Task: `delete`

Removes a task from the task list.

**Format**:
```delete TASK_NUMBER```

**Examples**:
- `list` followed by `delete 2` deletes the 2nd task in the list.


### Exiting the Application: `bye` or `exit`

Exits the Jarvis application.

**Format**:
```bye``` or ```exit```

**Example**:
- `bye` or `exit` Closes the application.



### Saving Data

Jarvis automatically saves your tasks to the `data/jarvis.txt` file after any changes. There is no need for manual saving.

---

## FAQ

### Q: How do I transfer my data to another computer?

A: Install the Jarvis application on the other computer and copy the `data/jarvis.txt` file to the new computer. Ensure that the file is located in the same directory as the JAR file. When you run the application, your tasks will be loaded from the file.

---

## Editing the Data File

Jarvis automatically saves your task data in a text file located at `./data/jarvis.txt`, relative to the directory where you run the program. Advanced users can update their tasks by directly editing this file.

> ⚠️**Caution:** Be cautious when editing the file directly, as improper formatting may cause errors or data loss when the application is next launched.

---

## Command Summary

| **Action** | **Format, Examples**                                                                                                |
|-----------|---------------------------------------------------------------------------------------------------------------------|
| **Todo**  | ```todo DESCRIPTION```<br>e.g., ```todo Read a book```                                                                |
| **Deadline** | ```deadline DESCRIPTION /by DATE_TIME```<br>e.g., ```deadline Submit report /by Sept 9 1pm```                 |
| **Event** | ```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```<br>e.g., ```event Team meeting /from 2pm /to 4pm``` |
| **List**  | ```list```                                                                                                          |
| **Mark**  | ```mark TASK_NUMBER```<br>e.g., ```mark 1```                                                                        |
| **Unmark** | ```unmark TASK_NUMBER```<br>e.g., ```unmark 1```                                                                    |
| **Find**  | ```find KEYWORD```<br>e.g., ```find book```                                                                         |
| **Delete** | ```delete TASK_NUMBER```<br>e.g., ```delete 2```                                                                    |
| **Exit**  | ```bye``` or ```exit```                                                                                                           |

---
