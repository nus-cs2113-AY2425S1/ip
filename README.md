# JEFF Task Chatbot

## Jeff User Guide

**JEFF** is a temperamental Java chatbot who (begrudgingly) keeps track of your tasks!

## Jeff's Features

### 1. Adding a Todo Task: `todo`
**Purpose**: Add a non-time-sensitive tasks to the task list.
**Format**: `todo [description]`
   - `description` field must be non-empty.
**Examples**: 
   - `todo homework`
   - `todo something fun`

### 2. Adding a Deadline Task: `deadline`
**Purpose**: Adds a task due by a specific date to the task list.
**Format**: `deadline [description] [/by yyyy-MM-dd]`
   - `description` and `by` fields must be non-empty.
   - `by` field must be in the specified date format.
   **Examples**: 
   - `deadline homework /by 2025-10-02` Adds a deadline task with a description 'homework', which is to be 
   completed by 2nd October 2025.

### 3. Adding an Event Task
**Purpose**: Adds a tasks which  will happen within a specific date range.
**Format**: `event [description] [/from yyyy-MM-dd] [/to yyyy-MM-dd]`
   - `description`, `from` and `to` fields must be non-empty.
   - `from` and `to` fields must be in the specified date format.
   **Examples**: 
   - `event do homework /from 2025-10-02 /to 2025-10-03` Adds an event task with a description 'do homework',
   to be done from 2nd October to 3rd October 2025.

### 4. Listing all Tasks: `list`
**Purpose**: Displays all tasks the user has added.
**Format**: `list`
**Example Output**:
  ```
  1. [D][ ] Finish homework (by: Aug 9 1990)
  ...
  ```

### 5. Marking/Unmarking a Task: `mark` / `unmark`
**Purpose**: Mark a task as done or undone.
**Format**:
   - `mark [task index]`
   - `unmark [task index]`
**Examples**:
   - `mark 1`
   Output: `Marked task: [D][X] Finish homework (by: Aug 9 1990)`

   - `unmark 1`
   Output: `Unmarked task: [D][ ] Finish homework (by: Aug 9 1990)`

### 6. Deleting a Task: `delete`
**Purpose**: Removes a task from your list.
**Format**: `delete [task index]`
**Example**: `delete 1`

### 7. Finding a Task: `find`
**Purpose**: Find a task from your list based on a keyword that matches the task description.
**Format**: `find [keyword]`
**Example**: `find somekeyword`

### 8. Exiting the Program: `bye`
**Purpose**: Exit the chatbot.
**Format**: `bye`

### Saving the Data
JEFF automatically saves your tasks to a text file located in the `data` folder.
> **Note**: Please do not manually edit the text file, as incorrect formatting may cause data loss.