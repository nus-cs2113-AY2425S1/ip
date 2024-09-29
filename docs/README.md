# Quag User Guide


![img.png](img.png)

Quag is a task management system designed to help you keep track of your to-dos, deadlines, and events. With simple commands, you can easily add, mark, and organize your tasks directly from the command line.

## Adding Todos
The todo command adds a simple task with no deadline.

**Example:** `todo buy groceries`

**Expected output:**
```
_______________________________________
added:
    [T][ ] buy groceries
you have 4 quaggin tasks to do! get to work!
_______________________________________

```

## Adding deadlines

To add a deadline to your task list, use the deadline command, followed by the task description and the /by argument to specify the deadline date and time.

Date is to be in dd/mm/yyyy hhmm format

**Example:** `deadline Complete assignment /by 25/09/2024 1800`

**Expected Output:**

```
_______________________________________
added: 
    [D][ ] Complete assignment (by: Sep 25 2024, 18:00 pm)
you have 5 quaggin tasks to do! get to work!
_______________________________________
```

## Adding Events

To add an event, use the event command along with /from and /to to specify the start and end time.

Date is to be in dd/mm/yyyy hhmm format

**Example:** `event Meeting with team /from 25/09/2024 1400 /to 25/09/2024 1500`

**Expected Output:**

```
_______________________________________
added: 
    [E][ ] Meeting with team (from: Sep 25 2024, 14:00 pm, to: Sep 25 2024, 15:00 pm)
you have 6 quaggin tasks to do! get to work!
_______________________________________
```


## Marking and Unmarking Tasks

To mark a task as complete: `mark 4`

This marks the task at index 4 as complete.

**Expected Output:**
```
_______________________________________
quag! you're done with this task :
  [T][X] buy groceries
_______________________________________
```

To unmark a task as complete: `unmark 4`

This unmarks the task at index 4 as complete.

**Expected Output:**
```
_______________________________________
quag! you're NOT done with this task :
  [T][ ] buy groceries
_______________________________________
```

## Viewing the Task List

To display all tasks:

`list`

**Expected Output:**

```
_______________________________________
1. [D][ ] gym (by: Sep 09 2024, 16:30 pm)
2. [D][ ] submission (by: Sep 10 2024, 16:30 pm)
3. [T][ ] gym tonight
4. [T][ ] buy groceries
5. [D][ ] Complete assignment (by: Sep 25 2024, 18:00 pm)
6. [E][ ] Meeting with team (from: Sep 25 2024, 14:00 pm, to: Sep 25 2024, 15:00 pm)
_______________________________________
```

## Deleting Tasks
To delete a task: `delete 2`

This deletes the task at index 2.

**Expected Output:**
```
_______________________________________
quag! deleted this task :
  [D][ ] submission (by: Sep 10 2024, 16:30 pm)
You have 5 quaggin tasks to do! get to work!
_______________________________________
```

## Searching for tasks

To search for deadlines by their due date or events by their occurrence date:

**Example Input:** `due 09/09/2024`

**Expected Output:**
```
  [D][ ] gym (by: Sep 09 2024, 16:30 pm)
```

To search for tasks by keywords:

**Example Input:** `find meeting`

**Expected Output:**
```
[E][ ] Meeting with team (from: Sep 25 2024, 14:00 pm, to: Sep 25 2024, 15:00 pm)
```

## Exiting the Program
To exit Quag: `quag`

Expected Output:
```
_______________________________________
See you soon! quag quag
_______________________________________
```


## Inputting wrong commands

Any unrecognized commands will bring up the command list:

**Example:** `bye`

**Expected output:**
```
list of all quaggin commands:
_______________________________________
list: lists out all your tasks
mark <index>: marks task corresponding to index
unmark <index>: unmarks task corresponding to index
todo <description>: adds task type todo to list
deadline <description> /by <dd/mm/yyyy hhmm>: adds task type deadline to list
event <description> /from <dd/mm/yyyy hhmm> /to <dd/mm/yyyy hhmm>: adds task type event to list
delete <index>: delete task corresponding to index
due <dd/mm/yyyy>: deletes task type description to list
find <keyword>: finds all tasks with keyword in description
quag: exit program
_______________________________________
```