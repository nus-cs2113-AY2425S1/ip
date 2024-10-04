# Dobby User Guide


Dobby is a desktop chatbot that stores and manages your list of activities, optimised for use via a Command Line Interface (CLI). 
If you wish to immerse into the magical world of wizards and elves, look no further and start chatting with Dobby, your loyal follower!

---

# Features 
### Adding Todos

Creates a todo task and adds it to your task list.

* Format: `todo <task_description>`
* Example: `todo read research paper`

Expected Outcome: 
```
  ____________________________________________________________
    Dobby has added this task:
      [T][ ] read research paper
    Dobby says master has 1 task in the list!
  ____________________________________________________________
```
---
### Adding Deadlines

Creates a deadline task and adds it to your task list.
* Has a description and a do-by datetime component.
* Do-by datetime component can be of the following forms:
  * In text form (e.g. today, tomorrow)
  * In date format yyyy-MM-dd (e.g. 2024-09-23)
  * In datetime format yyyy-MM-dd HH:mm (e.g. 2024-09-23 15:32)
* Format: `deadline <deadline_description> /by <deadline_datetime>`
* Example: `deadline Submit report on sleep /by 2024-09-30`

Expected Outcome:
```
  ____________________________________________________________
    Dobby has added this task:
      [D][ ] Submit report on sleep (by: 30/09/2024)
    Dobby says master has 2 tasks in the list!
  ____________________________________________________________
```

---

### Adding Events

Creates an event task and adds it to your task list.

* Has a description, start and end time components
* Start and end date parameters can be of the following forms:
  * In text form (e.g. today, tomorrow)
  * In date format yyyy-MM-dd (e.g. 2024-09-23)
  * In datetime format yyyy-MM-dd HH:mm (e.g. 2024-09-23 15:32)
* Format: `event <event_description> /from <start_time> /to <end_time>`
* Example: `event write research paper /from this Monday /to next Friday`

Expected Outcome:
```
  ____________________________________________________________
    Dobby has added this task:
      [E][ ] write research paper (from: this Monday to: next Friday)
    Dobby says master has 3 tasks in the list!
  ____________________________________________________________
```

---

### Listing Tasks

Lists out all tasks in the task list.

Format: `list`

Expected Outcome:
```
  ____________________________________________________________
    Here are the tasks in master's list:
    1.[T][ ] read research paper
    2.[D][ ] Submit report on sleep (by: 30/09/2024)
    3.[E][ ] write research paper (from: this Monday to: next Friday)
  ____________________________________________________________
```

---

### Finding Tasks

Searches through the task list to find tasks with descriptions matching the keyword.

Format: `find <keyword>`

Example: `find sleep`

Expected Outcome:
```
  ____________________________________________________________
    Here are the matching tasks in master's list: 
    1.[D][ ] Submit report on sleep (by: 23/09/2024)
  ____________________________________________________________
```

---

### Marking Tasks as complete

Marks the task as done.

Format: `mark <task_number>`

Example: `mark 1`

Expected Outcome:
```
  ____________________________________________________________
    Dobby has magically marked this task as done:
      [T][X] read research paper
  ____________________________________________________________
```

---

### Unmarking Tasks as incomplete

Marks the task as not done.

Format: `unmark <task_number>`

Example: `unmark 1`

Expected Outcome:
```
  ____________________________________________________________
    Dobby has marked this task as incomplete:
      [T][ ] read research paper
  ____________________________________________________________
```

---

### Deleting Tasks

Remove the task from the task list.

Format: `delete <task_number>`

Example: `delete 3`

Expected Outcome:
```
  ____________________________________________________________
    Dobby is removing this task:
      [E][ ] write research paper (from: this Monday to: next Friday)
    Dobby says master has 2 remaining tasks!
  ____________________________________________________________
```

---

### Saving tasks

All task list data are saved in the hard disk automatically after any command that modifies the task list. 
There is no need to save manually. 

---

### Exit Program

Terminate the runtime of the chatbot. 

Formats: `bye`, `sock`

Expected Outcome:
```
  ____________________________________________________________
    Thank you master, Dobby is free!!!
  ____________________________________________________________
```

---