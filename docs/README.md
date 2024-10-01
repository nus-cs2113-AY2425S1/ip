# XiaoMe User Guide

XiaoMe - Your personal task manager bot!

## Introduction
XiaoMe is a Command Line Interface (CLI) chatbot designed to help you manage your tasks efficiently. It allows users to add, list, and manipulate tasks via simple text commands, while providing a user-friendly experience with helpful prompts and feedback.

## Features

### Adding a to-do: `todo`
Adds a new to-do task to your task list.

- **Format**: `todo TASK_DESCRIPTION`

- **Example**:
    ```bash
    todo Buy groceries
    ```

- **Output**:
    ```less
	 ____________________________________________________________
	 Got it. I've added this task:
	 	[T][ ] Buy groceries
	 Now you have 1 tasks in the list.
	 ____________________________________________________________
    ```

### Adding a deadline: `deadline`
Adds a new deadline task with a specified due date.

- **Format**: `deadline TASK_DESCRIPTION /by DATE`

- **Example**:
    ```bash
    deadline Finish assignment /by Monday
    ```

- **Output**:
    ```less
  ____________________________________________________________
    Got it. I've added this task:
      [D][ ] Finish assignment (by: Monday)
    Now you have 2 tasks in the list.
  ____________________________________________________________
    ```

### Adding an event: `event`
Adds a new event with a specified start and end time.

- **Format**: `event TASK_DESCRIPTION /from START /to END`

- **Example**:
    ```vbnet
    event Team meeting /from 5PM /to 6PM
    ```

- **Output**:
    ```less
  ____________________________________________________________
    Got it. I've added this task:
      [E][ ] Team meeting (from: 5PM to: 6PM)
    Now you have 3 tasks in the list.
  ____________________________________________________________
    ```

### Listing all tasks: `list`
Displays all tasks currently in the task list.

- **Format**: `list`

- **Output**:
    ```less
  ____________________________________________________________
    Here are the tasks in your list:
    1. [T][ ] Buy groceries
    2. [D][ ] Finish assignment (by: Monday)
    3. [E][ ] Team meeting (from: 5PM to: 6PM)
  ____________________________________________________________
    ```

### Marking tasks as done: `mark`
Marks the specified task as completed.

- **Format**: `mark INDEX`

- **Example**:
    ```css
    mark 1
    ```

- **Output**:
    ```less
  ____________________________________________________________
    Nice! I've marked this task as done:
      [T][X] Buy groceries
  ____________________________________________________________
    ```

### Unmarking tasks: `unmark`
Marks the specified task as not done.

- **Format**: `unmark INDEX`

- **Example**:
    ```bash
    unmark 1
    ```

- **Output**:
    ```less
  ____________________________________________________________
    OK, I've marked this task as not done yet:
      [T][ ] Buy groceries
  ____________________________________________________________
    ```

### Deleting tasks: `delete`
Deletes the specified task from the list.

- **Format**: `delete INDEX`

- **Example**:
    ```arduino
    delete 2
    ```

- **Output**:
    ```less
  ____________________________________________________________
    Noted. I've removed this task:
      [D][ ] Finish assignment (by: Monday)
    Now you have 2 tasks in the list.
  ____________________________________________________________
    ```

### Exiting the program: `bye`
Exits the program.

- **Format**: `bye`

- **Expected Output**:
    ```css
  ____________________________________________________________
    Bye. Hope to see you again soon!
  ____________________________________________________________
    ```

## Command Summary
| Action        | Format                                       | Example                                 |
|---------------|----------------------------------------------|-----------------------------------------|
| Add to-do     | `todo TASK_DESCRIPTION`                      | `todo Buy groceries`                    |
| Add deadline  | `deadline TASK_DESCRIPTION /by DATE`         | `deadline Finish assignment /by Monday` |
| Add event     | `event TASK_DESCRIPTION /from START /to END` | `event Team meeting /from 5PM /to 6PM`  |
| List tasks    | `list`                                       | `list`                                  |
| Mark task     | `mark INDEX`                                 | `mark 1`                                |
| Unmark task   | `unmark INDEX`                               | `unmark 1`                              |
| Delete task   | `delete INDEX`                               | `delete 2`                              |
| Exit          | `bye`                                        | `bye`                                   |
