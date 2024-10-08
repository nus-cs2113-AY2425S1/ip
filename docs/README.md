# Diana User Guide
Diana is a personal assistant that helps you keep track of our tasks.

## Diana's Special Features

1. It has an autosave feature, you never have to worry about your tasks disappearing when you exit the programme!


## List of Commands

`list`: displays all the tasks currently in your tasklist

`mark`: mark the task as completed 

`unmark`: unmarks the task in the case that you make a mistake

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
