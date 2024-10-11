# Edith User Guide

This is a user guide for a greenfield Java project. It's named after Tony Stark's voice assistant, Edith - which stands for "Even Dead I'm The Hero".
It is an assistant to help you manage your tasks.
Given below are instructions on how to use it.

## Interacting with Edith

After Edith has introduced itself, here are the commands Edith can execute
and how you can instruct it to perform a certain instruction:

### Add new Tasks

#### Add a ToDo task
Start with the word "todo" and then describe the task
#### Add a Deadline task
Start with the word "deadline" and then describe the task 
followed by the word "by" and the time of the deadline 
#### Add a Event task
Start with the word "deadline" and then describe the task
followed by the word "from" and the start time of the event
and end with the word "to" and the end time of the event

#### Note: By default, every task will be initially marked as not done

### Mark/Unmark Tasks as done
Depending on the instruction you want to give,
start the command either with "mark" or "unmark" followed by the task number
(as shown in the list of tasks)

### List all Tasks
Simply type in "list" to show the list of all tasks with their completion status

### Delete Tasks
Type "delete" followed by the task number
(as shown in the list of tasks)

### Find Tasks
Start with "find" followed by the keyword(s) you want to search. 
Edith will list all tasks whose descriptions contain that keyword, either in full or in part

### Common Errors
Edith looks at the first word of the command to determine the type of command. 
Hence, the first word must be the exact command you want to execute.

### Exit
The command "bye" will result in a goodbye message and terminate the program. 
Your list of tasks, along with their status, will be automatically saved. 
The next time you run Edith, you will be able to pick up exactly where you left 

