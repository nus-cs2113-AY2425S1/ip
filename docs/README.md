# Nova User Guide


# Introduction

Nova is a Command Line Interface (CLI)-based task management application designed to help users keep track of their
tasks efficiently. It supports adding, listing, marking, and deleting tasks such as todos, deadlines, and events.

# Nova - Quick Start Guide


1. Make sure you have **Java JDK 17** installed.
2. Download the latest nova.jar release from the releases page.
3. Navigate to the folder containing the nova.jar file and run it:
``` 
java -jar nova.jar
``` 
4. Start Using Nova

## Adding Todos

**Todo**: Add a simple todo task.

Format: `todo [task description]`

Example: `todo Buy a new phone`

```    
    ___________________________________________________________________
     Got it. I've added this task:
       [T][ ] Buy a new phone
     Now you have 1 tasks in the list.
    ___________________________________________________________________
```

## Adding Deadlines

**Deadline**: Add a task with a deadline.

Format: `deadline [task description] /by [due date]`

Example: `deadline Submit assignment /by 2024-09-30`

```
    ___________________________________________________________________
     Got it. I've added this task:
       [D][ ] Submit assignment (by: Sep 30 2024)
     Now you have 2 tasks in the list.
    ___________________________________________________________________
```

## Adding Events

**Event**: Add an event with start and end dates.

Format: `event [task description] /from [start date] /to [due date]`

Example: `event Korea holiday /from 2024-09-10 /to 2024-09-15`

```
    ___________________________________________________________________
     Got it. I've added this task:
       [E][ ] Korea holiday (from: Sep 10 2024 to: Sep 15 2024)
     Now you have 3 tasks in the list.
    ___________________________________________________________________
```   

## Listing Tasks

**List**: List all tasks or filter by date.

Format: `list`  

```   
    ___________________________________________________________________
     Listing all tasks
     1.[T][ ] Buy a new phone
     2.[D][ ] Submit assignment (by: Sep 30 2024)
     3.[E][ ] Korea holiday (from: Sep 10 2024 to: Sep 15 2024)
    ___________________________________________________________________
```

Format: `list [date]`

Example: `list 2024-09-30`
```
    ___________________________________________________________________
     Listing all tasks on 2024-09-30
     1.[D][ ] Submit assignment (by: Sep 30 2024)
    ___________________________________________________________________
```
## Marking  Tasks

**Mark**: Mark a task as done.

Format: `mark [task index]`

Example: `mark 2`  
```
    ___________________________________________________________________
     Nice! I've marked this task as done:
       [D][X] Submit assignment (by: Sep 30 2024)
    ___________________________________________________________________
```

## Unmarking Tasks

**Unmark**: Mark a task as undone.

Format: `unmark [task index]`

Example: `unmark 1`

```
    ___________________________________________________________________
     OK, I've marked this task as not done yet:
       [D][ ] Submit assignment (by: Sep 30 2024)
    ___________________________________________________________________
```

## Deleting Tasks

**Delete**: Remove a task from your list.

Format: `delete [task index]`

Example: `delete 2`

```
    ___________________________________________________________________
     Noted. I've removed this task:
       [D][ ] Submit assignment (by: Sep 30 2024)
     Now you have 2 tasks in the list.
    ___________________________________________________________________
```

## Finding Tasks

**Find**: Find tasks by a keyword.

Format: `find [keyword]`

Example: `find phone`

```
    ___________________________________________________________________
     All the tasks that contain the word 'phone'
     1.[T][ ] Buy a new phone
    ___________________________________________________________________
```

## Exiting the Application

**Exit**: End the program and save your tasks.

Format: `bye`

```
    ___________________________________________________________________
     Bye. Hope to see you again soon!
    ___________________________________________________________________
```