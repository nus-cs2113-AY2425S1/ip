
# Poirot User Guide

Poirot is a powerful command-line task management application designed to help you organize your to-dos, deadlines, and events efficiently.

## Adding a To-Do

Add tasks without any specific deadline or time, allowing you to track general tasks.

Example: `todo Buy groceries`

This command adds a new to-do task to your list.

```
____________________________________________________________
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 tasks in the list.
____________________________________________________________
```

## Adding deadlines

Add tasks with specific due dates and times to keep track of important deadlines.

Example: `deadline Submit report /by 2023-12-31 2359`

This command adds a new deadline task to your list.

```
____________________________________________________________
Got it. I've added this task:
  [D][ ] Submit report (by: Dec 31 2023, 11:59 PM)
Now you have 1 tasks in the list.
____________________________________________________________
```

## Adding events

Create events with start and end times to manage your schedule effectively.

Example: `event Team meeting /from 2023-11-15 1400 /to 2023-11-15 1500`

This command adds a new event to your task list.

```
____________________________________________________________
Got it. I've added this task:
  [E][ ] Team meeting (from: Nov 15 2023, 02:00 PM to: Nov 15 2023, 03:00 PM)
Now you have 2 tasks in the list.
____________________________________________________________
```

## Listing tasks

View all your current tasks in one place.

Example: `list`

This command displays all tasks currently in your list.

```
____________________________________________________________
1.[D][ ] Submit report (by: Dec 31 2023, 11:59 PM)
2.[E][ ] Team meeting (from: Nov 15 2023, 02:00 PM to: Nov 15 2023, 03:00 PM)
____________________________________________________________
```

## Marking tasks as done

Keep track of your progress by marking completed tasks.

Example: `mark 1`

This command marks the first task in your list as done.

```
____________________________________________________________
Nice! I've marked this task as done:

[X] Submit report
____________________________________________________________
```

## Finding tasks

Quickly locate tasks related to specific keywords.

Example: `find report`

This command searches for tasks containing the word "report".

```
Here are the matching tasks in your list:
1. [D][X] Submit report (by: Dec 31 2023, 11:59 PM)
```

## Deleting tasks

Remove tasks that are no longer needed.

Example: `delete 2`

This command deletes the second task in your list.

```
____________________________________________________________
Noted. I've removed this task:
  [E][ ] Team meeting (from: Nov 15 2023, 02:00 PM to: Nov 15 2023, 03:00 PM)
Now you have 1 tasks in the list.
____________________________________________________________
```

