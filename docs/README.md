# Tyrone User Guide

## 👋 Welcome to Tyrone! 👋

Tyrone is your dependable Java CLI companion for managing tasks like a pro.

With Tyrone, you can easily create, update, and manage your tasks using simple text commands.

Whether you need to:

- ➕ Add new tasks
- ✅ Mark tasks as completed
- ❌ Delete tasks
- 🔍 Search for tasks
- 📋 List all your current tasks

Tyrone's got your back. Let's jump in and see how Tyrone can simplify your daily tasks!

## Quick Start

❗ Below are the **prerequisites**:

1. Ensure you have Java 17 installed on your machine.
2. Download the latest `.jar` file from [here](https://github.com/paulktham/ip/releases).
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the directory where the `.jar` file is located.
5. Run the following command: `java -jar tyrone.jar`

On launch, you should see this interface:

```
___________________________________
Hello from
 _____
|_   _|   _ _ __ ___  _ __   ___
  | || | | | '__/ _ \| '_ \ / _ \
  | || |_| | | | (_) | | | |  __/
  |_| \__, |_|  \___/|_| |_|\___|
      |___/

What can I do for you cuh?

___________________________________

```

## Features

Tyrone supports 3 types of tasks: `Todo`, `Deadline`, and `Event`.

- `Todo`: Task to be completed without specific timing constraints.
- `Deadline`: Task to be completed by a specified deadline.
- `Event`: Task with a defined start and end time.

Here are some example commands:

- `todo grocery shopping`
- `deadline CS3244 tutorial /by 26/09/2024 2359`
- `event Swimming /from Thursday Morning /to Thursday Afternoon`
- `delete 1`
- `mark 1`
- `unmark 1`
- `find swim`
- `list`
- `priority list`
- `help`
- `bye`

Any unrecognized command will result in the following response:

```
___________________________________
Invalid command my brother.
___________________________________

```

## Adding Todo Task

Adds a `Todo` task to the task list.

Format: `todo <description>`

Examples:

- `todo grocery shopping`

Expected Outcome:

```
    ___________________________________
    yeye. I've added this task:
      [T][ ] grocery shopping
    Now you have 1 tasks in the list.
    ___________________________________
```

## Adding Deadline Task

Adds a `Deadline` task to the task list.

Format: `deadline <description> /by <DD/MM/YYYY 2400>`

Examples:

- `deadline CS3244 tutorial /by 26/09/2024 2359`

Expected Outcome:

```
    ___________________________________
    Got it. I've added this task:
      [D][ ] CS3244 tutorial (by: 26 Sept 2024 11:59pm)
    Now you have 2 tasks in the list.
    ___________________________________
```

## Adding Event Task

Adds an `Event` task to the task list.

Format: `event <description> /from <start> /to <end>`

Examples:

- `event Swimming /from Thursday Morning /to Thursday Afternoon`

Expected Outcome:

```
    ___________________________________
    Got it. I've added this task cuh:
      [E][ ] Swimming (from: Thursday Morning to: Thursday Afternoon)
    Now you have 3 tasks in the list.
    ___________________________________
```

## Delete Task

Delete a task from the task list.

Format: `delete <index>`

Examples:

- `delete 1`

Expected Outcome:

```
    ___________________________________
    Noted. I've removed this task:
      [T][ ] grocery shopping
    Now you have 2 tasks in the list.
    ___________________________________
```

## Mark Task

Mark a task as completed.

Format: `mark <index>`

Examples:

- `mark 1`

Expected Outcome:

```
    ___________________________________
    TIGHT! I've marked this task as done:
      [X] CS3244 tutorial
    ___________________________________
```

## Unmark Task

Unmark a task as uncompleted.

Format: `unmark <index>`

Examples:

- `unmark 1`

Expected Outcome:

```
    ___________________________________
    alright cuh, I've marked this task as unfinished:
      [ ] CS3244 tutorial
    ___________________________________
```

## Find Task With Keyword

Find and print out all the tasks related to the keyword.

Format: `find <keyword>`

Examples:

- `find swim`

Expected Outcome:

```
    ___________________________________
    Here are the matching tasks in your list:
    ___________________________________
    1. [E][ ] Swimming (from: Thursday Morning to: Thursday Afternoon)
    ___________________________________
```

## List Task

Lists all the tasks in the task list.

Format: `list`

Expected Outcome:

```
    ___________________________________
    1. [D][ ] CS3244 tutorial (by: 26 Sept 2024 11:59pm)
    2. [E][ ] Swimming (from: Thursday Morning to: Thursday Afternoon)
    ___________________________________
```

## Priority List

Lists all the deadlines, ordered by their due date.

Format: `priority list`

Expected Outcome:

```
    ___________________________________
    Here are your deadlines in order of priority:
    1. [D][ ] CS3244 tutorial (by: 26 Sept 2024 11:59pm)
    ___________________________________
```

## Clearing All Tasks

Clears all tasks from the list after confirmation.

Format: `clear`

Expected Outcome:

```
    Are you sure you want to clear all tasks? (y/n)
y
    ___________________________________
    All tasks have been cleared.
    ___________________________________
```

## Viewing help

Lists out all the available commands and their command format.

Format: `help`

Expected Outcome:

```
Available commands:
        - bye: Exit the application.
        - todo [description]: Add a new todo task.
        - deadline [description] /by [DD/MM/YYYY 2400]: Add a new task with a deadline.
        - event [description] /from [start] /to [end]: Add a new event.
        - mark [task number]: Mark a task as completed.
        - unmark [task number]: Unmark a completed task.
        - delete [task number]: Delete a task.
        - clear: Clear all tasks in the list.
        - list: List all tasks.
        - priority list: Show all the deadlines due, with the earliest deadline at the top.
        - find [keyword]: Find a task by keyword.
        - help: Show this help message.
```

## Exit Program

Exits the program.

Format: `bye`

Expected Outcome:

```
    ___________________________________
    see you brother
    ___________________________________
```

## Saving the data

Tyrone's data is saved in an external `.txt` file automatically after the `bye` command is executed. There is no need to save manually.
