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

Ensure you have Java 17 or above installed on your computer.

1. Download the latest Aether JAR file from the releases page [here](https://github.com/NCF3535/ip/releases).

2. Copy the file to the folder you want to use as the home folder for Aether.

3. Open a command terminal and navigate to the folder where you placed the JAR file by using the `cd` command. 

4. Run the application using the following command: 

   ```bash
   java -jar aether.jar

5. After a few seconds, the application will launch in your terminal. You should see the user interface output for Aether as shown below:
   ![image](https://github.com/user-attachments/assets/999edfcb-9a79-4ea7-9d04-f49224020c40)

6. Type the command in the terminal and press Enter to execute it. Some example commands you can try:
   - list : Lists all tasks.
   - todo Read book : Adds a task named "Read book" to your task list.
   - deadline Submit report /by 15/09/2023 23:59 : Adds a deadline task for "Submit report".
   - event Team meeting /from 20/09/2023 14:00 /to 20/09/2023 16:00 : Adds an event named "Team meeting" with start and end times.
   - delete 3 : Deletes the 3rd task shown in the current list.
   - clear : Deletes all tasks.
   - bye : Exits the application.

7. Refer to the Features section below for more details about each command.

---

## Features

> :information_source: **Notes about the command format**:
>- Words in **UPPER_CASE** are placeholders to be supplied by the user.
>    - e.g., in `todo DESCRIPTION`, **DESCRIPTION** is a placeholder that can be replaced with the actual task, such as `todo Read book`.
>- If you are using a **PDF version** of this document, be careful when copying and pasting commands that span multiple lines, as space characters surrounding line-breaks may be omitted when copied into the application.

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

> :warning:**Caution:** Be cautious when editing the file directly, as improper formatting may cause errors or data loss when the application is next launched.

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
| **Find**         | ```find KEYWORD```<br>e.g., ```find book```                                                                     |
| **Delete**       | ```delete TASK_NUMBER```<br>e.g., ```delete 2```                                                                |
| **Exit**         | ```bye```                                                                                                       |

---

