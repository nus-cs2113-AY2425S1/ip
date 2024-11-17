# Conglo Task Manager Chatbot

Conglo is a command-line chatbot designed to help you manage tasks efficiently. Whether it's keeping track of todos, deadlines, or events,
Conglo provides simple commands to organize your tasks, making it easier to stay productive.

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
   Example: todo Buy groceries

2. **Add a deadline**
   ```plaintext
   deadline [description] /by [dd-MM-yyyy HHmm]
   ```
   Example: deadline Submit report /by 12-10-2024 2359

3. **Add an event**
   ```plaintext
   event [description] /from [start time] /to [end time]
   ```
   Example: event Team meeting /from 13-10-2024 0900 /to 13-10-2024 1000

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
   Example: find groceries

9. **Exit the application**
   ```plaintext
   bye
   ```

## Prerequisites

* JDK 17
* Conglo JAR file (Ensure you have the compiled JAR file to run the application)

## Setup Instructions

1. Download the Conglo JAR file to your local machine.
2. Open a command prompt or terminal in the directory where the JAR file is located.
3. Run the application using the following command:
   ```plaintext
   java -jar Conglo.jar
   ```
4. If the setup is correct, you should see the following output:

   ```plaintext
   ----------------------------------------------------------
   Hola! I'm Conglo, the friendly task manager.
   ----------------------------------------------------------
   Here's a quick manual to get you started:
   1. Add a todo: todo [description]
   2. Add a deadline: deadline [description] /by [dd-MM-yyyy HHmm]
   3. Add an event: event [description] /from [start time] /to [end time]
   4. Mark a task as done: mark [task number]
   5. Unmark a task: unmark [task number]
   6. Delete a task: delete [task number]
   7. List all tasks: list
   8. Find matching keyword: find [keyword]
   9. Exit the application: bye
   ----------------------------------------------------------
   The list is empty!
   ----------------------------------------------------------
   ```
