# TheThinker User Guide

// Update the title above to match the actual product name

![img.png](img.png)

TheThinker is a task manager which allows you to add different task.
Task includes Deadlines , Events and Todo

## Adding deadlines

Add task which has a specific deadline.

Example: `deadline return book /by 20/10/2025 1800`

```
____________________________________________________________
Got it. I've added this task:
    [D][ ] return book (by: 20 October 2025 , 6 pm)
Now you have 1 tasks in the list.
____________________________________________________________
```

## Add Event

Add task which has a duration.

Example: `event travel /from 20/10/2025 1800 /to 20/11/2025 1800`

```
____________________________________________________________
Got it. I've added this task:
    [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
Now you have 2 tasks in the list.
____________________________________________________________
```


## Add todo

Add task without deadlines.

Example: `todo travel`

```
____________________________________________________________
Got it. I've added this task:
    [T][ ] travel
Now you have 3 tasks in the list.
____________________________________________________________
```

## Mark and Unmark Task

Mark task as complete and incomplete.

Example: `mark 1`

```
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] return book (by: 20 October 2025 , 6 pm)
2. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
3. [T][ ] travel
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book
____________________________________________________________
```
Example: `Mark 1`

```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book
____________________________________________________________
```

## Help

Prints the format for all the commands available

Example: `help`

```
____________________________________________________________
Formats for the commands are : 
mark : mark [number]
unmark : unmark [number]
delete : delete [number]
todo : todo [task]
event : event [task] /from [start time] /by [end time]
deadline : deadline [task] /by [time]
get : get [dd/mm/yyyy]
find : find [keyword]
____________________________________________________________
```

## Delete

Delete a specific task from the list

Example: `delete 1`

```
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] return book (by: 20 October 2025 , 6 pm)
2. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
3. [T][ ] travel
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
 [D][ ] return book (by: 20 October 2025 , 6 pm)
Now you have 2 tasks in the list.
____________________________________________________________
```


## Get

Get all the task which are of specific date

Example: `get 20/10/2025`

```
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
3. [T][ ] return book
____________________________________________________________
get 20/10/2025
____________________________________________________________
Here are the tasks in your list in 20/10/2025 :
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
____________________________________________________________
```

## Find

Get all the task which are of specific keyword

Example: `find travel`

```
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
3. [T][ ] return book
____________________________________________________________
find travel
____________________________________________________________
Here are the matching tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
____________________________________________________________
```