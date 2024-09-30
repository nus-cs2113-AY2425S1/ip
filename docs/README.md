# Ran User Guide

![Welcome screen of Ran greeting the user when Ran chatbot is started up](../Ran-Welcome-Screen.png)

### Ran? Ran Yakumo? From Touhou Project?
Yes!
The power of Ran Yakumo, from the hit video-game series Touhou Project, in the chasis of your computer.
Yukari Yakumo has agreed to loan out a tiny portion of her beloved Shikigami's power towards making a chatbot.
Harness a small percentage of Ran's intellect to help you manage your day-to-day tasks! 

# Quick Start
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest `ran.jar` file from [here](https://github.com/3CCLY/ip/releases).
3. Navigate to the directory where `ran.jar` is installed, and relocate it to whichever directory you please.
4. Open the terminal, and `cd` into the directory of `ran.jar`, and run the command `java -jar ran.jar` to start the chatbot.
5. If all goes well, you should see the screen look something like the picture above.
6. Use commands listed in the [Features](#features) section to get Ran to manage your tasks.
7. Type `bye` to terminate the Ran chatbot.

# Features

## Add a Todo Task: `todo`

## Add a Deadline Task: `deadline`

## Add an Event Task: `event`

## Show list of all tasks: `list`

## Mark task as done: `mark`

## Unmark task (set as not done): `unmark`

## Delete task from list: `delete`

Delete the task from the list of tasks at a specified index.

Format: `delete [INDEX]`

Example: `delete 2`

Possible Output:

```
    ____________________________________________________________
    Noted. I've removed this task:
      [T][ ] Play guitar
    You currently have 3 tasks in your list.
    ____________________________________________________________
```

## Find task: `find`

Find tasks from the entire list of tasks that contain a keyword/phrase.

Format: `find [KEYWORD/PHRASE]`

Example: `find homework`

Possible Output:

```
    ____________________________________________________________
    1.[D][ ] Math homework (by: Friday)
    2.[D][ ] Physics homework (by: Thursday)
    3.[D][ ] Chemistry homework (by: Friday)
    ____________________________________________________________
```

## Terminate chatbot: `bye`

Terminate the Ran chatbot.

Format: `bye`

Expected Output:

```
    ____________________________________________________________
    Farewell. May we meet again!
    ____________________________________________________________
```

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```
// Feature details
