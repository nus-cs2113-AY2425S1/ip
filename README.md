# Aegis Task Manager & Chatbot User Guide

Welcome to Aegis, your Anti-Shadow Suppression Weapon and Task Manager! Aegis helps you manage your daily tasks efficiently while providing a simple, command-based interface. This guide will walk you through how to set up Aegis, use its features, and make the most out of this task manager.

## Table of Contents
- [Introduction](#introduction)
- [Setting Up Aegis](#setting-up-aegis)
- [Features](#features)
  - [Adding a Task](#adding-a-task)
  - [Listing All Tasks](#listing-all-tasks)
  - [Marking a Task as Done](#marking-a-task-as-done)
  - [Unmarking a Task](#unmarking-a-task)
  - [Deleting a Task](#deleting-a-task)
  - [Finding Tasks](#finding-tasks)
  - [Exiting the Program](#exiting-the-program)
- [Command Summary](#command-summary)
- [FAQs](#faqs)

## Introduction
Aegis is a command-line based task manager that helps you keep track of your to-dos, deadlines, and events. With Aegis, you can quickly add tasks, mark them as done, find specific tasks using keywords, and delete tasks when they are no longer needed.

## Setting Up Aegis
To get started with Aegis, follow these steps:

1. **Download the latest version** of the Aegis application from the [GitHub repository](https://github.com/ThisisXXZ/ip).
2. **Ensure you have Java installed** on your computer (version 17 is recommended).
3. **Run Aegis** by executing the following command in your terminal: ``java -jar Aegis.jar``
4. **Start managing your tasks** using the command-line interface.

## Features

### Adding a Task
You can add different types of tasks: ToDos, Deadlines, and Events.

- **ToDo**: A basic task without a time constraint.
- Command: `todo <description>`
- Example: `todo Read a book`

- **Deadline**: A task that needs to be completed by a certain time.
- Command: `deadline <description> /by <date and time>`
- Example: `deadline Submit report /by 2024-09-30 1800`

- **Event**: A task that occurs at a specific time frame.
- Command: `event <description> /from <start time> /to <end time>`
- Example: `event Project meeting /from 2024-09-28 1000 /to 2024-09-28 1200`

### Listing All Tasks
Displays all the tasks in your task list.

- **Command**: `list`
- **Example**: `list`
Aegis will display all tasks with their statuses.

### Marking a Task as Done
Marks a specific task as completed.

- **Command**: `mark <task number>`
- **Example**: `mark 2`

### Unmarking a Task
Marks a specific task as not completed.

- **Command**: `unmark <task number>`
- **Example**: `unmark 2`

### Deleting a Task
Deletes a specific task from the task list.

- **Command**: `delete <task number>`
- **Example**: `delete 3`

### Finding Tasks
Searches for tasks that contain a specific keyword.

- **Command**: `find <keyword>`
- **Example**: `find book`

### Exiting the Program
Exits the Aegis application.

- **Command**: `bye`
- **Example**: `bye`

## Command Summary
| Command                     | Description                              |
|-----------------------------|------------------------------------------|
| `todo <description>`        | Adds a ToDo task.                       |
| `deadline <description> /by <date and time>` | Adds a Deadline task.                |
| `event <description> /from <start time> /to <end time>` | Adds an Event task.                  |
| `list`                      | Lists all tasks.                        |
| `mark <task number>`        | Marks a task as done.                   |
| `unmark <task number>`      | Marks a task as not done.               |
| `delete <task number>`      | Deletes a task.                         |
| `find <keyword>`            | Finds tasks containing the keyword.     |
| `bye`                       | Exits the application.                  |

## FAQs

**Q: What happens if I enter an invalid command?**  
A: Aegis will notify you that the command is not recognized and provide guidance on the correct command format.

**Q: Can I save my tasks?**  
A: Yes, Aegis automatically saves your tasks to a file every time you add, mark, unmark, or delete a task.

**Q: How do I know if a task is marked as done?**  
A: Tasks marked as done will display an `[X]` next to their description.

For any additional questions or troubleshooting, please refer to the full documentation at [Aegis GitHub Repository](https://github.com/ThisisXXZ/ip).

---

This user guide should provide you with the necessary information to efficiently use Aegis Task Manager & Chatbot. Happy task managing!


