# Cubone User Guide
version 1.0

A friendly CLI program helping user to manage all tasks todo.

---

- [Cubone User Guide](#cubone-user-guide)
  - [Features](#features)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Viewing help: `help`](#viewing-help-help)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Adding a Task: `task`](#adding-a-task-task)
    - [Adding a Todo: `todo`](#adding-a-todo-todo)
    - [Adding a Deadline: `deadline`](#adding-a-deadline-deadline)
    - [Adding an Event: `event`](#adding-an-event-event)
    - [Marking a task: `mark`](#marking-a-task-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Finding tasks based on keyword: `find`](#finding-tasks-based-on-keyword-find)

---
## Features

- Words in `< >` are the parameters to be supplied by the user.
e.g. in `mark <index>`, `<index>` is a parameter which user should supply.
- Extraneous parameters for commands that do not take in parameters (such as `help`, `list` and `exit`) will be ignored.
- The `<time>` parameters will be formated if possible
e.g. `2023/05/13` will be formated as `May 13 2023`
- The `<index>` parameter in `mark`, `unmark` and `deleter` should be providedd as an interger and start by 1.

### Exiting the program: `bye`

Exits the program.
Formate: `bye`

### Viewing help: `help`

Shows a message explaning how to access the help page.
Formate: `help`

### Listing all tasks: `list`

Shows a list of all tasks in the TaskList.

- The output will show the index, type, mark status, desctiptions and time (if avaliable) of the tasks.
- In the first quotation mark shows the type of the task.
  - `T` means Todos.
  - `D` means Deadlines.
  - `E` means Events.
  - If non, means it is a simple task without a type.
- In the second quotation mark shows the mark status of the task.
  - `X` means the task was marked as done.
  - If empty, means the task was marked as undone.

Formate: `list`

Example output:
```
  1. [T][ ] | do the laundary
  2.    [X] | John Doe need to make dinner
  3. [D][X] | assignment 1 (by: 23:00)
  4. [E][X] | party @ Jsons' (from: 024/09/29 18:00 to: 2024/09/29 23:00)
  5. [E][ ] | tsou teng yuan attend CS2113 lecture (from: Friday 20 Sep 4pm to: 6pm)
```

### Adding a Task: `task`

Adds a simple Task to the TaskList.
Description detail must be added.

Format: `task <description>`

Examples:

- `task John Doe need to make dinner`
- `task do the laundary`


### Adding a Todo: `todo`

Adds a Todo to the TaskList.
Description detail must be added.

Format: `todo <description>`

Examples:

- `todo John Doe need to make dinner`
- `todo do the laundary`


### Adding a Deadline: `deadline`

Adds a Deadline to the TaskList.
Description and time details must be added.

Format: `deadline <description- /by <time>`

Examples:

- `deadline John Doe need to make dinner /by 1800`
- `deadline do the laundary /by 2024/09/29`


### Adding an Event: `event`

Adds an Event to the TaskList.
Description and time (from and to) details must be added.

Format: `event <description- /from <time1> /to <time2>`

Example:

- `event party @ Jasons' /from 2024/09/29 18:00 /to 2024/09/29 23:00`

### Marking a task: `mark`

Marks a task as done.
Index of the task must be provided.
Do use the command [`list`](#listing-all-tasks-list) to check the index of task.

Format: `mark <index>`

Example:

- `mark 1`

### Unmarking a task: `unmark`

Marks a task as undone.
Index of the task must be provided.
Do use the command [`list`](#listing-all-tasks-list) to check the index of task.

Format: `unmark <index>`

Example:

- `unmark 1`

### Deleting a task: `delete`

Deletes a task from the list.
Index of the task must be provided.

> After deletion, the index of subsequent tasks will change. Please keep this in mind when executing this command consecutively.
> Do use the command [`list`](#listing-all-tasks-list) to check the index of task.

Format: `delete <index>`


Example:

- `delete 1`

### Finding tasks based on keyword: `find`

Finds tasks that contain the keyword in its `<description>`.
Keyword must be provided.

Format: `find <keyword>`


- The search is case-sensitive. e.g `Job` will not match `job`
- The search only perform in `<description>`, not in - `<time>`
  e.g. keyword `1300` will not match the task `[D][X] | gym (by: 1300-1400)`
- The task's index in the TaskList will also be provided to - perform `mark`, `unmark` or `delete`.

Example:

- `find assignment`

Example output:
```
  1.  [T][ ] | do the assignment 1
  2.  [D][X] | assignment 3 (by: 23:00)
  3.  [E][X] | burn down my assignments
```

