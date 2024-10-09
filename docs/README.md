# TheThinker User Guide

![img.png](img.png)

Welcome to my user guide !!!

First , TheThinker is a task manager which allows you to add different task.
Task includes Deadlines , Events and Todo via CLI . 

Second, various editing features like mark , unmarking , deleting task are available.

Third, unique search features include search by date and search by keyword are available.

Finally, a Save feature is also introduced which allows you to save and load task via a file.

# Getting Started

1) Make sure you have Java 17 or above installed on your computer.<br>
2) Download the .jar file<br>
3) Open a terminal in the directory which you saved the .jar file, then run the following: <br>
<br>`java -jar ip.jar` <br>


# Initial configuration

After starting the program , you will be prompted on whether you would like to save your tasks to a file.
Please continue to read on the differences before deciding.

```
____________________________________________________________
Do you want to save your tasks to a file? yes / no
____________________________________________________________
```

## <u>NO</u>

If you have input "no" after the prompt , do take note that the task which are created will not be saved anywhere.<br>

#### Note : <b>All data will be lost upon ending the program</b>

## <u>YES</u>

If you have input "yes" after the prompt , the system will check if there exist a "Data" directory
which exist relative to your current directory. If not , a "Data" directory will be automatically created.

```
Loading file now........
Data does not exist. Creating it now.......
Data directory created successfully.
```

Once the "Data" directory is created , we will check if you have a file named "TaskContents.txt" in the Data
directory. If there is not , you would see this prompt. Input the file name which would like to store the
data in without .txt extension.

```
____________________________________________________________
Input file name you want to save data to under the Data directory [filename] without .txt
____________________________________________________________
```

Once the file is created , you have now successfully configured your program and may now continue to use the 
commands below



# Commands available
## Adding deadlines

Add task which has a specific deadline.

Example: `deadline return book /by 20/10/2025 1800`

Formatting rules :
1) "deadline" is not Caps-Sensitive. <br>
2) Dates in the form of `dd/MM/yyyy HHmm` (e.g. 20/10/2025 1800) will be converted
to `dd/MMM/yyyy H a` (e.g. 20 October 2025 , 6 pm) automatically <br>
3) Dates other than `dd/MMM/yyyy H a` and `dd/MM/yyyy` format will not be available for the `get` command<br>

General format : `deadline [task] /by [time]`

### Expected output
```
____________________________________________________________
Got it. I've added this task:
    [D][ ] return book (by: 20 October 2025 , 6 pm)
Now you have 1 tasks in the list.
____________________________________________________________
```


## Add Event

Add task which has a duration.

Example: `event travel /from 20/10/2025 1800 /to 20/11/2025 1800`

Formatting rules :
1) "event" is not Caps-Sensitive.<br>
2) Dates in the form of `dd/MM/yyyy HHmm` (e.g. 20/10/2025 1800) will be converted
   to `dd/MMM/yyyy H a` (e.g. 20 October 2025 , 6 pm) automatically<br>
3) Dates other than `dd/MMM/yyyy H a` and `dd/MM/yyyy` format will not be available for the `get` command<br>

General format : `event [task] /from [start time] /by [end time]`

### Expected output
```
____________________________________________________________
Got it. I've added this task:
    [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
Now you have 2 tasks in the list.
____________________________________________________________
```


## Add todo

Add task without deadlines.

Example: `todo travel`

Formatting rules :
1) "todo" is not Caps-Sensitive.<br>

General format : `todo [task]`

### Expected output
```
____________________________________________________________
Got it. I've added this task:
    [T][ ] travel
Now you have 3 tasks in the list.
____________________________________________________________
```

## Mark and Unmark Task

Mark task as complete and incomplete.

Formatting rules :
1) "mark" / "unmark" is not Caps-Sensitive.<br>
2) Only <b>one</b> task can be marked per command (i.e. `mark 1 2`) is not valid<br>

General format : `mark [number]`

### Expected output
Example: `mark 1`
```
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] return book (by: 20 October 2025 , 6 pm)
2. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
3. [T][ ] travel
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book
____________________________________________________________
```
Example: `unmark 1`

```
____________________________________________________________
OK, I've marked this task as not done yet:
[D][ ] return book
____________________________________________________________
```

## Help

Prints the format for all the commands available

Example: `help`

Formatting rules :
1) "help" is not Caps-Sensitive.<br>
2) Help command will be triggered if first word is "help" and ignores remainder of the commands
   (i.e. `help me with command` , `help 12414`) would work.<br>

General format : `help`

### Expected output
```
____________________________________________________________
Formats for the commands are : 
mark : mark [number]
unmark : unmark [number]
delete : delete [number]
todo : todo [task]
event : event [task] /from [start time] /by [end time]
deadline : deadline [task] /by [time]
get : get [dd/mm/yyyy]
find : find [keyword]
____________________________________________________________
```

## Delete

Delete a specific task from the list

Example: `delete 1`

Formatting rules :
1) "delete" is not Caps-Sensitive.<br>
2) Deleting a task using a number greater than total of no. of task will not result in any change.<br>

General format : `delete [number]`

### Expected output
```
____________________________________________________________
Here are the tasks in your list:
1. [D][ ] return book (by: 20 October 2025 , 6 pm)
2. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
3. [T][ ] travel
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
 [D][ ] return book (by: 20 October 2025 , 6 pm)
Now you have 2 tasks in the list.
____________________________________________________________
```


## Get

Get all the task which are of specific date

Example: `get 20/10/2025`

Formatting rules :
1) "get" is not Caps-Sensitive.<br>
2) Date specified must be of `dd/MM/yyyy` format only<br>
3) Dates in `dd/MMM/yyyy H a` and `dd/MM/yyyy` format will be compared with and task with same day , month and year
will be shown.<br>
4) Multiple dates are not accepted and would result 0 matching cases.<br>

General format : `get [dd/MM/yyyy]`

### Expected output
```
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
3. [T][ ] return book
____________________________________________________________
get 20/10/2025
____________________________________________________________
Here are the tasks in your list in 20/10/2025 :
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
____________________________________________________________
```

## Find

Get all the task which are of specific keyword

Example: `find travel`

Formatting rules :
1) "find" is not Caps-Sensitive.<br>
2) Keyword should only be 1 word.<br>
4) Multi-word queries are not accepted and would result 0 matching cases.<br>

General format : `find [single word]`

### Expected output
```
____________________________________________________________
Here are the tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
3. [T][ ] return book
____________________________________________________________
find travel
____________________________________________________________
Here are the matching tasks in your list:
1. [E][ ] travel (from: 20 October 2025 , 6 pm to: 20 November 2025 , 6 pm)
2. [T][ ] travel
____________________________________________________________
```

## Bye

Exits the program

Example: `bye`

Formatting rules :
1) "bye" is not Caps-Sensitive.<br>
2) Bye command will be triggered if first word is "Bye" and ignores remainder of the commands
   (i.e. `bye me with command` , `bye 12414`) would work.<br>

General format : `bye`

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

# The End

Thank you for using TheThinker. You may check your tasks saved in the file you created during Initial Configuration 
under `./Data/filename.txt`