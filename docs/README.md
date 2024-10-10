# Juan User Guide

               ._-'-_ .
          . '  /_-_-_\   ` .
       .'     |-_-_-_-|      `.
      (       `.-_-_-.'        )
      !`.                    .'!
        ! ` .            . ' !
          ! ! ! ! ! ! ! !  !
            / /       \ \
          _-| \___ ___/ /-_
         (_ )__\_)\(_/__( _)
             ))))\X\ ((((
               \/ \/ 

Juan is a simple command-line task manager that helps users keep track of tasks such as to-dos, deadlines, and events. It allows users to manage their tasks, mark them as complete, and store them for future use. Juan is written in Java and supports basic task operations with a fun user interface.

## Run

To Run Juan, open Command Prompt and navigate to the directory `ip.jar` is located in and run the following command:
```
java -jar ip.jar
```

## Command Overview

| Command       | Description                                | Format                                             | Example                                                             |
|---------------|--------------------------------------------|----------------------------------------------------|---------------------------------------------------------------------|
| Add ToDo      | Adds a to-do task                          | `todo <task_description>`                          | `todo Buy groceries`                                                |
| Add Deadline  | Adds a deadline task                       | `deadline <task_description> /by <date>`           | `deadline Submit assignment /by 15-10-2024 23:59`                   |
| Add Event     | Adds an event with start and end           | `event <task_description> /from <date> /to <date>` | `event Project meeting /from 15-10-2024 10:00 /to 15-10-2024 12:00` |
| Mark Task     | Marks a task as done                       | `mark <task_number>`                               | `mark 2`                                                            |
| Unmark Task   | Unmarks a task                             | `unmark <task_number>`                             | `unmark 2`                                                          |
| Delete Task   | Deletes a task                             | `delete <task_number>`                             | `delete 3`                                                          |
| List Tasks    | Lists all tasks                            | `list`                                             | `list`                                                              |
| Find Task     | Finds tasks matching a keyword             | `find <keyword>`                                   | `find groceries`                                                    |
| Exit          | Saves the tasks into a text file and exits | `bye`                                              | `bye`                                                               |


## Commands

### 1. Add ToDo Task

**Format**:
```
todo <task_description>
```

Adds a new to-do task to the task list.

Example:
```
todo Buy groceries

Muy Bien, work hard compadre!
I've Added the Task:
[T][ ] Buy groceries
```

### 2. Add Deadline Task

**Format**:
```
deadline <task_description> /by <dd-MM-yyyy HH:mm>
```

Adds a task with a specific deadline.

Example:
```
deadline Submit assignment /by 15-10-2024 23:59

Muy Bien, work hard compadre!
I've Added the Task:
[D][ ] Submit assignment (by: 15-10-2024 23:59)
```

### 3. Add Event Task

**Format**:
```
event <task_description> /from <dd-MM-yyyy HH:mm> /to <dd-MM-yyyy HH:mm>
```

Adds a task with a specific start and end time.

Example:
```
event Project meeting /from 15-10-2024 10:00 /to 15-10-2024 12:00

Muy Bien, work hard compadre!
I've Added the Task:
[E][ ] Project meeting (from: 15-10-2024 10:00 to: 15-10-2024 12:00)
```

### 4. Mark Task as Done

**Format**:
```
mark <task_number>
```

Marks the specified task as completed.

Example:
```
mark 1

Fantastica!!!! I marked it:
[T][X] Buy groceries
```

### 5. Unmark Task

**Format**:
```
unmark <task_number>
```

Unmarks the specified task, indicating it is not completed.

Example:
```
unmark 1

Ay Caramba, I unmarked it:
[T][ ] Buy groceries
```

### 6. Delete Task

**Format**:
```
delete <task_number>
```

Deletes the specified task from the task list.

Example:
```
delete 1

Ay Caramba, Task deleted: [T][ ] Buy groceries
```

### 7. List All Tasks

**Format**:
```
list
```

Lists all current tasks in the task list, including their status (marked as done or not done) and task numbers.

Example:
```
list

Si compinche, your 2 tasks:
1.[D][X] Submit assignment (by: 15-10-2024 23:59)
2.[E][ ] Project meeting (from: 15-10-2024 10:00 to: 15-10-2024 12:00)
```

### 8. Find Task

**Format**:
```
find <keyword>
```

Finds all tasks that contain the specified keyword in their description.

Example:
```
find meeting

Si compinche, your tasks with the phrase <meeting>:
2.[E][ ] Project meeting (from: 15-10-2024 10:00 to: 15-10-2024 12:00)
5.[T][X] Do Tutorial
```

### 9. Exit

**Format**:
```
bye
```
Saves changes done to .txt file and exits program.

Example:

```
bye

Data File Written
Adios amigo, la familia will miss you
```