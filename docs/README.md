# November User Guide

## Introduction
November is a task management CLI tool designed to help users organize their tasks into three categories: **Todos**, **Deadlines**, and **Events**. This guide walks you through the features of November and how to interact with it.

## Quick Start
1. Ensure Java 11 or above is installed.
2. Download the latest version of November.
3. Launch November by running `java -jar November.jar`.
4. Start entering your tasks!

## Features

### 1. Add a Todo Task
Adds a Todo task to your task list.

**Format:** `todo [description]`

**Example:** `todo read book`

**Expected Output:**
```
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
```

### 2. Add a Deadline Task
Adds a Deadline task with a specified due date and time.

**Format:** `deadline [description] /by [due date]`

**Example:** `deadline submit assignment /by 2024-11-01T23:59:59`

**Expected Output:**
```
Got it. I've added this task:
  [D][ ] submit assignment (by: Nov-01-24)
Now you have 2 tasks in the list.
```

### 3. Add an Event Task
Adds an Event task that starts and ends at specific times.

**Format:** `event [description] /from [start time] /to [end time]`

**Example:** `event project meeting /from 2024-11-02T14:00:00 /to 2024-11-02T16:00:00`

**Expected Output:**
```
Got it. I've added this task:
  [E][ ] project meeting (from: Nov-02-24 to: Nov-02-24)
Now you have 3 tasks in the list.
```

### 4. List All Tasks
Lists all tasks in the task list.

**Format:** `list`

**Example:** 
```
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit assignment (by: Nov-01-24)
3. [E][ ] project meeting (from: Nov-02-24 to: Nov-02-24)
```

### 5. Mark a Task as Complete
Marks a task as done based on its index in the list.

**Format:** `mark [task index]`

**Example:** `mark 1`

**Expected Output:**
```
Nice! I've marked this task as done:
  [T][X] read book
```

### 6. Unmark a Task
Marks a task as not done.

**Format:** `unmark [task index]`

**Example:** `unmark 1`

**Expected Output:**
```
Ok, I've marked this task as not done yet:
  [T][ ] read book
```

### 7. Delete a Task
Deletes a task from the task list based on its index.

**Format:** `delete [task index]`

**Example:** `delete 2`

**Expected Output:**
```
Got it. I've removed this task:
  [D][ ] submit assignment (by: Nov-01-24)
Now you have 2 tasks in the list.
```

### 8. Find Tasks
Finds tasks that match a keyword.

**Format:** `find [keyword]`

**Example:** `find book`

**Expected Output:**
```
Searching for tasks containing the specified keyword: book
1. [T][ ] read book
```

### 9. Save Task List
The task list is automatically saved to a file `saveFile.txt` in the `data` directory after every modification.

### 10. Load Tasks
Upon launching the application, November will automatically load tasks from the `saveFile.txt` if it exists.
