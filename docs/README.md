# Luke User Guide

## Notes about the command format: 
- Words in `UPPER_CASE` are the parameters to be supplied ot the user.
  <br> e.g. in `todo TODO`, `TODO` is a parameter which can be used as  `todo tutorial`.
- Extraneous parameters for commands that do not take in parameters (such as `list`) will be ignored. 
  <br> e.g. if the command specifies `list 123`, it will be interpreted as `list`. 

## Adding todos 
Adds a todo to the task list.

Format: `todo TODO`
 
Example: <br>`todo tutorial 5` 
``` 
____________________________________________________________
Task added: [T][ ] tutorial 5
  Now you have 1 task in the list.
____________________________________________________________
``` 
## Adding deadlines 
Adds a deadline to the task list.

Format: `deadline TASK /by DEADLINE`, where `DEADLINE` is formatted as `yyyy-MM-dd HHmm`
  
Example: <br>`deadline finish tutorial 6 /by 2024-10-03 1400`
 
``` 
____________________________________________________________
Added deadline: [D][ ] finish tutorial 6 (by: Oct 03 2024 1400)
  Now you have 2 tasks in the list.
____________________________________________________________
```

## Adding events 
Adds an event to the task list.

Format: `event EVENT /from START /to END`, where `START` and `END` are formatted as `yyyy-MM-dd HHmm`

Example: <br>`event lecture /from 2024-10-04 1600 /to 2024-10-04 1800`

``` 
____________________________________________________________
Added event: [E][ ] lecture (from: Oct 04 2024 1600 to: Oct 04 2024 1800)
 Now you have 3 tasks in the list.
____________________________________________________________
```

## Listing all tasks 
Shows a list of all tasks.

Format: `list`

Example: <br>`list`

``` 
____________________________________________________________
1. [T][ ] tutorial 5
2. [D][ ] finish tutorial 6 (by: Oct 03 2024 1400)
3. [E][ ] lecture (from: Oct 04 2024 1600 to: Oct 04 2024 1800)
____________________________________________________________
```

## Mark task as done 
Marks the specified task as done.

Format: `mark INDEX`

Example: <br>`mark 1`

``` 
____________________________________________________________
Marked:
  [T][X] tutorial 5
____________________________________________________________
```

## Mark task as undone 
Marks the specified task as undone.

Format: `unmark INDEX`

Example: <br>`unmark 1`

``` 
____________________________________________________________
Unmarked:
  [T][ ] tutorial 5
____________________________________________________________ 
```

## Find task based on description 
Finds all tasks that contain the specified keyword in the description.

Format: `find KEYWORD`

Example: <br>`find tutorial`

```
____________________________________________________________
1. [T][ ] tutorial 5
2. [D][ ] finish tutorial 6 (by: Oct 03 2024 1400)
____________________________________________________________
```
 
## Delete task 
Deletes the specified task.

Format: `delete INDEX`

Example: <br>`delete 1`
```
____________________________________________________________
Removed task:
  [T][ ] tutorial 5
Now you have 2 tasks in the list.
____________________________________________________________
```

## Exiting the program
Exits the program.

Format: `bye`

Example:<br>`bye`

```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```