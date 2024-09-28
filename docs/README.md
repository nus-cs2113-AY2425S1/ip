# Doug User Guide

![Screenshot of the Welcome Message when Doug is launched](..%2FDoug_Welcome_Screen.png)

### Who is Doug?
**Doug Dimmadome** is your friendly Texan chatbot, here to make your life easy with that typical Southern hospitality.
His main goal is to help you note down and access your important tasks.

# How To Launch and Use Doug
1. Ensure that you have Java 17 installed
   - Doug will not launch with any other version of Java
2. Open a command terminal
3. Navigate to the directory where 2113IP.jar is located at using the `cd` command
4. Type in `java -jar 2113IP.jar` to launch Doug 
5. If successful, you should see the welcome message as shown above


# Features

### Input Formatting Guide
- White spaces at the very front and end of the input are safely ignored
- White spaces between different parameters are safely ignored
  - e.g. deadline &nbsp;&nbsp;&nbsp; homework &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; /by &nbsp;&nbsp;&nbsp;&nbsp; tomorrow
- White spaces within format identifiers (e.g. /from, /by, etc.) will lead to input being rejected
  - e.g. **ev&nbsp;&nbsp;ent** CCA Meeting **/fr&nbsp;&nbsp;om** morning /to tonight
- Date and time inputs must match exactly the formats outlined later, and no whitespaces within them are allowed

### Interpreting Output Format
Tasks are shown to the user in the following format
`[Z][X] description [time/date]`
- In the first box, the letter represents the task type
  - T = **todo** task
  - D = **deadline** task
  - E = **event** task

- In the second box, the letter represents whether the task is marked as done or not done
  - X = task is marked as **done**. 
  - (blank) = task is marked as **not done**.

## Adding Todos

Format: `todo TASK_NAME`

Creates a new todo task
- Only has a description
- Does not have any date or time attached

Example: `todo collect laundry`

Expected Output:
```
_____________________________________________________________________________________________________
I've added: [T][ ] collect laundry for you.
Your list is now 1 tasks long partner
_____________________________________________________________________________________________________
```


## Adding Deadlines

Format: `deadline TASK_NAME /by DUE_DATE`

Creates a new deadline task
- Has a description and a single date/time component attached
- Due date parameter must be in one of the following forms:
  - In text form (e.g. tomorrow)
  - In date form yyyy-MM-dd (e.g. 2001-11-09)
  - In date and time form yyyy-MM-dd hhmm (e.g. 2020-03-14 1759) 
    - Note: There **must** be only **ONE** white space between the date and time

Example: `deadline ip submission /by tomorrow`

Expected Output:
```
_____________________________________________________________________________________________________
I've added: [D][ ] ip submission (by: tomorrow) for you.
Your list is now 2 tasks long partner
_____________________________________________________________________________________________________
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
_____________________________________________________________________________________________________
I've added: [E][ ] football game (from: May 21 2018, 07.30pm to: May 21 2018, 09.00pm) for you.
Your list is now 3 tasks long partner
_____________________________________________________________________________________________________
```


## Listing Tasks

Format: `list`

Lists out all the tasks, in order of insertion
- When accessing specific tasks (for marking, unmarking or deletion), use the index number shown by this command

Example: `list`

Expected Output (When list is not empty):
```
_____________________________________________________________________________________________________
Here, let me lay out your tasks for you.
1.[T][ ] collect laundry
2.[D][ ] ip submission (by: tomorrow)
3.[E][ ] football game (from: May 21 2018, 07.30pm to: May 21 2018, 09.00pm)
_____________________________________________________________________________________________________
```
Expected Output (When list is empty):
```
_____________________________________________________________________________________________________
Got nothing on your roster bud.
_____________________________________________________________________________________________________
```

## Marking Tasks

Format: `mark INDEX`

Marks the task as done
- Index must match that of the listed indexes

Example: `mark 1`

Expected Output:
```
_____________________________________________________________________________________________________
Sure thing partner, I'll mark it as done
[T][X] collect laundry
_____________________________________________________________________________________________________
```


## Unmarking Tasks

Format: `unmark INDEX`

Marks the task as not done
- Index must match that of the listed indexes

Example: `unmark 1`

Expected Output:
```
_____________________________________________________________________________________________________
Sure thing partner, I'll mark it as not done
[T][ ] collect laundry
_____________________________________________________________________________________________________
```


## Deleting Tasks

Format: `delete INDEX`

Removes the task from the list
- Index must match that of the listed indexes

Example: `delete 3`

Expected Output:
```
_____________________________________________________________________________________________________
I've deleted: [E][ ] football game (from: May 21 2018, 07.30pm to: May 21 2018, 09.00pm) for you.
Your list is now 2 tasks long partner
_____________________________________________________________________________________________________
```


## Finding Tasks

Format: `find KEYWORDS`

Looks through all the task to find matching task descriptions as the keywords
- Keywords can be as many words or numbers or both

Example: `find IP task`

Expected Output:
```
_____________________________________________________________________________________________________

[T][ ] IP task 1
[T][ ] IP task 2
[T][ ] IP task 3
Here, found these tasks for ya!
_____________________________________________________________________________________________________
```

## Closing the Chatbot

Format: `bye`

Ends the program

Expected Output:
```
_____________________________________________________________________________________________________
It's been a pleasure partner.
Hope to see you around these parts again soon!
_____________________________________________________________________________________________________
```

## Saving your List of Tasks

Your list of tasks is saved automatically every time you add, modify or delete a task

The save data is written to a .txt file located at `[JAR file location]/data/tasks.txt`

## Editing Save File

Users are welcome to edit the save file if they wish to do so

However, if the tasks are modified to a different format than what is accepted by the system,
then the modified tasks may be incorrectly registered by Doug. Take caution when doing so.
