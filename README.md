# Taylor Task Management Chatbot

Welcome to **Taylor**, a simple Java-based task management chatbot designed to help you organize your tasks effectively. Taylor can handle various task types such as Todos, Deadlines, and Events. This guide will walk you through setting up and using Taylor to manage your tasks.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first).
2. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 17** (not other versions) as explained in [this guide](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/Taylor.java` file, right-click it, and choose `Run Taylor.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the following as the output:
   ```
   Hello! I'm Taylor.command.Taylor
   What can I do for you?
   ```

## Using Taylor

Taylor is a quite simple chatbot that allows you to add, list, mark, unmark, and delete tasks. Below is a list of commands and features to help you get started.

### Available Commands

1. **Add a Todo**:
   - Adds a todo task to your list.
   - **Command**: `todo <task description>`
   - **Example**: `todo read a book`

2. **Add an Event**:
   - Adds an event task with a specific start and end time.
   - **Command**: `event <description> /from <start time> /to <end time>`
   - **Example**: `event project meeting /from Aug 1st 2pm /to Aug 1st 4pm`

3. **Add a Deadline**:
   - Adds a task with a specific deadline.
   - **Command**: `deadline <description> /by <date>`
   - **Example**: `deadline submit report /by 2024-12-01`

4. **List All Tasks**:
   - Displays all tasks currently in your list.
   - **Command**: `list` or `ls`

5. **Mark a Task as Completed**:
   - Marks a task as completed.
   - **Command**: `mark <task number>`
   - **Example**: `mark 2`

6. **Unmark a Task**:
   - Marks a task as not completed.
   - **Command**: `unmark <task number>`
   - **Example**: `unmark 2`

7. **Delete a Task**:
   - Deletes a task from the list.
   - **Command**: `delete <task number>`
   - **Example**: `delete 3`

8. **Find a Task**:
   - Finds tasks that match a specific keyword.
   - **Command**: `find <keyword>`
   - **Example**: `find book`

9. **Clear all tasks**:
   - Clear all the tasks in the list 
   - **Command**: `clear` followed by `yes` or `y` or `ok`
   - **Example**: `clear` -> `y`

10. **Exit the Program**:
   - Exits the chatbot.
   - **Command**: `bye`

### Task Types

- **Todo**: A simple task that only contains a description (e.g., "read a book").
- **Deadline**: A task with a deadline (e.g., "submit report by 2024-12-01").
- **Event**: A task that occurs over a specific time period (e.g., "meeting from 2pm to 4pm").

### Task Management

- Tasks can be marked as completed or not completed.
- You can delete tasks from the list when they are no longer needed.
- The chatbot saves your tasks automatically, so they will be available the next time you start the program.

### Example Interaction

```
Hello! I'm Taylor
What can I do for you?

todo read book
Got it. I've added this task:
  [T][ ] read book

deadline submit report /by 2024-12-01
Got it. I've added this task:
  [D][ ] submit report (by: Dec 1 2024)

list
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] submit report (by: Dec 1 2024)

mark 1
Nice! I've marked this task as done:
  [T][X] read book

bye
Bye. Hope to see you again soon!
```

With these commands, you'll be able to manage your tasks effectively using Taylor!

### Troubleshooting

If you encounter any issues, make sure:
- You are using JDK 17.
- The project is set up correctly in IntelliJ.
- You followed the command format as outlined above.

---

This guide should provide you with enough information to get started with Taylor. If you have further questions, feel free to refer to the code comments or reach out for additional help!