# Ronaldo User Guide

## Welcome to Ronaldo! ⚽
This chatbot develops the personality of Cristiano Ronaldo.
He is arguably the greatest football player of all time (He is for me).

![Ronaldo after scoring in the ucl final vs Juventus, 2017](https://www.transparentpng.com/thumb/cristiano-ronaldo/vFe4pk-cristiano-ronaldo-simple.png)

Ronaldo is a Java based CLI application that is able to manage tasks efficiently,
which will be known as goals in this chatbot.

## Getting Started

Prerequisites: JDK 17, update Intellij to the most recent version.

1. Download the latest `.jar` file.
2. Place the file in your desired folder for the chatbot.
3. Open a command terminal from the folder containing the `.jar` file, 
   or `cd` into the folder containing the `.jar` file.
4. Enter the command on the terminal: `java -jar Ronaldo.jar`

Upon launching the application, you should see the following introduction:

## Features
* Ability to add ➕ and delete ❌ goals
* Contains multiple goal types with different formats, 
  such as `Todos✅`, `Events🗓️` and `Deadlines💀`.
* Ability to show list of goals 📃
* Instantly saves goals upon addition, deletion and marking of goals 📩
* Ability to find goals 🔎
* Ability to mark and unmark goals 📌

### Hidden Features
* Exclaims when input contains "siu" 🤩
* Boasts when input contains "messi" 🥱

## Commands
1. `todo <description>` - Adds a goal in this format: [T][ ]
2. `deadline <description> /by <time>` - Adds a goal in this format: [D][ ] (by: )
3. `event <description> /from <time> /to <time>` - Adds a goal in this format: [E][ ] (from: to: )
4. `mark <Goal Number>` - Marks a goal as complete `[X]`
5. `unmark <Goal Number>` - Marks a goal as incomplete `[ ]`
6. `list` - Provides a list of all goals
7. `delete <Goal Number>`- Deletes a goal
8. `find <Keyword>` - Finds a goal with matching keywords
9. `bye` - Exits the program



## Help

Shows the user a list of commands available to use.

Input Format: `help`

### Example:

Input: `help`

Output:
```
Please enter either of these commands in this format without '<>': 
<bye> 
<list> 
<delete> <Goal Number> 
<mark> <Goal Number> 
<unmark> <Goal Number> 
<find> <Keyword> 
<event> <Description> /from <Time> /to <Time> 
<todo> <Description> 
<deadline> <Description> /by <Time> 
```

## Add Todo Goal

Adds a `todo` goal to the goal list.

Input Format: `todo <description>`

Output Format: `[T][ ] <Description>`

### Example:

Input: `todo Score a goal`

Output:
```
GOALLL! Your todo has been added: 
[T][ ] Score a goal
Now you have 1 goal in the list.

Saving data at: <filepath>
```

## Add Deadline Goal

Adds a `deadline` goal to the goal list.

Input Format: `deadline <description> /by <time>`

Output Format: `[D][ ] <Description> (by: )`

### Example:

Input: `deadline Prepare match /by today 2359`

Output: 
```
GOALLL! Your deadline has been added: 
[D][ ] Prepare match (by:today 2359)
Now you have 2 goals in the list.

Saving data at: <filepath>
```

## Add Event Goal

Adds an `event` goal to the goal list.

Input Format: `event <description> /from <time> /to <time>`

Output Format: `[E][ ] <Description> (from: <time> to: <time>)`

### Example:

Input: `event Matchday /from Saturday 1900 /to 2100`

Output:
```
GOALLL! Your event has been added: 
[E][ ] Matchday (from: Saturday 1900 to: 2100)
Now you have 3 goals in the list.

Saving data at: <filepath>
```

## Find Goal

Finds a goal with matching keywords. Do note that keywords are case-sensitive.

Input Format: `find <Keyword>`

### Example:

Input: `find Score`

Output: 
```
Here are the matching goals found in the list:
[T][ ] Score a goal
```

## Delete Goal

Deletes a goal from the goal list.

Input format: `delete <Goal Number>`

### Example:

Input: `delete 1`

Output:
```
VAR disallowed your goal: 
[T][ ] Score a goal
Now you have 2 goals in the list.

Saving data at: <filepath>
```

## Mark Goal

Marks a goal as complete via `[X]`

Input Format: `mark <Goal Number>`

### Example:

Input: `mark 1`

Output:
```
SIUUUUUUU! One step closer to achieving your dreams! Your goal is now completed:
[D][X] Prepare match (by:today 2359)

Saving data at: <filepath>
```

## Unmark Goal

Marks a goal as incomplete `[ ]`

Input Format: `unmark <Goal Number>`

### Example:

Input: `unmark 1`

Output:
```
Ronaldo is disappointed in you. Your goal is now incomplete:
[D][ ] Prepare match (by:today 2359)

Saving data at: <filepath>
```

## Show List Of Goals

Prints the list of goals current in the goal list.

Input Format: `list`

### Example:

Input: `list`

Output:
```
Here are the goals to complete in order for you to reach your dreams:
1. [D][ ] Prepare match (by:today 2359)
2. [E][ ] Matchday (from: Saturday 1900 to: 2100)
```

## Exit Program

Exits the program

Input Format: `bye`

### Example:

Input: `bye`

Output:
```
Bye. Hope to see you soon! SIUUUUUUU!
```
