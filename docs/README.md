Quinn Task Manager User Guide

Table of Contents

1. Introduction
2. Getting Started
3. Main Features
3.1 Adding Tasks
3.2 Listing Tasks
3.3 Marking Tasks as Done
3.4 Unmarking Tasks
3.5 Deleting Tasks
3.6 Finding Tasks
4. Command Reference
5. Understanding Task Types
6. Error Handling
7. Data Storage
8. Tips and Best Practices
9. Troubleshooting
10. Quinn Command Summary

1. Introduction
   Welcome to Quinn, your personal task management assistant! Quinn is a command-line application designed to help you efficiently manage your tasks, deadlines, and events. With its simple yet powerful interface, Quinn makes it easy to keep track of your to-do list and stay organized.
   Key Features:

Add various types of tasks: ToDos, Deadlines, and Events
List and view your tasks
Mark tasks as done or not done
Delete tasks
Search for specific tasks
Persistent storage of your tasks

2. Getting Started
   System Requirements

Java Runtime Environment (JRE) 8 or higher

Installation

Download the Quinn.jar file from [insert download link here].
Place the JAR file in your desired directory.

Running Quinn
Open a command prompt or terminal, navigate to the directory containing Quinn.jar, and run:
java -jar Quinn.jar
Upon successful launch, you'll see the Quinn welcome message:
Hello! I'm Quinn, your Personal Assistant ChatBot.

QQQ   U   U III N   N N   N
Q   Q  U   U  I  NN  N NN  N
Q   Q  U   U  I  N N N N N N
Q   Q  U   U  I  N  NN N  NN
QQQ    UUU  III N   N N   N
Q                       
QQ

What can I do for you?
3. Main Features
   3.1 Adding Tasks
   Quinn supports three types of tasks: ToDos, Deadlines, and Events.
   Adding a ToDo
   Command: todo [description]
   Example:
   Enter Command:
   todo Buy groceries
   Output:
   Got it. I've added this task:
   [T][ ] Buy groceries
   Now you have 1 task in the list.
   Adding a Deadline
   Command: deadline [description] /by [date] [time]
   Example:
   Enter Command:
   deadline Submit report /by 2023-06-30 1400
   Output:
   Got it. I've added this task:
   [D][ ] Submit report (by: Jun 30 2023 02:00 PM)
   Now you have 2 tasks in the list.
   Adding an Event
   Command: event [description] /from [start date] [start time] /to [end date] [end time]
   Example:
   Enter Command:
   event Team meeting /from 2023-06-15 0900 /to 2023-06-15 1100
   Output:
   Got it. I've added this task:
   [E][ ] Team meeting (from: Jun 15 2023 09:00 AM to: Jun 15 2023 11:00 AM)
   Now you have 3 tasks in the list.
   3.2 Listing Tasks
   To view all your tasks, use the list command.
   Command: list
   Example:
   Enter Command:
   list
   Output:
   Here are the tasks in your list:
   [Legend: T = todo, D = deadline, E = event]

   1.[T][ ] Buy groceries
   2.[D][ ] Submit report (by: Jun 30 2023 02:00 PM)
   3.[E][ ] Team meeting (from: Jun 15 2023 09:00 AM to: Jun 15 2023 11:00 AM)
   3.3 Marking Tasks as Done
   To mark a task as completed, use the mark command followed by the task number.
   Command: mark [task number]
   Example:
   Enter Command:
   mark 1
   Output:
   Roger! I've marked this task as done:
   [T][✔] Buy groceries
   3.4 Unmarking Tasks
   To mark a completed task as not done, use the unmark command followed by the task number.
   Command: unmark [task number]
   Example:
   Enter Command:
   unmark 1
   Output:
   OK, I've marked this task as not done yet:
   [T][ ] Buy groceries
   3.5 Deleting Tasks
   To remove a task from your list, use the delete command followed by the task number.
   Command: delete [task number]
   Example:
   Enter Command:
   delete 2
   Output:
   Noted. I've removed this task:
   [D][ ] Submit report (by: Jun 30 2023 02:00 PM)
   Now you have 2 tasks in the list.
   3.6 Finding Tasks
   To search for tasks containing a specific keyword, use the find command.
   Command: find [keyword]
   Example:
   Enter Command:
   find meeting
   Output:
   Here is the matching task in your list:
   [Keyword Search: meeting]
   [Legend: T = todo, D = deadline, E = event]

   1.[E][ ] Team meeting (from: Jun 15 2023 09:00 AM to: Jun 15 2023 11:00 AM)
4. Command Reference
   Here's a quick reference for all available commands:

todo [description]: Add a ToDo task
deadline [description] /by [date] [time]: Add a Deadline task
event [description] /from [start date] [start time] /to [end date] [end time]: Add an Event task
list: Display all tasks
mark [task number]: Mark a task as done
unmark [task number]: Mark a task as not done
delete [task number]: Remove a task
find [keyword]: Search for tasks containing the keyword
bye: Exit the application

5. Understanding Task Types
   Quinn supports three types of tasks:

ToDo: A simple task without any date/time constraint.

Displayed as: [T][ ] Description


Deadline: A task with a specific due date and time.

Displayed as: [D][ ] Description (by: Date Time)


Event: A task with a start and end date/time.

Displayed as: [E][ ] Description (from: Start Date Time to: End Date Time)



The square brackets next to the task type indicator (T/D/E) show the task's completion status:

[ ]: Task is not done
[✔]: Task is done

6. Error Handling
   Quinn will display error messages if it encounters issues. Here are some common errors and their meanings:

"INVALID COMMAND. Please try again!": The command entered is not recognized.
"The description of a todo cannot be empty!": You need to provide a description for the ToDo task.
"INCOMPLETE COMMAND": Some required information is missing from the command.
"Task not found. Please try again!": The task number provided doesn't exist in the list.
"Please enter a valid task number to be marked as done!": The mark/unmark command requires a valid task number.

If you encounter an error, read the error message carefully and adjust your command accordingly.
7. Data Storage
   Quinn automatically saves your tasks to a file named "tasks.txt" in a "data" folder in the same directory as the Quinn.jar file. This ensures that your tasks persist between sessions.
   Note: Do not modify the "tasks.txt" file manually, as this may cause Quinn to malfunction.
8. Tips and Best Practices

Use descriptive task names to make them easier to find later.
Regularly review and update your task list to keep it current.
Use the find command to quickly locate specific tasks in a long list.
Mark tasks as done as soon as you complete them to keep your list up-to-date.

9. Troubleshooting
   If you encounter issues while using Quinn:
Ensure you're using the correct command syntax.
Check that your Java Runtime Environment is up to date.
Make sure Quinn has write permissions in its directory for saving tasks.
If Quinn fails to start, check your system's console for error messages.

10. Quinn Command Summary
## Quinn Command Summary

| Command | Description | Example |
|---------|-------------|---------|
| `todo [description]` | Add a ToDo task | `todo Buy groceries` |
| `deadline [description] /by [date] [time]` | Add a Deadline task | `deadline Submit report /by 2023-06-30 1400` |
| `event [description] /from [start date] [start time] /to [end date] [end time]` | Add an Event task | `event Team meeting /from 2023-06-15 0900 /to 2023-06-15 1100` |
| `list` | Display all tasks | `list` |
| `mark [task number]` | Mark a task as done | `mark 1` |
| `unmark [task number]` | Mark a task as not done | `unmark 2` |
| `delete [task number]` | Remove a task | `delete 3` |
| `find [keyword]` | Search for tasks containing the keyword | `find meeting` |
| `bye` | Exit the application | `bye` |

### Examples of Task Display

After using these commands, tasks will be displayed in the following format:

1. ToDo: `[T][ ] Buy groceries`
2. Deadline: `[D][ ] Submit report (by: Jun 30 2023 02:00 PM)`
3. Event: `[E][ ] Team meeting (from: Jun 15 2023 09:00 AM to: Jun 15 2023 11:00 AM)`

Note:
- `[ ]` indicates a task is not done
- `[✔]` indicates a task is done