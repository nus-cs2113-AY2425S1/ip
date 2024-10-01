# Hsien Task Manager

Hsien is a powerful command-line task management application designed to help you organize, track, and prioritize your tasks effortlessly. Whether you're managing deadlines, scheduling events, or simply keeping track of your daily to-dos, Hsien offers a user-friendly interface that makes task management simple and efficient.

### Key Features

- **Add tasks**: Create new tasks, including todos, deadlines, and events.
- **View tasks**: List all your tasks at any time.
- **Mark and unmark tasks**: Keep track of completed tasks.
- **Delete tasks**: Remove tasks that are no longer needed.
- **Search for tasks**: Find specific tasks based on keywords.
- **Save tasks**: Automatically save tasks to a text file.

With Hsien, you can take control of your schedule, reduce stress, and enhance your productivity—all from your command line.

## Getting Started

### Prerequisites

- JDK 17.

### Download

You can download the latest version of Hsien as a JAR file from the following link:

[Download Hsien JAR](https://github.com/KuanHsienn/ip/releases/download/A-Release/Hsien.jar)

### Running the Application

1. Open your command line interface (CLI).
2. Navigate to the directory where the JAR file is located.
3. Run the application using the following command:

   ```
   java -jar Hsien.jar
   ```

4. This page will be shown if the previous command was run successfully


   ```
   --------------------------------------------------
    _   _         _____        _ _   _
   | | | | ##### |_   _|##### |   \ | |
   | |_| |#        | |  #     | |\ \| |
   |  _  | #####   | |  ##### | | \ \ |
   | | | |      # _| |_ #     | |  \  |
   |_| |_| ##### |_____|##### |_|   \_|
   
   Hello! I am Hsien, your personal chatbot... Please use me as a task planner
   
   --------------------------------------------------
   
   File not found
   
   --------------------------------------------------
   
   These are the possible commands:
   1. todo
   2. deadline
   3. event
   4. list
   5. mark
   6. unmark
   7. delete
   8. find
   9. save
   10. bye
   Please enter a command/add task (type 'bye' to exit):
   
   ```



## Features

| Command   | Description                                  |
|-----------|----------------------------------------------|
| `todo`    | Adds a new todo task to the task list.      |
| `deadline`| Adds a new deadline task to the task list.  |
| `event`   | Adds a new event task to the task list.     |
| `list`    | Displays all tasks in the task list.        |
| `mark`    | Marks a task as done.                        |
| `unmark`  | Unmarks a task as not done.                  |
| `delete`  | Deletes a task from the task list.          |
| `find`    | Searches for tasks containing a specific query. |
| `save`    | Saves the current tasks to a file.          |
| `bye`     | Exits the application.                       |


## Command Format

| Command Syntax               | Description                                          |
|------------------------------|------------------------------------------------------|
| `todo <description>`         | Adds a new todo task with the specified description. |
| `deadline <description> /by <date>` | Adds a new deadline task with the specified description and due date. |
| `event <description> /from <start> /to <end>` | Adds a new event task with the specified description, start date, and end date. |
| `list`                       | Displays all tasks in the task list.                 |
| `mark <list number>`         | Marks the task at the specified index as done.       |
| `unmark <list number>`       | Unmarks the task at the specified index as not done. |
| `delete <list number>`       | Deletes the task at the specified index from the task list. |
| `find <keyword>`             | Searches for tasks containing the specified keyword.   |
| `save`                       | Saves the current tasks to a file.                   |
| `bye`                        | Exits the application.                               |


## Command Usage

### 1. `todo <description>`
Adds a new todo task with the specified description.
- **Example**: `todo Buy groceries`

### 2. `deadline <description> /by <date>`
Adds a new deadline task with the specified description and due date.
- **Example**: `deadline Submit assignment /by 2024-10-10 2359`

### 3. `event <description> /from <start> /to <end>`
Adds a new event task with the specified description, start date, and end date.
- **Example**: `event Team meeting /from 2024-10-01 1000 /to 2024-10-01 1200`

### 4. `list`
Displays all tasks in the task list.
- **Example**: `list`

### 5. `mark <task number>`
Marks the task at the specified index as done.
- **Example**: `mark 1`

### 6. `unmark <task number>`
Unmarks the task at the specified index as not done.
- **Example**: `unmark 1`

### 7. `delete <task number>`
Deletes the task at the specified index from the task list.
- **Example**: `delete 2`

### 8. `find <keyword>`
Searches for tasks containing the specified keyword.
- **Example**: `find meeting`

### 9. `save`
Saves the current tasks to a file.
- **Example**: `save`

### 10. `bye`
Exits the application.
- **Example**: `bye`

## Notes
1. The commands are case-insensitive  
   e.g. `LiSt` is equivalent to `list`
2. Extraneous parameters for commands that do not take in parameters will be ignored
  e.g. `List me` will only be treated as `list`
3. Tasks will be saved upon `bye` command but will not be saved if the program is closed arubptly
4. Please conform to the command syntax for the program to work as expected and have fun using it!

## Contact and Support

For any queries, please drop an email to liangkuanhsien@gmail.com

