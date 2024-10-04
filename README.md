# Archibald - Task Management Chatbot

## User Guide

Welcome to Archibald, your personal task management assistant! This guide will help you navigate through Archibald's features and make the most of your task management experience.

### Table of Contents
1. [Getting Started](#getting-started)
2. [Basic Commands](#basic-commands)
3. [Task Types](#task-types)
4. [Managing Tasks](#managing-tasks)
5. [Finding and Filtering Tasks](#finding-and-filtering-tasks)
6. [Saving and Loading](#saving-and-loading)
7. [Exiting Archibald](#exiting-archibald)

### Quick Start

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest Archibald.jar file
3. Copy the file to your desired folder for Archibald.
4. Open a command terminal, navigate to the folder containing the jar file, and run:
   ```
   java -jar Archibald.jar
   ```
5. Archibald will launch with a welcome message. You can now start entering commands.

Some example commands to try:

- `list`: Lists all tasks.
- `todo Read a tome`: Adds a new todo task.
- `deadline Submit scroll /by 2023-04-15 1400`: Adds a new deadline task.
- `event Wizard meeting /from 2023-04-10 1000 /to 2023-04-10 1200`: Adds a new event task.
- `done 1`: Marks the first task as done.
- `delete 2`: Deletes the second task.
- `find scroll`: Finds tasks containing the word "scroll".
- `bye`: Exits the application.

Refer to the sections below for more details on each command and feature.

### Basic Commands

- `list`: Display all tasks
- `bye`: Exit the application

### Task Types

Archibald supports three types of tasks:

1. **Todo**: A basic task without a specific time
2. **Deadline**: A task with a specific due date and time
3. **Event**: A task with a start and end time

### Managing Tasks

#### Adding Tasks

- Add a Todo: `todo <description>`
  Example: `todo Read a tome`

- Add a Deadline: `deadline <description> /by <yyyy-MM-dd HHmm>`
  Example: `deadline Submit scroll /by 2023-04-15 1400`

- Add an Event: `event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
  Example: `event Wizard meeting /from 2023-04-10 1000 /to 2023-04-10 1200`

#### Marking Tasks as Done

- Mark a task as done: `done <task_number>`
  Example: `done 1`

#### Deleting Tasks

- Delete a task: `delete <task_number>`
  Example: `delete 2`

### Finding and Filtering Tasks

- Find tasks by keyword: `find <keyword>`
  Example: `find scroll`

- Show tasks happening on a specific date: `happening <yyyy-MM-dd>`
  Example: `happening 2023-04-15`

### Saving and Loading

Archibald automatically saves your tasks to a file. When you restart the application, it will load your previously saved tasks.

### Exiting Archibald

To exit Archibald, simply type `bye` and press Enter. Your tasks will be automatically saved before the application closes.

---

We hope this guide empowers you to conquer your tasks with Archibald by your side! May your productivity soar and your to-do list tremble before your newfound organizational prowess. Now go forth, dear taskmaster, and let Archibald be your trusty companion in the epic quest of Getting Things Done!
