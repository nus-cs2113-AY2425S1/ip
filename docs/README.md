# Yapper User Guide

**Yapper** is a CLI-based (Command Line Interface) ChatBot that 
helps you manage tasks such as ToDos, Deadlines and Events, 
all while "yapping" at you.

## Table of Contents
1. [Getting Started](#getting-started)
2. [Supported Commands](#supported-commands)
3. [Command Summary](#command-summary)

---

## Getting Started

When you first run Yapper, you will be greeted with a start-up message, and a help message:
```
___________________ PROGRAM OUTPUT BELOW ___________________
Wassup! 
Ya ready for me to yap yer ear off? 
Whatchu wanna talk about? 
____________________________________________________________
To jog your memory, here's what we can discuss: 
list, if you forgot what you said. 
help, if you forgot what kinda stuff we can yap about.
bye, if you want me to stop yappin. 
delete [index], if you don't want something. 
mark [index], if you're done with something. 
unmark [index], if you're not done with something. 
find [keyword], if you're looking for something. 
todo [todoDesc], to for a task with no dates. 
deadline [deadlineDesc] /by [end], for a task with an end date. 
event [eventDesc] /from [start] /to [end], for a task with a start date and an end date. 
_____________________ USER INPUT BELOW _____________________
```

You can then start entering commands to add tasks, and more.

---

## Supported Commands

Yapper supports 10 different command types:
1. Bye 
2. Help 
3. List 
4. Find 
5. Todo 
6. Deadline 
7. Event 
8. Delete 
9. Mark 
10. Unmark

Yapper will only understand user inputs that start with the keywords above.

To perform a command,
use the command keyword, followed by additional arguments if any, 
e.g. `Shown here is how to format your input so that Yapper understands`.
```
Shown here is a sample program output based on the sample input above.
```
The command keyword is always in lowercase.

### Adding and Deleting Tasks

To add a ToDo task, 
use the todo command followed by the task description 
e.g. `todo todoDesc`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, I gotta remember this too:
  [T][ ] todoDesc
If I counted correctly, you have a task total of 1
Don't worry, I've already memorized all about it!
```

To add a Deadline task, 
use the deadline command followed by the task description then an end date, 
e.g. `deadline deadlineDesc /by endDate`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, I gotta remember this too:
  [D][ ] deadlineDesc, due endDate
If I counted correctly, you have a task total of 2
Don't worry, I've already memorized all about it!
```

To add an Event task, 
use the event command followed by the event description, a start date then an end date, 
e.g. `event eventDesc /from startDate /to endDate`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now, I gotta remember this too:
  [E][ ] eventDesc, from startDate to endDate
If I counted correctly, you have a task total of 3
Don't worry, I've already memorized all about it!
```

To delete a (ToDo, Deadline or Event) task,
use the delete command followed by the task number, 
e.g. `delete 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
Now I don't need to remember this one anymore: 
  [E][ ] sleep, from today 2300 to tomorrow 0800
If I counted correctly, you have a task total of 0
Don't worry, I've already forgotten about it! 
```

### Listing All Tasks

To list all tasks in your task list, 
use the list command without any parameters.
e.g. `list`
```
___________________ PROGRAM OUTPUT BELOW ___________________
You're forgetting already? Lemme refresh your memory: 
1.[T][X] todoDesc 
2.[D][ ] deadlineDesc, due endDate 
3.[E][ ] eventDesc, from startDate to endDate 
That should be all of them. Did I forget any? 
```

### Finding Tasks 

To find tasks,
use the find command followed by the query string,
e.g. `find CS2113`
This will list all tasks which have that query in its description, if any.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Hmmm, let me think. What tasks could you be referring to? 
3.[T][O] CS2113 iP 
4.[T][X] CS2113 tP 
2 task(s) found contains your query string. 
___________________ YAPPING OUTPUT BELOW ___________________
Is any of them what you were looking for? 
```

### Marking and Unmarking Tasks

To mark a task as complete, 
use the mark command followed by the task number,
e.g. `mark 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
I know, you told me that this task is done
  [T][O] procrastinate
Keep it up! You'll be done with it eventually! 
```

To unmark a task as incomplete, 
use the unmark command followed by the task number,
e.g. `unmark 1`
```
___________________ PROGRAM OUTPUT BELOW ___________________
I know, you told me that this task is not done
  [T][X] be productive
Keep going, you'll get it done eventually!
```

### Getting Help

To get a help message, 
use the help command without any parameters,
e.g. `help`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
To jog your memory, here's what we can discuss: 
list, if you forgot what you said. 
help, if you forgot what kinda stuff we can yap about.
bye, if you want me to stop yappin. 
delete [index], if you don't want something. 
mark [index], if you're done with something. 
unmark [index], if you're not done with something. 
find [keyword], if you're looking for something. 
todo [todoDesc], to for a task with no dates. 
deadline [deadlineDesc] /by [end], for a task with an end date. 
event [eventDesc] /from [start] /to [end], for a task with a start date and an end date. 
_____________________ USER INPUT BELOW _____________________ 
```

### Exiting Yapper

To exit the program, 
use the bye command without any parameters,
e.g. `bye`.
```
___________________ PROGRAM OUTPUT BELOW ___________________
Thanks for listenin' to my yappin'. 
Call for me whenever ya feel like listening again. 
Cya! 
____________________________________________________________
```

## Command Summary

If a command requires additional parameters, they must be included and must be in the same order indicated.

If a command does not require additional parameters, do not include anything after the command keyword.

Command keywords are always in lowercase.

| Command  | Description                                                      | Format                               | Example                                    | 
|----------|------------------------------------------------------------------|--------------------------------------|--------------------------------------------|
| todo     | Adds a new Todo task to the list                                 | todo [desc]                          | todo study for exam                        |
| deadline | Adds a new Deadline task to the list                             | deadline [desc] /by [date]           | deadline canvas quiz /by today             |
| event    | Adds a new Event task to the list                                | event [desc] /from [date] /to [date] | event tP meeting /from today 2000 /to 2100 |
| delete   | Deletes a task from the list                                     | delete [ordinal]                     | delete 6                                   |
| mark     | Marks an uncompleted task in the list as done                    | mark  [ordinal]                      | mark 4                                     |
| unmark   | Marks a completed task in the list as not done                   | unmark [ordinal]                     | unmark 3                                   |
| list     | Lists all tasks in the list                                      | list                                 | list                                       |
| find     | Lists all tasks in the list whose description contains the query | find [query]                         | find CS2113                                |
| bye      | Exits the program with a goodbye message                         | bye                                  | bye                                        |

Do note that parameters must be of a certain type: 
* desc, date, query are strings.
* ordinal is an integer.
