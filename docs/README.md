# Pythia User Guide
// Product screenshot goes here

Pythia chatbot is desktop app **designed to help you manage all of your tasks, 
optimized for use via command-line interface**.
It allows for adding tasks, deleting, marking and saving them for later.

# Table of Contents
- [Feature overview](#feature-overview)
- [Add `Task`](#add-task) 
- [Add `ToDo`](#add-todo)
- [Add `Event`](#add-event)
- [Add `Deadline`](#add-deadline)
- [View all with `list`](#list-tasks)
- [Delete a task](#task-delete)
- [Mark task as done](#mark-task)
- [Find a task](#find-task)
- [Exit the app](#exit)

# Feature overview
App supports several types of commands with different functionalities and synatx. 
All of them summarized in a table below.

| Feature             | Syntax                                               |
|---------------------|------------------------------------------------------|
| **List all tasks**  | `list`                                               |
| **Add a task**      | `add <description>`                                  |
| **Add a todo**      | `todo <description>`                                 |
| **Add a deadline**  | `deadline <description> /by <deadline>`              |
| **Add an event**    | `event <description> /from <startDate> /to <endDate>`|
| **Mark task as done** | `mark <taskNumber>`                                |
| **Delete a task**   | `delete <taskNumber>`                                |
| **Find a task**     | `find <keyword>`                                     |
| **Quit the app**    | `bye`                                                |

> **Tip:**
> To know `<taskNumber>` is it advised to execute `list` command first

> **Warning:**
> Once a task is deleted it cannot be recovered

## Add `Task`
Adds a task to the list of tasks. Use syntax `add <description>`
where `<description>` is a task description of the task such 
as a name or anything desired.

### Example:
```
add My first task
	____________________________________________________________
	added: my first task
	____________________________________________________________
list
	____________________________________________________________
	1. [ ] My first task
	Now you have 1 task in the list.
	____________________________________________________________
```

## Add `ToDo`
Adds a `TdDo` to the list of tasks. Use syntax `todo <description>`
where `<description>` is a `ToDo` description of the task such
as a name or anything desired.

### Example:
```
todo My first todo
	____________________________________________________________
	added: my first todo
	____________________________________________________________
list
	____________________________________________________________
	1. [ ] My first task
	2. [T][ ] My first todo
	Now you have 2 tasks in the list.
	____________________________________________________________
```

> **Note:**
> Unless a task is not deleted it will be kept indefinitely.

## Add `Event`
Adds an `Event` to the list of tasks. Use syntax `event <description> /from <startDate> /to <endDate>`
where:
- `<description>` is a `Event` description
- `<startDate>` is the time at which the event starts, can we any sequence of characters
- `<endDate>` is the time at which the event end, can we any sequence of characters


### Example:
```
event My first event /from now /to some time in the future
	____________________________________________________________
	added: my first event
	____________________________________________________________
list
	____________________________________________________________
	1. [ ] My first task
	2. [T][ ] My first todo
	3. [E][ ] My first event (from now to some time in the future)
	Now you have 3 tasks in the list.
	____________________________________________________________
```

## Add `Deadline`
Adds a `Deadline` to the list of tasks. Use syntax `deadline <description> /by <deadline>`
where:
- `<description>` is a `Deadline` description
- `<dueDate>` date by which the deadline is due, any sequence of characters is accepted

### Example:
```
deadline My first deadline /by tomorrow :)
	____________________________________________________________
	added: my first deadline
	____________________________________________________________
list
	____________________________________________________________
	1. [ ] My first task
	2. [T][ ] My first todo
	3. [E][ ] My first event (from now to some time in the future)
	4. [D][ ] My first deadline (by: tomorrow :))
	Now you have 4 tasks in the list.
	____________________________________________________________
```

## View all with `list`
You can view all tasks in the list with `list` command. Once a task is added,
it will be kept indefinitely unless deleted explicitly. Look above for examples (like [Add `Deadline`](#add-deadline))

## Delete a task
Deletes a `Task` from the list of tasks. Use syntax `delete <taskNumber>`
where:
- `<taskNumber>` is a number of the task in a list

> **Tip:**
> To know `<taskNumber>` is it advised to execute `list` command first as task numbers change upon deletion

### Example:
```
delete 1
	____________________________________________________________
	Nice! I've deleted this task:
		[ ] My first task
	____________________________________________________________
list
	____________________________________________________________
	1. [T][ ] My first todo
	2. [E][ ] My first event (from now to some time in the future)
	3. [D][ ] My first deadline (by: tomorrow :))
	Now you have 3 tasks in the list.
	____________________________________________________________
```

## Mark task as done
Marks a `Task` as done. Use syntax `mark <taskNumber>`
where:
- `<taskNumber>` is a number of the task in a list

> **Tip:**
> To know `<taskNumber>` is it advised to execute `list` command first as task numbers change upon deletion

### Example:
```
mark 1
	____________________________________________________________
	Nice! I've marked this task as done:
		[T][X] My first todo
	____________________________________________________________
list
	____________________________________________________________
	1. [T][X] My first todo
	2. [E][ ] My first event (from now to some time in the future)
	3. [D][ ] My first deadline (by: tomorrow :))
	Now you have 3 tasks in the list.
	____________________________________________________________
```

## Find a task
Finds a `Task` with a keyword contain in the task description. Use syntax `find <keyword>`.

### Example:
```
list 
	____________________________________________________________
	1. [T][X] My first todo
	2. [E][ ] My first event (from now to some time in the future)
	3. [D][ ] My first deadline (by: tomorrow :))
	Now you have 3 tasks in the list.
	____________________________________________________________
add My second task
	____________________________________________________________
	added: my second task
	____________________________________________________________
list
	____________________________________________________________
	1. [T][X] My first todo
	2. [E][ ] My first event (from now to some time in the future)
	3. [D][ ] My first deadline (by: tomorrow :))
	4. [ ] My second task
	Now you have 4 tasks in the list.
	____________________________________________________________
find first
	____________________________________________________________
	Here are the matching tasks in your list:
	1. [T][X] My first todo
	2. [E][ ] My first event (from now to some time in the future)
	3. [D][ ] My first deadline (by: tomorrow :))
	____________________________________________________________
```

## Exit the app
To exit the app use command `bye`. Changes are saved automatically.

### Example:
```
bye
	____________________________________________________________
	Your path is set. Until we meet again.
	____________________________________________________________
```