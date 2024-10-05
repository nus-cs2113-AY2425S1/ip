# Yapper Developer Guide



## Table of Contents
1. [Acknowledgements](#acknowledgements)
2. [Getting Started](#getting-started)
3. [Architecture Overview](#architecture-overview)
4. [Classes and Packages](#classes-and-packages)
   1. [Yapper class](#yapper)
   2. [instructions Package](#instructions-package)
      1. [InstructionHandler class](#instructionhandler)
      2. [Instruction class](#instruction)
   3. [tasks Package](#tasks-package)
      1. [TaskHandler class](#taskhandler)
      2. [Task class](#task)
      3. [Todo class](#todo)
      4. [Deadline class](#deadline)
      5. [Event class](#event)
   4. [io Package](#io-package)
      1. [InputStringHandler class](#inputstringhandler)
      2. [OutputStringHandler class](#outputstringhandler)
      3. [InputFileHandler class](#inputfilehandler)
      4. [OutputFileHandler class](#outputfilehandler)
      5. [StringStorage class](#stringstorage)
      6. [DateAndTimeHandler class](#dateandtimehandler)
   5. [exceptions Package](#exceptions-package)
      1. [YapperException class](#yapperexception)
         2. [ExceptionHandler class](#exceptionhandler)

---

## Acknowledgements

This project is a fork of the Duke project template, found 
[here](https://github.com/nus-cs2113-AY2425S1/ip.git).

---

## Getting Started

Refer to this [README.md](../README.md) 
guide to set up and get started.

---

## Architecture Overview



The **architecture** diagram given above explains the high-level design of Yapper.

Given below is a quick overview of main components and how they interact with each other

1. **Yapper class**: The entry point of the program, managing the core loop and coordinating other components.
2. **instructions package**: Handles user input parsing and command processing.
3. **tasks package**: Handles tasks, including their types and related operations (ToDo, Deadline, Event).
4. **io package**: Handles input/output operations, including reading and writing tasks to/from files.
5. **exceptions package**: Handles custom exceptions for application-specific errors.

The sections below give more details of each component.

---

## Classes and Packages


### Yapper

API: [Yapper.java](../src/main/java/yapper/Yapper.java)

The `Yapper` class is the main entry point of the program. 
It manages the program's main loop, processes user input via the `InstructionHandler`, 
and also handles program initialization and termination.


<!-- DIVIDER -->

### instructions Package

This package contains classes used for handling and parsing user input.

#### Instruction

API: [Instruction.java](../src/main/java/yapper/instructions/Instruction.java)

The `Instruction` class represents a command issued by the user. 
It contains the parsed command type (e.g. add, delete) and its associated arguments.

#### InstructionHandler

API: [InstructionHandler.java](../src/main/java/yapper/instructions/InstructionHandler.java)

The `InstructionHandler` class is responsible for executing user commands. 
It interprets `Instruction` objects, invoking the appropriate methods to perform actions like adding or deleting tasks.


<!-- DIVIDER -->

### tasks Package

This package handles the logic for task management, including different types of tasks and their operations.

#### TaskHandler

API: [TaskHandler.java](../src/main/java/yapper/tasks/TaskHandler.java)

The `TaskHandler` class manages the list of tasks. 
It provides methods to add, remove, and retrieve tasks, 
as well as converting tasks to and from a string format for file storage.

#### Task

API: [Task.java](../src/main/java/yapper/tasks/Task.java)

The `Task` class is an abstract base class representing a generic task. 
It includes properties such as the task description and completion status. 
All task types (ToDo, Deadline, Event) inherit from this class.

#### Todo

API: [Todo.java](../src/main/java/yapper/tasks/Todo.java)

The `Todo` class extends `Task` and represents a simple task with no specific date or time constraints. 

#### Deadline

API: [Deadline.java](../src/main/java/yapper/tasks/Deadline.java)

The `Deadline` class extends `Task` and represents a task that has a due date, with only a end time.

#### Event

API: [Event.java](../src/main/java/yapper/tasks/Event.java)

The `Event` class extends `Task` and represents a task occurring at a specific time, with both start and end times.


<!-- DIVIDER -->

### io Package

This package manages input and output, including file storage and handling user input/output.

#### InputStringHandler

API: [InputStringHandler.java](../src/main/java/yapper/io/InputStringHandler.java)

The `InputStringHandler` class processes and validates raw user input, 
converting it into commands that the `InstructionHandler` can interpret.

#### OutputStringHandler

API: [OutputStringHandler.java](../src/main/java/yapper/io/OutputStringHandler.java)

The `OutputStringHandler` class formats output strings for the user. 
It prepares well-structured responses and error messages for display.

#### InputFileHandler

API: [InputFileHandler.java](../src/main/java/yapper/io/InputFileHandler.java)

The `InputFileHandler` class is responsible for reading task data from text files. 
It loads saved tasks at program start and parses them back into task objects.

#### OutputFileHandler

API: [OutputFileHandler.java](../src/main/java/yapper/io/OutputFileHandler.java)

The `OutputFileHandler` class writes tasks to a text file for persistent storage. 
It converts task objects into a savable string format and updates the file during runtime.

#### StringStorage

API: [StringStorage.java](../src/main/java/yapper/io/StringStorage.java)

The `StringStorage` class contains predefined strings used across the program for consistency, 
including responses, prompts, and error messages.

#### DateAndTimeHandler

API: [DateAndTimeHandler.java](../src/main/java/yapper/io/DateAndTimeHandler.java)

The `DateAndTimeHandler` class is responsible for handling date and time parsing and formatting. 
It converts user input dates into a standard format for `Deadline` and `Event` tasks.


<!-- DIVIDER -->

### exceptions Package

This package contains exception-related classes to handle errors specific to Yapper.

#### YapperException

API: [YapperException.java](../src/main/java/yapper/exceptions/YapperException.java)

The `YapperException` class is a custom exception used to represent Yapper-specific errors, 
such as invalid commands or missing arguments.

#### ExceptionHandler

API: [ExceptionHandler.java](../src/main/java/yapper/exceptions/ExceptionHandler.java)

The `ExceptionHandler` class processes exceptions that occur during runtime, with user-friendly error messages.