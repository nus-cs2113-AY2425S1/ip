# Akshan User Guide

Akshan is a Command Line Interface (CLI) application which helps to keep track of upcoming tasks.

## Table of Contents
1. [Getting Started](#getting-started)
2. [Quick Reference](#quick-reference)
3. [Features](#features)
4. [Usage](#usage)

## Getting Started

1. Make sure you have Java 17 or above installed on your computer.
2. Download the .jar file from [here](https://github.com/yijiano/ip/releases).
3. Open a terminal in the directory which you saved the .jar file, then run the following:
```
java -jar Akshan.jar
```

## Quick Reference

| Command                                     | Parameters                              |
| ------------------------------------------- | --------------------------------------- |
| [`list`](#printing-todo-list-list)          | None                                    |
| [`todo`](#adding-to-dos-todo)               | `<task name>`                           |
| [`deadline`](#adding-deadlines-deadline)    | `<task name> /by <deadline>`            |
| [`event`](#adding-events-event)             | `<task name> /from <start> /to <end>`   |
| [`mark`](#mark-completion-mark)             | `<index>`                               |
| [`unmark`](#unmark-completion-unmark)       | `<index>`                               |
| [`delete`](#deleting-a-task-delete)         | `<index>`                               |
| [`find`](#finding-a-task-find)              | `<description experession>`             |
| [`date`](#find-a-date-date)                 | `<date>`                                |
| [`bye`](#exiting-the-application-bye)       | None                                    |

## Features

### Add Tasks

Akshan allows for adding of tasks of the following types:
- ToDo
- Deadline (Consists of due date)
- Event (Consists of start date and end date)

### Various Commands

Akshan consists of many commands which allows users to manipulate their to-do list.
Commands are explained [below](#Usage).

### Local Storage

Akshan saves past data locally, allowing users to access past data even after restarting the application.
Data is saved to `./data/Akshan.txt` in the same directory as the `Akshan.jar` file.

## Usage

### Printing Todo List: `list`

Prints the list of tasks.
Prints flavour text if the list is currently empty.

Format: `list`

Expected outcome:
The current list of tasks is printed in order of being added.
Each task is shown in the format:
- S/N
- Task Type
- Completion Status
- Description
- Deadline/Start Date (for deadline/event respectively)
- End Date (for event)

Example outcome:
```
Here are the tasks in your list:
  1.[T][X] Coursemology Exercises
  2.[T][X] Canvas Quizzes
  3.[D][ ] IP Submission (by: Mar 23 2024)
  4.[E][ ] TP (from: Mar 07 2024 to: Apr 14 2024)
```

### Exiting the application: `bye`
Gracefully shuts the application down.

Format: `bye`

Expected outcome:
The program exits after printing flavour text.

### Adding To-Dos: `todo`

Create a to do task and add it to the list.

Format: `todo <task description>`

Example of usage: `todo Coursemology Exercises`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Got it. I've added this task:
  [T][ ] <task description>
Now you have <list size> tasks in the list.
```

### Adding Deadlines: `deadline`

Create a task with a deadline and add it to the list.
Deadline can be formatted in any date convention. If not a date, it will simply save the input as a string.

Format: `deadline <task description> /by <deadline>`

Example of usage: `deadline IP Submission /by 2024-03-07`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Got it. I've added this task:
  [D][ ] <task description> (by: <deadline>)
Now you have <list size> tasks in the list.
```

### Adding Events: `event`

Create an event with a start date and end date and add it to the list.
Start and end dates can be formatted in any date convention. If not a date, it will simply save the input as a string.

Format: `event <task description> /from <start date> /to <end date>`

Example of usage: `event TP /from 2024-03-07 /to 2024-04-11`

Expected outcome:
The following message indicates that the task has successfully been added to the list.
The task is then saved to storage.
```
Got it. I've added this task:
  [E][ ] <task description> (from: <start date> to: <end date>)
Now you have <list size> tasks in the list.
```

### Mark Completion: `mark`

Mark a task, based on its index in the list, as completed.
When printing, this is denoted as an X.

Format: `mark <index>`

Example of usage: `mark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as complete.
The completion status is then saved to storage.
```
Nice! I've marked this task as done:
  [<task type>][X] <task description>
```

### Unmark Completion: `unmark`

Mark a task, based on its index in the list, as incomplete.
When printing, this is denoted as a blank space.

Format: `unmark <index>`

Example of usage: `unmark 1`

Expected outcome:
The following message indicates that the task has successfully been marked as incomplete.
The completion status is then saved to storage.
```
OK, I've marked this task as not done yet:
 [<task type>][ ] <task description>
```

### Deleting a Task: `delete`

Removes a task, based on its index in the list, from the list.

Format: `delete <index>`

Example of usage: `delete 1`

Expected outcome:
The following message indicates that the task has successfully been removed.
The updated task list is then saved to storage.
```
Got it. I've removed this task:
  [<task type>][<completion>] <task description>
Now you have 20 tasks in the list.
```

### Finding a task: `find`

Searches for tasks with description containing or matching the input expression.

Format: `find <expression>`

Example if usage: `find Coursemology`

Expected outcome:
Prints a list of the tasks which contains or matches the input expression, in order of adding into the list.
```
Got it. Here are the tasks with the matching keyword '<expression>':
  1.[<task type>][<completion>] <task description>
  2.[<task type>][<completion>] <task description>
  3.[<task type>][<completion>] <task description>
  ...
```

### Find a date: `date`

Searches for tasks which occur on a specified date.
Can only search for deadlines and events.
Date must be of format YYYY-MM-DD

Format: `data <date>`

Example of usage: `date 2024-03-06`

Expected outcome:
Prints a list of the tasks which fall on the specified date, in order of adding into the list.
```
Got it. Here are the tasks with the matching date:
  1.[<task type>][<completion>] <task description>
  2.[<task type>][<completion>] <task description>
  3.[<task type>][<completion>] <task description>
```
