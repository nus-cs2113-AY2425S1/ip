# Andy Bot

Welcome to **Andy Bot**, your personal assistant to manage tasks, deadlines, and events effortlessly. Andy helps you keep track of all your tasks with an easy-to-use interface, and all your inputs are stored safely so you never lose track of important tasks.

## Overview

Andy Bot is designed to help you stay on top of your task management. You can add, delete, and mark tasks as done, as well as search for specific tasks in your list. Andy supports three types of tasks: ToDo, Deadline, and Event tasks.

### Task Types
- **ToDo**: Task with just a name.
- **Deadline**: Task with a name and a deadline.
- **Event**: Task with a name, start time, and end time.

### Storage
Andy stores all your tasks in a file called **andy.txt** located in the `data` directory. If the file doesn't exist, Andy will automatically create it for you when you first run the application.

## Running Andy Bot
### Prerequisites:
- Java version 11 or higher installed
- Latest JAR file of Andy Bot

### Steps to Run:
1. Open your terminal or command prompt.
2. Change directory to the location where the JAR file is stored.
3. Run the command:
   ```bash
   java -jar Andy.jar

To exit the application, simply type `bye`.

## Features

### 1. List All Tasks
**Command**: `list`

Displays all tasks in the current list.

### 2. Add a ToDo Task
**Command**: `todo NAME`

Adds a task with the specified name.

**Example**:
```
todo Complete CS2103T project
```

**Expected Outcome**:
```
Added: Complete CS2103T project
```

### 3. Add a Deadline Task
**Command**: `deadline NAME /by DEADLINE`

Adds a task with a specified deadline.

**Example**:
```
deadline Submit report /by 2024-10-11
```

**Expected Outcome**:
```
Added: Submit report (by: 2024-10-11)
```

### 4. Add an Event Task
**Command**: `event NAME /from START /to END`

Adds an event task with a start and end time.

**Example**:
```
event Project meeting /from 2024-10-10 2PM /to 2024-10-10 4PM
```

**Expected Outcome**:
```
Added: Project meeting (from: 2024-10-10 2PM to 2024-10-10 4PM)
```

### 5. Mark a Task as Done
**Command**: `mark INDEX`

Marks a task at the specified index as done.

**Example**:
```
mark 1
```

**Expected Outcome**:
```
Nice! I've marked this task as done:
[X] Complete CS2103T project
```

### 6. Unmark a Task
**Command**: `unmark INDEX`

Marks a task at the specified index as not done.

**Example**:
```
unmark 1
```

**Expected Outcome**:
```
OK, I've marked this task as not done yet:
[ ] Complete CS2103T project
```

### 7. Delete a Task
**Command**: `delete INDEX`

Deletes the task at the specified index.

**Example**:
```
delete 1
```

**Expected Outcome**:
```
Deleted: Complete CS2103T project
```

### 8. Find Tasks by Keyword
**Command**: `find KEYWORD`

Finds and displays all tasks that match the specified keyword.

**Example**:
```
find project
```

**Expected Outcome**:
```
Here are the matching tasks in your list:
1. [T][ ] Complete CS2103T project
```

### 9. Exit the Application
**Command**: `bye`

Closes Andy Bot and saves your tasks.

**Example**:

```
bye
```

**Expected Outcome**
```
Bye. Hope to see you again soon!
```







