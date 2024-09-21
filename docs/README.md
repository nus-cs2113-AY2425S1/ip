[//]: # (<link rel="stylesheet" href="assets/css/style.css">)

# Esme User Guide

## 🌟 Welcome to Esme! 🌟
Esme is your one-stop Java CLI companion for efficient task management. 

With Esme, you can effortlessly create, update, and manage your tasks using straightforward text commands. 

Whether you need to:

- ➕ Add new tasks
- ✅ Mark task as completed
- ❌ Delete task
- 🔍 Search for tasks
- 📋 List all your current tasks

Esme has you covered. Let’s dive in and explore how Esme can streamline your workflow!

## Quick Start

❗Below are the **prerequisites**:

1. Ensure you have Java 17 installed on your machine.
2. Download the latest `.jar` file from [here](https://github.com/ehz0ah/ip/releases/tag/A-Jar)
3. Copy the file to the folder you want to use as the home folder for your chatbot.
4. Open a command terminal, `cd` into the directory where the `.jar` file is located.
5. Run the following command: `java -jar esme.jar`

On launch, you should see this interface:
```
Hello! I'm
 _____                    
| ____|___ _ __ ___   ___ 
|  _| / __| '_ ` _ \ / _ \ 
| |___\__ \ | | | | |  __/ 
|_____|___/_| |_| |_|\___| , the Astrologer. The cosmos whispers its secrets to me.
------------------------------------------------------------------------------------------------------------------------
How may I assist you today? The stars and I are at your service.
------------------------------------------------------------------------------------------------------------------------
```

## Features
Esme supports 3 types of tasks: `Todo`, `Deadline` and `Event`.

`Todo`: Task to be completed without specific timing constraints.

`Deadline`: Task to be completed by a specified deadline.

`Event`: Task with a defined start and end time.

The following commands will be used as an example:
- todo grocery shopping
- deadline CS3244 tutorial /by 2024-09-26
- event sailing regatta /from 2024-09-28 /to 2024-09-29
- delete 1
- mark 1
- unmark 1
- find sail
- task in 2024-09-01
- list
- help
- bye

Any unrecognized command will result in the following response:
```
------------------------------------------------------------------------------------------------------------------------
The stars are unclear on this command. Could you please try again? Type "help" for more information.
------------------------------------------------------------------------------------------------------------------------
```

## Adding Todo Task
Adds a `Todo` task to the task list.

Format: `todo <description>`

Examples:
- todo grocery shopping

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
The stars have aligned and grocery shopping is now part of your destiny!
------------------------------------------------------------------------------------------------------------------------
Time to work! You got 1 tasks waiting for you!
------------------------------------------------------------------------------------------------------------------------
```

## Adding Deadline Task
Adds a `Deadline` task to the task list.

Format: `deadline <description> /by <YYYY-MM-DD>`

Examples:
- deadline CS3244 tutorial /by 2024-09-26

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
The stars have aligned and CS3244 tutorial is now part of your destiny!
------------------------------------------------------------------------------------------------------------------------
Time to work! You got 2 tasks waiting for you!
------------------------------------------------------------------------------------------------------------------------
```

## Adding Event Task
Adds an `Event` task to the task list.

Format: `event <description> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`

Examples:
- event sailing regatta /from 2024-09-28 /to 2024-09-29

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
The stars have aligned and sailing regatta is now part of your destiny!
------------------------------------------------------------------------------------------------------------------------
Time to work! You got 3 tasks waiting for you!
------------------------------------------------------------------------------------------------------------------------
```

## Delete Task
Delete a task from the task list.

Format: `delete <index>`

Examples:
- delete 1

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
grocery shopping has been removed from your destiny!
------------------------------------------------------------------------------------------------------------------------
Time to work! You got 2 tasks waiting for you!
------------------------------------------------------------------------------------------------------------------------
```

## Mark Task
Mark a task as completed.

Format: `mark <index>`

Examples:
- mark 1

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
Outstanding! This task is marked as done, and your destiny shines brighter.
  [X] CS3244 tutorial
------------------------------------------------------------------------------------------------------------------------
```

## Unmark Task
Unmark a task as uncompleted.

Format: `unmark <index>`

Examples:
- unmark 1

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
Fear not, for this task remains unfinished. We shall conquer it in due time!
  [] CS3244 tutorial
------------------------------------------------------------------------------------------------------------------------
```

## Find Task With Keyword
Find and print out all the tasks related to the keyword.

Format: `find <keyword>`

Examples:
- find sail

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
[E][ ] sailing regatta (from: 28 Sep 2024, to: 29 Sep 2024)
Type 'list' to see the index to delete, mark, unmark task!
------------------------------------------------------------------------------------------------------------------------
```

## Search All Task Within A Month
Search and print out all the tasks that occur within the month specified.

Format: `task in <YYYY-MM-DD>`

Examples:
- task in 2024-09-01

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
[D][ ] CS3244 tutorial (by: 26 Sep 2024)
[E][ ] sailing regatta (from: 28 Sep 2024, to: 29 Sep 2024)
Type 'list' to see the index to delete, mark, unmark task!
------------------------------------------------------------------------------------------------------------------------
```

## List Task
Lists all the tasks in the task list.

Format: `list`

Expected Outcome:
```
By the light of the moon, these are the tasks that guide your path:
1.[D][ ] CS3244 tutorial (by: 26 Sep 2024)
2.[E][ ] sailing regatta (from: 28 Sep 2024, to: 29 Sep 2024)
------------------------------------------------------------------------------------------------------------------------
```

## Viewing help
Lists out all the available commands and their command format.

Format: `help`

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
Available commands:
- bye: Exit the application.
- todo [description]: Add a new todo task.
- deadline [description] /by [YYYY-MM-DD]: Add a new task with a deadline.
- event [description] /from [YYYY-MM-DD] /to [YYYY-MM-DD]: Add a new event.
- mark [task number]: Mark a task as completed.
- unmark [task number]: Unmark a completed task.
- delete [task number]: Delete a task.
- list: List all tasks.
- task in [YYYY-MM-DD]: List all task in the same year and month.
- find [keyword]: Find a task by keyword.
- help: Show this help message.
------------------------------------------------------------------------------------------------------------------------
```

## Exit Program
Exits the program.

Format: `bye`

Expected Outcome:
```
------------------------------------------------------------------------------------------------------------------------
Au revoir, mon ami! May the cosmos continue to weave a tapestry of fortune in your favor!
------------------------------------------------------------------------------------------------------------------------
```

## Saving the data
Esme's data are saved in an external `.txt` file automatically after the `bye` command is executed. There is no need to save manually.