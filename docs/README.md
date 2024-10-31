# Fenix User Guide

![img.png](img.png)

Fenix is a refined task management assistant that helps you track your to-dos, deadlines, and events. With its gentlemanly tone, Fenix offers a polite, professional experience as you add, edit and complete tasks through simple commands.

## Adding To-dos
To add a simple task without a deadline, use the `todo` command followed by the task name.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
todo swim
```

**Expected Output:**
```
The task has been duly noted and added to your list.
        **************************************************************
        added: 1. [T][ ] swim
        **************************************************************
You now have 1 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Adding Deadlines
To add a task with a specific deadline, use the `deadline` command followed by the task name along with the `/by` argument to specify when the deadline is due.

**Example Input:** 
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
deadline run /by 9pm
```

**Expected Output:**
```
The task has been duly noted and added to your list.
	**************************************************************
	added: 2. [D][ ] run  (by: 9pm)
	**************************************************************
You now have 2 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Adding Events
To add an event with start and end times, use the `event` command followed by the task name along with the `/from` and `/to` arguments to indicate the times.

**Example Input:** 
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
event attend hackathon /from Monday 2pm /to 8pm
```

**Expected Output:**
```
The task has been duly noted and added to your list.
	**************************************************************
	added: 3. [E][ ] attend hackathon  (from: Monday 2pm to: 8pm)
	**************************************************************
You now have 3 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Marking Tasks
To mark a task as complete, use the `mark` command followed by the number of the task in the list.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
mark 2
```

**Expected Output:**
```
Task successfully completed. A job well executed.
	**************************************************************
	marked: 2. [D][X] run  (by: 9pm)
	**************************************************************
You now have 2 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Unmarking Tasks
To unmark a task as complete, use the `unmark` command followed by the number of the task in the list.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
unmark 2
```

**Expected Output:**
```
Understood. This task has been marked as not done yet.
	**************************************************************
	unmarked: 2. [D][ ] run  (by: 9pm)
	**************************************************************
You now have 3 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Viewing the Task List
To display all tasks in your list, use the `list` command.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
list
```
**Expected Output:**
```
    1. [T][ ] swim
    2. [D][ ] run  (by: 9pm)
    3. [E][ ] attend hackathon  (from: Monday 2pm to: 8pm)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Deleting Tasks
To remove a task from your list, use the `delete` command followed by the number of the task in the list.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
delete 1
```
**Expected Output:**
```
The task has been removed from your list as per your request.
	**************************************************************
	deleted: [T][ ] swim
	**************************************************************
You now have 2 tasks awaiting your attention.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Searching for Tasks
To search for tasks in the list by the task name, use the `find` command followed by the keyword.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
find hackathon
```
**Expected Output:**
```
Here are the tasks that align with your request
	1. [E][ ] attend hackathon  (from: Monday 2pm to: 8pm)
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

## Exiting the Program
To close the application, use the bye command. Fenix will save your task list and bid you a courteous farewell, ensuring that your tasks are securely stored before exiting.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
bye
```
**Expected Output:**
```
It has been a pleasure assisting you. Farewell.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```


## Invalid Commands
Any invalid syntax will prompt Fenix to courteously provide feedback, pinpointing the exact nature of the error and guiding you towards the correct format.

**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
hello
```
**Expected Output:**
```
Please provide a valid command
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
todo
```
**Expected Output:**
```
Please provide a task
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
**Example Input:**
```
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
event meeting /by 9pm
```
**Expected Output:**
```
Task input must be in the format '{taskName} /from {Time} /to {Time}'
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```