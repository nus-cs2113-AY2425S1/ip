# ATOM User Guide

![](/./docs/AtomScreenshot.png)

Looking for a **tool** to help you **keep track of your tasks** and stay on track?
Well, look no further because **ATOM** is here to help!! 

**ATOM** is a **Command Line Interface** (CLI) app that **manages and tracks all 
of your tasks** in one place. It is also **simple, fast and intuitive** to use!!

- [Features]()
  - [Adding a todo task: `todo`](#adding-a-todo-task-todo)
  - [Adding a deadline task: `deadline`](#adding-a-deadline-task-deadline)
  - [Adding an event task: `event`](#adding-an-event-task-event)
  - [Listing all tasks: `list`](#listing-all-tasks-list)
  - [Marking a task: `mark`](#marking-a-task-mark)
  - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Finding tasks by keyword: `find`](#finding-tasks-by-keyword-find)
  - [Filtering tasks by date: `filter`](#filtering-tasks-by-date-filter)
  - [Exiting the program: `bye`](#exiting-the-program-bye)
  - [Saving the task list](#saving-the-task-list)
- [Command Summary](#command-summary)

___
# Features:

## Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `todo <task name>`

Example: `todo read book`

Sample output:

```
Gotcha! TODO task added to list!
> [T][ ] read book
You now have 1 tasks in your list!
```

## Adding a deadline task: `deadline`

Adds a deadline task to the task list.

Format: `deadline <task name> /by <date time>`

- Date time **must be** in the following format: `DD/MM/YYYY HOUR:MIN`
- `DD`, `MM`, `HOUR` and `MIN` **must be** a 2 digit value.
- `YYYY` **must be** a 4 digit value.
- `YYYY` **must be** from 2024 onwards.
- For **single digit** date time parameter(s) (i.e. 0 to 9),
  append a 0 at the front.

Example: `deadline return book /by 01/12/2024 15:05`

Sample output:

```
Gotcha! DEADLINE task added to list
> [D][ ] return book (by: 01 Dec 2024, 03:05pm)
You now have 2 tasks in your list!
```

## Adding an event task: `event`

Adds an event task to the task list.

Format: `event <task name> /from <date time> /to <date time>`

- Date time **must be** in the following format: `DD/MM/YYYY HOUR:MIN`
- `DD`, `MM`, `HOUR` and `MIN` **must be** a 2 digit value.
- `YYYY` **must be** a 4 digit value.
- `YYYY` **must be** from 2024 onwards.
- For **single digit** date time parameter(s) (i.e. 0 to 9), 
append a 0 at the front.

Example: `event project /from 01/12/2024 15:05 /to 10/12/2024 17:30`

Sample output:

``` 
Gotcha! EVENT task added to list
> [E][ ] project (from: 01 Dec 2024, 03:05pm to: 10 Dec 2024, 05:30pm)
You now have 3 tasks in your list!
```

## Listing all tasks: `list`

Lists all the tasks in the task list.

Format: `list`

Sample output:

``` 
Here is your list:

1.[T][ ] read book
2.[D][X] return book (by: 11 Dec 2024, 03:30pm)
3.[E][ ] project meeting (from: 11 Dec 2024, 03:00pm to: 11 Dec 2024, 05:00pm)
```

## Marking a task: `mark`

Marks a specified task as **done**.

Format: `mark <task id>`

- Marks the task as **done** at the specified task id.
- The task id refers to **index number of task in the task list**.
- The task id **must be a postive integer.** (i.e. 1,2,3,..)

Example: `mark 1`

Sample output:

``` 
Wonderful! Task successfully marked as DONE!
> [T][X] read book
```

## Unmarking a task: `unmark`

Marks a specified task as **undone**.

Format: `unmark <task id>`

- Marks the task as **undone** at the specified task id.
- The task id refers to **index number of task in the task list**.
- The task id **must be a postive integer.** (i.e. 1,2,3,..)

Example: `unmark 1`

Sample output:

``` 
Got it. Task successfully marked as UNDONE!
> [T][ ] read book
```

## Feature ABC
## Deleting a task: `delete`

Deletes a specified task from the task list.

Format: `delete <task id>`

- Deletes the task at the specified task id.
- The task id refers to **index number of task in the task list.**
- The task id **must be a postive integer.** (i.e. 1,2,3,..)

Example: `delete 1`

Sample output:

``` 
Okie Dokie!! Removed task from list:
> [T][ ] read book
You now have 2 tasks in your list.
```

## Finding tasks by keyword: `find`

Finds tasks containing the **keyword** in the task list.

Format: `find <keyword>`

- The search is **case-sensitive.** (i.e. keyword should be in **lower-case**)
- Only the task name is searched.
- The **full keyword is not required.** (i.e. `bo` will match with tasks
  containing `bo` **anywhere in the task name**).

Examples: 

Sample list:

``` 
Here is your list:

1.[T][ ] read book
2.[D][ ] return book (by: 11 Dec 2024, 03:30pm)
3.[E][ ] project meeting (from: 11 Dec 2024, 03:00pm to: 11 Dec 2024, 05:00pm)
```

Example 1: `find book`

Sample output:

``` 
Your search resulted in 2 matches.

Here are the matching tasks that I found:
1.[T][ ] read book
2.[D][ ] return book (by: 11 Dec 2024, 03:30pm)
```
Example 2: `find et`

Sample output:

``` 
Your search resulted in 2 matches.

Here are the matching tasks that I found:
1.[D][ ] return book (by: 11 Dec 2024, 03:30pm)
2.[E][ ] project meeting (from: 11 Dec 2024, 03:00pm to: 11 Dec 2024, 05:00pm)
```

Example 3: `find BOOK`

Sample output:

``` 
Your search resulted in 0 matches. :(
```

## Filtering tasks by date: `filter`

Filters tasks by date in the task list.

Format: `filter <date>`

- Date **must be** in the following format: `DD/MM/YYYY`
- `DD` and `MM` **must be** a 2 digit value.
- `YYYY` **must be** a 4 digit value.
- `YYYY` **must be** from 2024 onwards.
- For **single digit** date parameter(s) (i.e. 0 to 9),
  append a 0 at the front.

Example: `filter 11/12/2024`

Sample list:

``` 
Here is your list:

1.[T][ ] read book
2.[D][ ] return book (by: 11 Dec 2024, 03:30pm)
3.[E][ ] project meeting (from: 11 Dec 2024, 03:00pm to: 11 Dec 2024, 05:00pm)
```

Sample output:

``` 
Your search resulted in 2 matches.

Here are the tasks occurring on 11 Dec 2024:
1.[D][ ] return book (by: 11 Dec 2024, 03:30pm)
2.[E][ ] project meeting (from: 11 Dec 2024, 03:00pm to: 11 Dec 2024, 05:00pm)
```

## Exiting the program: `bye`

Exits the program.

Format: `bye`

