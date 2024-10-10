# Freedom User Guide

![freedom_screenshot.png](https://github.com/Ridiculouswifi/ip/blob/branch-A-UserGuide/assets/product_screenshot.png)

Freedom is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

- [Quick Start](#quick-start)
- [Features](#features)
  - [Adding Tasks](#adding-tasks)
    - [ToDo: `todo`](#todo-todo)
    - [Deadline: `deadline`](#deadline-deadline)
    - [Event: `event`](#event-event)
  - [List: `list`](#list-list)
  - [Marking Tasks](#marking-tasks)
    - [Mark: `mark`](#mark-mark)
    - [Unmark: `unmark`](#unmark-unmark)
  - [Delete: `delete`](#delete-delete)
  - [Find: `find`](#find-find)
  - [Exit: `bye`](#exit-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data](#editing-the-data)
---

## Quick Start

1. Ensure you have `Java 17` installed on your Computer.
2. Download the latest `.jar` file from [here]().
3. Copy the file to the home folder of your Freedom Chatbot. 
4. Open a command terminal, `cd` into the folder with the `.jar` file 
   and use `java -jar freedom.jar` command to run the application.
5. Type the command to the CLI and press `Enter` to execute it.
6. Refer to the [Features](#features) below for details of each command.
---

## Features


## Adding Tasks

### ToDo: `todo`

Adds a task with only a task description and no set deadline or duration.<br>

Format: `todo {description}`

Example: `todo wash clothes` Adds a todo task with description `wash clothes`.

Freedom will respond with the task type, description and number of current tasks.

```
________________________________________
Alright! Added this task for you:
  [T][ ] wash clothes
Now you have 1 tasks.
________________________________________
```

### Deadline: `deadline`

Adds a task with a task description and set deadline.<br>

Format: `deadline {description} /by {deadline}`<br>
`deadline` must be in the format of `dd/MM/yyyy HHmm`.

Example: `deadline assignment /by 15/10/2024 1800` Adds a deadline task with description `assignment`
and deadline as `15/10/2024 1800`.

Freedom will respond with the task type, description, deadline and number of current tasks.

```
________________________________________
Alright! Added this task for you:
  [D][ ] assignment (by: 15 Oct 2024 1800)
Now you have 2 tasks.
________________________________________
```

### Event: `event`

Adds a task with a task description and set duration.<br>

Format: `event {description} /from {start date/time} /to {end date/time}`<br>
`start date/time` and `end date/time` must be in the format of `dd/MM/yyyy HHmm`.

Example: `event concert /from 17/10/2024 1800 /to 17/10/2024 2100` Adds an event task with description
`concert`, start date/time `17/10/2024 1800` and end start/time `17/10/2024 2100`.

Freedom will respond with the task type, description, start and end date/time and number of current tasks.

```
________________________________________
Alright! Added this task for you:
  [E][ ] concert (from: 17 Oct 2024 1800 to: 17 Oct 2024 2100)
Now you have 3 tasks.
________________________________________
```


## List: `list`

Lists out all the tasks being tracked by Freedom.

Format: `list`

```
________________________________________
Here are your tasks:
1.[T][ ] wash clothes
2.[D][ ] assignment (by: 15 Oct 2024 1800)
3.[E][ ] concert (from: 17 Oct 2024 1800 to: 17 Oct 2024 2100)
________________________________________
```


## Marking Tasks

### Mark: `mark`

Marks the specified task as done.<br>
`[X]` means done.<br>
`[ ]` means not done.

Format: `mark {task id}`<br>
`task id` is found with `list` command.

Example: `mark 2` Marks the 2nd task in `list` as done.

Freedom will respond with the task and mark it as done.

```
________________________________________
Marked this task as done! Good Job!
  [D][X] assignment (by: 15 Oct 2024 1800)
________________________________________
```

### Unmark: `unmark`

Marks the specified task as not done.<br>
`[X]` means done.<br>
`[ ]` means not done.

Format: `unmark {task id}`<br>
`task id` is found with `list` command.

Example: `unmark 2` Marks the 2nd task in `list` as not done.

Freedom will respond with the task and mark it as not done.

```
________________________________________
Unmarked this task! Get it done!
  [D][ ] assignment (by: 15 Oct 2024 1800)
________________________________________
```


## Delete: `delete`

Deletes the specified task from Freedom.

Format: `delete {task id}`<br>
`task id` is found with `list` command.

Example: `delete 1` Deletes the 1st task in `list`.

```
________________________________________
Deleted this task:
  [T][ ] wash clothes
________________________________________
```


## Find: `find`

Finds and lists all tasks that contain the specified condition in the description.

Format: `find {keyword}`

Example: `find con` Lists out all tasks with the keyword `con`.

```
________________________________________
Here are your tasks:
1.[E][ ] concert (from: 17 Oct 2024 1800 to: 17 Oct 2024 2100)
________________________________________
```


## Exit: `bye`

Exits the Freedom Chatbot.

Format: `bye`


## Saving the data

Freedom tasks are saved in the hard disk automatically after any command that changes the data.


## Editing the data

Freedom tasks are saved as a `.txt` file.<br>
Filepath: `[JAR file location]/data/freedom.txt`<br>
Advanced users can update the data by directly editing the file.<br>

Format: `{task type} | {status} | {description} | {date/time 1} | {date/time 1}`<br>
`task type`: `T` for `todo`, `D` for `deadline` or `E` for `event`.<br>
`status`: `X` for done, ` ` for not done.<br>
`descrption`: Task description.<br>
`date/time 1`: In `dd MMM yyyy HHmm` format. Deadline for `deadline` and Start time for `event`<br>
`date/time 2`: In `dd MMM yyyy HHmm` format. End time for `event`

> Caution<br>
> Attempting to run the Freedom chatbot with data that is incorrectly formatted might cause unexpected behaviours.

---

## Command Summary

| Command      | Format                                                            |
|--------------|-------------------------------------------------------------------|
| Add ToDo     | `todo {description}`                                              |
| Add Deadline | `deadline {description} /by {deadline}`                           |
| Add Event    | `event {description} /from {start date/time} /to {end date/time}` |
| List         | `list`                                                            |
| Mark         | `mark {task id}`                                                  |
| Unmark       | `unmark {task id}`                                                |
| Delete       | `delete {task id}`                                                |
| Find         | `find {keyword}`                                                  |