# DBot - User Guide

Welcome to **DBot**, a simple command-line task management chatbot designed to help you keep track of your tasks efficiently. This guide will walk you through all the key features of DBot, explaining how to interact with it and use its commands.

## Getting Started

When you run DBot, it will greet you with a welcome message and wait for your input. The interaction with DBot happens via commands that you type. DBot will help you manage tasks such as to-dos, deadlines, and events.

### Commands Overview

DBot supports a variety of commands, each of which performs a specific action. Here's a list of the commands you can use:

- `list` – Displays all your current tasks.
- `mark <index>` – Marks a task as done.
- `unmark <index>` – Marks a task as not done.
- `todo <description>` – Adds a new to-do task.
- `deadline <description> /by <due date>` – Adds a new deadline task.
- `event <description> /from <start> /to <end>` – Adds a new event task.
- `delete <index>` – Removes a task from the list.
- `find <keyword>` – Finds and displays tasks containing a keyword.
- `bye` – Exits DBot and saves your tasks to a file.

## Command Details

### 1. `list`

Displays the current list of tasks. Each task will be shown with a number and its completion status.

Example:
```
Command: list
1. [T][ ] Read a book
2. [D][X] Submit assignment (by: Sunday)
3. [E][ ] Attend workshop (from: Monday 10am to: Monday 12pm)
```

### 2. `mark <index>`

Marks a task as completed. Replace `<index>` with the task number as shown in the `list` command.

Example:
```
Command: mark 2
Nice! I've marked this task as done:
[D][X] Submit assignment (by: Sunday)
```

### 3. `unmark <index>`

Marks a task as not completed. Replace `<index>` with the task number.

Example:
```
Command: unmark 2
OK, I've marked this task as not done yet:
[D][ ] Submit assignment (by: Sunday)
```

### 4. `todo <description>`

Adds a new to-do task with the provided description.

Example:
```
Command: todo Buy groceries
Got it. I've added this task:
[T][ ] Buy groceries
Now you have 4 tasks in the list.
```

### 5. `deadline <description> /by <due date>`

Adds a new deadline task with a description and due date.

Example:
```
Command: deadline Submit report /by Friday
Got it. I've added this task:
[D][ ] Submit report (by: Friday)
Now you have 5 tasks in the list.
```

### 6. `event <description> /from <start> /to <end>`

Adds a new event task with a description, start time, and end time.

Example:
```
Command: event Team meeting /from 2pm /to 4pm
Got it. I've added this task:
[E][ ] Team meeting (from: 2pm to: 4pm)
Now you have 6 tasks in the list.
```

### 7. `delete <index>`

Removes a task from the list. Replace `<index>` with the task number.

Example:
```
Command: delete 3
Noted. I've removed this task:
[E][ ] Attend workshop (from: Monday 10am to: Monday 12pm)
Now you have 5 tasks in the list.
```

### 8. `find <keyword>`

Searches for tasks containing the specified keyword (case-insensitive).

Example:
```
Command: find report
Here are the matching tasks in your list:
1. [D][ ] Submit report (by: Friday)
```

### 9. `bye`

Saves your tasks to a file and exits DBot.

Example:
```
Command: bye
Bye. Hope to see you again soon!
```

## Saving and Loading Data

DBot automatically saves your tasks to a file (`data/tasks.txt`) when you exit using the `bye` command. When you restart DBot, it will load your saved tasks, so you can continue where you left off.

---

That’s it! You are now ready to use DBot to manage your tasks. We hope DBot makes your life a little easier!
