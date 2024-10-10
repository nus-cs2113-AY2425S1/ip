# User Guide for Lia

Welcome to Lia, your personal task management chatbot! This user guide will help you understand how to use Lia effectively.

## Features

Lia provides a variety of features to help you manage your tasks efficiently:

### 1. Add Tasks
- **ToDo Tasks:** 
  - Command: `todo <task description>`
  - Example: `todo read book`
  
- **Deadline Tasks:** 
  - Command: `deadline <task description> /by <yyyy-MM-dd HHmm>`
  - Example: `deadline return book /by 2023-12-01 1800`
  
- **Event Tasks:**
  - Command: `event <task description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>`
  - Example: `event project meeting /from 2023-11-01 1000 /to 2023-11-01 1200`

### 2. Mark Tasks
- **Mark as Done:**
  - Command: `mark <task number>`
  - Example: `mark 1`
  
- **Unmark as Done:**
  - Command: `unmark <task number>`
  - Example: `unmark 1`

### 3. Delete Tasks
- **Delete a Task:**
  - Command: `delete <task number>`
  - Example: `delete 2`

### 4. List Tasks
- **List All Tasks:**
  - Command: `list`
  
### 5. Find Tasks
- **Search for a Task:**
  - Command: `find <keyword>`
  - Example: `find book`

### 6. Exit the Chatbot
- **Exit Command:**
  - Command: `bye`

## Getting Started

1. **Installation:**
   - Ensure that you have Java installed on your machine.
   - Clone the repository from GitHub.
   - Navigate to the project directory.

2. **Running the Chatbot:**
   - Open a command window in the project folder.
   - Run the command: `java -jar lia.jar` (ensure the JAR file is correctly built and available).

3. **Interacting with Lia:**
   - Once Lia is running, you can enter commands as outlined in the features section.
   - Lia will respond accordingly and keep track of your tasks.

## Tips for Using Lia
- Always ensure your command follows the specified syntax.
- Use the `list` command to view all your tasks at any time.
- Remember to save your tasks, which Lia does automatically after each command.

## Conclusion

Thank you for using Lia! We hope this chatbot helps you manage your tasks effectively. For any issues or feature requests, feel free to reach out to the developer.
