# Anke User Guide

A chat bot

## Loading stored data
Upon running the application, data stored in "./Anke.txt" will be loaded into the application.
Expected output:
```
Loading data from file
... (message of loaded data)
Finish loading data
```
If no file is found, then the expected output is :
```
Loading data from file
no input file found.
```
## Welcome message
After loading data, a welcome message will be shown
```
Hello! I'm Anke.
What can I do for you?
```
## Automatic saving
data modified in after command will be saved automatically into "Anke.txt"

## Functionalities
### 0. Help
Any input other than commands specified by below will show all available functionalities of the application.
```
hi
```
output:
```
Please follow the format (parameter inside {} must be non-empty!) : 

list : visualizing tasks
mark {int n} : set task number {n} as done
unmark {int n} : set task number {n} as not done
todo {String s} : create todo with description {s}
deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}
event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}
delete {int n} : remove task number {n} from the list
find {String s} : find tasks with description containing s as substring
bye : quit the program
```
### 1. List
show all tasks in the application now

Example: `list`

Example output:
```
1. [T][] ip
```
### 2. Mark tasks
able to mark task as done

format: 
```
mark {int n} : set task number {n} as done
```
n can be obtained by the index of task by list function

Example: `mark 1`

Expected output: 
```
Nice! I've marked this task as done:
[T][X] ip
```
if n is not an integer

Expected output:
```
Please enter a valid number after mark
```
if a task is already marked

Expected output:
```
Task is already marked
```
if a task index out of bound is given

Expected output:
```
Please enter a task index from x to y
```
x and y are the range of acceptable integers
### 3. Unmark tasks
able to unmark task as not done yet

format:
```
unmark {int n} : set task number {n} as not done
```
n can be obtained by the index of task by list function

Example: `unmark 1`

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] ip
```
if n is not an integer

Expected output:
```
Please enter a valid number after unmark
```
if a task is already marked

Expected output:
```
Task is already unmarked
```
if a task index out of bound is given

Expected output:
```
Please enter a task index from x to y
```
x and y are the range of acceptable integers
### 4. Add todos
create todo with user specified description

format:
```
todo {String s} : create todo with description {s}
```
Example: `todo ip`

Expected output: (x is the number of tasks stored in the application now)
```
Got it. I've added this task:
[T][ ] ip
Now you have {x} tasks in the list.
```
if description of task is empty:

Expected output:
```
The description of a task cannot be empty.
```
### 5. Add deadlines
create deadline with user specified description and due time information

format:
```
deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}
```
Example: `deadline ip /by tmr`

Expected output: (x is the number of tasks stored in the application now)
```
Got it. I've added this task:
[D][ ] ip (by: tmr)
Now you have {x} tasks in the list.
```
if description of task is empty:

Expected output:
```
The description of a task cannot be empty.
```
if /by is not entered or empty after /by

Expected output:
```
Please enter the deadline after "/by". 
```
### 6. Add events
create event with user specified description and starting time and ending time information

format:
```
event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}
```
Example: `event ip /from today /to tmr`

Expected output: (x is the number of tasks stored in the application now)
```
Got it. I've added this task:
[E][ ] ip (from: today to: tmr)
Now you have {x} tasks in the list.
```
if description of task is empty:

Expected output:
```
The description of a task cannot be empty.
```
if /from is not entered or empty after /from

Expected output:
```
Please enter the event after "/from".
```
if /to is not entered or empty after /to

Expected output:
```
Please enter the end time of event after "/to". 
```
### 7. Delete tasks
delete task with user specified task index

format:
```
delete {int n} : remove task number {n} from the list
```
Example: `event ip /from today /to tmr`

Expected output: (x is the number of tasks remaining after deletion)
```
Noted. I've removed this task: 
[T][ ] ip
Now you have {x} tasks in the list.
```
if delete is empty

Expected output:
```
Please follow the format (parameter inside {} must be non-empty!) : 

list : visualizing tasks
mark {int n} : set task number {n} as done
unmark {int n} : set task number {n} as not done
todo {String s} : create todo with description {s}
deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}
event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}
delete {int n} : remove task number {n} from the list
find {String s} : find tasks with description containing s as substring
bye : quit the program
```
if input index is not an integer

Expected output:
```
Please enter a valid number after delete
```
if index is not in range

Expected output:
if a task index out of bound is given

Expected output:
```
Please enter a task index from x to y
```
x and y are the range of acceptable integers
### 8. Find tasks
find all tasks that match user specified substring

format:
```
find {String s} : find tasks with description containing s as substring
```
Example: `find ip`

Expected output: 
```
Tasks found:

[D][ ] ip (by: tmr)

```
if no substring is given after delete
Expected output:
```
Please follow the format (parameter inside {} must be non-empty!) : 

list : visualizing tasks
mark {int n} : set task number {n} as done
unmark {int n} : set task number {n} as not done
todo {String s} : create todo with description {s}
deadline {String s1} /by {String s2} : create deadline with description {s1} and due date {s2}
event {String s1} /from {String s2} /to {String s3} : create event with description {s1} from {s2} to {s3}
delete {int n} : remove task number {n} from the list
find {String s} : find tasks with description containing s as substring
bye : quit the program
```
### 9. Bye
quit program

format:
```
bye
```

Expected output:
```
Bye. Hope to see you again soon!
```
