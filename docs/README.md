# Aether User Guide

**Aether** is a personal task management application designed to help you manage your todos, deadlines, and events via a simple command-line interface.

---

## Table of Contents

- [Quick Start](#quick-start)
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
- [Saving Data](#saving-data)
- [FAQ](#faq)
- [Editing the Data File](#editing-the-data-file)
- [Command Summary](#command-summary)

---
## Quick Start

Ensure you have Java 17 or above installed on your computer. You may download Java 17 for your respective system [here](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

1. Download the latest Aether JAR file from the releases page [here](https://github.com/NCF3535/ip/releases).

2. Copy the file to the folder you want to use as the home folder for Aether.

3. Locate Your JAR File and copy the file path. Example:`C:\Users\YourUsername\Downloads`

4. Open a command terminal
    - Windows: Click on the Start button and type `cmd`, then press `Enter` to open the Command Prompt.
    - macOS/Linux: Press `Command + Space`, type `Terminal`, and hit `Enter` to open the Terminal.

5. Use `cd` to go to the folder where your JAR file is located. Example:

   `cd C:\Users\YourUsername\Downloads`

6. Run the application using the following command:

   `java -jar aether.jar`

7. After a few seconds, the application will launch in your terminal. You should see the user interface output for Aether as shown below:
   ![image](https://github.com/user-attachments/assets/999edfcb-9a79-4ea7-9d04-f49224020c40)

8. Type the command in the terminal and press Enter to execute it. Some example commands you can try:
    - list : Lists all tasks.
    - todo Read book : Adds a task named "Read book" to your task list.
    - deadline Submit report /by 15/09/2023 23:59 : Adds a deadline task for "Submit report".
    - event Team meeting /from 20/09/2023 14:00 /to 20/09/2023 16:00 : Adds an event named "Team meeting" with start and end times.
    - delete 3 : Deletes the 3rd task shown in the current list.
    - clear : Deletes all tasks.
    - bye : Exits the application.

9. Refer to the Features section below for more details about each command.

---

## Features
> ℹ️ **Notes about the command format**:
> - Words in **UPPER_CASE** are placeholders to be supplied by the user.
> - For example: In `todo DESCRIPTION`, **DESCRIPTION** is a placeholder that can be replaced with the actual task, such as `todo Read book`.
> - If you are using a **PDF version** of this document, be careful when copying and pasting commands that span multiple lines, as space characters surrounding line-breaks may be omitted when copied into the application.
> - **Dates and Times** must follow specific formats, **else they will be treated as strings** :
>- **Date Formats:**
   >  - `1/9/2024` (d/m/yyyy)
>  - `01/09/2024` (dd/mm/yyyy)
>  - `1-9-2024` (d-m-yyyy)
>  - `2024-09-01` (yyyy-mm-dd)
>  - **Time Formats:**
>    - `930` (hmm)
>    - `0930` (hhmm)
>    - `9:30` (h:mm)
>    - `09:30` (hh:mm)
>    - `9:30AM` (h:mma)

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

- Note: Ensure that the date and time follow the accepted formats,
  or they will be treated as strings. Refer to the
  [Notes about the command format](#Features) for valid date and time formats.

**Examples**:
- `deadline Submit assignment /by 10/10/2024 23:59` Adds a task "Submit assignment" with a deadline of 10th October 2024 at 11:59 PM.
- `deadline Pay rent /by 2024-12-01` Adds "Pay rent" with a deadline of 1st December 2024.


### Adding an Event Task: `event`

Adds a task with both a start and end time. Ideal for scheduling events like meetings.

**Format**:
```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```

- Note: Ensure that the date and time follow the accepted formats,
  or they will be treated as strings. Refer to the
  [Notes about the command format](#Features) for valid date and time formats.

**Examples**:
- `event Team lunch /from 24-12-2024 12:30 /to 25-12-2024 14:00` Adds "Team lunch" from 12:30 PM on 25th December 2024 to 2:00 PM on 25th December 2024.
- `event Dinner with friends /from 18:00 /to 21:00` Adds "Dinner with friends" from 6:00 PM to 9:00 PM.



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
- `mark 2` Marks the 2nd task in the list as done.



### Unmarking a Task: `unmark`

Marks a task as not completed (undoes the 'done' status).

**Format**:
```unmark TASK_NUMBER```

**Examples**:
- `unmark 1` Unmarks the 1st task in the list as not done.
- `unmark 3` Unmarks the 3rd task in the list as not done.

### Finding Tasks: `find`

Searches for tasks that contain a specific keyword. The number shown on the result corresponds to the original list.

**Format**:
```find KEYWORD```

**Examples**:
- `find book` Searches for all tasks that contain the word "book" and displays the results.
- `find meeting` Searches for tasks that contain the word "meeting".


### Deleting a Task: `delete`

Removes a task from the task list.

**Format**:
```delete TASK_NUMBER```

**Examples**:
- `list` followed by `delete 2` deletes the 2nd task in the list.
- `find report` followed by `delete 5` deletes the 5th task from the **original list**.


### Exiting the Application: `bye`

Exits the Aether application.

**Format**:
```bye```

**Example**:
- `bye` Closes the application.



### Saving Data

Aether automatically saves your tasks to the `data/aether.txt` file after any changes. There is no need for manual saving.

---

## FAQ

### Q: How do I transfer my data to another computer?

A: Install the Aether application on the new computer and run it once to generate the default data file. Then, overwrite the generated data/aether.txt file with the one from your previous computer to transfer all your tasks.

---

## Editing the Data File

Aether automatically saves your task data in a text file located at `./data/aether.txt`, relative to the directory where you run the program. Advanced users can update their tasks by directly editing this file.

> ⚠️**Caution:** Be cautious when editing the file directly, as improper formatting may cause errors or data loss when the application is next launched.

---

## Command Summary

| **Action** | **Format, Examples**                                                                                                |
|-----------|---------------------------------------------------------------------------------------------------------------------|
| **Todo**  | ```todo DESCRIPTION```<br>e.g., ```todo Read book```                                                                |
| **Deadline** | ```deadline DESCRIPTION /by DATE_TIME```<br>e.g., ```deadline Submit report /by 15/09/2023 23:59```                 |
| **Event** | ```event DESCRIPTION /from START_DATE_TIME /to END_DATE_TIME```<br>e.g., ```event Team meeting /from 2pm /to 4pm``` |
| **List**  | ```list```                                                                                                          |
| **Mark**  | ```mark TASK_NUMBER```<br>e.g., ```mark 1```                                                                        |
| **Unmark** | ```unmark TASK_NUMBER```<br>e.g., ```unmark 1```                                                                    |
| **Find**  | ```find KEYWORD```<br>e.g., ```find book```                                                                         |
| **Delete** | ```delete TASK_NUMBER```<br>e.g., ```delete 2```                                                                    |
| **Exit**  | ```bye```                                                                                                           |

---

