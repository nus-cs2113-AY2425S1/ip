# Diana User Guide
Diana is a personal assistant that helps you keep track of our tasks.

## Quick Start
1. Ensure you Java 17 installed on your computer. 
2. Download the latest .jar file from [here](jar link)
3. Copy the file to the folder you want to use as the home folder for Diana
4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar ip.jar` command to run the application.
5. Type the command in the terminal and press Enter to execute it.
    
    Some example commands you can try:
   1. `list`: Lists all tasks
   2. `delete 3` : Deletes the 3rd task in the current list 
   3. `date Oct 19 2019` : Searches through the task list to look for a task that has a deadline dated on Oct 19 2019. 
   4. `bye` : Exits the programme

## Features
> [!NOTE] 
> - Words in `UPPER_CASE` are the parameters supplied by the user.
> e.g. `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo read book`
> 
> - Extraneous parameters for commands that do no take in parameters (such as `list`, `bye`) will be ignored

### Listing all tasks: `list`

- displays all the tasks currently in your tasklist. 
- Format: `list`

### Completing a task: Mark / Unmark
- Able to mark a task as completed, or unmark the task. 
- Format: `mark INDEX`

Examples: 
- `mark 1`
- !["Image cannot be loaded"](/../assets/images/mark.png)
- `unmark 1`
- !["Image cannot be loaded"](/../ip/docs/assets/images/mark.png)

`todo`: creates a todo task without any date specification 

`deadline`: create a deadline task that you need to complete by a certain date 

`event`: create an event task that starts from a certain date and ends on a certain date

`delete`: delete any of the task that is currently within your tasklist 

`find`: find tasks based on keywords 

`date`: find tasks based on dates 

`bye`: Exits the programme

| Commands                                             | Example                                              | Output                                                            |
|------------------------------------------------------|------------------------------------------------------|-------------------------------------------------------------------|
| `list`                                               | `list`                                               | `1. [T][ ] read book`                                             |
| `mark <index>`                                       | `mark 1`                                             | `1. [T][X] read book`                                             |
| `unmark <index>`                                     | `unmark 1`                                           | `1. [T][ ] read book`                                             |
| `todo <task>`                                        | `todo read book`                                     | `1. [T][ ] read book`                                             |
| `event <task> /from <date & time> /to <date & time>` | `event read book /from 2/12/2019 1800 /to 3/12/2019` | `1. [E][ ] read book (from: Dec 02 2019 1800 to: Dec 03 2019)`    |
| `deadline <task> /by <date & time>`                  | `deadline read book /by 2/12/2019 1800`              | `1. [D][ ] read book (by: Dec 02 2019 1800)`                      |
| `delete <index>`                                     | `delete 7`                                           | `Task: [D][ ] read book (by: Oct 08 2024 15:08) has been deleted` 
| `find <task>`                                        | `find book`                                          | `1. [D][ ] read book (by: Oct 08 2024 15:08)`                     |
| `date <date>`                                        | `date Oct 19 2019`                                   | `1. [D][ ] read book (by: Oct 19 2019 15:08)`                     |
| `bye`                                                | `bye`                                                | `Goodbye!`                                                        |
