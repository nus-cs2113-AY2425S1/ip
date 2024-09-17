# Yapper User Guide

Yapper is a CLI-based ChatBot that 
helps you manage tasks such as ToDos, Deadlines and Events, 
all while "yapping" at you.

// Product screenshot goes here

// Product intro goes here


## Legend

To perform this command,
use the command_keyword [followed by additional arguments]
e.g. `shown here is how to format input`.
```
shown here is a sample program output
```

## Adding and Deleting Tasks

To add a ToDo task, 
use the todo command followed by the task description 
e.g. `todo todoDesc`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, ya gotta do this too:
  [T][ ] todoDesc
Your list is now THIS BIG: 1
```

To add a Deadline task, 
use the deadline command followed by the task description then an end date, 
e.g. `deadline deadlineDesc /by endDate`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, ya gotta do this too:
  [D][ ] deadlineDesc, due endDate
Your list is now THIS BIG: 9
```

To add an Event task, 
use the event command followed by the event description, a start date then an end date, 
e.g. `event eventDesc /from startDate /to endDate`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, ya gotta do this too:
  [E][ ] eventDesc, from startDate to endDate
Your list is now THIS BIG: 9
```

To delete a (ToDo, Deadline or Event) task,
use the delete command followed by the task number, 
e.g. `delete 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, ya dun hafta do dis anymore: 
  [E][ ] sleep, from today 2300 to tomorrow 0800
Your list is now THIS BIG: 4
```

## Listing All Tasks

To list all tasks in Yapper, use the list command.
use the list command
e.g. `list`
```
___________________ PROGRAM OUTPUT BELOW ___________________
You don't remember your tasks? Lemme refresh your memory:
1.[T][X] todoDesc 
2.[D][ ] deadlineDesc, due endDate 
3.[E][ ] eventDesc, from startDate to endDate 
```

## Marking and Unmarking Tasks

To mark a task as complete, 
use the mark command followed by the task number,
e.g. `mark 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
This task is now done:
  [T][X] procrastinate
```

To unmark a task as incomplete, 
use the unmark command followed by the task number,
e.g. `unmark 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
This task is now undone:
  [T][] be productive
```

## Exiting Yapper

To exit the program, use the bye command, 
e.g. `bye`.