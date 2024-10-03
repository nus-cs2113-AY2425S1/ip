# User Guide for Uranus
## Introduction

Uranus is an easy-to-use chatbot that helps manage tasks efficiently, 
providing a simple interface for staying organized.
---
## Adding Tasks

The `task` command allows users to add tasks without specifying any type.

### Usage:
```
task <task description>
```

### Example:
```
task Eat dinner
```

### Expected Output:
```
Got it. I've added this task:
  [ ][ ] Eat dinner
Now you have 1 task(s) in the list.
```
---

## Adding Deadlines

The `deadline` command allows users to add tasks with specific deadlines.

### Usage:
```
deadline <task> /by <deadline>
deadline <task> /by <date in dd-MM-yyyy> <time in HHmm>
```

### Example:
```
deadline Finish report /by tomorrow
deadline Finish report /by 25-12-2024 2359
```

### Expected Output:
```
Got it. I've added this task:
  [D][ ] Finish report (by: tomorrow)
Now you have 1 task(s) in the list
---------------------------------------------------
Got it. I've added this task:
  [D][ ] Finish report (by: Dec 25 2024, 11:59PM)
Now you have 1 task(s) in the list
```

---

## Adding Events

The `event` command allows users to add tasks with specific start and end times.

### Usage:
```
event <task> /from <start> /to <end>
event <task> /from <date in dd-MM-yyyy> <time in HHmm> /to <date in dd-MM-yyyy> <time in HHmm>
```

### Example:
```
event Team meeting /from tomorrow 0900 /to tomorrow 1000
event Team meeting /from 25-12-2024 0900 /to 25-12-2024 1000
```

### Expected Output:
```
Got it. I've added this task:
  [E][ ] Team meeting (from: tomorrow 0900 to: tomorrow 1000)
Now you have 1 task(s) in the list
------------------------------------------------------------------------------
Got it. I've added this task:
  [E][ ] Team meeting (from: Dec 25 2024, 9:00AM to: Dec 25 2024, 10:00AM)
Now you have 1 task(s) in the list
```

---

## Adding ToDos

The `todo` command allows users to add simple tasks without a specific time or deadline.

### Usage:
```
todo <task>
```

### Example:
```
todo Buy groceries
```

### Expected Output:
```
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 2 task(s) in the list.
```

---

## Marking Tasks as Done

The `mark` command allows users to mark tasks as done.

### Usage:
```
mark <task number>
```

### Example:
```
mark 1
```

### Expected Output:
```
Nice! I've marked this task as done:
  [D][X] Finish report (by: Dec 25 2024, 11:59PM)
```

---

## Unmarking Tasks

The `unmark` command allows users to mark tasks as not done.

### Usage:
```
unmark <task number>
```

### Example:
```
unmark 1
```

### Expected Output:
```
OK! I've marked this task as not done yet:
  [D][ ] Finish report (by: Dec 25 2024, 11:59PM)
```

---

## Listing All Tasks

The `list` command shows all tasks in the task list, including their status (done or not done).

### Usage:
```
list
```

### Expected Output:
```
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Finish report (by: Dec 25 2024, 11:59PM)
3. [E][ ] Team meeting (from: Dec 25 2024, 9:00AM to: Dec 25 2024, 10:00AM)
```

---

## Deleting Tasks

The `delete` command allows users to remove a task from the task list.

### Usage:
```
delete <task number>
```

### Example:
```
delete 1
```

### Expected Output:
```
Got it. I've removed this task:
  [T][ ] Buy groceries
Now you have 2 task(s) in the list.
```

---

## Finding Tasks

The `find` command allows users to search for tasks based on keywords.

### Usage:
```
find <keyword>
```

### Example:
```
find report
```

### Expected Output:
```
Here are the tasks in your list:
1. [D][ ] Finish report (by: Dec 25 2024, 11:59PM)
```

---

## Echo Mode

The `echo` command allows the chatbot to repeat anything the user says. It continues until the user types "exit."

### Usage:
```
echo
```

### Example:
```
echo
hi
exit
```

### Expected Output:
```
Say anything! If you are no longer bored, type exit !
-------------------------------------------------------
hi
-------------------------------------------------------
Say anything! If you are no longer bored, type exit !
-------------------------------------------------------
-------------------------------------------------------
Currently, I am able to execute the following functions:
...
```

---

## Clearing the Screen

The `clear` command simulates clearing the console screen by printing 35 blank lines.

### Usage:
```
clear
```

### Expected Output:
```
[Clears the screen by printing multiple blank lines]
```

---

## Exiting the Chatbot

The `bye` command exits the chatbot.

### Usage:
```
bye
```

### Expected Output:
```
Bye. Hope to see you again!
```

---

## Help

The `help` command lists all available commands that the chatbot can perform.

### Usage:
```
help
```

### Expected Output:
```
Currently, I am able to execute the following functions:
...
```

---
