# Sirius User Guide

Welcome to Sirius, your personal task management chatbot! 

This is a command-line-based chatbot designed to help you organize and track your tasks. 

It supports various task-related commands, such as adding, marking as done/undone, deleting, finding tasks, and listing all tasks.
It also saves your tasks to a file so you can pick up where you left off even after restarting the program.


## Quick Start

1. Ensure you have Java 17 or above installed in your Computer.
2. Download the latest .jar file from here.
3. Copy the file to the folder you want to use as the home folder for your Task Manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar Sirius.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it. e.g. typing help and pressing Enter will open the help window.
   Some example commands you can try:

    `list` : Lists all tasks.

    `todo read books`: Adds todo task named read books.

    `deadline return books /by Friday`: Adds deadline task named return books, and the deadline line is on Friday.

    `event attend lecture /from 2pm /to 4pm`: Adds event task named attend lecture, and the time is from 2pm to 4pm.

    `mark 3` : Mark the 3rd task in the task list as done.

    `unmark 3` : Unmark the 3rd task in the task list.

    `delete 3` : Deletes the 3rd task in the task list.

    `find book`: Find all tasks that have the keyword "book".

    `exit`: Exits and Sirius will save your task list.

6. Refer to the Features below for details of each command.


## Features
Notes about the command format:

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in todo TODO_TASK, TODO_TASK is a parameter which can be used as todo read books.

Words in angle brackets are determined by the actual situation.
e.g. in todo <task_num>, task_num refers to the total number of tasks in the list.

### Adding a task：  `todo` `deadline` `event`
There are three types of tasks you can add to the task list: Todo, Deadline and Event.

#### Todo task
Add a simple task without any date/time.

Example: `todo TODO_TASK`

The expected output:
```
-----------------------------
Got it. I've added this task:
[T][ ] TODO_TASK
Now you have <task_num> tasks in the list.
-----------------------------
```

#### Deadline task

Add a task with time for deadline.

Example: `deadline DEADLINE_TASK /by DEADLINE_TIME`

The expected output:
```
-----------------------------
Got it. I've added this task:
[D][ ] DEADLINE_TASK (by: DEADLINE_TIME)
Now you have <task_num> tasks in the list.
-----------------------------
```

#### Event task

Add a task with time for deadline.

Example: `event EVENT_TASK /from FROM_TIME /to TO_TIME`

The expected output:
```
-----------------------------
Got it. I've added this task:
[E][ ] EVENT_TASK (from: FROM_TIME to: TO_TIME)
Now you have <task_num> tasks in the list.
-----------------------------
```


### Marking a task

Mark a task with the specified index as done.

Example: `mark TASK_INDEX`

The expected output (assume the corresponding task is project2.1 with a deadline):
```
-----------------------------
Nice! I've marked this task as done:
[D][X] project2.1 (by: Oct. 12th)
-----------------------------
```

### Unmarking a task

Unmark a task with the specified index.

Example: `unmark TASK_INDEX`

The expected output (assume the corresponding task is project2.1 with a deadline):
```
-----------------------------
OK, I've marked this task as not done yet:
[D][ ] project2.1 (by: Oct. 12th)
-----------------------------
```

### Listing a task

List all tasks and their states in the task list.

Example: `list`

The expected output (assume there is a corresponding task list):
```
-----------------------------
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] project2.1 (by: Oct. 12th)
3. [E][X] return book (from: Friday 4pm to: 5pm)
-----------------------------
```

### Deleting a task

Delete a task with the specified index.

Example: `delete TASK_INDEX`

The expected output (assume the corresponding task is project2.1 with a deadline):
```
-----------------------------
Got it. I've removed this task:
[D][ ] project2.1 (by: Oct. 12th)
Now you have <task_num> tasks in the list.
-----------------------------
```

### Finding a task

Find the tasks with the specified keyword.

Example: `find KEYWORD`

The expected output (assume the keyword is "book"):
```
-----------------------------
Here are the matching tasks in your list:
1. [T][ ] read book
2. [E][X] return book (from: Friday 4pm to: 5pm)
-----------------------------
```

### Exiting

Exit the program.

Example: `bye`

The expected output:
```
-----------------------------
Goodbye! Hope to see you soon!
-----------------------------
```

### Saving the data
Data are saved in the hard disk automatically after any command that changes the task list. 
There is no need to save manually.
