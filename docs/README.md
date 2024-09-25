# CheonsaBot

### **Brief Introduction**
CheonsaBot is a simple and intuitive task management tool designed to keep track of your day-to-day tasks, just like a guardian angel. Whether it's a regular to-do, an event, or a deadline, CheonsaBot helps you stay on top of things with ease.

---

### **Installation**

1. **Ensure Java 17 is installed:**
   - Run the following command in your terminal or command line to check your Java version:
     ```
     java -version
     ```
   - If Java 17 is not installed, you can download it from [Oracle's Java download page](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html).

2. **Running the application:**
   - Once Java 17 is installed, navigate to the folder where `ip.jar` is located and run:
     ```
     java -jar ip.jar
     ```

---

### **Usage**

- **To add tasks:**
  - `todo <description>`: Adds a basic to-do task.
  - `deadline <description> /by <date>`: Adds a task with a deadline. You can use dates and times like `25/09/2024 1800`. The format is `dd/MM/yyyy HHmm` for date with time, `dd/MM/yyyy` for dates only, and `HHmm` for time, in order for CheonsaBot to recognise these as dates and time respectively.
  - `event <description> /from <start> /to <end>`: Adds an event task with a start and end time or date. Format is similar to that of a deadline.

- **Other commands:**
  - `list`: Displays all tasks with their respective index.
  - `delete <task_number>`: Deletes a task by its number.
  - `mark <task_number>`: Marks a task as done.
  - `unmark <task_number>`: Unmarks a task.
  - `find <search_term>`: Finds tasks based on their name or description.
  - `bye`: Exits the program.

---

### **Features**
- **Task Tracking**: Easily track tasks of three different types: ToDos, Deadlines, and Events.
- **Persistent Storage**: Tasks are stored in the same directory as the executable, ensuring easy access and simple management.
- **Find Feature**: Locate specific tasks based on descriptions or keywords.

---

### **Examples**

- **ToDo**:
```
todo Buy groceries
```

- **Deadline**:
```
deadline Submit assignment /by 25/09/2024
```

- **Event**:
```
event Team meeting /from 25/09/2024 1400 /to 25/09/2024 1600
```

- **Delete Task**:
```
delete 2
```

- **Find Task**:
```
find groceries
```

- **Exit**:
```
bye
```

---

### **FAQs**

- **Q: Why isn't my task saved after restarting?**
- Make sure the program has write permissions to the folder where `ip.jar` is located. The tasks are stored in `tasklist.txt` in the same folder.

- **Q: Why is the date format not being recognized?**
- Ensure the date is in the format `dd/MM/yyyy`, and if specifying time, include the time in `HH:mm` format, like `25/09/2024 1800`.

- **Q: What if I make a mistake while adding a task?**
- You can always delete a task by its number using the `delete` command or unmark it if marked by mistake.

- **Q: I keep getting an "Unknown task type" error.**
- Ensure you are using the correct task type: `todo`, `deadline`, or `event`. Spelling errors or missing keywords like `/by` or `/from` can trigger this error.

---

### **Troubleshooting**

- **Issue: Java version issue**
- **Solution**: Ensure that Java 17 is installed by running `java -version`. If not, install Java 17.

- **Issue: File not found or cannot access tasklist.txt**
- **Solution**: Make sure the `ip.jar` file has the necessary file permissions to read/write in its directory. Check if the `tasklist.txt` file exists or if it's being blocked by security settings.

- **Issue: Cannot find tasks using the `find` command**
- **Solution**: Ensure the search term is spelled correctly and is part of the task description.

---

