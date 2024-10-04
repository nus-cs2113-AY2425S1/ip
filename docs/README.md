# Ryan User Guide

- [Quick start](#Quick-start)
- [Features](#Features)
    - [Creating a To-Do: `todo`](#creating-a-to-do-todo)
    - [Creating a Deadline: `deadline`](#creating-a-deadline-deadline)
    - [Creating an Event: `event`](#creating-an-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Marking task as done: `mark`](#marking-a-task-as-done-mark)
    - [Marking task as not done: `unmark`](#marking-a-task-as-not-done-unmark)
    - [Deleting existing task: `delete`](#deleting-a-task-delete)
    - [Finding tasks: `find`](#finding-a-task-find)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quick start
1. Ensure you have Java `17` installed on your Computer.
2. Download the latest .jar file from [here]()
3. Copy the file to the folder you want to use as the *home folder* for the program.
4. Open a command terminal, `cd` into the folder you put the jar file in,
   and use the `java -jar Ryan.jar` command to run the application.
5. Type the command in the terminal window and press Enter to execute it.
6. Refer to [Features](#Features) below for details of each command.

## Features
> **Notes about command format:**
>
> Words in UPPER_CASE are parameters supplied by the user.
> - e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo Assignment`
>
> Words in lower_case are to be typed exactly
> - e.g. in `deadline DESCRIPTION /by BY` should be typed as `deadline Quiz /by 2024-10-15`,
    > where `deadline` and `/by` should be typed exactly
>
> Extraneous parameters for commands that do not take in parameters (such as list, bye) will be ignored.
> - e.g. if the command is `list everything`, it will be interpreted as `list`

### Creating a To-do: `todo`
Creates a todo task with a description.

Format: `todo DESCRIPTION`
- `DESCRIPTION` cannot be empty

Example:
```
todo tutorial
_________________________________________________________
Got it. I've added this task:
  [T][ ] tutorial
Now you have 1 tasks in the list.
_________________________________________________________
```

### Creating a Deadline: `deadline`
Creates a deadline task with a description and due date.

Format: `deadline DESCRIPTION /by BY`
- `DESCRIPTION` cannot be empty
- `BY` cannot be empty

Example:
```
deadline quiz /by 2024-09-20
_________________________________________________________
Got it. I've added this task:
  [D][ ] quiz (by: 2024-09-20)
Now you have 2 tasks in the list.
_________________________________________________________
```

### Creating an Event: `event`
Creates an event task with a description, start and end time.

Format: `event DESCRIPTION /from START /to END`
- `DESCRIPTION` cannot be empty
- `START` cannot be empty
- `END` cannot be empty

Example:
```
event lecture /from 2024-09-25 /to 2024-09-26
_________________________________________________________
Got it. I've added this task:
  [E][ ] lecture (from: 2024-09-25 to: 2024-09-26)
Now you have 3 tasks in the list.
_________________________________________________________
```

### Listing all tasks: `list`
Lists all tasks in order that they were added in, along with their
- Number in the list
- Task type
- Task status (done or not done)
- Description
- By date (for deadline tasks only)
- Start/End date (for event tasks only)

Format: `list`

Example:
```
list
list
_________________________________________________________
Here are the tasks in your list:
1. [T][ ] tutorial
2. [D][ ] quiz (by: 2024-09-20)
3. [E][ ] lecture (from: 2024-09-25 to: 2024-09-26)
_________________________________________________________
```

### Marking a task as done: `mark`
Marks a specified task as done.

Format: `mark INDEX`
- Marks the task specified by `INDEX` as done
- `INDEX` cannot be empty
- `INDEX` has to be within the valid range of the task list

Example:
```
mark 1
_________________________________________________________
Nice! I've marked this task as done:
  [T][X] tutorial
_________________________________________________________
```

### Marking a task as not done: `unmark`
Marks a specified task as not done.

Format: `unmark INDEX`
- Marks the task specified by `INDEX` as not done
- `INDEX` cannot be empty
- `INDEX` has to be within the valid range of the task list

Example:
```
unmark 1
_________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] tutorial
_________________________________________________________
```

### Deleting a task: `delete`
Removes a specified task from the task list.

Format: `delete INDEX`
- Removes the task specified by `INDEX`
- `INDEX` cannot be empty
- `INDEX` has to be within the valid range of the task list

Example:
```
delete 2
_________________________________________________________
Noted. I've removed this task:
  [D][ ] quiz (by: 2024-09-20)
Now you have 2 tasks in the list.
_________________________________________________________
```

### Finding a task: `find`
Filters tasks in which contain the given keywords.

Format: `find KEYWORD`
- Only the description is searched
- Order of keywords matters
- If multiple keywords are used, only results with all keywords will be shown

Example:
```
find tutorial
_________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] tutorial
_________________________________________________________
```

### Exiting the program: `bye`
Exits the program.

Format: `bye`

Example:
```
bye
_________________________________________________________
Goodbye! Have a great day.
_________________________________________________________
```

### Saving the data

Data is saved automatically when the program is terminated, and loaded when the program starts.
If there is no existing data file, the program will create a new data file.

### Editing the data file

Ryan saves past data locally in `./data/ryan.txt` in the same directory as `Ryan.jar`.  
Users can also manually update data by editing this file.

## Command summary

| Action       | Format, Examples                                                                                  |
|--------------|---------------------------------------------------------------------------------------------------|
| **todo**     | `todo DESCRIPTION` <br/> e.g., `todo tutorial`                                                    |
| **deadline** | `deadline DESCRIPTION /by BY` <br/> e.g., `deadline quiz /by 2024-09-20`                          |
| **event**    | `event DESCRIPTION /from FROM /to TO` <br/> e.g., `event lecture /from 2024-09-25 /to 2024-09-26` | 
| **list**     | `list`                                                                                            |
| **mark**     | `mark INDEX` <br/> e.g., `mark 1`                                                                 |
| **unmark**   | `unmark INDEX` <br/> e.g., `unmark 1`                                                             |
| **delete**   | `delete INDEX` <br/> e.g., `delete 2`                                                             |
| **find**     | `find NAME` <br/> e.g., `find tutorial`                                                           |
| **bye**      | `bye`                                                                                             |