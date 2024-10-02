# CodyChen User Guide

## Welcome to CodyChen.
**CodyChen** inputs tasks, mark tasks as done, delete tasks and search tasks.

## 1.0 Quick Start Guide
1. Download Java 17 
    - Go to the link [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html). 
      - To check if Java 17 is installed correctly, open Command Prompt and type Java.
2. Download the latest file available on CodyChen's Official Github Website [CodyChen](https://github.com/lowjunchen/ip/releases)
3. Locate the file directory which stores the downloaded file.
4. Open Command Prompt and run
```
java -jar CodyChen.jar
```

## Overview of Executables
### [List: Shows all tasks](#list)
```
list
```

### [Todo: Adds a todo task](#todo)
```
todo Watch Lecture
```
*Adds a Todo task called "Watch lecture"*

### [Todo: Adds a todo task](#deadline)
```
deadline Coursemology Homework /by 2021/12/5
```
*Adds a Deadline task called "Coursemology Homework"*

### [Event: Adds an event task](#event)
```
event CS2113 Test /from 2021/12/6 /to /2021/12/7
```
*Adds an Event task called "CS2113 Test"*

### [Mark: Marks any task](#mark)
```
mark 1
```
*\[X\] for first task*
* 
### [Unmark: Unmarks any task](#unmark)
```
unmark 1
```
*\[ \]  for first task*
### [Delete: Delete a task](#delete)
```
delete 1
```
*Deletes the first task*
### [Bye: Close the program](#bye)
```
bye
```


## Detailed User Manual
### Todo
<a id ="todo"></a>
`rgb(9, 105, 218)` Tasks include Todo, Deadlines and Events
* Todos includes tasks which does not have a deadline. To input a Todo, use the following syntax
* An error will be printed if the syntax is invalid
```
todo {taskName}
```

### Deadlines
<a id ="deadline"></a>
`rgb(9, 105, 218)` deadlines includes tasks which has a deadline. To input a Deadline, use the following syntax
* An error will be printed if the syntax is invalid 
* If the date is input in the format YYYY-MM-dd, it will be saved as the default format.
```
deadline {taskName} /by {deadline}
```

### Events
<a id ="event"></a>
`rgb(9, 105, 218)` Events includes tasks which has a start date and a deadline. To input an Event, use the following syntax
* An error will be printed if the syntax is invalid
* If the date is input in the format YYYY-MM-dd, it will be saved as the default format.
```
event {taskName} /from {startDate} /to {deadline}
```

## Mark Tasks
<a name ="mark"></a>
`rgb(9, 105, 218)`  arks a task as complete
* To mark an item, specify the index number of the item.
* An error will be printed if the syntax is invalid
* You can only mark an item if it exists. 
* To view the item list, refer to [list](#list)
```
mark {indexNmber}
```

## Unmark Tasks
<a name ="unmark"></a>
`rgb(9, 105, 218)`  Unmarks a task as incomplete
* To unmark an item, specify the index number of the item.
* An error will be printed if the syntax is invalid
* You can only mark an item if it exists.
* To view the item list, refer to [list](#list)
```
unmark {indexNmber}
```

## List Items
<a name ="list"></a>
`rgb(9, 105, 218)`  Lists the current items in your list
* To list, just enter the keyword list. 
```
list
```

## Find Tasks
<a name ="find"></a>
`rgb(9, 105, 218)` Returns all the items in the list according to a keyword

```
find {keyword}
```

## Delete Tasks
<a name ="delete"></a>
`rgb(9, 105, 218)` Deletes tasks in the list according to its index number
* If the item number is not found, an error will be returned
```
delete {indexNumber}
```

## Closing the bot
<a name ="bye"></a>
`rgb(9, 105, 218)` Ends the bot. Tasks saved will be recovered on next startup
```
bye
```



# Possible Errors
## List is empty
* Add some items in the list to continue

## List is corrupted
* Delete all existing items in the list to continue