# Duke User Guide

#### Welcome to KaiWen Chatbot!
KaiWen is a command-line chatbot designed to help you manage your tasks effectively. If you prefer typing commands to get things done quickly, KaiWen will help you manage your tasks seamlessly. With KaiWen, you can create to-do lists, set deadlines, schedule events, mark tasks as complete, delete tasks, and more.

---

## Quick Start

1. Ensure you have Java 17 or higher installed on your computer.
2. Download the latest `kaiwen.jar` file from the repository.
3. Copy the file to your desired folder for task management.
4. Open a command terminal, navigate (`cd`) to the folder where you placed the `.jar` file, and run the following command:

   ```bash
   java -jar kaiwen.jar
   ```
5. KaiWen will display a welcome message, and you can start entering commands.

---


## **Features**

### **1. Adding a ToDo: `todo`**
Adds a basic task without any time constraint.

**Format**: `todo DESCRIPTION`

**Example**:
```bash
todo Buy groceries
```

**Expected Outcome**: A new to-do task is added, and a confirmation message is displayed.
```less
Got it. I've added this task:
  [T][ ] Buy groceries
Now you have 1 task in the list.
```

### **2. Adding a Deadline: `deadline`**
Adds a task that has a deadline.

**Format**: `deadline DESCRIPTION /by yyyy-mm-dd`

**Example**:
```bash
deadline Finish project /by 2024-10-15
```

**Expected Outcome**: A new deadline task is added with a specified due date.
```less
Got it. I've added this task:
  [D][ ] Finish project (by: Oct 15 2024)
Now you have 2 tasks in the list.
```

### **3. Adding an Event: `event`**
Adds an event with a specific date and time range.

**Format**: `event DESCRIPTION /from yyyy-mm-dd HH:mm /to HH:mm`

**Example**:
```bash
event Meeting /from 2024-10-10 14:00 /to 16:00
```

**Expected Outcome**: A new event task is added with a date, start time, and end time.
```less
Got it. I've added this task:
  [E][ ] Team meeting (on: Oct 10 2024 from 14:00 to 16:00)
Now you have 3 tasks in the list.
```

### **4. Listing All Tasks: `list`**
Displays all tasks in your task list.

**Format**: `list`

**Expected Outcome**: A list of all tasks is displayed.
```less
Here are the tasks in your list:
1. [T][ ] Buy groceries
2. [D][ ] Finish project (by: Oct 15 2024)
3. [E][ ] Team meeting (on: Oct 10 2024 from 14:00 to 16:00)
```

### **5. Marking a Task as Done: `mark`**
Marks a task as done.

**Format**: `mark INDEX`

**Example**:
```bash
mark 2
```

**Expected Outcome**: The specified task is marked as done, and a confirmation message is shown.
```less
Nice! I've marked this task as done:
  [D][X] Finish project (by: Oct 15 2024)
```

### **6. Unmarking a Task: `unmark`**
Marks a task as not done.

**Format**: `unmark INDEX`

**Example**:
```bash
unmark 2
```

**Expected Outcome**: The specified task is marked as not done, and a confirmation message is shown.
```less
OK, I've marked this task as not done yet:
  [D][ ] Finish project (by: Oct 15 2024)
```

### **7. Deleting a Task: `delete`**
Removes a task from the list.

**Format**: `delete INDEX`

**Example**:
```bash
delete 1
```

**Expected Outcome**: The specified task is deleted, and a confirmation message is shown.
```less
Noted. I've removed this task:
  [T][ ] Buy groceries
Now you have 2 tasks in the list.
```

### **8. Finding a Task: `find`**
Searches for tasks that contain the specified keyword.

**Format**: `find KEYWORD`

**Example**:
```bash
find project
```

**Expected Outcome**: A list of tasks matching the keyword is displayed.
```less
Here are the matching tasks in your list:
1. [D][ ] Finish project (by: Oct 15 2024)
```

### **9. Exiting the Program: `bye`**
Exits the chatbot.

**Format**: `bye`

**Expected Outcome**: The chatbot terminates, and a goodbye message is displayed.
```css
Bye. Hope to see you again soon!
```

---

## Saving and Loading Tasks
- **Automatic Saving**: All tasks are saved automatically to a file (data/tasks.txt) when the list is modified.
- **Automatic Loading**: Tasks are automatically loaded from the file when you start KaiWen.

---

## Command Summary

| Action             | Format                                     | Example                                       | Outcome                              |
|--------------------|--------------------------------------------|-----------------------------------------------|--------------------------------------|
| Add ToDo           | `todo DESCRIPTION`                         | `todo Buy milk`                               | Adds a new to-do task.              |
| Add Deadline       | `deadline DESCRIPTION /by yyyy-mm-dd`      | `deadline Finish report /by 2024-10-15`       | Adds a new task with a deadline.    |
| Add Event          | `event DESCRIPTION /from yyyy-mm-dd HH:mm /to HH:mm` | `event Workshop /from 2024-10-10 14:00 /to 16:00` | Adds a new event task.              |
| List               | `list`                                     | `list`                                        | Lists all tasks.                    |
| Mark Task as Done  | `mark INDEX`                               | `mark 2`                                      | Marks a task as done.               |
| Unmark Task        | `unmark INDEX`                             | `unmark 2`                                    | Marks a task as not done.           |
| Delete Task        | `delete INDEX`                             | `delete 1`                                    | Deletes a task from the list.       |
| Find Task          | `find KEYWORD`                             | `find homework`                               | Searches for tasks with a keyword.  |
| Exit               | `bye`                                      | `bye`                                         | Exits the chatbot.                  |

---

## FAQ

**Q: How do I save my tasks?**  
A: KaiWen automatically saves tasks to `data/tasks.txt` whenever a task is added, deleted, or marked as done.

**Q: What happens if the task file is corrupted?**  
A: If the task file is corrupted or not in the expected format, KaiWen will discard the file and start with an empty task list. Make sure to back up the file regularly if you edit it manually.

**Q: Can I edit the task file directly?**  
A: Yes, the task data is stored in a text file (`data/tasks.txt`), and you can edit it manually. However, please ensure you maintain the correct format.

---

## Known Issues

- If you accidentally delete the task file, KaiWen will start with a blank task list.
- Incorrectly formatted commands will result in error messages, but KaiWen will continue running.

---
