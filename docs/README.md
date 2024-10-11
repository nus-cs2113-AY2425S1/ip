# Poppy Chatbot User Guide

Poppy is a chatbot designed for task management, enabling users to easily create, view, and manage their tasks via a Command Line Interface (CLI). With Poppy, you can efficiently handle your tasks without the need for a traditional graphical user interface.

## Quick Start

### Features
- Listing all tasks: `list`
- Deleting a task: `delete`
- Exiting the program: `Bye`
- Finding a task: `Find`
- Add a todo: `todo`
- Add a deadline: `deadline`
- Add an event: `event`
- Marking a task as done: `mark`
- Unmarking a task as done: `unmark`
- Saving your data: 
- Loading your data

## Quick Start Instructions

1. Ensure you have Java 17 or above installed on your computer.
2. Download the latest `poppy.jar` file from the release page.
3. Copy the file to the folder you want to use as the home folder for Poppy.
4. Open a command terminal, `cd` into the folder where you placed the `poppy.jar` file, and use the following command to run the application:

   ```bash
   java -jar poppy.jar
5. Enjoy using Poppy!

## Detailed Features

1. **Add a todo**
   ```plaintext
   todo [description] 
   ```
   Example: todo Buy pen

2. **Add a deadline**
   ```plaintext
   deadline [description] /by [yyyy-MM-dd] [HH:MM:SS]
   ```
   Example: deadline Submit Homework /by 2024-10-11 18:00:00

3. **Add an event**
   ```plaintext
   event [description] /from [start time] /to [end time]
   ```
   Example: event wedding /from 0900 /to 1800

4. **Mark a task as done**
   ```plaintext
   mark [task number]
   ```
   Example: mark 1

5. **Unmark a task**
   ```plaintext
   unmark [task number]
   ```
   Example: unmark 1

6. **Delete a task**
   ```plaintext
   delete [task number]
   ```
   Example: delete 1

7. **List all tasks**
   ```plaintext
   list
   ```

8. **Find tasks by keyword**
   ```plaintext
   Find [keyword]
   ```
   Example: find groceries

9. **Exit the application**
   ```plaintext
   Bye
   ```
## Data Management
- **Saving Data**: Poppy automatically saves task data to your hard disk after any command that modifies the data.
- **Editing the Data File**: Task data is saved as a txt file at `[jar file location]/data/Poppy.txt`. Caution is advised when editing this file directly.

