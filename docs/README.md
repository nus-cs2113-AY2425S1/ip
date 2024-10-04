
# Bebe Task Manager User Guide

---

## Quick Start

1. **Install Java**: Ensure you have Java `17` or above installed on your computer.
2. **Download the Application**: Download the latest `.jar` file from [here].
3. **Set Up**: Copy the `.jar` file to the folder you want to use as the _home folder_ for Bebe Task Manager.
4. **Run the Application**:
  - Open a command terminal.
  - Navigate (`cd`) into the folder where you placed the `.jar` file.
  - Run the application with:
    ```bash
    java -jar bebe.jar
    ```
5. **Using the Application**: The application is now running, and you can start entering commands.

---

## Key Features

Bebe Task Manager supports several essential task management features:
1. **ToDo**: A basic task with no time constraints.
2. **Deadline**: A task that has a due date and time.
3. **Event**: A task with a defined start and end time.
4. **Mark Tasks**: Mark tasks as completed.
5. **Unmark Tasks**: Unmark tasks to mark them as incomplete.
6. **Delete Tasks**: Remove tasks from the list.
7. **Find Tasks**: Search tasks by keywords.
8. **View All Tasks**: List all tasks.
9. **Exit Program**: Terminate the program.

---

## Command Format

ℹ️ **Notes**:
- Words in `UPPER_CASE` are placeholders for user-supplied parameters.  
  e.g., `todo TASK` means `todo` is the command and `TASK` is the user-provided task description, such as `todo Read a book`.
- Commands can be entered in any order as long as the required parameters are present.
- Invalid or extra parameters for commands that don’t accept arguments (e.g., `list`, `bye`) will be ignored.

---

## Managing Tasks

### 1. Adding a ToDo Task
Adds a simple task without a deadline.

- **Format**:
  ```bash
  todo TASK
  ```
- **Example**:
  ```bash
  todo Read a book
  ```
- **Output**:
  ```plaintext
  Got it. I've added this task:
    [T][ ] Read a book
  ```

---

### 2. Adding a Deadline Task
Adds a task with a specific deadline.

- **Format**:
  ```bash
  deadline TASK /by DATE_TIME
  ```
  - `DATE_TIME` should be in the format `yyyy-MM-dd HHmm`.

- **Example**:
  ```bash
  deadline Submit assignment /by 2024-10-10 2359
  ```
- **Output**:
  ```plaintext
  Got it. I've added this task:
    [D][ ] Submit assignment (by: Oct 10 2024, 11:59 pm)
  ```

---

### 3. Adding an Event Task
Adds a task with both a start and end time.

- **Format**:
  ```bash
  event TASK /from START_DATE_TIME /to END_DATE_TIME
  ```
  - `START_DATE_TIME` and `END_DATE_TIME` should be in the format `yyyy-MM-dd HHmm`.

- **Example**:
  ```bash
  event Project presentation /from 2024-10-20 1010 /to 2024-10-22 2359
  ```
- **Output**:
  ```plaintext
  Got it. I've added this task:
    [E][ ] Project presentation (from: Oct 20 2024, 10:10 am to: Oct 22 2024, 11:59 pm)
  ```

---

### 4. Listing All Tasks
Displays a list of all tasks.

- **Format**:
  ```bash
  list
  ```
- **Example**:
  ```bash
  list
  ```
- **Output**:
  ```bash
  Here are the tasks in your list:
  1. [T][ ] Read a book
  2. [D][ ] Submit assignment (by: Oct 10 2024, 11:59 pm)
  3. [E][ ] Project presentation (from: Oct 20 2024, 10:10 am to: Oct 22 2024, 11:59 pm)
  ```

---

### 5. Marking a Task as Done
Marks a task at the specified index as completed.

- **Format**:
  ```bash
  mark INDEX
  ```
- **Example**:
  ```bash
  mark 2
  ```
- **Output**:
  ```plaintext
  Nice! I've marked this task as done:
    [D][X] Submit assignment (by: Oct 10 2024, 11:59 pm)
  ```

---

### 6. Unmarking a Task
Unmarks a task at the specified index as not done.

- **Format**:
  ```bash
  unmark INDEX
  ```
- **Example**:
  ```bash
  unmark 2
  ```
- **Output**:
  ```plaintext
  OK, I've marked this task as not done yet:
    [D][ ] Submit assignment (by: Oct 10 2024, 11:59 pm)
  ```

---

### 7. Deleting a Task
Removes a task from the list by its index.

- **Format**:
  ```bash
  delete INDEX
  ```
- **Example**:
  ```bash
  delete 1
  ```
- **Output**:
  ```plaintext
  Noted. I've removed this task:
    [T][ ] Read a book
  ```

---

### 8. Finding Tasks
Searches for tasks that contain a specific keyword in their description.

- **Format**:
  ```bash
  find KEYWORD
  ```
- **Example**:
  ```bash
  find project
  ```
- **Output**:
  ```plaintext
  Here are the matching tasks in your list:
  1. [E][ ] Project presentation (from: Oct 20 2024, 10:10 am to: Oct 22 2024, 11:59 pm)
  ```

---

### 9. Exiting the Program
Exits the program.

- **Format**:
  ```bash
  bye
  ```
- **Example**:
  ```bash
  bye
  ```
- **Output**:
  ```plaintext
  Bye. Hope to see you again soon!
  ```

---
## Error Handling

### Invalid Commands
If the system does not recognize a command, it will return an error message such as:
```bash
Unknown command: <your input>
Please use the 'help' command to see the command list.
```

### Missing Arguments
If a command is entered without the required arguments (e.g., `mark` without a task number), the system will notify you of the missing information:
```bash
The 'mark' command requires a valid task number.
```

### Out-of-Range Task Indices
If you try to mark, delete, or unmark a task that does not exist (e.g., task number 10 when there are only 5 tasks), the system will alert you:
```bash
Task number does not exist.
```

### Invalid Date Format
If an invalid date format is entered for a deadline or event, the system will respond with:
```bash
The 'deadline' command requires a valid date format (e.g., YYYY-MM-DD).
```

### Empty Input
If no command is entered, the system will prompt the user to enter a valid command:
```bash
Please enter a valid command. Type 'help' to see available commands.
```

---

---

## Saving Data

Bebe Task Manager automatically saves your tasks to a file after any modification. You don't need to save manually.

---

## Editing the Data File

The task data is saved in a `tasks.txt` file. You can edit this file directly, but ensure the format remains valid to avoid errors when loading the data.

**Caution:**  
If you make changes that corrupt the format, Bebe Task Manager may discard all your tasks and start fresh with an empty list the next time it's run. Always back up the data file before making edits.

---

## Command Summary

| Action                  | Format                                                                 |
|-------------------------|------------------------------------------------------------------------|
| **Add a Todo Task**     | `todo <task description>`                                              |
| **Add a Deadline Task** | `deadline <task description> /by <due date>`                           |
| **Add an Event Task**   | `event <task description> /from <start date time> /to <end date time>` |
| **List All Tasks**      | `list`                                                                 |
| **Mark a Task as Done** | `mark <task number>`                                                   |
| **Unmark a Task**       | `unmark <task number>`                                                 |
| **Delete a Task**       | `delete <task number>`                                                 |
| **Find Tasks**          | `find <keyword>`                                                       |
| **Exit the Program**    | `bye`                                                                  |
| **Help**                | `help`                                                                 |
