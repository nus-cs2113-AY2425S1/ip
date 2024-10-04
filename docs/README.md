# Chatty Charlie User Guide


[img.png](img.png)

Chatty Charlie is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 
If you can type fast, AB3 can track your task faster than traditional GUI apps.

## Adding deadlines
Add a task that has a specific deadline. You provide the task description along with a deadline date.

Format: deadline TASK_DESCRIPTION by DATE

Example: `deadline Submit project report by 2024-10-15`

When the command is successfully executed, it will add the task to your task list with the specified deadline. For example:

```
------------
        Added task: [D][ ] make a box (by: 04/10/2024)
------------
```

## Adding todos

Adds a simple task without a specific deadline.

Format: todo TASK_DESCRIPTION

Example: `todo Buy groceries`

When the command is successfully executed, it will add the task to your list. For example:
```
------------
        Added task: [T][ ] Buy groceries
------------
```

## Adding events
Add an event that takes place at a specific date and time.

Format: event TASK_DESCRIPTION from START_DATE START_TIME to END_DATE ENDTIME 

Example: `event Team meeting from 2024-10-01 15:00 to 2024-10-01 18:00`

When the command is successfully executed, it will add the task to your list. For example:
```
------------
Added task: [E][ ] Team meeting (from: 01/10/2024 15:00 to: 01/10/2024 18:00)
------------
```

## Marking a task as done 
Mark a task as completed. You need to provide the task number from the task list.

Format: mark TASK_NUMBER

Example: `mark 1`

When the command is successfully executed, it will mark your task with an X. For example:
```
------------
        yay, 1 down! 4 to go!
        [D][X] make a box (by: 11/12/1999)
------------
```

## Marking a task as undone
Mark a task as incompleted. You need to provide the task number from the task list.

Format: unmark TASK_NUMBER

Example: `unmark 1`

When the command is successfully executed, it will unmark the task, removing the X. For example:
```
------------
        Hmmm, not quite done yet, 4 to go!
        [D][ ] make a box (by: 04/10/2024)
------------
```

## Deleting a task 
Deletes a task from the task list. You need to provide the task number.

Format: delete TASK_NUMBER

Example: `delete 1`

When the command is successfully executed, the specified task will be removed from the list. For example:
```
------------
        Task is removed. Pending task: 4 to go!
        [D][X] make a box (by: 11/12/1999)
------------
```

## Finding tasks containing the same keyword
Finds tasks in the list that contain a specific keyword.

Format: find KEYWORD

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

## Printing the whole list of task
Displays all tasks currently in the task list.

Format: list

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

## Printing the list of deadlines and events due or happening on a specified date
Displays all task that is occuring or due on the same date

Format: print DATE

Example: `print 2024-10-05`

When the command is successfully executed, all relevant tasks for the given date will be listed. For example:
```
------------
Tasks found:
        1. [E][ ] book fair (from: 05/10/2024 15:00 to: 07/10/2024 15:00)
        2. [D][ ] read a book (by: 05/10/2024)
------------
```