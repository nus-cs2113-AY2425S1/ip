# JerChatBot - User Guide

Welcome to JerChatBot. This guide will help you to navigate the bot's features, along with utilizing it to its best capabilities.

## Table of Contents
- [Getting Started](#getting-started)
- [Features](#features)
  - [Add Tasks: `todo`, `deadline`, `event`](#add-tasks)
  - [List Tasks: `list`](#list-tasks)
  - [Mark Tasks as Done: `mark`](#mark-tasks-as-done)
  - [Unmark Tasks: `unmark`](#unmark-tasks)
  - [Delete Tasks: `delete`](#delete-tasks)
  - [Find Tasks: `find`](#find-tasks)
  - [Exit Bot: `bye`](#exit-bot)
  - [Save and Load Task](#save-and-load)
- [FAQ](#faq)
- [Feedback](#feedback)

## Getting Started

1. **Installation**: Download and install Java (version 17) or above. Clone or download this repository.
2. **Running JerChatBot Program**:
   - Open terminal
   - Navigate via `cd` to working directory of JerChatBot
   - Run program using the following command:
     ```sh
     java -jar JerChatBot.jar
     ```

## Features

### Add Tasks

#### 1. To-Do
Add a to-do task:
```plaintext
todo <task description>
```
Example: `todo task1`

#### 2. Deadline
Add a deadline task:
```plaintext
deadline <task description> /by <yyyy-MM-dd HHmm>
```
Example: `deadline Submit assignment /by 2024-10-10 2359`

#### 3. Event
Add a event task:
```plaintext
event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>
```
Example: `event Project meeting /from 2024-10-10 1000 /to 2024-10-10 2359`


### List Tasks

#### List
List all tasks in your task list:
```plaintext
list
```

### Mark Tasks as Done
Mark specific tasks in task list as done:
```plaintext
mark <task number>
```
Example: `mark 1`

### Unmark Tasks
Unmark specific tasks in task list as not done:
```plaintext
unmark <task number>
```
Example: `unmark 1`

### Delete Tasks 
Remove a task from your task list:
```plaintext
delete <task number>
```
Example: `delete 1`

### Find Tasks
Search for task using a specific keyword:
```plaintext
find <keyword>
```
Example: `find meeting`

### Exit Bot
Close JerChatBot Program:
```plaintext
bye
```

### Save and Load Task
JerChatBot data will be saved automatically into a text file (`tasks.txt`). 
JerChatBot will load data from `tasks.txt`.

## FAQ
Qn: How can I edit an existing task?
Ans: Currently, JerChatBot does not support task edit. Please do delete an existing task and add a new entry to effectively update a task.

Qn: What date/time format does JerChatBot use?
Ans: ISO-8601 format. Dates should be formatted as `yyyy-MM-dd HHmm`.

## Feedback
For any questions or feedback related to JerChatBot, do provide us your views or on how we can improve via this [link](https://github.com/tayjerom/ip/issues) on GitHub.
