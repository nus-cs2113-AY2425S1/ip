# Gertrude
### A new intelligent chatbot for all your scheduling needs!

## Listing tasks: `list`
Shows a list of all tasks, their completeness status, and any dates/times attached to them.

Format: `list`

## Adding a todo: `todo`
Adds a todo to the list of tasks.

Format: `todo NAME`

Examples:
- `todo Homework`
- `todo Eat dinner`

## Adding a deadline: `deadline`
Adds a deadline to the list of tasks.

Format: `deadline NAME /by TIME`

Examples:
- `deadline Homework /by 7`
- `deadline Software Engineering Increment /by 11:59 pm`

## Adding an event: `event`
Adds a event to the list of tasks.

Format: `event NAME /from TIME /to TIME`

Examples:
- `event Dinner /from 5 /to 7`
- `event Meeting with prof /from 10 am /to 11 am`

## Marking/Unmarking a task: 'mark'
If an task is marked, unmark it. Otherwise, mark it.

Format: `mark INDEX`

- A task's index can be found using `list`.
- If an index that doesn't exist in the list is inputted, the program throws an error.

## Deleting a task: `delete`
Deletes a task from the list of tasks.

Format: `delete INDEX`

- A task's index can be found using `list`.
- If an index that doesn't exist in the list is inputted, the program throws an error.

## Searching for a task by name: `find`
Lists all tasks with matching names to user-inputted keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- `find` is not sensitive for case. For instance, `homeWork` will match `Homework`.
- The order of the keywords doesn't matter. For instance, `homework math` is the same as `math homework`.
- Only the name of the tasks is searched.

Examples:

![image](https://github.com/user-attachments/assets/c3883a01-796e-4529-89e2-94c2ecbc70a9)

## Exiting the program: `exit`
Exits out of the program and gives a farewell message.

Format: `bye`

## Saving Gertrude:
Gertrude saves automatically to the user's hard drive after each input. No manual saving is necessary.

