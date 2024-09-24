# JEFF Task Chatbot

**JEFF** is a temperamental Java chatbot who (begrudgingly) keeps track of your tasks!

## Getting Started with IntelliJ

**Prerequisites:** JDK 17 and the latest version of IntelliJ IDEA.

### Setting Up the Project
1. **Open IntelliJ IDEA**:
   - If you’re not on the welcome screen, click `File` > `Close Project` to close any existing project first.

2. **Open the Project**:
   - Click `Open`.
   - Select the project directory and click `OK`.
   - Accept any further prompts with the default options.

3. **Configure JDK**:
   - Set up the project to use **JDK 17** as explained [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).
   - Set the **Project language level** field to `SDK default`.

4. **Run JEFF**:
   - Locate `src/main/java/Jeff.java`.
   - Right-click the file and select `Run Jeff.main()` (if you see compile errors, try restarting the IDE).
   - If set up correctly, you should see JEFF’s initial output in the console.

## How to Use JEFF

JEFF can manage three types of tasks:

### Task Types
1. **Todo**:
   - **Purpose**: Tracks non-time-sensitive tasks.
   - **Command**: `todo [description]`

2. **Deadline**:
   - **Purpose**: Tracks tasks due by a specific date.
   - **Command**: `deadline [description] /by [yyyy-MM-dd]`

3. **Event**:
   - **Purpose**: Tracks tasks happening within a specific date range.
   - **Command**: `event [description] /from [yyyy-MM-dd] /to [yyyy-MM-dd]`

### Commands Overview
You can manipulate or view your tasks using the following commands:

1. **List Tasks**:
   - **Purpose**: Displays all tasks you've added.
   - **Command**: `list`
   - **Example Output**:
     ```
     1. [D][ ] Finish homework (by: Aug 9 1990)
     ```

2. **Mark/Unmark Tasks**:
   - **Purpose**: Mark a task as done or undone.
   - **Commands**:
      - `mark [task index]`
      - `unmark [task index]`
   - **Example Output**:
      - `Marked task: [D][X] Finish homework (by: Aug 9 1990)`
      - `Unmarked task: [D][ ] Finish homework (by: Aug 9 1990)`

3. **Delete a Task**:
   - **Purpose**: Removes a task from your list.
   - **Command**: `delete [task index]`

## Data Storage

JEFF automatically saves your tasks to a text file located in the `data` folder. **Note**: Please do not manually edit the text file, as incorrect formatting may cause data loss.
