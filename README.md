# Nateh User Guide



Nateh is a command line application which acts as a digital todo list optimized for fast typers.
This app is perfect for those in need of a simple todo list that with a good typing speed.

## Adding Todos

Adds a todo which is simply a task with a description to the list.

Format: `todo <description>`

Example: `todo buy tomatoes`

Expected Output:

```
____________________________________________________________
added: [T][ ] buy tomatoes
____________________________________________________________

```

## Adding Deadlines

Adds a deadline which is a task with both a description 
and a date that the task must be finished by.
- Date format must be yyyy-mm-dd
- command terms are case-sensitive 

Format: `deadline <description> /by <deadline>`

Example: `deadline Study for CS2113 Test /by 2024-10-09`

Expected Output:
```
____________________________________________________________
added: [D][ ] Study for CS2113 Test (by: 9 Oct 2024)
____________________________________________________________
```


## Adding Events

Adds an event which is a task with a description, a start date, and an end date.
- Date format must be yyyy-mm-dd
- command terms are case-sensitive

Format: `event <description> /from <start date> /to <end date>`

Example: `event Carnival! /from 2023-10-10 /to 2023-10-15`

Expected Output:

```
____________________________________________________________
added: [E][ ] Carnival! (from: 10 Oct 2023 to: 15 Oct 2023)
____________________________________________________________
```

## Deleting Tasks

Deletes a task in the list based on its associated number in the list.
- command terms are case-sensitive

Format: `delete <number>`

Example: `delete 1`

```
____________________________________________________________
Okay. I've deleted the task:
[T][ ] buy tomatoes
Now you have 2 tasks
____________________________________________________________
```

## List Tasks

Lists all tasks in the list.
- command terms are case-sensitive

Format: `list`

Example: `list`

```
____________________________________________________________
1. [D][ ] Study for CS2113 Test (by: 9 Oct 2024)
2. [E][ ] Carnival! (from: 10 Oct 2023 to: 15 Oct 2023)
3. [D][ ] Math Homework (by: 3 Aug 2016)
4. [E][ ] Summer Camp (from: 2 Feb 2018 to: 7 Feb 2018)
____________________________________________________________
```

## Marking and Unmarking tasks

Marks or unmarks the task associated with the given number from the user.
- command terms are case-sensitive

Format: `mark <number>`
        `unmark <number>`

Example: `mark 1`
```
____________________________________________________________
Wow! Great job! :)
[D][X] Study for CS2113 Test (by: 9 Oct 2024)
____________________________________________________________
```
`unmark 1`
```
____________________________________________________________
Aw you didn't get to finish. :(
[D][ ] Study for CS2113 Test (by: 9 Oct 2024)
____________________________________________________________
```

## Searching for tasks
Searches for and prints out tasks based on the flag provided.
- Flags are: `/d` searches for tasks of that input date
- `/b` searches for tasks before the input date 
- `/a` searches for tasks after the input date
- `/c` searches for tasks that contain the input term
- command terms are case-sensitive

Format: `search <flag> <search term>`

Example: `search /d 2023-10-10`
```
____________________________________________________________
2. [E][ ] Carnival! (from: 10 Oct 2023 to: 15 Oct 2023)
____________________________________________________________
```
`search /b 2020-01-01`

```
____________________________________________________________
3. [D][ ] Math Homework (by: 3 Aug 2016)
4. [E][ ] Summer Camp (from: 2 Feb 2018 to: 7 Feb 2018)
____________________________________________________________
```
`search /a 2001-05-10`
```
____________________________________________________________
1. [D][ ] Study for CS2113 Test (by: 9 Oct 2024)
2. [E][ ] Carnival! (from: 10 Oct 2023 to: 15 Oct 2023)
3. [D][ ] Math Homework (by: 3 Aug 2016) 
4. [E][ ] Summer Camp (from: 2 Feb 2018 to: 7 Feb 2018)
____________________________________________________________
```
`search /c Math`
```
____________________________________________________________
3. [D][ ] Math Homework (by: 3 Aug 2016)
____________________________________________________________
```
