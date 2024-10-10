# User Guide for lovespiritual Task Manager

## Product Screenshot
![Product Screenshot](.\Product Screenshot.png)

## Product Introduction
The loveSpiritual Task Manager is a task management chatbot developed as part of the CS2113 iP component. It enables users to efficiently manage tasks by allowing them to add, mark, and delete items seamlessly. With built-in functionality for automatic data saving and loading, your task information is preserved and readily available each time you access the command line interface.

## Quick start
1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest ip.jar file from here.
3. Copy the file to the folder you want to use as the home folder for your Task Manager.
4. Open a command terminal, cd into the folder where you placed the jar file, and run the following command to start the application:java -jar lovespiritual.jar
5. The application will start, and any existing tasks will be loaded from the file data/lovespiritual.txt. If this file doesn't exist, it will be created automatically when tasks are added.
6. Type commands below the outputs and press Enter to execute them. For example:
   - 'list': Lists all tasks.
   - 'todo Buy groceries': Adds a todo task with the description "Buy groceries".
   - 'mark 1': Marks the 1st task as done.
   - 'delete 2': Deletes the 2nd task in the list.
   - 'bye': Exits the application.

For details on all available commands, refer to the Features section below.

## Features
### Notes about the command format:
- Words in `UPPER_CASE` are the parameters to be supplied by the user. 

    eg. In `todo TASK_DESCRIPTION`, `TASK_DESCRIPTION` is a parameter which can be used as `todo Buy groceries`.


- All parameters must be provided for commands that require them.

    eg. In `todo TASK_DESCRIPTION`, you must provide the task description, such as `todo Buy groceries`.


- Parameters must be entered in the order specified for each command.

    eg. In `event EVENT_DESCRIPTION from START_TIME to END_TIME`, you must provide the parameters in this exact order such as `event Team meeting from Oct 5 2pm to Oct 5 4pm`.


- Extraneous parameters for commands that do not take in parameters (such as `list` and `bye`) will be ignored.

    eg. if the command specifies `list 123`, it will be interpreted as `list`.


- Avoid including special characters or symbols. 

    eg. Do not use `|`, `<`, `>`.


- Date/time must be entered in the format DD-MM-YYYY HHMM. 

    eg. In `deadline TASK_DESCRIPTION by DEADLINE_TIME`, you must provide the `DEADLINE_TIME` parameter as such, `05-10-2024 1400`, for 5 October 2024 2:00 PM.


- Date adjustments to the last date of the month will automatically be made for invalid inputs from 29 to 31

    eg. February dates (29, 30, or 31) will be set to the last valid date of February.
    
    eg. 31 will be adjusted to the last valid date for months with only 30 days.

### Listing all tasks: `list`
Displays all tasks in the task list, along with their completion status and details. Tasks are shown in the order they were added.

Format: `list`

### Adding a To-Do task: `todo`
Adds a to-do task to the task list. This type of task does not have a specific time component.

Format: `todo TASK_DESCRIPTION`

Examples:
- `todo Buy groceries`
- `todo Read book`

### Adding a Deadline task: `deadline`
Adds a task with a specific deadline to the task list.

Format: `deadline TASK_DESCRIPTION by DEADLINE_TIME`

Examples:
- `deadline Submit assignment by 10-10-2024 1200`
- `deadline Complete peer review by 28-02-2024 1500`

### Adding an Event task: `event`
Adds an event to the task list, with a specific start and end time.

Format: `event TASK_DESCRIPTION from START_TIME to END_TIME`
- `START_TIME` must be before `END_TIME`.

Example:
- `event Team meeting from 05-10-2024 1400 to 05-10-2024 1600`
- `event Europe trip 03-12-2024 1015 to 03-01-2025 0900`

### Marking a task as completed: `mark`
Marks a task as completed.

Format: `mark INDEX`
- Marks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example:
- `mark 3` marks task 3 in the task list as completed.

### Unmarking a task as not completed: `unmark`
Reverts a previously marked task to an incomplete state.

Format: `unmark INDEX`
- Unmarks the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example:
- `unmark 3` marks task 3 in the task list as not completed.

### Deleting a task: `delete`
Removes a task from the task list.

Format: `delete INDEX`
- Deletes the task at the specified `INDEX`.
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, ...

Example:
- `delete 2` deletes task 2 from the task list.

### Finding tasks: `find`
Searches for tasks that contain a specific keyword.

Format: `find WORD`
- The search is case-insensitive. 

    eg `book` will match `Book`
- Partial words can also be matched. 

    eg. `bo` will match `book`

Example:
- `find assignment` will display all tasks containing the word "assignment".

### Exiting the program: `exit`
Exits the program.

Format: `exit`

## Saving the data
lovespiritual data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually. 

## Editing the data file
lovespiritual data are saved automatically as a JSON file JAR file location/data/lovespiritual.json. Advanced users are welcome to update data directly by editing that data file.