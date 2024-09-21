# Doug User Guide

// Product screenshot goes here

### Product Intro
Doug is a chatbot.


## Adding Todos

Format: `todo TASK_NAME`

Creates a new todo task
- Only has a description
- Does not have any date or time attached

Example: `todo collect laundry`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Adding Deadlines

Format: `deadline TASK_NAME /by DUE_DATE`

Creates a new deadline task
- Has a description and a single date/time component attached
- Due date parameter must be in one of the following forms:
  - In text form (e.g. tomorrow)
  - In date form yyyy-MM-dd (e.g. 2001-11-09)
  - In date and time form yyyy-MM-dd hhmm (e.g. 2020-03-14 1759) 
    - Note: There must be only ONE white space between the date and time

Example: `deadline ip submission /by tomorrow`

Expected Output:
```
EXPECTED OUTPUT IS
```

## Adding Events

Format: `event TASK_NAME /from START_DATE /to END_DATE`

Creates a new event task
- Has a description and two date/time components attached
- Start date and end date parameters must be in one of the following forms:
    - In text form (e.g. tomorrow)
    - In date form yyyy-MM-dd (e.g. 2001-11-09)
    - In date and time form yyyy-MM-dd hhmm (e.g. 2020-03-14 1759)
        - Note: There must be only ONE white space between the date and time

Example: `event football game /from 2018-05-21 1930 /to 2018-05-21 2100`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Listing Tasks

Format: `list`

Lists out all the tasks, in order of insertion

Example: `list`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Marking Tasks

Format: `mark INDEX`

Marks the task as done
- Index must match that of the listed indexes

Example: `mark 1`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Unmarking Tasks

Format: `unmark INDEX`

Marks the task as not done
- Index must match that of the listed indexes

Example: `unmark 2`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Deleting Tasks

Format: `delete INDEX`

Removes the task from the list
- Index must match that of the listed indexes

Example: `delete 3`

Expected Output:
```
EXPECTED OUTPUT IS
```


## Finding Tasks

Format: `find KEYWORDS`

Looks through all the task to find matching task descriptions as the keywords
- Keywords can be as many words or numbers or both

Example: `find homework`

Expected Output:
```
EXPECTED OUTPUT IS
```