# Erika :: User Guide

Erika is a handy CLI task organizer for your daily tasks, supporting Todos, Deadlines and Events. 

## Quick Start
1. Ensure that you have Java 17 installed on your computer
2. Download the latest <code>.jar</code> file from here
3. Copy the file into the folder you want to use at the root directory of Erika. Erika will create a new folder (/data) that will be used to store a locally-saved copy of your list. 
4. Open a terminal, <code>cd</code> into the folder you placed the <code>.jar</code> file into 

## Notes about Command Format
- Words in <code>UPPER_CASE</code> are the parameters to be supplied by the user. <i>Eg in <code>todo DESCRIPTION</code>, <code>DESCRIPTION</code> is a parameter which can be used as <code>todo borrow book</code></i>
- ```{...}``` (curly braces) represent optional parameters that may not be used
- Parameters must be supplied with a preceding <code>/</code> character. <i> I.e to supply the <b>by</b> parameter the command given must be <code>deadline DESCRIPTION /by DEADLINE</code></i>
- Order of parameters does not matter. <i><code>event DESCRIPTION /from START /to END</code> produces the same result as <code>event DESCRIPTION /to END /from START</code></i>
- Commands are case sensitive, <i>i.e <code>find hello</code> is not equivalent to <code>find Hello</code></i>
- Spaces are <i>optional</i> <b>except</b> for the <b>single</b> space between the <b>command keyword</b> (i.e ```todo```,```deadline```,```event```,```delete``` etc) and the rest of the command. Example: ```deadline sleep/bytonight``` is equivalent to ```deadline sleep /by tonight``` but ```deadlinesleep/bytonight``` will raise an exception.
- Likewise, additional spaces between parameters other than the aforementioned initial space between the command keyword and the rest of the command will be <b>ignored</b>. 
Example:
```plaintext
deadline     sleep     /by         tonight
```

will produce output
```angular2html
        ____________________________________________________________
	Got it. I've added this task:
	  [D][ ] sleep (by: tonight)
	Now you have 5 tasks in the list.
        ____________________________________________________________
```

## Adding Todos: <code>todo</code>
Adds a <b>todo</b> to the list of tasks

Format: <code>todo DESCRIPTION</code>

Example: `todo borrow book`

Erika will then confirm with the following sample message

```
        ____________________________________________________________
        Got it. I've added this task:
          [T][ ] borrow book
        Now you have 3 tasks in the list.
        ____________________________________________________________
```

## Adding Deadlines: <code>deadline</code>
Adds a <b>deadline</b> to the list of tasks. Deadlines are defined to be tasks with a <b>by</b> attribute.

Format: <code>deadline DESCRIPTION /by DEADLINE</code>

Where <code>DEADLINE</code> can either be:
1. An unformatted <code>string</code>, example <code>tomorrow</code>
2. A <b>formatted</b> Date <code>string</code> in the format specified in [additional notes](#a-name--dateformatsaaccepted-datedatetime-formats) which will be interpreted as a Java<code>LocalDate</code> object
3. A <b>formatted</b> DateTime <code>string</code> in the format specified in [additional notes](#a-name--dateformatsaaccepted-datedatetime-formats) which will be interpreted as a Java<code>LocalDateTime</code> object

Example: `deadline return book /by tomorrow`, `deadline return book /by 23/09/2024`, `deadline return book /by 23/09/2024 23:59` for each of the three aforementioned cases respectively. 

Erika will then confirm with the following sample message

```
        ____________________________________________________________
        Got it. I've added this task:
          [D][ ] return book (by: 23/09/24 23:59)
        Now you have 4 tasks in the list.
        ____________________________________________________________
```

## Adding Events: <code>event</code>
Adds an <b>event</b> to the list of tasks. Events are defined to be tasks with a <b>start</b> and <b>end</b> attribute. 

Format: <code>event DESCRIPTION /from START /to END</code> <b>or </b> <code>event DESCRIPTION /to END /from START</code>

#### Note: The order of parameters <i>does not matter</i>.

Where <code>START</code> and <code>END</code> can either be:
1. An unformatted <code>string</code>, example <code>tomorrow</code>
2. A <b>formatted</b> Date <code>string</code> in the format specified in [additional notes](#a-name--dateformatsaaccepted-datedatetime-formats) which will be interpreted as a Java<code>LocalDate</code> object
3. A <b>formatted</b> DateTime <code>string</code> in the format specified in [additional notes](#a-name--dateformatsaaccepted-datedatetime-formats) which will be interpreted as a Java<code>LocalDateTime</code> object

Example: `event book fair /from Monday /to Friday`, `event book fair /from 23/09/2024 /to 30/09/2024`, `event book fair /from 23/09/2024 00:00 /to 30/09/2024 23:59` for each of the three aforementioned cases respectively.

Erika will then confirm with the following sample message

```
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] book fair (from: 23/09/24 00:00 to: 30/09/24 23:59)
	Now you have 1 task in the list.
	____________________________________________________________
```

## Deleting Tasks: `delete`

Deletes a task (todo, deadline or event) from the list. 

Format: <code>delete PARAMETER</code>

Where `PARAMETER` can be either
1. An interger `index` that represents the 1-index position of the entry in the list or ```-1``` which deletes <b>all</b>
entries in the ```TaskList```.
2. Keyword `all` that will also delete <b>all</b> entries in the list.

Example: `delete 1`, `delete all`/`delete -1`

Erika will then confirm with the following sample message in the case of deletion of a <i>single</i> entry

```
	____________________________________________________________
	Nice! I've deleted this task:
	[T][ ] delete me
	Now you have 3 tasks in the list.
	____________________________________________________________
```
Or in the case of deleting <i>all</i> entries
```
	____________________________________________________________
	All tasks deleted. There are now 0 tasks
	____________________________________________________________
```
### Note: 
If the `index` supplied is <b>out of the range</b> of the current task list, Erika throws an `OutOfBoundsException`:

```
	____________________________________________________________
	Error: Supplied index is out of bounds
	____________________________________________________________
```

## Marking Tasks: `mark`

Marks a task (todo, deadline or event) as <code>done</code>.

Format: <code>mark INDEX</code>

where <code>INDEX</code> is the 1-index of the entry you wish to mark.

Example: `mark 1`

Erika will then confirm with the following sample message

```angular2html
	____________________________________________________________
	Nice! I've marked this task as done:
		[T][X] hello
	____________________________________________________________
```

## Unmarking Tasks: `unmark`

Marks a task (todo, deadline or event) as <code>not done</code>.

Format: <code>unmark INDEX</code>

where <code>INDEX</code> is the 1-index of the entry you wish to mark.

Example: `unmark 1`

Erika will then confirm with the following sample message

```angular2html
	____________________________________________________________
    Nice! I've marked this task as not done yet:
    [T][ ] hello
    ____________________________________________________________
```

## Listing Tasks: <code>list</code>

Lists all the tasks in the current list in the following format:

<code>[INDEX]. [TASK_TYPE][MARK_STATUS] [TASK DESCRIPTION] </code>

Where ```Index``` can take an ```integer``` value, ```TASK_TYPE``` takes one of the letters ```T```,```D``` or ```E``` and ```MARK_STATUS``` takes the value of either ```[X]``` (marked) or ```[ ]``` (unmarked).

Example:

Running the following command sequence: 

```angular2html
todo todo task 
deadline deadline task /by deadline
event event task /from start /to end
```

Produces the following result when ```list``` is executed immediately after: 

```
	____________________________________________________________
	Here are the items in your list: 
	1. [T][ ] todo task
	2. [D][ ] deadline task (by: deadline)
	3. [E][ ] event task (from: start to: end)
	____________________________________________________________
```
## Searching for Tasks: <code>find</code>

Returns tasks with ```DESCRIPTION``` matching the ```KEY```

<code>find KEY</code>

Where ```KEY``` is a ```string``` to match.

Example: Supposing our list of tasks is as follows

```
	____________________________________________________________
	Here are the items in your list: 
	1. [T][ ] todo task
	2. [D][ ] deadline task (by: deadline)
	3. [E][ ] event task (from: start to: end)
	____________________________________________________________
```

executing```find todo``` produces

```
    ____________________________________________________________
    Here are the matching items in your list:
    1. [T][ ] todo task
    ____________________________________________________________
```


## Saving Data: 

- Tasks are automatically saved in <b>real-time</b> (after each command execution) to a text file (```./data/tasks.txt```).

#### Note: ```./data/task.txt``` is a relative path that is with respect to the directory from which Erika is run from the terminal application. 
- Tasks are stored in a single line with the following format: 
```
[TASK_TYPE],[MARK_STATUS],[DESCRIPTION],{DEADLINE}/{START},{END}
```

Where ```[TASK_TYPE]``` takes on one of the letters ```T```,```D``` or ```E``` representing ```Todo```, ```Deadline``` and ```Event``` Tasks respectively, ```MARK_STATUS``` takes on either a ```0``` (not done) or a ```1``` (done), and ```[DESCRIPTION]```,```{DEADLINE}/{START}``` and ```{END}``` take on ```string``` values, with ```{ }``` representing optional parameters.

Example - Supposing the following commands are executed sequentially: 

```angular2html
todo todo task 
deadline deadline task /by deadline
event event task /from start /to end
```
The corresponding ```./data/tasks.txt``` file will contain the following data:
```angular2html
T,0,todo task
D,0,deadline task,deadline
E,0,event task,start,end
```
- Upon startup, Erika checks for the existence of this file. If it does not exist, an empty file is created. If it exists, the file is read and the entries are automatically loaded into Erika and can be accessed/manipulated. 
- Advanced users are free to directly modify the ```./data/tasks.txt``` file to directly add/modify tasks to their liking. 

## Additional Notes: 
### <a name = "dateFormats"></a>Accepted DateTime/Date Formats:
#### DateTime: 
```angular2html
yyyy-MM-dd HH:mm
dd/MM/yyyy HH:mm
dd-MM-yyyy HH:mm
dd/MM/yy HH:mm
dd-MM-yy HH:mm
MMM dd yyyy h.mma
dd MMM yyyy h.mma
MMM dd yy h.mma
dd MMM yy h.mma
```
#### Date:
```angular2html
yyyy-MM-dd 
dd/MM/yyyy
dd-MM-yyyy
dd/MM/yy
dd-MM-yy
MMM dd yyyy
dd MMM yyyy
MMM dd yy
dd MMM yy
```
