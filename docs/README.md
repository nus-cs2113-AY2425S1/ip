# Ran User Guide

![Welcome screen of Ran greeting the user when Ran chatbot is started up](https://github.com/3CCLY/ip/blob/master/Ran-Welcome-Screen.png?raw=true)

### Ran? Ran Yakumo? From Touhou Project?
Yes!
The power of Ran Yakumo, from the hit video-game series Touhou Project, in the chasis of your computer.
Yukari Yakumo has agreed to loan out a tiny portion of her beloved Shikigami's power towards making a chatbot.
Harness a small percentage of Ran's intellect to help you manage your day-to-day tasks! 

# Quick Start
1. Ensure that you have Java `17` installed on your computer.
2. Download the latest `ran.jar` file from [here](https://github.com/3CCLY/ip/releases).
3. Navigate to the directory where `ran.jar` is installed, and relocate it to whichever directory you please.
4. Open the terminal (`Terminal` on Linux and MacOS, `Command Prompt` on Windows), and use the `cd` command to go into the directory of `ran.jar`, and run the command `java -jar ran.jar` to start the chatbot.
5. If all goes well, you should see the screen look something like the picture above.
6. Use commands listed in the [Features](#features) section to get Ran to manage your tasks (alternatively type `help` to get Ran to guide you).
7. Type `bye` to terminate the Ran chatbot.

# Features

### Input formatting

Ran waits for you to type an input and hit the enter key, and proceed to interpret it as a command. 
If your input is formatted badly, Ran might be unwilling to follow through. 
Try your best to follow the format given with each type of commands!

- Extra white spaces at the start and end of your input are safely ignored
- The first word in your input represent the base command
  - The base command is not case-sensitive, so both `bye` and `Bye` would work for example
  - If just a base command is sufficient (e.g. `bye`, `help`), all input after the base command is ignored
- Parameters are shown between square brackets in the format of the command
  - e.g. for the `todo` command, replace the `[DESCRIPTION]` in the format with whatever you want to type
  - Extra white spaces at the start and end of your parameters are safely ignored
- White spaces in between the base command and format identifiers (/by, /from, /to) are not allowed
  - e.g. `dead line Math homework /b y Friday` is considered an invalid command

### Output formatting

Ran will show your added tasks in this format: `[ ][ ] description (optional-time)`

- The first `[ ]` represents the type of the task, using a letter within the square brackets:
  - `T` stands for a Todo task
  - `D` stands for a Deadline task
  - `E` stands for an Event task
- The second `[ ]` shows if the task is done or not
  - A `X` within the square bracket indicates the task is marked as done

## Getting help: `help`

Receive a help message from Ran.

Format: `help`

## Add a Todo Task: `todo`

Add a Todo task to the list of tasks.

Format: `todo [DESCRIPTION]`

Example: `todo Water the plants`

Expected Output:

```
    ____________________________________________________________
    Understood, I have noted down the following task:
     [T][ ] Water the plants
    You currently have 1 task in your list.
    ____________________________________________________________
```

## Add a Deadline Task: `deadline`

Add a Deadline task to the list of tasks.

Format: `deadline [DESCRIPTION] /by [DEADLINE]`

Example: `deadline Math homework /by Friday`

Expected Output:

```
    ____________________________________________________________
    Understood, I have noted down the following task:
     [D][ ] Math homework (by: Friday)
    You currently have 3 tasks in your list.
    ____________________________________________________________
```

## Add an Event Task: `event`

Add an Event task to the list of tasks.

Format: `event [DESCRIPTION] /from [START-TIME] /to [END-TIME]`

Example: `event Music festival /from 29/5/2024 /to 5/5/2024`

Expected Output:

```
    ____________________________________________________________
    Understood, I have noted down the following task:
     [E][ ] Music festival (from: 29/5/2024 to: 5/5/2024)
    You currently have 6 tasks in your list.
    ____________________________________________________________
```

## Show list of all tasks: `list`

List out all of the tasks currently in the list.

Format: `list`

Possible Output:

```
    ____________________________________________________________
    Here's what you have so far:
    1.[T][ ] Water the plants
    2.[T][ ] Play guitar
    3.[D][ ] Math homework (by: Friday)
    4.[D][ ] Physics homework (by: Thursday)
    5.[T][ ] Feed cat
    6.[E][ ] Music festival (from: 29/5/2024 to: 5/5/2024)
    7.[D][ ] Chemistry homework (by: Friday)
    ____________________________________________________________
```

## Mark task as done: `mark`

Mark the task as done from the list of tasks at a specified index.

Format: `mark [INDEX]`

Example: `mark 5`

Possible Output:

```
    ____________________________________________________________
    Excellent! I've marked this task as done:
      [T][X] Feed cat
    ____________________________________________________________
```

## Unmark task (set as not done): `unmark`

Unmark the task (set as not done) from the list of tasks at a specified index.

Format: `unmark [INDEX]`

Example: `unmark 5`

Possible Output:

```
    ____________________________________________________________
    Sure, I've marked this task as not done yet:
      [T][ ] Feed cat
    ____________________________________________________________
```

## Delete task from list: `delete`

Delete the task from the list of tasks at a specified index.

Format: `delete [INDEX]`

Example: `delete 2`

Possible Output:

```
    ____________________________________________________________
    Noted. I've removed this task:
      [T][ ] Play guitar
    You currently have 6 tasks in your list.
    ____________________________________________________________
```

## Find task: `find`

Find tasks from the entire list of tasks that contain a keyword/phrase.

Format: `find [KEYWORD/PHRASE]`

Example: `find homework`

Possible Output:

```
    ____________________________________________________________
    By separating the boundary between the haves and have nots,
    here's what I managed to find:
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

## Saving list of tasks

Ran automatically saves all your tasks in a text file.
In the directory that Ran chatbot is started up, a `data` directory is created,
with a `ran.txt` text file within to backup your entire list of tasks.
If such a text file in the correct directory already exists,
then Ran will read from it and load all the saved tasks.

More advanced users may want to tinker with the file to manually modify their tasks, 
but care must be taken to ensure that the format of the file is followed. 
Advanced users may also keep this text file as a backup, and run Ran on another computer with this backup.

# Extra

### Credits

This is a fan-made derivative work of the Touhou Project. 
All Touhou Project characters mentioned belongs to ZUN (Team Shanghai Alice).
Touhou Project fan creation guideline: [here](https://touhou-project.news/guideline/) 
