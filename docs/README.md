# Bron User Guide

Welcome to Bron!

## Table of Content

1.	Introduction 
2.  Quick Start
3.	Features
•	1. Print User Guide
•	2. Add a To-Do
•	3. Add a Deadline
•	4. Add an Event
•	5. List Tasks
•	6. Find Tasks by Keyword
•	7. Mark a Task as Done
•	8. Unmark a Task
•	9. Delete a Task
•	10. Exit Chatbot
4.	Additional Notes

## Introduction

Bron is a chatbot designed to help you manage your tasks efficiently with simple commands. 
From adding to-do tasks, deadlines, and events to managing them, Bron has you covered.

## Quick Start

1.	Ensure Java 17 or above is installed on your system.
2.	Download the latest .jar file from the repository.
3.	Copy the .jar file to your preferred folder.
4.	Open a terminal in the folder and run the command:
```java -jar Bron.jar```

Features

	ℹ️ Note: Commands are case-insensitive, and extra spaces between parameters will be ignored.

### 1. Print User Guide: help

Syntax: `help`

Description: Displays a guide on how to use Bron and its features.

### 2. Add a To-Do: todo

Syntax: `todo DESCRIPTION`

Description: Adds a to-do task to your list.

Example:

	•	`todo Buy groceries` 
adds “Buy groceries” to your task list.

### 3. Add a Deadline: deadline

Syntax: `deadline DESCRIPTION /by YYYY-MM-DD HHMM`

Description: Adds a deadline task with a specified due date and time.

Example:

	•	`deadline Submit assignment /by 2024-10-12 1800`
adds “Submit assignment” with the due date of October 12, 2024, 6:00 PM.

### 4. Add an Event: event

Syntax: `event DESCRIPTION /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM`

Description: Adds an event to your list with start and end times.

Example:

	•	'event Project meeting /from 2024-10-12 0900 /to 2024-10-12 1100'
adds "Project meeting" event with the timeline from October 12, 2024, 9:00AM to October 12, 2024, 11:00AM

### 5. List Tasks: list

Syntax: `list`

Description: Lists all tasks currently in your task list.

### 6. Find Tasks by Keyword: find

Syntax: `find KEYWORD`

Description: Searches and lists tasks containing the specified keyword.

Example:

	•	`find groceries`
display all tasks that contain the word “groceries”.


### 7. Mark a Task as Done: mark

Syntax: `mark TASK_INDEX`

Description: Marks the specified task as completed.

Example:

	•	'mark 1' 
marks the first task in the list as done.

### 8. Unmark a Task: unmark

Syntax: `unmark TASK_INDEX`

Description: Unmarks the specified task, indicating it is not completed.

Example:

	•	`unmark 1` 
unmarks the first task.

### 9. Delete a Task: delete

Syntax: `delete TASK_INDEX`

Description: Deletes the specified task from your list.

Example:

	•	`delete 1` 
deletes the first task.

### 10. Exit Chatbot: bye

Syntax: `bye`

Description: Exits the chatbot.

## Additional Notes

	•	Your tasks are automatically saved after each command.
	•	Tasks are loaded from the saved file when you restart Bron.