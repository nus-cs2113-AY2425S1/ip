# King Chatbot Project - User Guide

Welcome to **King**, a chatbot designed to assist you with managing tasks efficiently, whether they are basic *todos*, *deadlines*, or *events*.

## Getting Started

### Prerequisites

Before running King, make sure you have the following set up on your computer:
- **Java Development Kit (JDK) 17** installed.
- **IntelliJ IDEA** (latest version).

### Setting Up the Project

1. **Open IntelliJ IDEA**: If you have an existing project open, close it by selecting `File` > `Close Project`.
2. **Open King Project**:
   - Click `Open` on the welcome screen.
   - Select the King project directory, then click `OK`.
3. **Configure the Project**:
   - Set the JDK to **JDK 17** as outlined [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
   - Ensure the project language level is set to `SDK default`.
4. **Run King**:
   - In IntelliJ, locate `src/main/java/King.java`.
   - Right-click on `King.java` and choose `Run King.main()`.
   - If the setup is correct, you should see the following output:
     ```
     Hello from
     
     8 8888     ,88'  8 8888 b.             8     ,o888888o.
     8 8888    ,88'   8 8888 888o.          8    8888     `88.
     8 8888   ,88'    8 8888 Y88888o.       8 ,8 8888       `8.
     8 8888  ,88'     8 8888 .`Y888888o.    8 88 8888
     8 8888 ,88'      8 8888 8o. `Y888888o. 8 88 8888
     8 8888 88'       8 8888 8`Y8o. `Y88888o8 88 8888 
     8 888888<        8 8888 8   `Y8o. `Y8888 88 8888   8888888
     8 8888 `Y8.      8 8888 8      `Y8o. `Y8 `8 8888       .8
     8 8888   `Y8.    8 8888 8         `Y8o.`    8888     ,88
     8 8888     `Y8.  8 8888 8            `Yo     `8888888P
     ```

### Key Features

King offers a range of task management features to help you stay organized.

#### 1. Add a Todo
Adds a simple task to your task list without a specific date or time.

- **Command:** `todo <task description>`
- **Example:** `todo Read book`

#### 2. Add a Deadline

Adds a task that must be completed by a specific deadline.

- **Command:** `deadline <task description> /by <deadline date/time>`
- **Example:** `deadline Submit assignment /by 2024-09-30`

The deadline can be provided in either the `yyyy-mm-dd` format or the `yyyy-mm-dd hhmm` format (e.g., `2024-09-30 2359`). Other formats are not supported.

#### 3. Add an Event

Adds an event with a specific start and end date/time.

- **Command:** `event <event description> /from <start date/time> /to <end date/time>`
- **Example:** `event Team meeting /from 2024-10-01 1000 /to 2024-10-01 1200`

The start and end date/time can be provided in either the `yyyy-mm-dd` format or the `yyyy-mm-dd hhmm` format (e.g., `2024-10-01 1000 to 2024-10-01 1200`). Other formats are not supported.

#### 4. List All Tasks
Lists all the tasks currently in your task list.

- **Command:** `list`

#### 5. Mark a Task as Done
Marks a task in the task list as completed.

- **Command:** `mark <task index>`
- **Example:** `mark 2`

#### 6. Mark a Task as Undone

Marks a task in the task list as completed.

- **Command:** `mark <task index>`
- **Example:** `unmark 3`

#### 7. Delete a Task
Removes a task from your task list.

- **Command:** `delete <task index>`
- **Example:** `delete 4`

#### 8. Find Tasks by Keyword
Searches for tasks containing a specific keyword.

- **Command:** `find <keyword>`
- **Example:** `find assignment`

#### 9. Exit the Program
Exits the King chatbot.

- **Command:** `bye`

### Saving and Loading Tasks

King automatically saves your tasks to a local file on exit and loads them when you start the program again. You don't need to worry about losing your tasks after exiting the application.

### Error Handling

King provides clear error messages when incorrect commands are entered. If you accidentally enter a command King doesn't recognize, it will prompt you to enter a valid command.

### User Experience

King is designed to be lightweight and fast. All important features are readily available via simple commands, so you can focus on managing your tasks rather than dealing with a complex interface.

## Troubleshooting

### IntelliJ Setup Issues
If you encounter issues while setting up the project, try the following:
- **Restart IntelliJ** to resolve any potential caching issues.
- Make sure you are using **JDK 17**. Some issues can arise if using the wrong JDK version.

### Command Errors
If the chatbot doesn't respond to a command, ensure:
- The correct syntax for the command is being used (e.g., including `/by` for deadlines and `/from.../to` for events).
- Task indexes are correct when marking or deleting tasks.

## Conclusion

King is a simple yet effective chatbot for managing your tasks. With commands that are easy to remember and intuitive features, King is the perfect companion to keep you organized.

Feel free to visit the [project's GitHub Pages](https://github.com/cxc0418/ip) for more details and up-to-date information.
