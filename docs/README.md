# **Wildpeace User Guide**

## **Introduction**
```
__        ___ _     _                           
\ \      / (_) | __| |_ __   ___  __ _  ___ ___ 
 \ \ /\ / /| | |/ _` | '_ \ / _ \/ _` |/ __/ _ \
  \ V  V / | | | (_| | |_) |  __/ (_| | (_|  __/
   \_/\_/  |_|_|\__,_| .__/ \___|\__,_|\___\___|
                     |_|                                      
```
Wildpeace is a command-line interface (CLI)-based task management application designed to help users organize their 
daily tasks efficiently. It allows for the creation of tasks such as todos, deadlines, and events. Wildpeace supports 
operations like adding, deleting, marking, unmarking, and listing tasks. It also saves tasks automatically and loads 
them upon restarting the application.
- **The Main UI will let you choose 1 or 2 for the echo function or the Task Manager Function. You may type the input
accordingly and press enter. In the echo function, the programme will repeat everything you have said. For the Task
Manager Function, please refer to the guide below.**

---

## **Quick Start**

1. Ensure you have Java `17` or above installed on your computer.
2. Download the latest `.jar` file from the Wildpeace repository.
3. Open a terminal and navigate to the folder where the `.jar` file is located.
4. Run the application using the command:
   ```
   java -jar wildpeace.jar
   ```
5. You can start adding and managing tasks through the following commands.

---

## **Features**

### **1. Adding Tasks**

Wildpeace supports three types of tasks: `todo`, `deadline`, and `event`.

#### Adding a Todo Task
You can add a simple todo task without any specific date or time associated with it.

- **Command**: `todo DESCRIPTION`
- **Example**:
   ```
   todo Finish reading book
   ```
- **Expected Outcome**: A todo task will be added to the task list.
   ```
   Added: [T][ ] Finish reading book
   ```

#### Adding a Deadline Task
For tasks that need to be completed by a certain date, you can add a deadline.

- **Command**: ` deadline DESCRIPTION /by DEADLINE`
- **Example**:
   ```
    deadline Submit assignment /by 2024-12-01
   ```
- **Expected Outcome**: A deadline task will be added with the specified deadline.
   ```
   Added: [D][ ] Submit assignment (by: 2024-12-01)
   ```

#### Adding an Event Task
For events that occur at a specific time, use the event task type.

- **Command**: ` event DESCRIPTION /at EVENT_DATE`
- **Example**:
   ```
    event Project presentation /at 2024-10-05 
   ```
- **Expected Outcome**: An event task will be added with the event time specified.
   ```
   Added: [E][ ] Project presentation (at: 2024-10-05)
   ```

---

### **2. Listing All Tasks**

You can view all the tasks in your task list, including todos, deadlines, and events.

- **Command**: `list`
- **Example**:
   ```
   list
   ```
- **Expected Outcome**: Displays all the tasks along with their statuses.
   ```
   1. [T][ ] Finish reading book
   2. [D][ ] Submit assignment (by: 2024-12-01)
   3. [E][ ] Project presentation (at: 2024-10-05 14:00)
   ```

---

### **3. Marking Tasks as Completed**

You can mark tasks as completed, which changes their status in the list.

- **Command**: `mark INDEX`
- **Example**:
   ```
   mark 1
   ```
- **Expected Outcome**: The task at the specified index will be marked as done.
   ```
   Marked as done: [T][X] Finish reading book
   ```

---

### **4. Unmarking Tasks**

If you mistakenly mark a task as done, you can unmark it.

- **Command**: `unmark INDEX`
- **Example**:
   ```
   unmark 1
   ```
- **Expected Outcome**: The task at the specified index will be unmarked as done.
   ```
   Unmarked: [T][ ] Finish reading book
   ```

---

### **5. Deleting Tasks**

You can remove tasks from your list.

- **Command**: `delete INDEX`
- **Example**:
   ```
   delete 1
   ```
- **Expected Outcome**: The task at the specified index will be deleted.
   ```
   Removed: [T][ ] Finish reading book
   ```

---

### **6. Exiting the Application**

You can exit the Task Management Function by typing the following command:

- **Command**: `bye`
- **Example**:
   ```
   bye
   ```
- **Expected Outcome**: Goodbye!

---
### **7. Clear Tasks**

You can Clear all the tasks in the Task List by typing the following command:
- **Command**: `clear`
- **Example**:
   ```
   clear
   ```
### **8. Find Tasks**

You can find tasks in the Task List by key word search using the following command:
- **Command**: `find DESCRIPTION`
- **Example**:
   ```
   find book
   ```
- **Expected Outcome**: 1. [T] [ ] Finish reading book

### **9. Saving Data**

Wildpeace automatically saves your tasks to a local `tasks.json` file after any modification. You do not need to manually save the tasks. When you restart the application, your tasks will be loaded from this file.

---

### **10. Editing the Data File**

Advanced users can directly modify the `tasks.json` file, which is stored in the application's directory. This file is formatted as JSON, so be cautious when editing it. Incorrect formatting may cause the application to fail when loading the tasks.

---

## **FAQ**

### **Q:** What happens if I make an invalid command?

**A:** Wildpeace will show an error message to indicate that the command was invalid, and will prompt you to try again.

### **Q:** How do I transfer my tasks to another computer?

**A:** You can copy the `tasks.json` file from your current computer to another. Place the file in the same directory as the `.jar` file on the new computer, and Wildpeace will load the tasks upon starting.

---

## **Command Summary**

| **Command**      | **Format**                          | **Example**                                       |
|------------------|-------------------------------------|---------------------------------------------------|
| **Todo**         | `todo DESCRIPTION`                  | `todo Finish reading book`                        |
| **Deadline**     | `deadline DESCRIPTION /by DEADLINE` | `deadline Submit assignment /by 2024-12-01`       |
| **Event**        | `event DESCRIPTION /at EVENT_TIME`  | `event Project presentation /at 2024-10-05 14:00` |
| **List Tasks**   | `list`                              |                                                   |
| **Mark Task**    | `mark INDEX`                        | `mark 1`                                          |
| **Unmark Task**  | `unmark INDEX`                      | `unmark 1`                                        |
| **Delete Task**  | `delete INDEX`                      | `delete 1`                                        |
| **Exit Program** | `exit`                              |                                                   |
| **Clear Tasks**  | `clear`                             | `clear`                                           |
| ** Find Tasks**  | `find DESCRIPTION`                   | `find book`                                        |

---

This user guide outlines the core functionality of Wildpeace, giving users clear instructions on how to manage their tasks through the command line. Let me know if you need additional sections or adjustments!