# Quinn Task Manager User Guide

Quinn is a personal assistant chatbot that helps in Task Management. <br>
It is a desktop application optimized to use from a Command Line Interface (CLI). <br>
Quinn helps to keep track of your to-dos, deadlines, and events. <br>
With Quinn, you can easily add, list, mark, and delete tasks using simple commands. <br>
This User Guide(UG) is also found at this link : [Quinn User Guide](https://kaboomzxc.github.io/ip/)

## Table of Contents
1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Command Reference](#command-reference)
4. [Understanding Task Types](#understanding-task-types)
5. [Error Handling](#error-handling)
6. [Data Storage](#data-storage)
7. [Quinn Command Summary](#quinn-command-summary)

## Quick Start

1. Ensure you have Java 17 installed on your computer.
2. Download the latest `Quinn.jar` file from [here](https://github.com/kaboomzxc/ip/releases).
3. Copy the file to your desired folder.
4. Open a command prompt or terminal, navigate to the folder containing Quinn.jar, and run: **java -jar Quinn.jar**


## Features

### Adding Tasks

Quinn supports three types of tasks: ToDos, Deadlines, and Events.

#### Adding a ToDo
Command: `todo <description>` <br>
Example: `todo Buy groceries`

#### Adding a Deadline
Command: `deadline <description> /by <date> <time>` <br>
Example: `deadline Submit report /by 2023-05-15 1400` <br>
* For proper recognition of `date` and `time`,
   * `date` has to be entered first, followed by `time`.
   * `date` has to be in the format `yyyy-MM-dd` (e.g. `2024-01-21`).
   * `time` has to be in the format `HHmm` (e.g. `1400`) and can be optional. 
   * Nonetheless, any other string e.g. 13121995 will still be accepted, albeit not formatted
   * (i.e. the same exact string "13121995" would be displayed.)

#### Adding an Event
Command: `event <description> /from <start date> <start time> /to <end date> <end time>` <br>
Example: `event Team meeting /from 2023-06-20 0900 /to 2023-06-20 1100` <br>
* For proper recognition of `date` and `time`,
   * `date` has to be entered first, followed by `time`.
   * `date` has to be in the format `yyyy-MM-dd` (e.g. `2024-01-21`).
   * `time` has to be in the format `HHmm` (e.g. `1800`) and can be optional.
   * Nonetheless, any other string e.g. 13121995 will still be accepted, albeit not formatted
   * (i.e. the same exact string "13121995" would be displayed.)

### Examples of Tasks Display

After using these commands, tasks will be displayed in the following format:

1. ToDo: `[T][ ] Buy groceries`
2. Deadline: `[D][ ] Submit report (by: Jun 30 2023 02:00 PM)`
3. Event: `[E][ ] Team meeting (from: Jun 15 2023 09:00 AM to: Jun 15 2023 11:00 AM)`

### Listing Tasks
Command: `list`

### Marking Tasks as Done
Command: `mark <task number>`
Example: `mark 1`

### Unmarking Tasks
Command: `unmark <task number>`
Example: `unmark 2`

### Deleting Tasks
Command: `delete <task number>`
Example: `delete 3`

### Finding Tasks
Command: `find <keyword>`
Example: `find meeting`

### Exiting the Application
Command: `bye`


## Command Reference
Here is a quick reference for all available commands:

• todo [description]: Add a ToDo task <br>
• deadline [description] /by [date] [time]: Add a Deadline task <br>
• event [description] /from [start date] [start time] /to [end date] [end time]: Add an Event task <br>
• list: Display all tasks <br>
• mark [task number]: Mark a task as done <br>
• unmark [task number]: Mark a task as not done <br>
• delete [task number]: Remove a task <br>
• find [keyword]: Search for tasks containing the keyword <br>
• bye: Exit the application

## Understanding Task Types
Quinn supports three types of tasks:

• ToDo: A simple task without any date/time constraint.

Displayed as: [T][ ] <Task Description>


• Deadline: A task with a specific due date and time.

Displayed as: [D][ ] <Task Description> (by: Date Time)


• Event: A task with a start and end date/time.

Displayed as: [E][ ] <Task Description> (from: Start Date Time to: End Date Time)

The square brackets next to the task type indicator (T/D/E) show the task's completion status: <br>
[ ]: Task is not done <br>
[✔]: Task is done

## Error Handling
   Quinn will display error messages if it encounters issues. Here are some common errors and their meanings:

"INVALID COMMAND. Please try again!": The command entered is not recognized. <br>
"The description of a todo cannot be empty!": You need to provide a description for the ToDo task. <br>
"INCOMPLETE COMMAND": Some required information is missing from the command. <br>
"Task not found. Please try again!": The task number provided doesn't exist in the list. <br>
"Please enter a valid task number to be marked as done!": The mark/unmark command requires a valid task number. <br>

If you encounter an error, read the error message carefully and adjust your command accordingly.


## Data Storage
   Quinn automatically saves your tasks to a file named "tasks.txt" in a "data" folder in the same directory as the Quinn.jar file. This ensures that your tasks persist between sessions.
   Note: Do not modify the "tasks.txt" file manually, as this may cause Quinn to malfunction.




## Quinn Command Summary

| Command | Description | Example |
|---------|-------------|---------|
| `todo [description]` | Add a ToDo task | `todo Buy groceries` |
| `deadline [description] /by [date] [time]` | Add a Deadline task | `deadline Submit report /by 2023-06-30 1400` |
| `event [description] /from [start date] [start time] /to [end date] [end time]` | Add an Event task | `event Team meeting /from 2023-06-15 0900 /to 2023-06-15 1100` |
| `list` | Display all tasks | `list` |
| `mark [task number]` | Mark a task as done | `mark 1` |
| `unmark [task number]` | Mark a task as not done | `unmark 2` |
| `delete [task number]` | Remove a task | `delete 3` |
| `find [keyword]` | Search for tasks containing the keyword | `find meeting` |
| `bye` | Exit the application | `bye` |





