# Yappatron User Guide

Yappatron is a useful app for managing tasks in a calendar. 

## Features 

### List all tasks: `list`
Lists down all tasks that were previously input by the user.<br/>
Format: `list`


### Mark task as done: `mark`
Marks an existing entry in the list of tasks. <br/>
Format: `mark INDEX`
- Marks the task at the specified index. Index refers to the index number displayed as shown in the list.
- Marked tasks are denoted by [X] in the list.
<br/>
Example:`mark 1` Marks the first task in the list as done.


### Unmark task as done: `unmark`
Unmarks an existing entry in the list of tasks.<br/>
Format: `unmark INDEX`
- Unmarks the task at specified index. Index refers to index number displayed as shown in the list.
- Unmarked tasks are denoted by [] in the list.
<br/>
Example:`unmark 1` Unmarks the first task in the list as done.


### Add a Todo task: `todo`
Adds a new task that is a Todo, with no deadlines or times. <br/>
Format: `todo ACTIVITY NAME` <br/>
Example: `todo water plants`


### Add a Deadline task: `deadline`
Adds a new task that is a Deadline, with a specific deadline in the form of a date and time. <br/>
Format: `deadline ACTIVITY NAME by DEADLINE`
- Deadline must be provided in form of dd/MM/yyyy HHmm
- Output when listed will be shown in the form MMM dd yyyy H:mm (eg Oct 11 2024 23:59)
<br/>
Example: `deadline CS2113 iP by 11/10/2024 2359`


### Add an Event task: `event`
Adds a new event task, with a specific from and to (duration) specified.<br/>
Format: `event ACTIVITY NAME from START TIME to END TIME`
- Start and end time does not have to be in dd/MM/yyyy HHmm format like in add deadline.
- Start and end time can be generic (eg from Mon 12am to 2am)
<br/>
Example: `event night cycling from Mon 10pm to Tues 5am`


### Delete a task from record: `delete`
Deletes a task from the list. <br/>
Format: `delete INDEX`
- Deletes the task at the specified index. Index refers to the index number displayed as shown in the list.
<br/>
Example: `delete 1` deletes first task in the list


### Find task: `find`
Finds a task based on the filter keyword(s) provided.<br/>
Format: `find KEYWORD(s)`<br/>
Example: `find book` filters list to show only tasks with the word "book" in the task name.
