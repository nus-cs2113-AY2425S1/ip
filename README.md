# Ruhi project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Ruhi_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 17** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Ruhi.java` file, right-click it, and choose `Run Ruhi.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
Hello! I'm Ruhi.

   ```

User Guide for Ruhi

Welcome to Ruhi, your personal task management chatbot! Ruhi is designed to help you organize your tasks efficiently - you can add, mark, and search for tasks effortlessly!

*Features*

1. Adding Tasks
You can add tasks to your list using the following command:

Syntax: add <task_description>
Example: add read book

2. Deadline Tasks
You can create tasks with deadlines:

Syntax: deadline <task_description> /by <yyyy-mm-dd HH:mm>
Example: deadline return book /by 2023-10-15 18:00

3. Listing Tasks
To view all tasks you have added, use:

Command: list
Output: Displays all tasks in your list with their status.

4. Marking Tasks
You can mark tasks as done or not done:

Mark as Done:
Syntax: mark <task_number>
Example: mark 1
Mark as Not Done:
Syntax: unmark <task_number>
Example: unmark 1

5. Deleting Tasks
To remove a task from your list:

Syntax: delete <task_number>
Example: delete 1

6. Finding Tasks
You can search for tasks that contain specific keywords:

Syntax: find <keyword>
Example: find book

7. Exiting the Chatbot
To exit Ruhi, simply type:

Command: bye
Output: Ruhi will say goodbye and close the session.
Example Interaction

Here's how a typical interaction with Ruhi might look:

markdown
Copy code
Hello! I'm Ruhi.
What can I do for you?
____________________________________________________________
add read book
Added: read book
____________________________________________________________
deadline return book /by 2023-10-15 18:00
Added: return book (by: 2023-10-15 18:00)
____________________________________________________________
list
Here are the tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 2023-10-15 18:00)
____________________________________________________________
find book
Here are the matching tasks in your list:
1. [T][ ] read book
2. [D][ ] return book (by: 2023-10-15 18:00)
____________________________________________________________
bye
Bye. Hope to see you again soon!

Exclusive Tips for Using Ruhi

Use clear and concise task descriptions for better organisation.
Ensure your date and time formats are correct when adding deadline tasks.
If you encounter any issues, double-check your command syntax.
With this guide, you should have everything you need to get started with Ruhi. Enjoy organizing your tasks!

