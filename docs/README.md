# KBot User Guide


KBot is your personal task management bot that helps you organize and track your tasks efficiently. Whether you need to manage deadlines, schedule events, or simply keep track of to-do items, KBot is here to assist you. With a user-friendly command interface, you can quickly add, delete, and find tasks, making task management effortless.

## Adding deadlines

To add a deadline task, use the command `deadline` followed by the task description and the `/by` keyword to specify the due date.

### Example usage:

```plaintext
deadline Submit assignment /by 2024-10-15
```

### Expected outcome:
KBot will add a new deadline task to your task list, and you'll receive confirmation of the addition.

```
Got it. I've added this task:
  [D][ ] Submit assignment (by: 2024-10-15)
Now you have 1 tasks in the list.
```

## Adding events

To add an event task, use the command `event` followed by the task description, the `/from` keyword to specify the start time, and the `/to` keyword to specify the end time.

### Example usage:

```plaintext
event Project meeting /from 2024-10-12 14:00 /to 2024-10-12 16:00
```

### Expected outcome:
KBot will add a new event task to your task list, confirming the details of the event.

```
Got it. I've added this task:
  [E][ ] Project meeting (from: 2024-10-12 14:00 to: 2024-10-12 16:00)
Now you have 2 tasks in the list.
```

## Adding to-do tasks

To add a to-do task, use the command `todo` followed by the task description.

### Example usage:

```plaintext
todo Read a book
```

### Expected outcome:
KBot will add a new to-do task to your task list and confirm the addition.

```
Got it. I've added this task:
  [T][ ] Read a book
Now you have 3 tasks in the list.
```

## Marking tasks as done

To mark a task as completed, use the command `mark` followed by the task number.

### Example usage:

```plaintext
mark 1
```

### Expected outcome:
KBot will mark the specified task as done.

```
Nice! I've marked this task as done:
  [T][X] Read a book
```

## Unmarking tasks

To mark a task as not done, use the command `unmark` followed by the task number.

### Example usage:

```plaintext
unmark 1
```

### Expected outcome:
KBot will mark the specified task as not done.

```
OK, I've marked this task as not done yet:
  [T][ ] Read a book
```

## Deleting tasks

To delete a task, use the command `delete` followed by the task number.

### Example usage:

```plaintext
delete 1
```

### Expected outcome:
KBot will delete the specified task and confirm the deletion.

```
Noted. I've removed this task:
  [T][X] Read a book
Now you have 2 tasks in the list.
```

## Finding tasks

To find tasks containing a specific keyword, use the command `find` followed by the keyword.

### Example usage:

```plaintext
find book
```

### Expected outcome:
KBot will display all tasks that match the keyword.

```
Here are the matching tasks in your list:
1. [T][X] Read a book
2. [D][ ] Submit assignment (by: 2024-10-15)
```

## Exiting KBot

To exit the application, simply type `bye`.

### Example usage:

```plaintext
bye
```

### Expected outcome:

```
Bye. Hope to see you again soon!
```

