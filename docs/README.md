# TulipTask

## Overview

TulipTask allows users to manage tasks, including to-dos, events, and deadlines. It provides a command-line interface where users can add, delete, mark task as done, and list tasks. The tasks are saved to a file so they persist across sessions.

---

## Features

- **Add Tasks**
    - **To-Do**: A basic task without any time constraints.
    - **Deadline**: A task with a specific deadline.
    - **Event**: A task that has a defined start and end time.

- **Manage Tasks**
    - Mark tasks as done or not done.
    - Delete tasks from the task list.

- **File Storage**
    - Tasks are saved to and loaded from a file, making them persistent between sessions.

- **Task Searching**
    - You can search for tasks based on keywords in the task description.

---

## Commands

### 1. `todo <description>`
Adds a To-Do task to the task list.

- **Example**:
    ```
    todo Read a book
    ```

### 2. `deadline <description> /by <due date>`
Adds a Deadline task to the task list.

- **Example**:
    ```
    deadline Submit assignment /by Sunday
    ```

### 3. `event <description> /from <start time> /to <end time>`
Adds an Event task to the task list.

- **Example**:
    ```
    event Project meeting /from Monday 2pm /to Monday 4pm
    ```

### 4. `list`
Displays all tasks in the list along with their statuses.

- **Example**:
    ```
    list
    ```

### 5. `mark <task number>`
Marks a specific task as done based on its task number.

- **Example**:
    ```
    mark 2
    ```

### 6. `unmark <task number>`
Marks a specific task as not done based on its task number.

- **Example**:
    ```
    unmark 3
    ```

### 7. `delete <task number>`
Deletes a specific task based on its task number.

- **Example**:
    ```
    delete 4
    ```

### 8. `find <keyword>`
Finds and lists tasks that contain the specified keyword in their description.

- **Example**:
    ```
    find book
    ```

### 9. `save`
Manually saves the current task list to the file.

- **Example**:
    ```
    save
    ```

### 10. `load`
Loads tasks from the file if they exist.

- **Example**:
    ```
    load
    ```

### 11. `bye`
Exits the application, automatically saving all tasks to the file.

- **Example**:
    ```
    bye
    ```

---

## Task Types

### 1. **To-Do Task**
- A basic task with no specific time or deadline.
- **Format**: `[T][status] description`

### 2. **Deadline Task**
- A task that must be completed by a specific deadline.
- **Format**: `[D][status] description (by: deadline)`

### 3. **Event Task**
- A task that occurs within a specific time range.
- **Format**: `[E][status] description (from: start time to: end time)`

### Status
- `X` means the task is done.
- ` ` means the task is not done.

---

## File Storage

- Tasks are saved in a file located at `./data/data.txt`.
- Tasks are loaded automatically when the application starts, if the file exists.
- If the data directory or file does not exist, they will be created automatically.

---

## Running the Application

1. **Compile and Run**:
    - Ensure all the required classes (`Task`, `ToDo`, `Deadline`, `Event`, `TaskList`, `Ui`, `Storage`, `InputParser`) are compiled.
    - Run the `Ui` class which provides the main interaction loop for the application.

2. **Interacting with the App**:
    - Upon starting, you will be greeted with a welcome message, and you can enter commands as described above.

3. **Exiting**:
    - Use the `bye` command to save all tasks and exit the application.

---

## Example Usage

```
Hello, I'm TulipTask
What can I do for you today?

> todo Read a book
Okay! I have added this task:
[T][ ] Read a book
You currently have 1 task in your list.

> deadline Submit assignment /by Sunday
Okay! I have added this task:
[D][ ] Submit assignment (by: Sunday)
You currently have 2 tasks in your list.

> event Project meeting /from Monday 2pm /to Monday 4pm
Okay! I have added this task:
[E][ ] Project meeting (from: Monday 2pm to: Monday 4pm)
You currently have 3 tasks in your list.

> list
Here are your current tasks:
1. [T][ ] Read a book
2. [D][ ] Submit assignment (by: Sunday)
3. [E][ ] Project meeting (from: Monday 2pm to: Monday 4pm)

> mark 1
Great job! I have marked this task as done:
[T][X] Read a book

> find assignment
Here are the matching tasks in your list:
[D][ ] Submit assignment (by: Sunday)

> bye
Bye! Hope to see you again soon :)
```

---

## Future Enhancements

- **Task Editing**: Add functionality to modify existing tasks.
- **Recurring Tasks**: Implement support for tasks that repeat on a regular schedule.
- **Priority Levels**: Allow users to assign priority levels to tasks.

---
