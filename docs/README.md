# Cuboyd Taskmanager User Guide

## Getting Started


1. Make sure you have Java 17 installed on your computer.
2. Download the `.jar` file.
3. Run the following command in the directory which you saved the `.jar` file:

```
java -jar cuboyd.jar
```

## Features

### List tasks

List all available tasks

Format: `list`

### Find tasks

Finds list of tasks with the keyword

Format: `find <keyword>`
- `<keyword>` is a string

Examples:
- `find book`

### ToDo

Creates a ToDo Task.

Format: `todo <description>`
- `<description>` is a string

Examples:
- `todo read book`

### Deadline

Creates a Deadline task with a `/by` date

Format: `deadline <description> /by <by date>`
- `<description>` is a string
- `<by date>` is a string with no specified format

Examples:
- `deadline read book /by Mon 6pm`

### Event

Creates an event task with a `/from` and a `/to` date

Format: `event <description> /from <from date> /to <to date>`
- `<description>` is a string
- `<from date>` is a string with no specified format
- `<to date>` is a string with no specified format

Examples:
- `event read book /from Mon 6pm /to Mon 9pm`

### Mark tasks

Mark task at the specified index as done

Format: `mark <task_index>`
- `<task_index>` is a positive integer representing the related task index in the list

Examples:
- `mark 1`


### Unmark tasks

Unmark task at the specified index (make it undone)

Format: `unmark <task_index>`
- `<task_index>` is a positive integer representing the related task index in the list

Examples:
- `unmark 3` 


### Delete task

Delete task at the specified index in the list

Format: `delete <task_index>`
- `<task_index>` is a positive integer representing the related task index in the list

Examples:
- `delete 3`


