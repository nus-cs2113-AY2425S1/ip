# Chatty Charlie User Guide
![Starting Image](./images/intro.png)

**Chatty Charlie** is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, Chatty Charlie can track your task faster than traditional GUI apps.

## Quick Start
1. Ensure you have **Java 17** or above installed on your computer.
2. Download the latest `.jar` file from [here](https://github.com/Bev-low/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Chatty Charlie.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the command `java -jar ip.jar` to run the application.

5. A GUI similar to the image below should appear in a few seconds.
![Starting Image](./images/intro.png)

6. Type the command in the command box and press Enter to execute it.
### Some example commands you can try: 
- `list` : Lists all tasks 
- `todo Buy a book` : Adds a todo task with task description Buy a book to the task list.

7. Refer to the [Features](#features) below for details of each Command.

--- 

## Features 

### Adding a deadline task
Add a task that has a specific deadline. You provide the task description along with a deadline date.

Format: `deadline DESCRIPTION by DATE TIME`
- **DESCRIPTION**: Task description
- **DATE**: The deadline date in format `YYYY-MM-DD` 
- **TIME**: The deadline time in format `HH:MM` 

Example: `deadline make a box by 2024-10-15 15:00`

When the command is successfully executed, it will add the task to your task list with the specified deadline. For example:

```
------------
        Added task: [D][ ] make a box (by: 04/10/2024)
------------
```

### Adding a todo task

Adds a simple task without a specific deadline.

Format: `todo DESCRIPTION`
- **DESCRIPTION**: Task description

Example: `todo Buy groceries`

When the command is successfully executed, it will add the task to your list. For example:
```
------------
        Added task: [T][ ] Buy groceries
------------
```

### Adding an event task
Add an event that takes place at a specific date and time.

Format: `event DESCRIPTION from START_DATE START_TIME to END_DATE ENDTIME`
- **DESCRIPTION**: Task description
- **START_DATE**: The start date of the event in format `YYYY-MM-DD`
- **START_TIME**: The start time of the event in format `HH:MM`
- **END_DATE**: The end date of the event in format `YYYY-MM-DD`
- **END_TIME**: The end time of the event in format `HH:MM`

 Example: `event Team meeting from 2024-10-01 15:00 to 2024-10-01 18:00`

When the command is successfully executed, it will add the task to your list. For example:
```
------------
Added task: [E][ ] Team meeting (from: 01/10/2024 15:00 to: 01/10/2024 18:00)
------------
```

### Marking a task
Mark a task as completed. You need to provide the task number from the task list.

Format: `mark TASK_NUMBER`
- **TASK-NUMBER**: The task index that you want to mark as completed.
- The index is shown on the task list.


Example: `mark 1`

When the command is successfully executed, it will mark your task with an X. For example:
```
------------
        yay, 1 down! 4 to go!
        [D][X] make a box (by: 11/12/1999)
------------
```

### Unmarking a task
Mark a task as incompleted. You need to provide the task number from the task list.

Format: `unmark TASK_NUMBER`
- **TASK-NUMBER**: The task index that you want to mark as not completed.
- The index is shown on the task list.

Example: `unmark 1`

When the command is successfully executed, it will unmark the task, removing the X. For example:
```
------------
        Hmmm, not quite done yet, 4 to go!
        [D][ ] make a box (by: 04/10/2024)
------------
```

### Deleting a task 
Deletes a task from the task list. You need to provide the task number.

Format: `delete TASK_NUMBER`
- **TASK-NUMBER**: The task index that you want to delete.
- The index is shown on the task list.

Example: `delete 1`

When the command is successfully executed, the specified task will be removed from the list. For example:
```
------------
        Task is removed. Pending task: 4 to go!
        [D][X] make a box (by: 11/12/1999)
------------
```

### Finding specific tasks
Finds tasks in the list that contain a specific keyword.

Format: `find KEYWORD`
- **KEYWORD**: It is a `STRING` variable that you want to search for in your list. 

Example:   `find book`

When the command is successfully executed, all tasks containing the keyword will be listed. For example:
```
------------
Tasks found:
        1. [T][ ] buy a book
        2. [D][ ] read a book (by: 02/10/2024)
        3. [E][ ] book fair (from: 05/10/2024 15:00 to: 07/10/2024 15:00)
------------
```

### List all tasks
Displays all tasks currently in the task list.

Format: `list`

When the command is successfully executed, all tasks will be printed. For example:
```
------------
Task List:
pending Task: 5
        1. [T][ ] buy a book
        2. [D][ ] read a book (by: 02/10/2024)
        3. [E][ ] book fair (from: 05/10/2024 15:00 to: 07/10/2024 15:00)
        4. [T][ ] make a box
        5. [E][ ] go for a run (from: 08/10/2024 07:00 to: 08/10/2024 08:00)
------------
```

### List tasks with specific timeline
Displays all task that is occuring or due on the same date

Format: `print DATE`
- **DATE**: The date of tasks you want to find associated to it.

Example: `print 2024-10-05`

When the command is successfully executed, all relevant tasks for the given date will be listed. For example:
```
------------
Tasks found:
        1. [E][ ] book fair (from: 05/10/2024 15:00 to: 07/10/2024 15:00)
        2. [D][ ] read a book (by: 05/10/2024)
------------
```

### Exit 
Displays all task that is occuring or due on the same date

Format: `bye`

When the command is successfully executed, all relevant tasks for the given date will be listed. For example:
```
------------
Charlie: We saved your file in: data/tasks.txt
Charlie: All the best in clearing your list!
```

---

## Command Summary

| Command      | Description                                                | Format                                 |
|--------------|------------------------------------------------------------|----------------------------------------|
| **Deadline** | Adds a deadline task.                                      | `deadline DESCRIPTION by DATE TIME`                                 |
| **Todo**     | Adds a todo task.                                          | `todo DESCRIPTION`                     |
| **Event**    | Adds a event task.                                         | `event DESCRIPTION from START_DATE START_TIME to END_DATE ENDTIME`    |
| **Mark**     | Mark a task as completed.                                  | `mark TASK_NUMBER` |
| **Unmark**   | Mark a task as incompleted.                                | `unmark TASK_NUMBER`                           |
| **Delete**   | Deletes a task from the task list.                         | `delete TASK_NUMBER`                         |
| **Find**     | Finds tasks in the list that contain a specific keyword.   | `find KEYWORD`                         |
| **List**     | Displays all tasks currently in the task list.             | `list`                         |
| **Print**    | Displays all task that is occuring or due on the same date | `print DATE`                                |
| **Bye**      | Exits the application.                                     | `bye`                                  |
