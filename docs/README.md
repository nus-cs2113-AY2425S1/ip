# JeM chatbot User Guide

![Jem ChatBot Screenshot](https://imgur.com/RZW6wEB)

JeM is a personal task management chatbot designed to help you organize and track your to-dos, deadlines, and events in a simple and efficient way. With JeM, you can add tasks, mark them as completed, delete them, and much more. This guide will walk you through all of JeM's features so you can use it effectively.
To try out the chatbot, you can download the latest version of JeM from [here](https://github.com/Atulteja/ip/releases/tag/A-Release).

## Features Overview

- Words in UPPER_CASE represents parameters to be supplied by the user. 
  - e.g.: `deadline TASK /by DATE_TIME`, where `TASK` and `DATE_TIME` are parameters you provide.
- Types of Tasks that can inputted into the chatbot are Todo, Deadline and Event using the keywords `Todo`, `Deadline`, `Event` at the start of the inputs
- Certain flags need to be used when it comes to inputting tasks
  - e.g.: `/by DATE_TIME` for deadlines and `/from START_DATE_TIME /to END_DATE_TIME` for events

## Adding a deadline

The `deadline` command allows you to add a task with a specified deadline.

### Usage:

`deadline TASK /by DATE_TIME`
- **TASK**: The description of the task.
- **DATE_TIME**: The deadline date and time in the format `dd/MM/yyyy HH:mm`.

### Example:

deadline submit project report /by 15/10/2024 18:00

### Outcome:

```
Got it. I've added this task: 
[D][ ] submit project report (by: 15-10-2024 06:00 PM)
Now you have 1 task in your list.
1.[D][ ] submit project report (by: 15-10-2024 06:00 PM)
```

---

## Adding an Event

The `event` command allows you to add a task that occurs over a period of time.

### Usage:

`event TASK /from START_DATE_TIME /to END_DATE_TIME`


- **TASK**: The description of the event.
- **START_DATE_TIME**: The start date and time of the event.
- **END_DATE_TIME**: The end date and time of the event.

### Example:
`event project meeting /from 15/10/2024 09:00 /to 15/10/2024 11:00`


### Outcome:
```
Got it. I've added this task: 
[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
Now you have 2 tasks in your list.
1.[D][ ] submit project report (by: 15-10-2024 06:00 PM)
2.[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
```

---

## Adding a To-Do

The `todo` command allows you to add a simple task without a deadline.

### Usage:
`todo TASK`

- **TASK**: The description of the task.

### Example:
`
- **TASK**: The description of the task.

### Outcome:
```
Got it. I've added this task: 
[T][ ] buy groceries 
Now you have 3 tasks in your list.
1.[D][ ] submit project report (by: 15-10-2024 06:00 PM)
2.[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
3.[T][ ] buy groceries
```

---

## Marking a Task as Done

The `mark` command allows you to mark a task as completed.

### Usage:

`mark INDEX`

- **INDEX**: The index of the task to be marked as done.

### Example:
`mark 1`

### Outcome:

```
Here is your current list:
1.[D][X] submit project report (by: 15-10-2024 06:00 PM)
2.[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
3.[T][ ] buy groceries
```

---

## Unmarking a Task

The `unmark` command allows you to mark a task as completed.

### Usage:

`unmark INDEX`

- **INDEX**: The index of the task to be marked as done.

### Example:
`unmark 1`

### Outcome:

```
Here is your current list:
1.[D][ ] submit project report (by: 15-10-2024 06:00 PM)
2.[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
3.[T][ ] buy groceries 
```

---

## Deleting a Task

The `delete` command allows you to remove a task from your list.

### Usage:

`delete INDEX`


- **INDEX**: The index of the task to delete.

### Example:

`delete 3`


### Outcome:

```
I have removed this task: 
[T][ ] buy groceries 
Here is your current list:
1.[D][ ] submit project report (by: 15-10-2024 06:00 PM)
2.[E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)

```

---

## Finding a Task

The `find` command allows you to search for tasks that contain a specific keyword.

### Usage:

`find KEYWORD`


- **KEYWORD**: The keyword to search for in your task list.

### Example:

`find project`


### Outcome:
```
Here are the matching tasks in your list:
[D][ ] submit project report (by: 15-10-2024 06:00 PM)
```


---

## Clearing the Task List

The `clear` command allows you to remove all tasks from your list.

### Usage:

`clear`


### Example:

`clear`


### Outcome:
```
Your task list is empty.
```


---

## Listing All Tasks

The `list` command allows you to view all the tasks in your list.

### Usage:
`list`


### Example:
`list`


### Outcome:
```
Here is your current list:
1. [D][ ] submit project report (by: 15-10-2024 06:00 PM)
2. [E][ ] project meeting (from: 15-10-2024 09:00 AM to 15-10-2024 11:00 AM)
```

---

## Exiting the Chatbot

The `bye` command allows you to exit the chatbot.

### Usage:
`bye`


### Outcome:

```
Bye. Hope to see you again soon!
```


---

## Command Summary

| Command          | Description                                 | Format                                            |
|------------------|---------------------------------------------|---------------------------------------------------|
| **List Tasks**   | Displays all the tasks in the list.         | `list`                                            |
| **Add Todo**     | Adds a todo task.                           | `todo DESCRIPTION`                                |
| **Add Deadline** | Adds a deadline task.                       | `deadline DESCRIPTION /by DEADLINE`               |
| **Add Event**    | Adds an event task.                         | `event DESCRIPTION /from START_DATE /to END_DATE` |
| **Mark Task**    | Marks a task as completed using its index.  | `mark INDEX`                                      |
| **Unmark Task**  | Marks a task as incomplete using its index. | `unmark INDEX`                                    |
| **Delete Task**  | Deletes a task using its index.             | `delete INDEX`                                    |
| **Find Task**    | Displays tasks matching a keyword.          | `find KEYWORD`                                    |
| **Clear Tasks**  | Clears the entire task list.                | `clear`                                           |
| **Exit**         | Exits the application.                      | `bye`                                             |

---

## Conclusion

JeM is a powerful and simple chatbot that helps you stay organized by managing your to-dos, deadlines, and events. Use the commands listed above to effectively track your tasks and manage your workload. Enjoy your time with JeM, and feel free to explore the commands to see how they can assist you in your daily life!

---

