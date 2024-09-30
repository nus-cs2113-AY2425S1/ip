# TobyBot User Guide

// Product screenshot goes here
![TobyBot Logo](C:\Users\85260\OneDrive - National University of Singapore\Documents\Year 3\exchange in NUS fall\cs2113 software engineering and oop\working progress of ip\week 7)


Welcome to the **TobyBot** User Guide! TobyBot is a task management chatbot designed to help you manage your to-do list, deadlines, and events efficiently. Below is a guide to help you use all the important features of TobyBot.

---

## Features

- [Adding a Todo](#adding-a-todo)
- [Adding a Deadline](#adding-a-deadline)
- [Adding an Event](#adding-an-event)
- [Listing All Tasks](#listing-all-tasks)
- [Marking a Task as Done](#marking-a-task-as-done)
- [Unmarking a Task](#unmarking-a-task)
- [Deleting a Task](#deleting-a-task)
- [Finding Tasks](#finding-tasks)
- [Exiting TobyBot](#exiting-tobybot)

---

## Adding a Todo

To add a simple to-do task, use the `todo` command followed by the task description.

### Example:

`todo assignment 0`

```
____________________________________________________________
Got it. I've added this task:
  [T][ ] assignment 0
Now you have 1 tasks in the list.
____________________________________________________________
```

---

## Adding a Deadline

To add a task with a deadline, use the `deadline` command followed by the task description and due date. You need to separate the task description and due date with the keyword `by`.

### Example:

`deadline assignment 1 by 12 Feb`

```
____________________________________________________________
Got it. I've added this task:
  [D][ ] assignment 1 (by: 12 Feb)
Now you have 2 tasks in the list.
____________________________________________________________
```

---

## Adding an Event

To add an event with a start and end time, use the `event` command followed by the task description, start time, and end time. You need to separate the description, start time, and end time with the keywords `from` and `to`.

### Example:

`event party from 1pm to 2pm`

```
____________________________________________________________
Got it. I've added this task:
  [E][ ] party (from: 1pm to: 2pm)
Now you have 3 tasks in the list.
____________________________________________________________
```
---

## Listing All Tasks

To display all the tasks in your list, use the `list` command. The tasks will be numbered and include their type (`T`, `D`, `E`), completion status (`[X]` for done, `[ ]` for not done), and description.

### Example:

`list`

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] assignment 0
2.[D][ ] assignment 1 (by: 12 Feb)
3.[E][ ] party (from: 1pm to: 2pm)
____________________________________________________________
```

---

## Marking a Task as Done

To mark a task as done, use the `mark` command followed by the task number (the number as shown in the `list`).

### Example:

`mark 3`

```
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] assignment 0
____________________________________________________________
```

---

## Unmarking a Task

To unmark a task (mark it as not done), use the `unmark` command followed by the task number.

### Example:

`unmark 1`

```
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] assignment 0
____________________________________________________________
```

---

## Deleting a Task

To delete a task, use the `delete` command followed by the task number.

### Example:

`delete 3`

```
____________________________________________________________
Noted. I've removed this task:
  [E][ ] party (from: 1pm to: 2pm)
Now you have 2 tasks in the list.
____________________________________________________________
```

---

## Finding Tasks

To find tasks that contain a specific keyword, use the `find` command followed by the keyword. TobyBot will search through the task descriptions and list all matching tasks.

### Example:

`find assignment`

```
____________________________________________________________
Here are the matching tasks in your list:
1.[T][ ] assignment 0
2.[D][ ] assignment 1 (by: 12 Feb)
____________________________________________________________
```

---

## Exiting TobyBot

To exit TobyBot, use the `bye` command. TobyBot will say goodbye and terminate the session.

### Example:

`bye`

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________

```

---

## Error Handling

TobyBot will notify you of any errors, such as:

- Invalid task numbers for `mark`, `unmark`, or `delete` commands.
- Missing or incorrect command formats.
- Empty task descriptions.

For example, if you try to mark a task that doesn't exist, you will see this message:

`mark 6`

```
____________________________________________________________
Invalid task number.
____________________________________________________________

```

---

For example, if you have missing or incorrect command formats, you will see this message:

`todowork`

```
____________________________________________________________
Unknown command: todowork
____________________________________________________________

```

---

For example, if you have emtpy task descriptions, you will see this message:

`todowork`

```
____________________________________________________________
The description of a todo cannot be empty.
____________________________________________________________

```

---

## Conclusion

That's all you need to know to start using TobyBot effectively! Whether you're managing your daily to-do tasks, tracking deadlines, or scheduling events, TobyBot is here to help you stay organized and saved automatically in a TobyBot.txt file . Have fun managing your tasks with TobyBot!