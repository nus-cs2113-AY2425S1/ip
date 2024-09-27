# Cuboyd Taskmanager User Guide

## Getting Started


1. Make sure you have Java 17 installed on your computer.
2. Download the .jar file.
3. Run the following command in the directory which you saved the .jar file:

```
java -jar cuboyd.jar
```

## Features

### List tasks

List all available tasks

```
list
```

### Find tasks

Finds list of tasks with the keyword

```
find <keyword>
find book
```

### ToDo

Creates a ToDo Task.

```
todo <description>
todo read book
```

### Deadline

Creates a Deadline task with a `/by` date

```
deadline <description> /by <by date>
deadline read book /by Mon 6pm
```

### Event

Creates an event task with a `/from` and a `/to` date

```
event <description> /from <from date> /to <to date>
event read book /from Mon 6pm /to Mon 9pm
```

### Mark/Unmark tasks

Mark/ Unmark tasks as done

```
mark <task_index>
unmark <task_index>

mark 1
unmark 3
```

### Delete tasks

Delete tasks from list

```
delete <task_index>
delete 3
```


