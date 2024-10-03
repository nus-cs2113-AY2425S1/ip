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



```
expected output
```

## Add Todo Goals

Adds a `todo` goal in this format: `<Goal Number> <Description> [T][ ]`

Format: `todo <description>`

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

Adds a `deadline` goal in this format: `<Goal Number> <Description> [D][ ] (by: )`

Format: `deadline <description> /by <time>`

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

Adds a goal in this format: `<Description> [E][ ] (from: <time> to: <time>)`

Format: `event <description> /from <time> /to <time>`

### Example:

Input: `event Matchday /from Saturday 1900 /to 2100`

Output:
```
GOALLL! Your event has been added: 
[E][ ] Matchday(from: Saturday 1900 to: 2100)
Now you have 3 goals in the list.

Saving data at: <filepath>
```



