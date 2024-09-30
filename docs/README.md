
# Cassandra User Guide

**Cassandra** is a CLI-based task manager app designed to help users manage their daily tasks effectively.

## Features

### 1. Add a Todo Task: `todo`

Add a simple todo task to your list.

```bash
todo <task_description>
```

**Example:**
```bash
todo Read a book
```

### 2. Add a Deadline Task: `deadline`

Add a task with a deadline.

```bash
deadline <task_description> /by <due_date> 
(date fmt:  dd-mm-yyyy or dd-mm-yyyy HHmm format or <day-of-the-week> or next <day-of-the-week>)
```

**Example:**
```bash
deadline Submit assignment /by 2024-09-15
```

### 3. Add an Event Task: `event`

Add an event task with a start and end time.

```bash
event <event_description> /from <start_date> /to <end_date>
(date fmt:  dd-mm-yyyy or dd-mm-yyyy HHmm format or <day-of-the-week> or next <day-of-the-week>)

```

**Example:**
```bash
event Team meeting /from 2024-09-15 1400 /to 2024-09-15 1600
```

### 4. List All Tasks: `list`

Displays all tasks currently in your list.

```bash
list
```

### 5. Mark a Task as Complete: `mark`

Mark a task as complete by specifying its index.

```bash
mark <task_index>
```

**Example:**
```bash
mark 2
```

### 6. Unmark a Task (set as incomplete): `unmark`

Unmark a task (set it as incomplete) by specifying its index.

```bash
unmark <task_index>
```

**Example:**
```bash
unmark 2
```

### 7. Find Tasks with Similar Descriptions: `find`

Find tasks with descriptions similar to the provided prompt.

```bash
find <prompt>
```

**Example:**
```bash
find book
```

### 8. Exit the App: `bye`

Exit the application.

```bash
bye
```

---

**Note:** Task indices start from 1.
