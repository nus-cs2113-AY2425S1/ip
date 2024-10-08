# Mel User Guide


// Product screenshot goes here
![img.png](img.png)

Mel is a console-based chatbot designed to help you manage tasks efficiently, including todos, deadlines, and events. The chatbot understands a variety of commands to add, remove, and track your tasks, as well as search and manage them interactively. With Mel, you can keep track of your schedule, deadlines, and daily tasks seamlessly.

## Key Features:

- Supports **todo** tasks for general items.
- Adds **deadline** tasks with a specified date and optional time.
- Manages **event** tasks with both start and end dates/times.
- Allows marking/unmarking of task completion, and finding tasks by description. 
- Keeps a running list of your tasks and saves them automatically.

## List tasks: list

Displays the current list of tasks, showing their index, type, description, and whether they are completed or not.

Format: list

## Adding a todo task: todo

Adds tasks without any date/time attached to it e.g., explore the new garden

Format: todo DESCRIPTION
- Adds the todo task with the specified DESCRIPTION. The DESCRIPTION refers to the name of the task that will be shown in the task list. 
- The DESCRIPTION is a string and must not be empty.

Examples:

    todo read book


## Adding a deadline task: deadline

Adds tasks that need to be done before a specific date/time e.g., return book by 04/11/2024 8pm

Format: deadline DESCRIPTION /by DATE
- Adds the deadline task with the specified DESCRIPTION. The DESCRIPTION refers to the name of the task that will be shown in the task list.
- The DESCRIPTION is a string and must not be empty.
- Adds deadline date and/or time to the task with the specified DATE. The DATE is input as a string and must not be empty.
- The DATE has to be specified in one of the following formats:
  - yyyy-MM-dd HH:mm 
  - yyyy-MM-dd 
  - dd/MM/yyyy HH:mm 
  - dd/MM/yyyy 
- Adding deadline time to DATE is optional, if not given, it will be automatically filled with 23:59


Examples:

    deadline return book /by 2024-11-04 20:00 Adds a deadline task with a description and deadline of return book and Nov 04 2024, 08:00 pm, respectively.
    deadline submit quiz /by 2024-11-05 Adds a deadline task with a description and deadline of submit quiz and Nov 05 2024, 11:59 pm, respectively.
    deadline order cake /by 07/11/2024 21:00 Adds a deadline task with a description and deadline of return book and Nov 07 2024, 09:00 pm, respectively.
    deadline submit form /by 09/11/2024 Adds a deadline task with a description and deadline of submit form and Nov 09 2024, 11:59 pm, respectively.

## Adding a event task: event

Adds tasks that start at a specific date/time and ends at a specific date/time e.g., (a) team project meeting 2/10/2019 2-4pm (b) orientation week 4/10/2019 to 11/10/2019

Format: event DESCRIPTION /from START_TIME /to END_TIME
- Adds the event task with the specified DESCRIPTION. The DESCRIPTION refers to the name of the task that will be shown in the task list.
- The DESCRIPTION is a string and must not be empty.
- Adds event start and end date/time to the task with the specified START_TIME and END_TIME, respectively. The START_TIME and END_TIME are input as strings and must not be empty.
- The START_TIME and END_TIME can be specified in any string format.


Examples:

    event dinner with friends /from 5pm /to 6pm Adds an event task with a description, start time and end time of dinner with friends, 5pm and 6pm, respectively.

## Deleting a task: delete

Deletes the specified task from the task list.

Format: delete INDEX
- Deletes the task at the specified INDEX.
- The index refers to the index number shown in the displayed task list. 
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

    delete 2 deletes the 2nd task in the task list.

## Mark/Unmark Task Completion: mark/ unmark

Marks/unmarks the specified task from the task list.

Format: mark/unmark INDEX
- Marks/unmarks the task at the specified INDEX to completed/uncompleted.
- The index refers to the index number shown in the displayed task list.
- The index **must be a positive integer** 1, 2, 3, ...

Examples:

    mark 2 marks the 2nd task in the task list as completed.
    unmark 2 unmarks the 2nd task in the task list so that is becomes uncompleted.

## Finding tasks by description keywords: find

Finds persons whose names contain any of the given keywords.

Format: find KEYWORD [MORE_KEYWORDS]
- The search is case-insensitive. e.g book will match Book
- The order of the keywords matter. e.g. Pencil Case will not match Case Pencil
- Only the task description is searched.
- Parts of words will be matched e.g. Pen will match Pencil
- Tasks matching at least one keyword will be returned (i.e. OR search). e.g. Pencil Case will return Pencil Box, Pencil Case

Examples:

    find submit returns submit quiz and submit form
    find read book returns read magazine, book reservation

## End Chatbot: bye

Exits the program.

Format: bye