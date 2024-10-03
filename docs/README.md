# User Guide for lovespiritual Task Manager

## Quick start
1. Ensure that you have Java 17 or above installed on your computer.
2. Download the latest lovespiritual.jar file from here.
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

### Listing all tasks: `list`
### Adding a To-Do task: `todo`
### Adding a Deadline task: `deadline`
### Adding an Event task: `event`
### Marking a task as completed: `mark`
### Unmarking a task as not completed: `unmark`
### Deleting a task: `delete`
### Finding tasks: `find`
### Exiting the program: `exit`
## Saving the data
## Editing the data file


<!-- // Product screenshot goes here 

// Product intro goes here

## Adding deadlines

// Describe the action and its outcome.

// Give examples of usage

Example: `keyword (optional arguments)`

// A description of the expected outcome goes here

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
-->