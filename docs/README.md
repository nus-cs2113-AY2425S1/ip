## Sleepy User Guide

// Product screenshot goes here

Sleepy is a desktop app for managing tasks, optimized for use via a Command Line Interface (CLI).

Ensure you have Java 17 or above installed in your Computer.

Download the latest .jar file from [here](https://github.com/parasytezz/ip/releases).

Copy the file to the folder you want to use as the home folder for your Sleepy chatbot.

Open a command terminal, cd into the folder you put the jar file in, and use the java -jar ip.jar command to run the application.

## Contents
- [Adding a Deadline Task: deadline](#adding-a-deadline-task-deadline)
- [Adding an Event Task: event](#adding-an-event-task-event)
- [Adding a Todo Task: todo](#adding-a-todo-task-todo)
- [Listing Tasks: list](#listing-tasks-list)
- [Marking a Task: mark](#marking-a-task-mark)
- [Unmarking a Task: unmark](#unmarking-a-task-unmark)
- [Deleting a Task: delete](#deleting-a-task-delete)
- [Finding Tasks: find](#finding-tasks-find)
- [Exiting the Program: bye](#exiting-the-program-bye)
- [Command Summary](#command-summary)

## Adding a Deadline task: deadline

Adds a deadline task to the task list

Format: deadline [task] /by [yyyy-MM-dd HHmm]

Example: `deadline complete README /by 2024-09-28 2359`

```
expected output:
____________________________________________________________
added...
 [D][ ] complete README (by: Sep 28 2024, 11:59 pm)
Now you have 1 tasks in the list...all the best, im going back to bed
____________________________________________________________
```

## Adding an event task: event

Adds an event task to the task list

Format: event [task] /from [time] /to [time]

Example: `event zouk /from 10pm /to 4am`

```
expected output:
____________________________________________________________
added...
 [E][ ] zouk (from: 10pm to: 4am)
Now you have 2 tasks in the list...all the best, im going back to bed
____________________________________________________________
```
## Adding a todo task: todo

Adds a todo task to the task list

Format: todo [task]

Example: `todo laundry`

```
expected output:
____________________________________________________________
added...
 [T][ ] laundry
Now you have 3 tasks in the list...all the best, im going back to bed
____________________________________________________________
```
## Listing tasks: list

Lists all the tasks in the task list

Format: list

Example: `list`

```
expected output:
____________________________________________________________
Stop bothering me, u have unfinished tasks...:
1.[D][ ] complete README (by: Sep 28 2024, 11:59 pm)
2.[E][ ] zouk (from: 10pm to: 4am)
3.[T][ ] laundry
____________________________________________________________
```
## Marking a task: mark

Marks a task in the task list

Format: mark [task index]

- Marks the task at the specified [task index]. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

Example: `mark 1`

```
____________________________________________________________
Yay...good for you:
 [D][X] complete README (by: Sep 28 2024, 11:59 pm)
____________________________________________________________
```
## Unmarking a task: unmark

Unmarks a task in the task list

Format: unmark [task index]

- Unmarks the task at the specified [task index]. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

Example: `unmark 1`

```
expected output:
____________________________________________________________
Could you stop making me do extra work:
 [D][ ] complete README (by: Sep 28 2024, 11:59 pm)
____________________________________________________________
```
## Deleting a task: delete

Deletes a task in the task list

Format: delete [task index]

- Deletes the task at the specified [task index]. The index refers to the index number shown in the displayed task list. The index **must be a positive integer** 1, 2, 3, ...

Example: `delete 1`

```
expected output:
____________________________________________________________
I've removed the task...
 [D][ ] complete README (by: Sep 28 2024, 11:59 pm)
Now you have 2 tasks in the list...can I go and sleep now
____________________________________________________________
```
## Finding tasks: find

Finds task with a certain keyword in the task list

Format: find [keyword]

- The search is case-insensitive. e.g. `Zouk` will match `zouk`
- The order of the keywords matters. e.g. `Do Laundry` will not match `Laundry Do`
- Partial words can match searches. e.g. `zou` will match `zouk`


Example: `find zouk`

```
expected output:
____________________________________________________________
Here are the matching tasks in your list:
1.[E][ ] zouk (from: 10pm to: 4am)
____________________________________________________________
```
## Exiting the program: bye

Exits the program

Format: bye

Example: `bye`

```
expected output:
____________________________________________________________
Bye. Going back to sleep...ZZZ
____________________________________________________________
```
## Command summary

| Action   | Format, examples                                                                                |
|----------|-------------------------------------------------------------------------------------------------|
| Deadline | `deadline [task] /by [yyyy-MM-dd HHmm]`<br/>e.g. `deadline complete README /by 2024-09-28 2359` |
| Event    | `event [task] /from [time] /to [time]`<br/>e.g. `event zouk /from 10pm /to 4am`                 |
| Todo     | `todo [task]` <br/>e.g.`todo laundry`                                                           |
| List     | `list`                                                                                          |
| Mark     | `mark [task index]`<br/>e.g.`mark 1`                                                            |
| Unmark   | `unmark [task index]`<br/>e.g.`unmark 1`                                                        |
| Delete   | `delete [task index]`<br/>e.g.`delete 1`                                                        |
| Find     | `find [keyword]`<br/>e.g.`find zouk`                                                            |
| Exit     | `bye`                                                                                           |
 
