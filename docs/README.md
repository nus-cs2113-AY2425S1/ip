# Edith User Guide

This is a user guide for a greenfield Java project. 
It is named after Tony Stark's voice assistant, Edith - which stands for "Even Dead I'm The Hero".
It is an assistant to help you manage your tasks.
Given below are instructions on how to use it.

## Interacting with Edith

Edith will first introduce itself.

```
Successfully loaded tasks into the list
Hello I am Edith.
What can I do for you?
```

Here are the commands Edith can execute 
and how you can instruct it to perform a certain instruction:

### Feature: Adding new Tasks

You can add a Task to the list. There are 3 different types of tasks.

#### Adding a ToDo task
Add a task of the type ToDo to the list.

How: Start with the word "todo" and then describe the task.

Example:

    todo read book

Expected output: 
```
Got it. I've added this task:
[T][ ] read book
Now you have 1 tasks in the list.
```
#### Adding a Deadline task
Add a task of the type Deadline to the list.

How: Start with the word "deadline" and then describe the task,
followed by the word "by" and the time of the deadline.

Example:

    deadline submit quiz by tonight 11pm

Expected output:
```
Got it. I've added this task:
[D][ ] submit quiz (by: tonight 11pm)
Now you have 2 tasks in the list.
```
#### Adding a Event task
Add a task of the type Event to the list.

How: Start with the word "event" and then describe the task,
followed by the word "from" and the start time of the event
and end with the word "to" and the end time of the event

Example:

    event football match from tom 2pm to tom 4pm

Expected output:
```
Got it. I've added this task:
[E][ ] football match (from: tom 2pm  to: tom 4pm)
Now you have 3 tasks in the list.  
```

#### Note: By default, every task will be initially marked as not done.

### Feature Marking/Unmarking Tasks as done
You can mark tasks as done or not done. These are displayed everytime 
you print that particular task.


How: Depending on the instruction you want to give,
start the command either with "mark" or "unmark" followed by the task number
(as shown in the list of tasks).

Example:

    mark 2

Expected output:
```
Nice! I've marked this task as done:
[D][X] submit quiz (by: tonight 11pm)
```
Example:

    unmark 1

Expected output:
```
OK, I've marked this task as not done yet:
[T][ ] read book
```

### Feature Listing all Tasks
You can choose to view all the tasks currently on your list.

How: Simply type in "list" to show the list of all tasks
with the type of each task and its respective completion status.

Example:

    list

Expected output:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[D][X] submit quiz (by: tonight 11pm)
3.[E][ ] football match (from: tom 2pm  to: tom 4pm)
```

### Feature Deleting Tasks
You can permanently remove tasks from the list.

How: Type "delete" followed by the task number
(as shown in the list of tasks).

Example:

    delete 2

Expected output:
```
Noted. I've removed this task:
[D][X] submit quiz (by: tonight 11pm)
Now you have 2 tasks in the list.
```

Here is the list after this command:
```
Here are the tasks in your list:
1.[T][ ] read book
2.[E][ ] football match (from: tom 2pm  to: tom 4pm)
```

### Feature Finding Tasks
You can search for certain tasks in you list. You should use a keyword(s) and
Edith will list all tasks with the keyword.

How: Start with "find" followed by the keyword(s) you want to search.

Example:

    find read

Expected output:
```
Here are the matching tasks in your list:
1.[T][ ] read book
```

### Feature Exiting
You can exit the program. 
This will result in a goodbye message and terminate the program.
Your list of tasks, along with their status, will be automatically saved.
The next time you run Edith, you will be able to pick up exactly where you left.

How: Type "bye."

Example:

    bye

Expected output:
```
Bye. Hope to see you again soon!
```
### Common Error:
Edith looks at the first word of the command to determine the type of command.
Hence, the first word must be the exact command you want to execute.
