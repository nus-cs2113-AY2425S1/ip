# Blossom User Guide

<img width="705" alt="image" src="https://github.com/user-attachments/assets/38adae40-8440-4dda-942a-0d46d83745cb">

Blossom is your personal assistant designed to help you stay organised and keeping on top of all your tasks! Whether you're a busy professional, a student juggling assignments, or anyone in between, Blossom is here to make your life easier by keeping track of your daily tasks. 

## Features
- [Adding a task: todo, deadline, event](#adding-a-task) <br>
- [Listing all tasks: list](#listing-all-tasks-list) <br>
- [Locating tasks by name: find](#locating-tasks-by-name-find) <br>
- [Deleting a task: delete](#deleting-a-task-delete) <br>
- [Marking tasks as complete: mark](#marking-tasks-as-complete-mark) <br>
- [Unmarking tasks: unmark](#unmarking-tasks-unmark) <br>
- [Exiting the program: bye](#exiting-the-program-bye) <br>
- [Saving the data](#saving-the-data) <br>

### Adding a task: 
`todo` <br>
Adds a todo task to the task list. <br>
Format: `todo TASK_DESCRIPTION` <br>
**Example**: <br>
`todo read book`

`deadline` <br>
Adds a deadline to the task list. <br>
Format: `deadline TASK_DESCRIPTION /by TIME` <br>
**Example**: <br>
`deadline do assignment /by Friday`

`event` <br>
Adds an event to the task list. <br>
Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME` <br>
**Example**: <br>
`event team meeting /from Friday 3pm /to Friday 5pm`

### Listing all tasks: list
Shows all tasks in the task list. <br>
Format: `list`

### Location tasks by name: find
Find tasks whose description matches the keyword. <br>
Format: `find KEYWORD` <br>
**Example**: <br>
`find book` returns tasks with the keyword "book" in it.

### Deleting a task: delete
Deletes the specified task from the task list. <br>
Format: `delete INDEX` <br>

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
**Example** <br>
`delete 2` deletes the second task in the task list.

### Marking tasks as complete: mark
Marks the specified task in the task list as complete. <br>
Format: `mark INDEX` <br>

- Marks the task at the specified INDEX as complete.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
**Example** <br>
`mark 1` marks the first in the task list as complete.

### Unmarking tasks: unmark
Marks the specified task in the task list as incomplete. <br>
Format: `unmark INDEX`

- Marks the task at the specified INDEX as incomplete.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​
  **Example** <br>
`unmark 1` marks the first in the task list as incomplete.

### Exiting the program : bye
Exits the program. <br>
Format: `bye`

### Saving the data
Task data are saved in the hard disk automatically after the bye command. There is no need to save manually.
