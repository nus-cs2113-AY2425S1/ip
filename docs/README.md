# CodyChen User Guide

This bot allows you to input tasks, mark tasks as done, delete tasks and search tasks.

## Input Tasks
**Tasks include Todo, Deadlines and Events**
### Todos
* Todos includes tasks which does not have a deadline. To input a Todo, use the following syntax
* An error will be printed if the syntax is invalid
```
todo {taskName}
```
### Deadlines
* Deadlines includes tasks which has a deadline. To input a Deadline, use the following syntax
* An error will be printed if the syntax is invalid 
```
deadline {taskName} /by {deadline}
```
### Events
* Events includes tasks which has a start date and a deadline. To input an Event, use the following syntax
* An error will be printed if the syntax is invalid
```
event {taskName} /from {startDate} /to {deadline}
```

## Mark or Unmark Tasks
** Marks or unmarks a task as complete instead of deleting them **
* To mark an item, specify the index number of the item.
* An error will be printed if the syntax is invalid
* You can only mark an item if it exists. 
* To view the item list, refer to (##list-items)

### Mark
```
mark {indexNmber}
```

### Mark
```
unmark {indexNmber}
```

## List Items
**Lists the current items in your list**
* To list, just enter the keyword list. 
```
list
```

## Delete Tasks
** Deletes tasks in the list according to its index number **
* If the item number is not found, an error will be returned
```
delete {indexNumber}
```

## Find Tasks
** Returns all the items in the list according to a keyword **

```
find {keyword}
```

# Possible Errors
## List is empty
* Add some items in the list to continue

## List is corrupted
* Delete all existing items in the list to continue