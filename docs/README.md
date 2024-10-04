# Aerus User Guide
*Organize and manage your tasks with ease!*

Welcome to Aerus, an application that helps you manage tasks and more!
This is a quick introduction to get you started.

## Getting Started with Aerus
Aerus runs on Java 17. To get started, put the .jar file into your desired folder and run.

Aerus keeps track of three types of tasks: 
- To-do (tasks with a short description only), 
- Deadline (with a final date), 
- Event (with a start and end time). 

You can mark each task as done or undone and view/delete them at any time.

Aerus automatically saves your tasks after each session.

## The `help` command
Type `help` anytime to see a list of the app's commands as follows.

```
list                                            : view all your tasks
todo     [description]                          : note down a todo task
deadline [description] /by [time]               : note down a deadline task
event    [description] /from [time] /to [time]  : note down an event task
search   [input query]                          : search for tasks by description
mark     [task number]                          : mark a task as done
unmark   [task number]                          : mark a task as undone
delete   [task number]                          : delete a task from Aerus
bye / quit                                      : Exit the program
```

## `list` - View all your tasks

**Arguments: None**

Shows all tasks in your list in the order they are added.

## `todo` - Create a ToDo task

**Argument: `description`**

**Example: `todo Clean my workspace`**

Create a ToDo task and add it to the end of your list.

## `deadline` - Create a Deadline

**Arguments: `description`, `/by`**

**Example: `deadline Finish homework /by 6 pm`**

Create a Deadline and add it to the end of your list.

`by` indicates the date/time of your deadline.

## `event` - Create an Event

**Arguments: `description`, `/from`, `/to`**

**Example: `event Group meeting /from 4pm /to 6pm (09 Oct 2024)`**

Create an Event and add it to the end of your list.

`from` and `to` indicate the start and end time of your event.

## `search` - Search for a task

**Arguments: `search query`**

**Example: `search homework project`**

Searches through the list of your Tasks and return all matches 
(i.e. Tasks whose description contains any word in your query).

## `mark`, `unmark` & `delete` - Organize your tasks!

**Argument: `task number`**

**Examples:** `mark 2` `unmark 5` `delete 3`

Mark/unmark a task to indicate whether the task is completed, 
or delete it from the software.

`task number` should be no more than how many tasks you have.

## `bye` & `quit` - Say goodbye to Aerus!

**Arguments: None**

Don't forget to say bye before you leave! 
Aerus will save all changes made during your session.

*Note: Turning off Aerus without using this command may 
lead to your changes being lost!* 

## Have a great tasking session!