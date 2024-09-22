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
2. A <b>formatted</b> <code>string</code> in the format <code>dd/MM/yyyy</code> which will be interpreted as a Java<code>LocalDate</code> object
3. A <b>formatted</b> <code>string</code> in the format <code>dd/MM/yyyy hh:mm</code> which will be interpreted as a Java<code>LocalDateTime</code> object

Example: `deadline return book /by tomorrow`, `deadline return book /by 23/09/2024`, `deadline return book /by 23/09/2024 23:59` for each of the three aforementioned cases respectively. 

Erika will then confirm with the following sample message

```
        ____________________________________________________________
        Got it. I've added this task:
          [D][ ] return book (by: 23/09/24 23:59)
        Now you have 4 tasks in the list.
        ____________________________________________________________
```

// A description of the expected outcome goes here
## Adding Events: <code>event</code>
Adds an <b>event</b> to the list of tasks. Events are defined to be tasks with a <b>start</b> and <b>end</b> attribute. 

Format: <code>event DESCRIPTION /from START /to END</code> <b>or </b> <code>event DESCRIPTION /to END /from START</code>

#### Note: The order of parameters <i>does not matter</i>.

Where <code>START</code> and <code>END</code> can either be:
1. An unformatted <code>string</code>, example <code>tomorrow</code>
2. A <b>formatted</b> <code>string</code> in the format <code>dd/MM/yyyy</code> which will be interpreted as a Java<code>LocalDate</code> object
3. A <b>formatted</b> <code>string</code> in the format <code>dd/MM/yyyy hh:mm</code> which will be interpreted as a Java<code>LocalDateTime</code> object

Example: `event book fair /from Monday /to Friday`, `event book fair /from 23/09/2024 /to 30/09/2024`, `event book fair /from 23/09/2024 00:00 /to 30/09/2024 23:59` for each of the three aforementioned cases respectively.

Erika will then confirm with the following sample message

```
	____________________________________________________________
	Got it. I've added this task:
	  [E][ ] book fair (from: 23/09/24 00:00 to: 30/09/24 23:59)
	Now you have 1 task in the list.
	____________________________________________________________
```

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details