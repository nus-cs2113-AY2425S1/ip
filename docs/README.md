# Jeff User Guide 

## Introduction

Jeff is command-line-based chatbot to help users keep track of their tasks! 
There are 3 types of tasks available in Jeff: `Todo`, `Deadline` and `Event`

This guide will bring you through the various features of Jeff, and how to utilise them!

## Jeff's Features

### 1. Adding a Todo Task: `todo`

**Purpose**: Adds a non-time-sensitive task to the task list.

**Format**: `todo [description]`
- `description` field must be non-empty.

**Example**: `todo homework`

**Expected output**: 
```
Haiyaa the following task needs to be done:
  [T][ ] homework
Now you have 1 task(s) in ur list
```

### 2. Adding a Deadline Task: `deadline`
**Purpose**: Adds a task due by a specific date to the task list. 

**Format**: `deadline [description] [/by yyyy-MM-dd]` 
- `description` and `by` fields must be non-empty. 
- `by` field must be in the specified date format. 

**Example**: `deadline homework /by 2025-10-02` 

**Expected output**:
```
Haiyaa the following task needs to be done:
  [D][ ] homework (by: Oct 2 2025)
Now you have 2 task(s) in ur list
```

### 3. Adding an Event Task: `event`
**Purpose**: Adds a task which  will happen within a specific date range. 

**Format**: `event [description] [/from yyyy-MM-dd] [/to yyyy-MM-dd]` 
- `description`, `from` and `to` fields must be non-empty.  
- `from` and `to` fields must be in the specified date format. 

**Example**: `event do homework /from 2025-10-02 /to 2025-10-03` 

**Expected output**:
```
Haiyaa the following task needs to be done:
  [E][ ] do homework (from: Oct 2 2025 to: Oct 3 2025)
Now you have 3 task(s) in ur list
```

### 4. Listing all Tasks: `list`
**Purpose**: Displays all tasks the user has added.

**Format**: `list`

**Example Output**:
```
orh hor never finish ur tasks:
1.[T][ ] homework
2.[D][ ] homework (by: Oct 2 2025)
3.[E][ ] do homework (from: Oct 2 2025 to: Oct 3 2025)
```

### 5. Marking a task: `mark` 
**Purpose**: Mark a task which is complete by specifying its task number.

**Format**: `mark [task index]`

**Example**: `mark 1`

**Expected Output**:
```
ogei marked task dOnE
[T][X] homework
```

### 6. Unmarking a task: `unmark`
**Purpose**: Unmark a task which is uncomplete by specifying its task number.

**Format**:
- `unmark [task index]`

**Example**:
- `unmark 1`

**Expected Output**:
```
womp womp task not finished :(
[T][ ] homework
```

### 7. Deleting a Task: `delete`
**Purpose**: Removes a task from your list.

**Format**: `delete [task index]`

**Example**: `delete 1`

**Expected Output**:
```
i have reeeemoved the taskkk: 
[T][ ] homework
YAYYYY!!! Only 2 task(s) left in ur list!
```

### 8. Finding a Task: `find`
**Purpose**: Find a task from your list based on a keyword that matches the task description.

**Format**: `find [keyword]`

**Example**: `find homework`

**Expected Output**:
```
here's all ur matches:
1.[D][ ] homework (by: Oct 2 2025)
2.[E][ ] do homework (from: Oct 2 2025 to: Oct 3 2025)
```
  
### 9. Exiting the Program: `bye`

**Purpose**: Exit the chatbot.

**Format**: `bye`

**Expected Output**:
```
Bye. Hope to see you again soon!
```

### Saving the Data
Jeff automatically saves your tasks to a text file located in the `data` folder.
> **Note**: Do not manually edit the text file, as incorrect formatting may cause data loss.