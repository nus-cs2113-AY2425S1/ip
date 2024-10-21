# Melchizedek User Guide
```
Hello! I'm Melchizedek.
How can I be a blessing to you?
```
Melchizedek is a chatbot that manages your tasks.

## Getting the list of commands:`help`
Get the full list of available commands.

Example:`help`

```
Here is a list of commands:
    to add a todo: todo DESCRIPTION
    to add a deadline: deadline DESCRIPTION /by BY
    to add an event: event DESCRIPTION /from FROM /to TO
```

## Viewing task list:`list`
Get the full task list.

Example:`list`

```
Here is the list of tasks:
1.[T][ ] read lecture notes
2.[E][X] coding lecture (from: 24 Sep 2024 2:00 pm to: 24 Sep 2024 4:00 pm)
3.[D][X] coding assignment (by: 24 Sep 2024 12:00 pm)
```

## Searching for tasks:`find`
Get all tasks that contain the keyword, ignoring case.

Format:`find KEYWORD`

Example:`find coding`
```
Here are the matching tasks in your list:
1.[E][X] coding lecture (from: 24 Sep 2024 2:00 pm to: 24 Sep 2024 4:00 pm)
2.[D][X] coding assignment (by: 24 Sep 2024 12:00 pm)
```

## Adding todos:`todo`
Add a todo to the task list.

Format:`todo DESCRIPTION`

Example:`todo research for report`
```
I have added this task:
  [T][ ] research for report
Now you have 4 tasks in the list.
```

## Adding deadlines:`deadline`
Add a deadline to the task list.

Format:`deadline DESCRIPTION /by YYYY-MM-DD (HH:MM)`

Example:`deadline report /by 2024-10-31`
```
I have added this task:
  [D][ ] report (by: 31 Oct 2024)
Now you have 5 tasks in the list.
```

## Adding events:`event`
Add an event to the task list.

Format:`event DESCRIPTION /from YYYY-MM-DD (HH:MM) /to YYYY-MM-DD (HH:MM)`

Example:`event report review with prof /from 2024-10-11 15:00 /to 2024-10-11 16:00`
```
I have added this task:
  [E][ ] report review with prof (from: 11 Oct 2024 3:00 pm to: 11 Oct 2024 4:00 pm)
Now you have 6 tasks in the list.
```
## Deleting tasks:`delete`
Delete a task from the task list.

Format:`delete INDEX`

Example:`delete 2`
```
I have removed the following task from your list:
  [E][X] coding lecture (from: 24 Sep 2024 2:00 pm to: 24 Sep 2024 4:00 pm)
Now you have 5 tasks in the list.
```

## Marking tasks:`mark`
Mark a task as done.

Format:`mark INDEX`

Example:`mark 3`
```
I have marked the following task:
  [T][X] research for report
```

## Unmarking tasks:`unmark`
Unmark a task as done.

Format:`unmark INDEX`

Example:`unmark 2`
```
I have unmarked the following task:
  [D][ ] coding assignment (by: 24/09/24 12pm)
```

## Exiting the chat:`bye`
Exit the chat session and saves the list in a save file.

Format:`bye`

