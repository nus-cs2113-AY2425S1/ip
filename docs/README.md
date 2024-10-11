# Bitwise User Guide

```
____   _  _              _            
|  _ \\ (_)| |            (_)           
| |_) | _ | |_ __      __ _  ___   ___ 
|  _ < | || __|\\ \\ / /| |/ __| / _ \\ 
| |_) || || |_  \\ V  V / | |\\__ \\|  __/
|____/ |_| \\__|  \\_/\\_/  |_||___/ \\___|                                      
```

Bitwise is a user-friendly Command Line Interface (CLI) chatbot designed 
to help you manage your tasks efficiently. Whether you need to keep track 
of deadlines, organize events, or manage simple to-do items, Bitwise provides a
n intuitive way to add, delete, and monitor your tasks. With straightforward 
commands and real-time feedback, you can easily stay organized and ensure nothing 
slips through the cracks.

Get started with Bitwise and take control of your task management today!

---

## Table of Contents
- [Overview](#bitwise-user-guide)
- [Getting Started](#getting-started)
- [Quick Reference](#quick-reference)
- [Types of Tasks](#types-of-tasks)
    - [Deadlines](#deadlines)
    - [Todos](#todos)
    - [Events](#events)
- [Commands](#commands)
    - [Adding Deadlines](#adding-deadlines)
    - [Adding Events](#adding-events)
    - [Adding Todos](#adding-todos)
    - [Tracking Task Status](#tracking-task-status)
    - [Finding Tasks](#finding-tasks)
    - [Viewing Task List](#viewing-task-list)
    - [Loading Task List](#loading-task-list)
    - [Saving Task List](#saving-task-list)
    - [Exiting the Application](#exiting-the-application)

---

## Getting Started
- Check if you have Java SDK 17 or above installed using `java -version` on your terminal
- Download the [Bitwise.jar](https://github.com/cnivedit/ip/releases/tag/A-Release) file
- Navigate to the directory and run `java -jar Bitwise.jar` on the terminal

---

## Quick Reference

| Command                           | Description                        | Parameters                                              | Example                                |
|-----------------------------------|------------------------------------|---------------------------------------------------------|----------------------------------------|
| [`deadline`](#deadlines)          | Adds a deadline task               | `<task description> /by <deadline>`                     | `deadline complete assignment /by 4pm` |
| [`event`](#events)                | Adds an event                      | `<event description> /from <begin time> /to <end time>` | `event watch movie /from 4pm /to 6pm`  |
| [`todo`](#todos)                  | Adds a todo item                   | `<task description>`                                    | `todo borrow books`                    |
| [`mark`](#tracking-task-status)   | Marks a task as completed          | `<task number>`                                         | `mark 1`                               |
| [`unmark`](#tracking-task-status) | Marks a task as not completed      | `<task number>`                                         | `unmark 1`                             |
| [`find`](#finding-tasks)          | Finds tasks with matching keywords | `<keyword>`                                             | `find assignment`                      |
| [`list`](#viewing-task-list)      | Displays the list of tasks         | N/A                                                     | `list`                                 |
| [`bye`](#exiting-the-application) | Exits the application              | N/A                                                     | `bye`                                  |

---

## Types of tasks
### Deadlines: 
Indicated by `[D]` <br>
Any time-sensitive task with an established due date
[Go to adding deadlines](#adding-deadlines)
### Todos: 
Indicated by `[T]` <br>
Simple todo with task description
[Go to adding todos](#adding-todos)
### Events: 
Indicated by `[E]` <br>
Activities or events of a fixed duration or timespan
[Go to adding events](#adding-events)

---

## Commands
### Adding deadlines

**[deadline](#deadlines)**: Use this command to add an item with a deadline to the list of tasks

#### Command format: `deadline <task description> /by <deadline>`

#### Example: `deadline complete assignment /by 4pm`
```
--------------------------------------------------
        Got it! Added:
        [D] [ ] complete assignment (by: 4pm)
        Now you have 1 tasks in the list.
--------------------------------------------------
```

### Adding events

**[event](#events)**: Use this command to add an event to the list of tasks

#### Command format: `event <event description> /from <begin time> /to <end time>`

#### Example: `event watch movie /from 4pm /to 6pm`
```
--------------------------------------------------
        Got it! Added:
        [E] [ ] watch movie (from: 4pm  to: 6pm)
        Now you have 1 tasks in the list.
--------------------------------------------------
```

### Adding todos

**[todo](#todos)**: Use this command to add a todo item to the list of tasks

#### Command format: `todo <task description>`

#### Example: `todo borrow books`
```
--------------------------------------------------
        Got it! Added:
        [T] [ ] borrow books
        Now you have 1 tasks in the list.
--------------------------------------------------
```

### Tracking task status

**Legend**
- `[ ]` stands for not completed
- `[X]` stands for completed

**mark**: Use this command to mark a task as completed

#### Command format: `mark <task number>`

#### Example: `mark 1`
```
--------------------------------------------------
        Awesome, I've marked this task as completed!
        Here are the tasks in your list: 
        1. [T] [X] borrow books
        2. [D] [ ] complete assignment (by: 4pm)
        Now you have 2 tasks in the list.
--------------------------------------------------
```

**unmark**: Use this command to mark a task as not completed

#### Command format: `unmark <task number>`

#### Example: `unmark 1`
```
--------------------------------------------------
        I've added the task back in
        Here are the tasks in your list: 
        1. [T] [ ] borrow books
        2. [D] [ ] complete assignment (by: 4pm)
        Now you have 2 tasks in the list.
--------------------------------------------------
```

### Finding tasks

**find**: Use this command to find tasks with matching keywords

#### Command format: `find <keyword>`

#### Example: `find assignment`
```
--------------------------------------------------
        Here's what I found!

        1. [D] [ ] complete assignment (by: 4pm)
--------------------------------------------------
```

### Viewing task list

**list**: Use this command to display the list of tasks

#### Command format: `list`

#### Example: `list`
```
--------------------------------------------------
        Here are the tasks in your list: 
        1. [T] [ ] borrow books
        2. [D] [ ] complete assignment (by: 4pm)
        Now you have 2 tasks in the list.
--------------------------------------------------
```
### Loading task list

Bitwise automatically loads task list from the previously saved text file if present. If the file does
not exist or is found to be corrupted, a new file is created and the corrupted file is discarded.

### Saving task list

Bitwise automatically saves the task list to the path `./data/tasks.txt` each time a change is made

### Exiting the application

**bye**: Use this command to end the chatbot conversation

#### Command format: `bye`

#### Example: `bye`
```
--------------------------------------------------
        Bye, see you soon!
==================================================
```
---