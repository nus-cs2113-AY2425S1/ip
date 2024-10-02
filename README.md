## [CS2113 - Individual Project](https://github.com/yakultbottle/ip)

# Jeremy user guide

Jeremy is a Command Line Interface(CLI) for keeping track of upcoming tasks. 

- [Quick start](#Quick-start)
- [Features](#Features)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking task as done: `mark`](#marking-task-as-done-mark)
  - [Marking task as not done: `unmark`](#marking-task-as-not-done-unmark)
  - [Deleting existing task: `delete`](#deleting-existing-task-delete)
  - [Finding tasks: `find`](#find-existing-task-find)
  - [Creating a To-Do: `todo`](#creating-a-to-do-todo)
  - [Creating a Deadline: `deadline`](#creating-a-deadline-deadline)
  - [Creating an event: `event`](#creating-an-event-event)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)

## Quick start

1. Ensure you have Java `17` installed on your Computer. 
2. Download the latest .jar file from [here](https://github.com/yakultbottle/ip/releases)
3. Copy the file to the folder you want to use as the *home folder* for the program. 
4. Open a command terminal, `cd` into the folder you put the jar file in,
   and use the `java -jar Jeremy.jar` command to run the application. 
5. Type the command in the terminal window and press Enter to execute it.
6. Refer to [Features](#Features) below for details of each command. 

## Features

> **Notes about command format:**
> 
> Words in UPPER_CASE are parameters to be passed to the command
> - e.g. in `mark INDEX`, `INDEX` is a parameter which can be used as `mark 1` 
> 
> Words in lower_case are to be typed exactly
> - e.g. in `deadline DESCRIPTION /by BY` should be typed as `deadline return book /by 2023-06-15`,
> where `deadline` and `/by` should be typed exactly
> 
> Extraneous parameters for commands that do not take in parameters (such as list, bye) will be ignored.
> - e.g. if the command is `help me with midterms`, it will be interpreted as `help`

### Listing all tasks: `list`
Lists all tasks in order that they were added in, along with their
- Number in the list
- Task type
- Task status(done or not done)
- Description
- By date (for deadline tasks only)
- Start/End date (for event tasks only)

Format: `list`

Example output:
```
list

1.[T][X] read book
2.[D][ ] return book (by: Jun 6 2024)
3.[E][ ] project meeting (from: Aug 6 2024 to: Aug 7 2024)
```

### Marking task as done: `mark`
Marks a specific task as done. 

Format: `mark INDEX`
- Marks the task at the specified `INDEX` as done

Example output:
```
mark 1

Nice! I've marked this task as done:
[T][X] read book
```

### Marking task as not done: `unmark`
Marks a specific task as not done.

Format: `unmark INDEX`
- Marks the task at the specified `INDEX` as not done

Example output:
```
unmark 2

I've unmarked this task:
[D][ ] return book (by: Jun 6 2024)
```

### Deleting existing task: `delete`
Delete a specific task.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`

Example output:
```
delete 4

Ok, deleted this task:
[T][ ] be deleted
Now you have 3 tasks in the list.
```

### Find existing task: `find`
Find all instances of tasks with certain keyword. 

Format: `find NAME`
- Finds all tasks with `NAME` in its description. 
- Returns the tasks with all the data that `list` prints

Example output:
```
find meeting

Here are the matching tasks in your list:
1.[E][X] project meeting (from: Aug 6 2024 to: Aug 7 2024)
3.[D][ ] prepare for next meeting (by: Aug 9 2024)
```

### Creating a To-do: `todo`
Creates a todo task with the specified description. 

Format: `todo DESCRIPTION`
- `DESCRIPTION` cannot be empty

```
todo read book

Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```

### Creating a Deadline: `deadline`
Creates a deadline task with the specified description and due date. 

Format: `deadline DESCRIPTION /by BY`
- `BY` has to be in the format yyyy-mm-dd, e.g. 2020-04-23

Example output:
```
deadline return book /by 2024-06-06

Got it. I've added this task:
[D][ ] return book (by: Jun 6 2024)
Now you have 2 tasks in the list.
```

### Creating an Event: `event`
Creates an event task with the specified description, from date, and due date.

Format: `event DESCRIPTION /from FROM /to TO`
- `FROM` and `TO` has to be in the format yyyy-mm-dd, e.g. 2020-04-23

Example output:
```
event project meeting /from 2024-06-06 /to 2024-06-07

Got it. I've added this task:
[E][ ] project meeting (from: Aug 6 2024 to: Aug 7 2024)
Now you have 3 tasks in the list.
```

### Exiting the program: `bye`
Exits the program. 

Format: `bye`

Example output:
```
Bye. Hope to see you again soon!
```

### Saving the data
Data is saved automatically when the program is terminated, and loaded when the program starts. 
If there is no prior data file, it will create a new file. 

### Editing the data file
Jeremy saves past data locally in `./data/Jeremy.txt` in the same directory as `Jeremy.jar`.  
Advanced users can manually update data by editing this file.

> **Warning:**  
> Edit the file at your own risk. Data that is not in the correct format will be 
> ignored by the program and will be erased once the file is saved after loading.

## Command summary
| Action       | Format, Examples                                                                                          |
|--------------|-----------------------------------------------------------------------------------------------------------|
| **list**     | `list`                                                                                                    |
| **mark**     | `mark INDEX` <br/> e.g., `mark 2`                                                                         |
| **unmark**   | `unmark INDEX` <br/> e.g., `unmark 2`                                                                     |
| **delete**   | `delete INDEX` <br/> e.g., `delete 2`                                                                     |
| **find**     | `find NAME` <br/> e.g., `find book`                                                                       |
| **todo**     | `todo DESCRIPTION` <br/> e.g., `todo read book`                                                           |
| **deadline** | `deadline DESCRIPTION /by BY` <br/> e.g., `deadline return book /by 2024-06-06`                           |
| **event**    | `event DESCRIPTION /from FROM /to TO` <br/> e.g., `event project meeting /from 2024-06-06 /to 2024-06-07` |
| **bye**      | `bye`                                                                                                     |
