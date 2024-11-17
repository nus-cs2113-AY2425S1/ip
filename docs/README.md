# TARS User Guide

![TARS Interface.png](img.png)

## Product Introduction
TARS is an intelligent task manager designed to help users keep track of their tasks, deadlines, and events.
It is built to improve daily scheduling and productivity through a simple, easy-to-use command interface.
Whether you're tracking personal tasks or organizing a team project, TARS ensures that your to-dos are well managed.


## Adding deadlines
The `deadline` command allows users to add tasks with specific deadlines.
By using this command, you can track tasks that need to be completed by a certain date.

When you use this command, the task is added to your list, 
and TARS will keep track of the deadline to ensure you don't miss it.

Example: `deadline submit report /by 2024-12-31`

```
Got it. I've added this task:
[D][ ] submit report (by: Dec 31 2024)
Now you have X tasks in the list.
```

## Adding a Todo Task
The `todo` command allows users to add simple tasks without specific deadlines or times.

This is useful for general tasks that need to be tracked without a specific date.

Example:
`todo finish homework`

```
Got it. I've added this task:
[T][ ] finish homework
Now you have X tasks in the list.
```

## Adding an Event
The `event` command allows users to add tasks that span a certain period, such as meetings or events.
This command requires both a start time (/from) and an end time (/to).

Example:
`event team meeting /from 2024-11-11 /to 2024-11-12`

```
Got it. I've added this task:
[E][ ] team meeting (from: Nov 11 2024 to: Nov 12 2024)
Now you have X tasks in the list.
```

## Listing All Tasks
The `list` command shows all tasks in the current task list.
This provides an overview of the tasks and their statuses (whether completed or not).

Example:
`list`

```
Here are your tasks. If you're planning world domination, you're off to a slow start:
1. [T][ ] finish homework
2. [D][ ] submit report (by: Dec 31 2024)
3. [E][ ] team meeting (from: Nov 11 2024 to: Nov 12 2024)
```

## Marking a Task as Done
The `mark` command allows users to mark a task as completed.

Example:
`mark <task number>`

```
Great! Task marked as complete:
[T][X] finish homework
Now you're one step closer to world domination!
```

## Unmarking a Task
The `unmark` command allows users to unmark a completed task, indicating that it is not yet finished.

Example:
`unmark <task number>`

```
Task unmarked. Looks like you changed your mind, no worries:
[T][ ] finish homework
I'll be here when you're ready to finish the job.
```

## Deleting a Task
The `delete` command removes a task from the list, whether completed or not.

Example:
`delete <task number>`

```
Noted. I've successfully removed this task:
[D][ ] submit report (by: Dec 31 2024)
Now you have X tasks left in your list.
```

## Finding Tasks by Keyword
The `find` command allows users to search for tasks containing a specific keyword.

Example:
`find report`

```
Here are the matching tasks in your list:
1. [D][ ] submit report (by: Dec 31 2024)
```

## Exiting the Program
The `bye` command allows users to exit the program.

Example:
`bye`

```
Oh, leaving already? Fine, I will just sit here calculating the probability of you returning. It's... pretty high.
```

## Help Command
The `help` command shows a list of available commands and their usage.

Example:
`help`

```
Here are the available commands:
1. todo <description> - Adds a todo task.
2. deadline <description> /by <date> - Adds a deadline task.
3. event <description> /from <start> /to <end> - Adds an event task.
4. list - Lists all tasks.
5. mark <task number> - Marks a task as completed.
6. unmark <task number> - Marks a task as not completed.
7. delete <task number> - Deletes a task.
8. find <keyword> - Finds tasks containing the keyword.
9. bye - Exits the program.
10. help - Shows this help message.
```
