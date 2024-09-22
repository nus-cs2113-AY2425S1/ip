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