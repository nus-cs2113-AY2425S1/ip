# Doot User Guide
Doot - a minimal CLI task manager application. Allows storage of todos, events, and deadlines. Also allows the user to
mark, unmark, search, and delete tasks.


## Adding deadlines

Adds a deadline to the application. Must include the keyword "deadline" followed by the task, followed by "/by" followed
by the deadline date.

Example: `deadline run /by 2024-07-29`

Prints a message saying that the deadline was successfully added.

```
added: [D][ ] run (by: 2024-07-29)
```

## Adding todo

Adds a todo task to the application. Must include the keyword "todo" followed by a description of the task.

Example: `todo read`

Prints a message saying that the todo was successfully added.

```
added: [T][ ] read
```

## Adding events

Adds an event task to the application. Must include the keyword "event" followed by a description of the event, followed
by "/from" followed by the start date of the event, followed by "/to" followed by the end date of the event.

// Give examples of usage

Example: `event play /from today /to tomorrow`

Prints a message saying that the event was successfully added.

```
added: [E][ ] play (from: today to: tomorrow)
```


## Removing a task

Removes the specified task from the application. Must include the keyword "delete" followed by the index of the task
to be removed.

Example: `delete 1`

Prints a message saying which task was deleted.

```
Noted. I've removed this task: 
[T][ ] read book
Now you have 2 tasks in this list.
```


## Marking a task

Marks a task in the application as done. Must include they keyword "mark" followed by the index of the task to mark.

Example: `mark 1`

Prints a message saying which task was marked.

```
OK, I've marked this task as done: read
```


## Unmarking a task

Unmarks a task in the application as done. Must include they keyword "unmark" followed by the index of the task to mark.

Example: `unmark 1`

Prints a message saying which task was unmarked.

```
OK, I've marked this task as not done yet: read
```


## Listing tasks

Prints a list of the tasks currently stored in the application. Must include the keyword "list".

Example: `list`

Prints all the tasks in the list.

```
Here are the tasks in your list:
1. [T][ ] read book
```


## Finding tasks

Finds all tasks in the application containing the search term. Must include the keyword "find" followed by the search
term.

// Give examples of usage

Example: `find read`

Prints all tasks containing the keyword.

```
1. [T][ ] read
```
