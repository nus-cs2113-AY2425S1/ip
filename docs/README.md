# Crystal User Guide

Welcome to Crystal, _the_ personal bot for you!

Crystal is a command-line bot that helps you manage your daily tasks!

She will be able to help you record, mark/unmark, delete, find and lists 
your tasks. It also will save your past progress, so fret not if you want
to access your past tasks!

## QUICK START
1. Ensure you have Java `17` or above installed in your Computer.
2. Download the latest `.jar` file from here.
3. Copy the file to the folder you want to use as the home folder 
for your Task Manager.
4. Open a command terminal, `cd` into the folder you put the jar file in, 
and use the `java -jar Crystal.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
Here are some commands you can try:
    - `list` : Lists all the tasks you have so far
    - `todo yoga` : Adds todo task named yoga
    - `deadline cs2113 by 8pm` : Adds deadline task named cs2113, and the deadline is 8pm
    - `event lecture from 4pm to 6pm` : Adds event task named lecture, starting at 4pm and ends at 6pm
    - `mark 2` : Mark the second task in the list
    - `unmark 2` : Unmark the second task in the list
    - `delete 3` : Delete the third task in the list
    - `find yoga` : Finds all tasks that contain the word "yoga"
    - `help`: Displays a list of commands that can be called to Crystal
    - `bye` : Crystal bids you goodbye and the new list is saved.
6. Refer to the features below for a more detailed explanation of each command.

## Features

### Adding a task: `todo`, `deadline`, `event`

#### Add `todo` task

Add a task with just the name.

Example: `todo yoga`

expected output:
```
____________________________________________________________
Got it! I have added this task:
[T][ ] yoga
Now you have 4 tasks in the list.
____________________________________________________________

```

#### Add `deadline` task

Add a task with its name and its deadline (time).

Example: `deadline cs2113 ip by 8pm`

expected output:
```
____________________________________________________________
Got it! I have added this task:
[D][ ] cs2113 ip (by: 8pm)
Now you have 5 tasks in the list.
____________________________________________________________
```

#### Add `event` task

Add a task and its start and end time.

Example: `event CS2113 lecture from 4pm to 6pm`

expected output:
```
____________________________________________________________
Got it! I have added this task:
[E][ ] CS2113 lecture (from: 4pm to: 6pm)
Now you have 6 tasks in the list.
____________________________________________________________
```

### Listing a task

This will produce a list of the tasks that were saved and input before.

Example: `list`

expected output:
```
____________________________________________________________
1. [T][ ] yoga
2. [D][ ] cs2113 ip (by: 8pm)
3. [E][ ] CS2113 lecture (from: 4pm to: 6pm)
____________________________________________________________
```

### Marking a task

This will mark the task with that task number in the list.

Example: `mark 2`

expected output:
```
____________________________________________________________
YAY!! This task is now marked done:
[D][X] CS2113 iP (by: 8pm)
____________________________________________________________

```
### Unmarking a task
This will unmark the task with that task number in the list.

Example: `unmark 2`

expected output:
```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] cs2113 ip (by: 8pm)
____________________________________________________________
```
### Deleting a task
Delete a task with that task number in the list.

Example: `delete 2`

expected output:
```
____________________________________________________________
Noted. I have deleted the task below: 
[D][ ] cs2113 ip (by: 8pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

### Finding a task
Find the task(s) in the list that contain the keyword you input
(not case sensitive).

Example: `find CS2113`

expected output:
```
____________________________________________________________
Here are the matching tasks in your list:
2. [D][ ] cs2113 ip (by: 8pm)
3. [E][ ] CS2113 lecture (from: 4pm to: 6pm)
____________________________________________________________
```

### Help!
Call for a list of commands that can be used when calling Crystal.

Example: `help`

expected output:
```
____________________________________________________________
Type the command in the command box and press Enter to execute it. Here are some commands you can try:
list : Lists all the tasks you have so far
todo yoga : Adds todo task named yoga
deadline cs2113 by 8pm : Adds deadline task named cs2113, and the deadline is 8pm
event lecture from 4pm to 6pm : Adds event task named lecture, starting at 4pm and ends at 6pm
mark 2 : Mark the second task in the list
unmark 2 : Unmark the second task in the list
delete 3 : Delete the third task in the list
find yoga : Finds all tasks that contain the word “yoga”
help : Displays this list of commands to ask Crystal.
bye : Crystal bids you goodbye and the new list is saved.
____________________________________________________________
```

### Bye Crystal!
Terminate your session with Crystal.

Example: `bye`

expected output:
```
____________________________________________________________
Adios, hasta luego!
____________________________________________________________

```
### Saving the file
There is no need to specifically save your file as it is automatically
saved to your hard disk when you terminate.