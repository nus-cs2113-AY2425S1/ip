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
- ![mark](https://github.com/user-attachments/assets/4f3ec920-0cb2-4e1d-822a-d29ce719519c)
- `unmark 1`
- ![image](https://github.com/user-attachments/assets/6f5329c6-7ac2-496f-961a-7a0436d938f3)

### Create a todo task: `todo`
- creates a todo task without any date specification
- Format: `todo DESCRIPTION`

Examples: 
- `todo read book`
- ![image](https://github.com/user-attachments/assets/0ac35756-a4eb-4228-9d81-d4b05a46e633)

### Create a deadline task: `deadline`
- create a deadline task that you need to complete by a certain date 
- Format: `deadline DESCRIPTION /by DD/MM/YYYY HHMM`

Examples: 
- `deadline read book /by 2/12/2019 1800`
- ![image](https://github.com/user-attachments/assets/e5f53dc3-a571-4475-a802-6efc084f16a1)

### Create an event task: `event`
- create an event task that starts from a certain date and ends on a certain date
- Format: `event DESCRIPTION /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM`

Examples:
- `event read book /from 2/12/2019 1800 /to 3/12/2019 1800`
- ![image](https://github.com/user-attachments/assets/3553574f-7a74-43e2-a8d4-556ec88a8b90)

### Delete a task: `delete`
- delete any of the task that is currently within your tasklist
- Format: `delete INDEX`

Examples: 
- `delete 1`
- ![image](https://github.com/user-attachments/assets/0dfd4d36-1fa5-4ed0-af56-de292f10de87)

### Find tasks: `find`
- find tasks based on keywords
- Format: `find DESCRIPTION`

Examples: 
- `find read`
- ![image](https://github.com/user-attachments/assets/2e5abbe1-9547-4473-b785-176f66786118)

### Find date: `date`
- find tasks based on due dates 
- Format: `date MMM DD YYYY`

Examples: 
- `date Dec 03 2019`
- ![image](https://github.com/user-attachments/assets/074ef13a-e087-49c9-89a7-0238362901fb)

### Exit programme: `bye`
- Exits the programme
- Format: `bye`

Examples: 
- `bye`
- ![image](https://github.com/user-attachments/assets/9d9cb751-50a0-4f9c-90a0-f5f8991ae704)


| Commands                                             | Example                                              | Output                                                                                    |
|------------------------------------------------------|------------------------------------------------------|-------------------------------------------------------------------------------------------|
| `list`                                               | `list`                                               | ![mark](https://github.com/user-attachments/assets/4f3ec920-0cb2-4e1d-822a-d29ce719519c)  |
| `mark INDEX`                                         | `mark 1`                                             | ![image](https://github.com/user-attachments/assets/6f5329c6-7ac2-496f-961a-7a0436d938f3) |
| `unmark INDEX`                                       | `unmark 1`                                           | ![image](https://github.com/user-attachments/assets/0ac35756-a4eb-4228-9d81-d4b05a46e633) |
| `todo <task>`                                        | `todo read book`                                     | ![image](https://github.com/user-attachments/assets/0ac35756-a4eb-4228-9d81-d4b05a46e633) |
| `event <task> /from <date & time> /to <date & time>` | `event read book /from 2/12/2019 1800 /to 3/12/2019` | ![image](https://github.com/user-attachments/assets/3553574f-7a74-43e2-a8d4-556ec88a8b90) |
| `deadline <task> /by <date & time>`                  | `deadline read book /by 2/12/2019 1800`              | ![image](https://github.com/user-attachments/assets/e5f53dc3-a571-4475-a802-6efc084f16a1) |
| `delete <index>`                                     | `delete 1`                                           | ![image](https://github.com/user-attachments/assets/0dfd4d36-1fa5-4ed0-af56-de292f10de87) |
| `find <task>`                                        | `find read`                                          | ![image](https://github.com/user-attachments/assets/2e5abbe1-9547-4473-b785-176f66786118) |
| `date <date>`                                        | `date Dec 03 2019`                                   | ![image](https://github.com/user-attachments/assets/074ef13a-e087-49c9-89a7-0238362901fb) |
| `bye`                                                | `bye`                                                | ![image](https://github.com/user-attachments/assets/9d9cb751-50a0-4f9c-90a0-f5f8991ae704) |
