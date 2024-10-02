# Yukee User Guide

Welcome to **Yukee**, your friendly task management chatbot! Yukee is designed to help you manage your tasks efficiently, keeping track of what needs to be done, upcoming deadlines, and important events. Below is a guide to help you get started and utilize Yukee to its fullest.

## Table of Contents
- [Introduction](#introduction)
- [Quick Start](#quick-start)
- [Features](#features)
    - [Add a Todo](#add-a-todo)
    - [Add a Deadline](#add-a-deadline)
    - [Add an Event](#add-an-event)
    - [List All Tasks](#list-all-tasks)
    - [Mark a Task as Done](#mark-a-task-as-done)
    - [Unmark a Task](#unmark-a-task)
    - [Delete a Task](#delete-a-task)
    - [Find a Task](#find-a-task)
    - [Help](#help)
- [Command Summary](#command-summary)

## Introduction
**Yukee** is a task management assistant that allows you to:
- Keep track of your todos, deadlines, and events.
- Easily find, add, delete, and update tasks.
- Stay organized and never miss a task again!

## Quick Start
1. Ensure you have **Java 11** installed.
2. Download the latest version of Yukee from the [releases page](https://github.com/YukeeHong/ip/releases).
3. Open your terminal and navigate to the folder where Yukee is located.
4. Run the chatbot using the command:
   ```sh
   java Yukee

## Features

### Add a Todo
Adds a simple todo task to your list.
- Format: todo <task_description>
- Example: 
    ```sh
  todo read book


### Add a Deadline

Adds a task with a deadline.
- Format: deadline <task_description> /by <d/M/yyyy HHmm>
- Example:
    ```sh
  deadline return book /by 2/12/2019 1800

### Add an Event

Adds an event task with a start and end time.
- Format: event <task_description> /from <d/M/yyyy HHmm> /to <d/M/yyyy HHmm>
- Example:
    ```sh
  event project meeting /from 10/12/2019 0900 /to 10/12/2019 1100

### List All Tasks

Adds a simple todo task to your list.
- Format: list
- Example:
    ```sh
  list
  
### Mark a Task as Done

- Marks a specific task as done.
- Format: mark <task_number>
- Example
    ```sh
  mark 2

### Unmark a Task

- Unmarks a specific task..
- Format: unmark <task_number>
- Example
    ```sh
  unmark 2


### Delete a Task

- Deletes a specific task from the list.
- Format: delete <task_number>
- Example
    ```sh
  delete 2

### Find a Task

- Finds tasks containing a specific keyword.
- Format: find <keyword>
- Example
    ```sh
  find book
  
### Help

- Displays a list of available commands.
- Format: help
- Example
    ```sh
  help
  

## Command Summary
| Command           | Format                                      | Example                                 |
|-------------------|--------------------------------------------|-----------------------------------------|
| Add a Todo        | `todo <task_description>`                  | `todo read book`                        |
| Add a Deadline    | `deadline <task_description> /by <date>`   | `deadline return book /by 2/12/2019 1800` |
| Add an Event      | `event <task_description> /from <date> /to <date>` | `event project meeting /from 10/12/2019 0900 /to 10/12/2019 1100` |
| List Tasks        | `list`                                     | `list`                                  |
| Mark Task Done    | `mark <task_number>`                       | `mark 2`                                |
| Unmark Task       | `unmark <task_number>`                     | `unmark 2`                              |
| Delete Task       | `delete <task_number>`                     | `delete 2`                              |
| Find Task         | `find <keyword>`                           | `find book`                             |
| Help              | `help`                                     | `help`                                  |



