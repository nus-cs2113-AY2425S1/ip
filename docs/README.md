# Legin User Guide

Legin is a command line chat bot that helps users with their daily tracking of tasks, these tasks includes various types such as Todo, Deadline and Event types. The bot is able to store these data and retrieve where the user last left off, allowing continuous usage of the bot from day to day.

## Quick Start

1. Ensure you have Java ```17``` or above installed in your Computer.
2. Download the latest ```.jar``` file from [here](https://github.com/NigelYeoTW/ip/releases).
3. Copy the file to the folder you want to use as the home folder for Legin. Note that the data storage text file will also be created in this folder.
4. Open a command terminal, ```cd``` into the folder you put the jar file in, and use the ```java -jar ip.jar``` command to run the application.
   The following should be displayed in your command line upon successfully starting up Legin 
   ![image](https://github.com/user-attachments/assets/d37c981f-5a60-4824-896b-45a59795157b)
   Note that the file path displayed will be your specific file path where you store the jar file
5. Type some of these commands into the command line and press Enter to execute it
   Here are some of the commands you can try:
   + ```list```: List all tasks you have with indexes
   + ```todo read up CS2113 Tutorial 2```: Adds a Todo task into your task list with description 'read up CS2113 Tutorial 2'
   + ```mark 3```: Marks the 3rd task in the list as done
   + ```delete 5```: Deletes the 5th task in the task list
   + ```find homework```: Search through the task list and outputs all the task that have 'homework' in their description
   + ```bye```: Terminates Legin
6. Refer to the [Features](#features) below for details of each command.

## Features

### Adding a Todo task: ```todo```

Adds a Todo task to the task list.
Format: ```todo TASK_DESCRIPTION``` <br />
Example: 
+ ```todo Water the plants```
  The following would be seen on the terminal if done correctly:
  ![image](https://github.com/user-attachments/assets/777aa717-3c10-4e65-a484-79ec87161220)


### Adding a Deadline task: ```deadline```

Adds a task that has a deadline to the task list.
Format: ```deadline TASK_DESCRIPTION /by DEADLINE``` <br />
Example:
+ ```deadline submit quiz 1 /by 27 October 4pm```
  The following would be seen on the terminal if done correctly:
  ![image](https://github.com/user-attachments/assets/ad576583-c6d2-49ab-9cbe-867826817688)
  
Note: Users are to adhere to the space after ```TASK_DESCRIPTION``` and /by to prevent unexpected behavior

### Adding a Event task: ```event```

Adds a event that has a duration period into the task list. <br />
Format: ```event EVENT_DESCRIPTION /from EVENT_START /to EVENT_END``` <br />
Example:
+ ```event Meet friend at Yishun /from 18 October 9pm /to 18 October 11pm``` <br />
  The following would be seen on the terminal if done correctly:
  ![image](https://github.com/user-attachments/assets/2ac2d029-b1fe-4083-9835-0e599d41f0e9)
  
Note: Users are to adhere to the space after ```EVENT_DESCRIPTION```, /from and /to to prevent unexpected behavior


### Marking a task: ```mark```

Marks a tasks in the task list by its index as done. Index of task can be obtained by using the ```list``` command. <br />
Format: ```mark INDEX``` <br />
Example:
+ ```mark 3```

Note: ```INDEX``` should be displayed in task list when ```list``` is called


### Unmarking a task: ```unmark```

Unmarks a task in the task list by its index as undone. Index of task can be obtained by using the ```list``` command. <br />
Format: ```unmark INDEX``` <br />
Example:
+ ```unmark 5```

Note: ```INDEX``` should be displayed in task list when ```list``` is called


### Listing all tasks: ```list```

Lists out all the tasks in the task list. <br />
Format: ```list```


### Deleting a task: ```delete```

Deletes a task in the task list by it index. Index of task can be obtained by using the ```list``` command. <br />
Format: ```delete INDEX``` <br />
Note: ```INDEX``` should be displayed in task list when ```list``` is called


### Find task by description: ```find```

Finds all tasks with description that matches the given keyword by user <br />
Format: ```find KEYWORD [MORE_KEYWORDS]...``` <br />
Example: 
+ ```find tutorial``` returns ```tutorial 4```, ```math tutorial```, ```english tutorial 6```
+ ```find jurong west``` returns ```meeting at jurong west```, ```jurong west cafe```
+ ```find real``` returns ```really good cafe```, ```surreal theme park at Hougang```

Note:
+ Keywords used are case sensitive
+ Keywords can be partial words of a full word
+ Keywords should only be used to match task desciption, task type should be ommited


### Terminating Legin: ```bye```

Terminates program. <br />
Format: ```bye``` <br />
Note: User should not close the application before using this command to avoid unsaved data from current window


### Saving the data
Data will be saved in a text file where the ```.jar``` file is stored with file name ```tasklist.txt``` <br />
Data will be saved upon termination of the program with ```bye```


### Editting the data file 
Data is stored in the form of:
+ 'TASK_TYPE|IS_TASK_DONE|TASK_DESCRIPTION' for Todo tasks
+ 'TASK_TYPE|IS_TASK_DONE|TASK_DESCRIPTION|DUEDATE' for Deadline tasks
+ 'TASK_TYPE|IS_TASK_DONE|TASK_DESCRIPTION|TIME_START|TIME_END' for Event tasks
Each line stores data for only one task

The following are some fixed parameters: 
TASK_TYPE:
+ 'T' - Todo task
+ 'D' - Deadline task
+ 'E' - Event task

IS_TASK_DONE:
+ 'true' - marks task as done
+ 'false' - marks task as undone

## Command Summary
| Action | Format, Examples |
| --- | --- |
| Add Todo task | ```todo TASK_DESCRIPTION``` <br /> eg. ```todo Water the plants```|
| Add Deadline task | ```deadline TASK_DESCRIPTION /by DEADLINE``` <br /> eg. ```deadline submit quiz 1 /by 27 October 4pm```|
| Add Event task | ```event EVENT_DESCRIPTION /from EVENT_START /to EVENT_END``` <br /> eg. ```event Meet friend at Yishun /from 18 October 9pm /to 18 October 11pm``` |
| Mark task as done | ```mark INDEX``` <br /> eg. ```mark 3``` |
| Mark task as undone | ```unmark INDEX``` <br /> eg. ```unmark 5``` |
| List all tasks | ```list``` |
| Delete task | ```delete INDEX``` |
| Find task | ```find KEYWORD [MORE_KEYWORDS]...``` <br /> eg . ```find jurong west``` |
