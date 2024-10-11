# Amy User Guide

Meet Amy, your cheerful and supportive task management bot! Amy is designed to help you effortlessly manage your to-do lists, deadlines, and events through a simple command-line interface (CLI). Whether you’re juggling multiple projects or just trying to stay on top of daily tasks, Amy is here to make your life easier and more organized.

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.

2. Download the latest `.jar` file from here.

3. Copy the file to the folder you want to use as the home folder for your Amy.

4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar addressbook.jar command to run the application.
A GUI similar to the below should appear in a few seconds. 

![welcome](/ip/assets/welcome.png)

## Features

### Adding a Todo task: `todo`

Adds a task to the list.

Format: ```todo DESCRIPTION```

Examples:
* ```todo read book``` adds the task ```read book``` to the bottom of the list.

### Adding a Deadline task: `deadline`

Adds a task with deadline to the list.

Format: ```deadline DESCRIPTION /by TIME```
- Note: `TIME` argument accepts all kind of format, not just limited to datetime. Please feel free to fill in this field as you wish.

Examples: 
* ```deadline read book /by Thursday``` adds the task ```read book``` with additional deadline note ```Thursday``` to the bottom of the list.

### Adding an Event task: `event`

Adds a task with a start time and end time.

Format: ```event DESCRIPTION /from START_TIME /to END_TIME```

- Note: Similar to `TIME` argument, `START_TIME` and `END_TIME` accept all kind of formats.

Examples:
* ```event attend meeting /from 2pm /to 5pm``` adds the task ```attend meeting``` with additional note regarding time range to the bottom of the list.

### List all tasks: `list`

Shows a list of all tasks in the storage.

Format: ```list```

### Locating tasks by description: `find`

Finds all tasks which have descriptions contains the given keywords. The index displayed is the official index of the task in task list.

- Notes: The search is case sensitive. 
Format: ```find KEYWORDS```

Examples:
* ```find read``` will returns ```read book``` and ```read manga``` tasks.

### Deleting a task: `delete`
Deletes a speciic task from the task list.

Format: ```delete INDEX```
- Notes: The task at specified index will be deleted. The index mentioned is the number shown when using ```list```  or ```find``` feature. 

Examples:
* ```list``` followed by ```delete 2``` deletes the 2nd task in the list.
* ```find read``` followed by ```delete 3``` deletes the task with displayed index 3 in the results of the find command.
* ```delete 5``` simply deletes the 5th task in the list.

### Marking a task: `mark`
Mark a task as done.

Format: ```mark INDEX```
- Notes: The task at specified index will be marked. The index mentioned is the number shown when using ```list```  or ```find``` feature. 

Examples:
* ```mark 5``` will make the 5th task in the list have a 'X' mark, indicating that it's been done.

### Unmarking a task: `unmark`
Undoes the marking of a task i.e mark it as undone again.

Format: ```unmark INDEX```
- Notes: The task at specified index will be unmarked. The index mentioned is the number shown when using ```list```  or ```find``` feature. 

Examples:
* ```unmark 5``` will undo the 'X' mark the 5th task in the list, indicating that it's not done.


### Exit the program: ```bye```
Exits the program.

Format: ```bye```

### Saving the data
Tasks data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file
Tasks data are saved automatically as a .txt file ```[JAR file location]/data/tasks.txt```.
