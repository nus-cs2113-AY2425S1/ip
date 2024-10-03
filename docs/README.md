
# BEBE (Task Management System) - User Guide

## 1. Introduction
The BEBE is designed to help users manage their tasks efficiently using various commands. Users can add, delete, mark tasks as done, and search for specific tasks based on keywords. The application supports different types of tasks, including Todos, Deadlines, and Events. It operates through a simple command-line interface (CLI).

This guide will walk you through the installation, usage, and available commands.

---

## 2. System Requirements
- Java version: 17.
- Operating System: Any system that supports Java (Windows, macOS, Linux).

---

## 3. Installation
### Step 1: Download
Download the source code files from the repository or the provided distribution package. Ensure that the following files are included:
- `AddCommand.java`
- `Bebe.java`
- `BebeException.java`
- `Command.java`
- `Deadline.java`
- `DeleteCommand.java`
- `Event.java`
- `ExitCommand.java`
- `FindCommand.java`
- `HelpCommand.java`
- `ListCommand.java`
- `MarkCommand.java`
- `Parser.java`
- `Storage.java`
- `Task.java`
- `TaskList.java`
- `Todo.java`
- `Ui.java`

### Step 2: Compilation
Navigate to the folder where the files are located and compile the files using the `javac` command in your terminal:
```bash
javac *.java
```

### Step 3: Running the Application
Once compiled, you can run the application using:
```bash
java Bebe
```

---

## 4. Usage

Once the program is running, you can start entering commands to manage your tasks. The available commands are listed in the **Commands** section below.

Each command follows a specific format that should be adhered to. If an invalid format is detected, the system will provide an appropriate error message.

---

## 5. Commands

### 5.1 `todo <task description>`
Adds a new ToDo task to the list.

- **Example Input:**
  ```bash
  todo Read a book
  ```
- **Example Output:**
  ```plaintext
  Got it. I've added this task:
    [T][✗] Read a book
  Now you have 1 task in the list.
  ```

### 5.2 `deadline <task description> /by <due date>`
Adds a new Deadline task to the list.

- **Example Input:**
  ```bash
  deadline Submit assignment /by 2024-10-10
  ```
- **Example Output:**
  ```plaintext
  Got it. I've added this task:
    [D][✗] Submit assignment (by: 2024-10-10)
  Now you have 2 tasks in the list.
  ```

### 5.3 `event <task description> /from <start date> /to <end date>`
Adds a new Event task to the list.

- **Example Input:**
  ```bash
  event Project presentation /from 2024-10-20 /to 2024-10-22
  ```
- **Example Output:**
  ```plaintext
  Got it. I've added this task:
    [E][✗] Project presentation (from: 2024-10-20 to: 2024-10-22)
  Now you have 3 tasks in the list.
  ```

### 5.4 `list`
Displays all tasks currently in the list.

- **Example Input:**
  ```bash
  list
  ```
- **Example Output:**
  ```plaintext
  Here are the tasks in your list:
  1.[T][✗] Read a book
  2.[D][✗] Submit assignment (by: 2024-10-10)
  3.[E][✗] Project presentation (from: 2024-10-20 to: 2024-10-22)
  ```

### 5.5 `mark <task number>`
Marks the task at the specified index as done.

- **Example Input:**
  ```bash
  mark 2
  ```
- **Example Output:**
  ```plaintext
  Nice! I've marked this task as done:
    [D][✓] Submit assignment (by: 2024-10-10)
  ```

### 5.6 `unmark <task number>`
Unmarks the task at the specified index (marks it as not done).

- **Example Input:**
  ```bash
  unmark 2
  ```
- **Example Output:**
  ```plaintext
  OK, I've marked this task as not done yet:
    [D][✗] Submit assignment (by: 2024-10-10)
  ```

### 5.7 `delete <task number>`
Deletes the task at the specified index.

- **Example Input:**
  ```bash
  delete 1
  ```
- **Example Output:**
  ```plaintext
  Noted. I've removed this task:
    [T][✗] Read a book
  Now you have 2 tasks in the list.
  ```

### 5.8 `find <keyword>`
Searches for tasks that contain the specified keyword in their description.

- **Example Input:**
  ```bash
  find project
  ```
- **Example Output:**
  ```plaintext
  Here are the matching tasks in your list:
  1.[E][✗] Project presentation (from: 2024-10-20 to: 2024-10-22)
  ```

### 5.9 `help`
Displays a list of available commands and their usage.

- **Example Input:**
  ```bash
  help
  ```
- **Example Output:**
  ```plaintext
  Here are the available commands:
  - todo <task description>
  - deadline <task description> /by <due date>
  - event <task description> /from <start date> /to <end date>
  - list
  - mark <task number>
  - unmark <task number>
  - delete <task number>
  - find <keyword>
  - help
  - bye
  ```

### 5.10 `bye`
Exits the application, saving the current list of tasks.

- **Example Input:**
  ```bash
  bye
  ```
- **Example Output:**
  ```plaintext
  Bye. Hope to see you again soon!
  ```

---

## 6. Error Handling

### Invalid Commands
If the system does not recognize a command, it will return an error message such as:
```bash
Unknown command: <your input>
```

### Missing Arguments
If a command is entered without the required arguments (e.g., `mark` without a task number), the system will notify you of the missing information:
```bash
The 'mark' command requires a valid task number.
```

### Out-of-Range Task Indices
If you try to mark, delete, or unmark a task that does not exist (e.g., task number 10 when there are only 5 tasks), the system will alert you:
```bash
Task number does not exist.
```

### Invalid Date Format
If an invalid date format is entered for a deadline or event, the system will respond with:
```bash
The 'deadline' command requires a valid date format (e.g., YYYY-MM-DD).
```

### Empty Input
If no command is entered, the system will prompt the user to enter a valid command:
```bash
Please enter a valid command. Type 'help' to see available commands.
```

---

## 7. Saving and Loading

The system automatically saves tasks upon exiting the application. When you restart the application, your previous tasks will be loaded automatically, allowing you to continue where you left off.

---

## 8. Conclusion

This Task Management System provides an easy-to-use interface for managing tasks of different types. By following the commands listed in this guide, you can keep track of todos, deadlines, and events effectively. For any help or clarification, use the `help` command at any time.

---

