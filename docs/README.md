# Ellio User Guide   

Ellio is a command-line chatbot designed to manage your tasks efficiently.

## Features

1. Add tasks, deadlines, and events.
2. Mark tasks as done or unmark them.
3. Delete tasks you no longer need.
4. View all tasks or search for tasks using keywords.
5. Persistent task storage: Tasks are saved locally, allowing the chatbot to reload them automatically the next time the application is launched.
6. Exit the application smoothly by saying 'bye'.

## Commands

1. **Add a todo**
   ```plaintext
   todo [description] 
   ```
   Example: todo New Task

2. **Add a deadline**
   ```plaintext
   deadline [description] /by [dd/MM/yyyy]
   ```
   Example: deadline New Deadline /by 12/10/2024

3. **Add an event**
   ```plaintext
   event [description] /from [start time] /to [end time]
   ```
   Example: event New Event /from 13/10/2024 /to 13/10/2024

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
   Example: delete 2

7. **List all tasks**
   ```plaintext
   list
   ```

8. **Find tasks by keyword**
   ```plaintext
   find [keyword]
   ```
   Example: find Task

9. **Exit the application**
   ```plaintext
   bye
   ```

## Prerequisites

* JDK 17
* Eliio JAR file (Ensure you have the compiled JAR file to run the application)