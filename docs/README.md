# Tommi User Guide

**Tommi** is a chatbot designed to help users efficiently manage and track their tasks. 
With Tommi, users can easily create, edit, save, and load a list of tasks at any time. 

Tasks are categorized into three types:
- **To-dos**: Simple tasks that need to be completed.
- **Events**: Tasks that have a defined start and end.
- **Deadlines**: Tasks with a target completion.



## Adding To-do

You can add to-do tasks to your task list. A to-do includes only a description and does not have any time-related information.

Example: `todo [task description]`

Outcome: A new to-do task is added to your list.

Example usage:
```
todo read book
```

Expected output:
```
____________________________________________________________
Sure. I've added the task: 
[T][ ] read book
There are now 2 tasks in the list.
____________________________________________________________
```


## Adding Deadlines

You can add deadlines to your task list. A deadline task includes a 
description and a specific completion target.

Example: `deadline [task description] /by [target]`

Outcome: A new deadline task is added to your list.

Example usage:
```
deadline submit cs2113 IP /by Friday 2359
```

Expected output:
```
____________________________________________________________
Sure. I've added the task: 
[D][ ] submit cs2113 IP (by: 11/10/2024 2359)
There are now 3 tasks in the list.
____________________________________________________________
```

## Adding Events

You can add events to your task list. An event includes a description, a start, and an end.

Example: `event [task description] /from [start] /to [end]`

Outcome: A new event task is added to your list.

Example usage:
```
event attend CS2113 award ceremony /from 12/10/2024 /to 17/10/2028
```

Expected output:
```
____________________________________________________________
Sure. I've added the task: 
[E][ ] attend CS2113 award ceremony (from: 12/10/2024 to 17/10/2028) 
There are now 3 tasks in the list.
____________________________________________________________
```

## Listing Tasks

You can list all tasks currently in your task list. This command will show each task with its type, description, and any associated time information.

Example: `list`

Outcome: A list of all tasks is displayed.

Example usage:
```
list
```

Expected output:
```
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] submit cs2113 IP (by: 11/10/2024 2359)

2. [E][ ] attend CS2113 award ceremony (from: 12/10/2024 to 17/10/2028) 
____________________________________________________________
```

## Marking a Task as Done

You can mark a task as done by specifying its index in the task list.

Example: `mark [task index]`

Outcome: The specified task is marked as done.

Example usage:
```
mark 2
```

Expected output:
```
____________________________________________________________
Awesomesauce! I've marked this task as done:
[D][X] submit cs2113 IP (by: 11/10/2024 2359)
____________________________________________________________
```

## Unmarking a Task as Undone

You can unmark a task as undone by specifying its index in the task list.

Example: `unmark [task index]`

Outcome: The specified task is marked as undone.

Example usage:
```
unmark 2
```

Expected output:
```
____________________________________________________________
OK, I've marked this task as undone:
[D][ ] submit cs2113 IP (by: 11/10/2024 2359)
____________________________________________________________
```

## Deleting a Task

You can delete a task from your task list by specifying its index.

Example: `delete [task index]`

Outcome: The specified task is removed from your list.

Example usage:
```
delete 2
```

Expected output:
```
____________________________________________________________
I've removed the task: 
[D][ ] submit cs2113 IP (by: 11/10/2024 2359)

There are now 2 tasks in the list.
____________________________________________________________
```

## Searching for a Task

You can search for tasks in your task list by providing a keyword. The search will return tasks that match the keyword.

Example: `search [keyword]`

Outcome: A list of tasks that match the search keyword is displayed.

Example usage:
```
search lunch
```

Expected output:
```
____________________________________________________________
Here are the matching results: 
1: [D][ ] eat lunch (by: 2pm)
____________________________________________________________
```

## Exiting the Program

You can exit the program by using the `bye` command.

Example: `bye`

Outcome: The program terminates, and a goodbye message is displayed.

Example usage:
```
bye
```

Expected output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________

Process finished with exit code 0
```
