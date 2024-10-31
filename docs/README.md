# Flash Chatbot User Guide

Welcome to **Flash** - Your personal assistant chatbot designed to help you manage tasks efficiently, including todos, deadlines, and events.

## Features

### Add Task

You can add three types of tasks:

1. Todos - description only
2. Deadlines - description with a by date
3. Events - description with from and to dates

### List Tasks

Display all tasks, encompassing todos, deadlines, and events, along with their respective statuses denoting completion or pending. Deadline tasks include the due date, while event tasks display both the start and end dates.

### Mark Task as Done

Mark any task as completed based on its list number.

### Mark Task as Incomplete

Restore any completed task to pending status based on its list number.

### Delete Task

Remove any task in the task list based on its list number.

### Find Tasks

Search for tasks by keywords in the task list.

### Save Data

All tasks are automatically saved as you add tasks. They will be loaded when you start the program again.

## Getting Started

### Prerequisites

Make sure you have Java 17 or above installed on your computer.

### Installation

1. Download the latest release of Flash from the releases page.
2. Extract the downloaded zip file to a folder on your computer.
3. Open a terminal and navigate to the folder where you extracted the zip file.
4. Run the following command to start the Flash application:

    ```bash
    java -jar flash.jar
    ```
   If the chatbot starts successfully, it will display the following welcome message:

    ```plaintext
        ---------------------------------------------------------------------------------------
         Hello! I'm Flash
         What can I do for you?
        ---------------------------------------------------------------------------------------
    ```

## Usage

### `todo` - Add a Todo Task

Adds a simple task with no additional information.

**Example of usage:**

```plaintext
todo Clean the Garage
```

A new Todo task "Clean the Garage" is added to your task list.

**Expected Outcome:**

```plaintext
	---------------------------------------------------------------------------------------
	 Got it. I've added this task:
	   [T][ ] Clean the Garage
	 Now you have 1 task in the list.
	---------------------------------------------------------------------------------------
```

### `deadline` - Add a Deadline Task

Adds a task with a by date which is the task's deadline.

**Example of usage:**

```plaintext
deadline Submit Report /by 03/15/2024 5:00 PM
```

**Expected Outcome:**
A new Deadline task "Submit Report" with the deadline "03/15/2024 5:00 PM" is added to your task list.

```plaintext
	---------------------------------------------------------------------------------------
	 Got it. I've added this task:
	   [D][ ] Submit Report (by: 03/15/2024 5:00 PM)
	 Now you have 2 tasks in the list.
	---------------------------------------------------------------------------------------
```

### `event` - Add an Event Task

Adds a task with from and to dates, indicating the period of time.

**Example of usage:**

```plaintext
event Team Meeting /from 03/10/2024 3:00 PM /to 03/10/2024 4:00 PM
```

**Expected Outcome:**
A new Event task "Team Meeting" from "03/10/2024 3:00 PM" to "03/10/2024 4:00 PM" is added to your task list.

```plaintext
	---------------------------------------------------------------------------------------
	 Got it. I've added this task:
	   [E][ ] Team Meeting (from: 03/10/2024 3:00 PM to: 03/10/2024 4:00 PM)
	 Now you have 3 tasks in the list.
	---------------------------------------------------------------------------------------
```

### `list` - List all Tasks

Displays all your tasks, including todos, deadlines, and events, along with their status (completed or pending).

**Example of usage:**

```plaintext
list
```

**Expected Outcome:**
All your tasks are displayed in a list format.

```plaintext
	---------------------------------------------------------------------------------------
	 Here are the tasks in your list:
	 1. [T][ ] Clean the Garage
	 2. [D][ ] Submit Report (by: 03/15/2024 5:00 PM)
	 3. [E][ ] Team Meeting (from: 03/10/2024 3:00 PM to: 03/10/2024 4:00 PM)
	---------------------------------------------------------------------------------------
```

### `mark` - Mark Task as Completed

Marks a specific task as completed based on the list number provided.

**Example of usage:**

```plaintext
mark 1
```

**Expected Outcome:**
The first task in your list is marked as completed.

```plaintext
	---------------------------------------------------------------------------------------
	 Nice! I've marked this task as done:
	   [T][X] Clean the Garage
	---------------------------------------------------------------------------------------
```

### `unmark` - Mark Completed Task as Incomplete

Restores a completed task to pending status based on the list number provided.

**Example of usage:**

```plaintext
unmark 1
```

**Expected Outcome:**
The first task in your list is restored as pending.

```plaintext
	---------------------------------------------------------------------------------------
	 OK, I've marked this task as not done yet:
	   [T][ ] Clean the Garage
	---------------------------------------------------------------------------------------
```

### `delete` - Remove Task

Removes a specific task from the list based on the list number provided.

**Example of usage:**
The second task in your list is removed.

```plaintext
delete 2
```

**Expected Outcome:**

```plaintext
	---------------------------------------------------------------------------------------
	 Noted. I've removed this task:
	   [D][ ] Submit Report (by: 03/15/2024 5:00 PM)
	 Now you have 2 tasks in the list.
	---------------------------------------------------------------------------------------
```

### `find` - Find Tasks

Search for tasks by keywords in the task list.

**Example of usage:**

```plaintext
find Meeting
```

**Expected Outcome:**
All tasks containing the keyword “Meeting” are displayed.

```plaintext
	---------------------------------------------------------------------------------------
	 Here are the matching tasks in your list:
	 1. [E][ ] Team Meeting (from: 03/10/2024 3:00 PM to: 03/10/2024 4:00 PM)
	---------------------------------------------------------------------------------------
```

### `bye` - Exit the Program

Exits the program.

**Example of usage:**

```plaintext
bye
```

**Expected Outcome:**
The program exits.

```plaintext
	---------------------------------------------------------------------------------------
	 Bye. Hope to see you again soon!
	---------------------------------------------------------------------------------------
```

