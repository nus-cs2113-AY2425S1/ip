
# CodeCatalyst User Guide

## Introduction

**CodeCatalyst** is a command-line-based task manager optimized for efficient task management via a Command Line 
Interface (CLI). It helps users keep track of their tasks through `Todo`, `Deadline`, and `Event` entries.

This user guide provides detailed instructions on how to get started, use the main features, and a summary of available 
commands for easy reference.

## Quick Start

1. Ensure you have **Java 11** or above installed on your computer.
2. Download the latest release of CodeCatalyst from [here](#).
3. Open a terminal and `cd` into the folder you put the jar file in, and use the `java -jar .\ip.jar` command 
   to run the application.

6. Start entering your tasks using the command-line commands described below.

## Features

### 1. Add a task

#### 1.1. Adding a Todo Task: `todo`
Adds a new Todo task to the task list.

Format: `todo TASK_DESCRIPTION`

Example:
```bash
todo read book
```

Expected Output:
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 task in the list.
```

#### 1.2. Adding a Deadline Task: `deadline`
Adds a new task with a deadline.

Format: `deadline TASK_DESCRIPTION /by DATE_TIME`

Example:
```bash
deadline submit CS2113 assignment /by 2023-12-01 1800
```

Expected Output:
```
Got it. I've added this task:
[D][ ] submit CS2113 assignment (by: 2023-12-01 18:00)
Now you have 2 tasks in the list.
```

#### 1.3. Adding an Event Task: `event`
Adds a new event task with a start and end time.

Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME`

Example:
```bash
event CS2113 team meeting /from 2023-12-05 1400 /to 1600
```

Expected Output:
```
Got it. I've added this task:
[E][ ] CS2113 team meeting (from: 2023-12-05 14:00 to: 16:00)
Now you have 3 tasks in the list.
```

### 2. Listing all tasks: `list`
Displays all tasks currently stored in the task list with their completion status.

Format: `list`

Example:
```bash
list
```

Expected Output:
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit CS2113 assignment (by: 2023-12-01 18:00)
3. [E][ ] CS2113 team meeting (from: 2023-12-05 14:00 to: 16:00)
```

### 3. Marking a task as done: `mark`
Marks a task as done by specifying its task number.

Format: `mark TASK_NUMBER`

Example:
```bash
mark 1
```

Expected Output:
```
Nice! I've marked this task as done:
[T][X] read book
```

### 4. Unmarking a task: `unmark`
Unmarks a task as undone by specifying its task number.

Format: `unmark TASK_NUMBER`

Example:
```bash
unmark 1
```

Expected Output:
```
Nice! I've marked this task as not done yet:
[T][ ] read book
```

### 5. Deleting a task: `delete`
Deletes a task by specifying its task number.

Format: `delete TASK_NUMBER`

Example:
```bash
delete 2
```

Expected Output:
```
Noted. I've removed this task:
[D][ ] submit CS2113 assignment (by: 2023-12-01 18:00)
Now you have 2 tasks in the list.
```

### 6. Finding tasks: `find`
Finds and displays tasks that contain a specified keyword.

Format: `find KEYWORD`

Example:
```bash
find book
```

Expected Output:
```
Here are the matching tasks in your list:
1. [T][ ] read book
```

### 7. Saving tasks to file: Automatic
The task list is automatically saved to a file after any modification. No command is required to save manually.

The save file is located at: `data/CodeCatalystData.txt`

The format of the saved file is as follows:
```bash
TASK_TYPE | TASK_COMPLETION_STATUS | TASK_DESCRIPTION [| ADDITIONAL_INFO]
````

- **TASK_TYPE**: Represents the type of task. It can be one of the following:
  - `T`: Todo
  - `D`: Deadline
  - `E`: Event
  

- **TASK_COMPLETION_STATUS**: Indicates whether the task is completed:
  - `1`: Marked as done
  - `0`: Not done
  

- **TASK_DESCRIPTION**: The description of the task.


- **ADDITIONAL_INFO**:
  - For `Deadline`: The due date in the format `DATE`.
  - For `Event`: The start and end times in the format `DATE START-END`.

#### Example of Saved File Format:
```bash
T | 1 | read book 
D | 0 | submit CS2113 assignment | 2023-12-01 18:00
E | 0 | CS2113 team meeting | 2023-12-05 14:00-16:00 
````

In this example:
- The first line represents a completed Todo task: "read book."
- The second line represents an incomplete Deadline task: "submit CS2113 assignment" 
  with a due date of "2023-12-01 18:00"
- The third line represents an incomplete Event task: "CS2113 team meeting" 
  scheduled from "2023-12-05 14:00" to "16:00"


### 8. Exiting the program: `bye`
Exits the program and closes the application.

Format: `bye`

Example:
```bash
bye
```

Expected Output:
```
Bye. Hope to see you again soon!
```

## FAQ

**Q**: How do I edit a task?
- **A**: To edit a task, you will need to delete the task using the `delete` command and re-add it using `todo`, `deadline`, or `event` with the updated description or time.

**Q**: Can I undo a command?
- **A**: Currently, there is no undo feature available. Ensure your commands are correct before executing them.

**Q**: Are the command case-sensitive?
- **A**: Yes. Ensure your commands are lowercase before executing them.

## Command Summary

| Command     | Format                                      | Example                                      |
|-------------|---------------------------------------------|----------------------------------------------|
| **Todo**    | `todo TASK_DESCRIPTION`                     | `todo read book`                             |
| **Deadline**| `deadline TASK_DESCRIPTION /by DATE_TIME`   | `deadline submit report /by 2023-12-01 1800` |
| **Event**   | `event TASK_DESCRIPTION /from START /to END`| `event meeting /from 1400 /to 1600`          |
| **List**    | `list`                                      | `list`                                       |
| **Mark**    | `mark TASK_NUMBER`                          | `mark 2`                                     |
| **Unmark**  | `unmark TASK_NUMBER`                        | `unmark 1`                                   |
| **Delete**  | `delete TASK_NUMBER`                        | `delete 2`                                   |
| **Find**    | `find KEYWORD`                              | `find book`                                  |
| **Exit**    | `bye`                                       | `bye`                                        |

By following the guide, you can efficiently manage your tasks using the CodeCatalyst application.
