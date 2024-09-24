# Nell - User Guide

Nell is a chatbot that allows users to manage tasks through a command-line interface. 

## Quick start

1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest `.jar` file from here.
3. Copy the file into the folder 
4. Open a new terminal in the folder you copied the `.jar` file into, and enter the following command:
   ```
   java -jar nell.jar
   ```
   Upon running the command, the following messages should be printed in your terminal:
   ```
   Hello! I'm Nell!
   What can I do for you?
   ```

## Features

> ### Notes on commands
> 
> * Words in `UPPERCASE` represent parameters that are to be entered by the user.  
> e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter, like in `todo buy bread`.
> * For commands that do not take in parameters (like `list` and `bye`), additional parameters
> entered will be ignored by the program.  
> e.g. the program will interpret `list 1-10` as `list`.
> 

### Adding `ToDo` tasks: `add`
Adds a `ToDo` task to the task list. 

Format: `todo DESCRIPTION`

Examples
* `todo buy bread` adds a `ToDo` task with description `buy bread`.

### Adding `Deadline` tasks: `deadline`
Adds a `Deadline` task to the task list.

Format: `deadline DESCRIPTION /by BY-DATE`
* The value at `BY-DATE` has to be entered in the format `yyyy-MM-dd HHmm`.

Examples
* `deadline collect laundry /by 2024-10-01 1200` adds a `Deadline` task with description `collect laundry` and  
by-date `2024-10-01 1200`.

### Adding `Event` tasks: `event`
Adds an `Event` task to the task list.

Format: `event DESCRIPTION /from FROM-DATE /to TO-DATE`
* The values at `FROM-DATE` and `TO-DATE` have to be entered in the format `yyyy-MM-dd HHmm`.

Examples
* `event do laundry /from 2024-10-01 0600 /to 2024-10-01 0800` adds an `Event` task with description `do laundry`,  
from-date `2024-10-01 0600` and to-date `2024-10-01 0800`.

### Listing all tasks: `list`
Shows a list of all tasks in the task list.

Format: `list`

### Finding tasks by keyword: `find`
Finds all tasks in the task list that contains a specified keyword.

The program then shows a list of those tasks, as follows:
```
-> The matching tasks are as follows:
   1. [T][ ] buy bread
   3. [D][ ] eat bread (by: 30/09/2024 13:30)
```

Format: `find KEYWORD`
* The finding is case-insensitive, e.g `bread` will match `Bread`.
* If multiple words are entered for `KEYWORD`, only the first word will be searched for. 
* Only tasks with entire words matching will be found, e.g `cs` will return `CS` but not `CS2113`.

Examples
* `find bread` returns all tasks that contain the word `bread`.

Note
* The indexes of the tasks in the shown list correspond to their indexes in the complete task list.

### Searching for tasks by date: `search`
Shows a list of all tasks that occur on a particular date.

Format: `search DATE`
* The value of `DATE` has to be entered in the format `yyyy-mm-dd`.
* If `DATE` is not entered in the correct format, the program prints the following message:
  ```
  -> Invalid date!
   Please key in dates in this format:
      yyyy-MM-dd
  ```

Examples
* `search 2024-10-01` shows a list of tasks that occur on `2024-10-01`

Note
* The indexes of the tasks in the shown list correspond to their indexes in the complete task list.

### Marking a task as done: `mark`
Marks an existing task as done.

Format: `mark INDEX`
* Marks the task at `INDEX` as done. The value of `INDEX` is the same as the one shown in the task list.
* The value of `INDEX` must be a positive integer (1, 2, 3,...).
* If the value of `INDEX` is not within the task list, the program prints the following message:
```
-> Invalid task!
```
  
Example
* `mark 2` marks the task at index 2 in the task list as done.

### Marking a task as not done: `unmark`
Marks an existing task as not done.

Format: `unmark INDEX`
* Marks the task at `INDEX` as not done. The value of `INDEX` is the same as the one shown in the task list.
* The value of `INDEX` must be a positive integer (1, 2, 3,...).
* If the value of `INDEX` is not within the task list, the program prints the following message:
  ```
  -> Invalid task!
  ```
  
Example
* `unmark 1` marks the task at index 1 in the task list as not done.

### Removing a task: `remove`
Removes a task from the list.

Format: `remove INDEX`
* Removes the task at `INDEX` from the task list. The value of `INDEX` is the same as the one shown in the task list.
* The value of `INDEX` must be a positive integer (1, 2, 3,...).
* If the value of `INDEX` is not within the task list, the program prints the following message:
    ```
  -> Invalid task!
  ```
  
Example
* `remove 1` removes the task at index 1 from the task list.

### Exiting the program: `bye`
Exits the program.
Upon exiting, the program shows the following message:
```
-> Bye. Hope to see you again soon!
```

Format: `bye`

## Command summary

| Action         | Format                                          | Example                                                      |
|----------------|-------------------------------------------------|--------------------------------------------------------------|
| Add `ToDo`     | `todo DESCRIPTION`                              | `todo buy bread`                                             |
| Add `Deadline` | `deadline DESCRIPTION /by BY-DATE`              | `deadline collect laundry /by 2024-10-01 1200`               |
| Add `Event`    | `event DESCRIPTION /from FROM-DATE /to TO-DATE` | `event do laundry /from 2024-10-01 0600 /to 2024-10-01 0800` |
| List           | `list`                                          | -                                                            |
| Find           | `find KEYWORD`                                  | `find bread`                                                 |
| Search         | `search DATE`                                   | `search 2024-10-01`                                          |
| Mark done      | `mark INDEX`                                    | `mark 2`                                                     |
| Mark not done  | `unmark INDEX`                                  | `unmark 1`                                                   |
| Exit           | `bye`                                           | -                                                            |