# Blossom User Guide

<img width="705" alt="image" src="https://github.com/user-attachments/assets/38adae40-8440-4dda-942a-0d46d83745cb">

Blossom is your personal assistant designed to help you stay organised and keeping on top of all your tasks! Whether you're a busy professional, a student juggling assignments, or anyone in between, Blossom is here to make your life easier by keeping track of your daily tasks. 

## Features
[Adding a task: todo, deadline, event](adding-a-task)
[Listing all tasks: list]()
Locating tasks by name: find
Deleting a task: delete
Marking tasks as complete: mark
Unmarking tasks: unmark
Exiting the program : bye
Saving the data

### Adding a task: 
`todo` <br>
Adds a todo task to the task list. <br>
Format: `todo TASK_DESCRIPTION`

`deadline` <br>
Adds a deadline to the task list. <br>
Format: `deadline TASK_DESCRIPTION /by TIME`

`event` <br>
Adds an event to the task list. <br>
Format: `event TASK_DESCRIPTION /from START_TIME /to END_TIME
`
### Listing all tasks: list
Shows all tasks in the task list. <br>
Format: `list`

### Location tasks by name: find
Find tasks whose description matches the keyword. <br>
Format: `find KEYWORD`

### Deleting a task: delete
Deletes the specified task from the task list. <br>
Format: `delete INDEX`

- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

### Marking tasks as complete: mark
Marks the specified task in the task list as complete. <br>
Format: `mark INDEX`

- Marks the task at the specified INDEX as complete.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

### Unmarking tasks: unmark
Marks the specified task in the task list as incomplete. <br>
Format: `mark INDEX`

- Marks the task at the specified INDEX as incomplete.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …​

### Exiting the program : bye
Exits the program.
Format: `bye`

### Saving the data
Task data are saved in the hard disk automatically after the bye command. There is no need to save manually.
