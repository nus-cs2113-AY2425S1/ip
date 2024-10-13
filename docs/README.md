# Bro: Task Manager User Guide

// Product screenshot goes here


Bro task manager is a simple and intuitive task management application that helps you track todos, deadlines, adn events. It keeps you organized with your schedule with ease of use and flexibility.


## Adding deadlines


This allow users to add tasks with a due date. This ensures that you can track time-sensitive tasks and be notified.



Example: 

deadline return book /by Sunday

```
expected output
____________________________________________________________
Got it. I've added this task:
[D][ ] return book (by: Sunday)
Now you have 6 tasks in the list.
```

## Adding Todos

A **ToDo** is a task without any date or time attached to it.

Example:

todo borrow book

```
expected output
____________________________________________________________
Got it. I've added this task:
[T][ ] borrow book
Now you have 5 tasks in the list.
____________________________________________________________  
```


## Adding events

An **Event** is a task that has a start and end time, making it ideal for tracking appointments or multi-day events.

Example:

event project meeting /from Mon 2pm /to 4pm

```
expected output
____________________________________________________________
Got it. I've added this task:
[E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 6 tasks in the list.
____________________________________________________________  

```


## Listing all tasks

You can list all your tasks, including ToDos, Deadlines, and Events, to get an overview of what you need to do.

Example:

list

```
expected output
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][ ] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
____________________________________________________________

```


## Deleting tasks

This feature allows users to delete tasks from the list, whether they are ToDos, Deadlines, or Events.

Example:

list

```
expected output
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
3.[E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
4.[T][X] join sports club
5.[T][ ] borrow book
____________________________________________________________

```

delete 3

```
expected output
____________________________________________________________
Noted. I've removed this task:
   [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 4 tasks in the list.
____________________________________________________________

```



## Saving tasks

The task list is automatically saved to the hard disk whenever there is a change in the list, and it is loaded when the chatbot starts up. The tasks are saved in a file, and the format of the saved file includes the task type, completion status, and task details.

The file can be saved as ./data/duke.txt in this format:

```
T | 1 | read book
D | 0 | return book | June 6th
E | 0 | project meeting | Aug 6th 2-4pm
T | 1 | join sports club
```




## Finding tasks

This feature allows users to search for tasks by a keyword, whether the task is a ToDo, Deadline, or Event.

Example:

find book

```
expected output
____________________________________________________________
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: June 6th)
____________________________________________________________
```

