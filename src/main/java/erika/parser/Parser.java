package erika.parser;

import erika.command.Command;
import erika.console.Console;
import erika.exception.*;
import erika.settings.Settings;
import erika.task.Deadline;
import erika.task.Event;
import erika.task.Task;
import erika.task.Todo;

import java.io.IOException;

public class Parser {

    private static int extractTaskIndex(String line) throws NumberFormatException {
        String digitString = line.replaceAll("[^0-9-]", "");
        if (!digitString.isEmpty()) {
            return Integer.parseInt(digitString);
        } else {
            throw new NumberFormatException();
        }
    }

    private void saveChangesToFileSystem() {
        try{
            fileSystem.updateFileSystemWithLocalTasks(tasks);
        } catch (IOException e) {
            Console.printMessage("IO Error encountered when attempting to save changes to FS");
        }
    }

    private void indexOperation(String line, boolean isDelete) throws IndexOutOfBoundsException, EmptyDescriptionException{
        if(!isDelete) {
            markEntry(line);
        } else {
            deleteEntry(line);
        }
    }

    private void addEvent(String line) throws FormatErrorException, EmptyDescriptionException{
        if(!line.contains("event ")) {
            throw new EmptyDescriptionException("Event");
        }
        int indexOfFrom = line.indexOf("/from ");
        if(line.indexOf(" ") == indexOfFrom - Settings.FROM_REAR_OFFSET) {
            throw new FormatErrorException();
        }
        int indexOfTo = line.indexOf("/to ");
        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new FormatErrorException();
        }
        Event newEvent = addNewEvent(line, indexOfFrom, indexOfTo);
        Console.printAddedMessage(newEvent);
    }

    private Event addNewEvent(String line, int indexOfFrom, int indexOfTo) throws EmptyDescriptionException {
        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = indexOfFrom - Settings.FROM_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd);
        String fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET, indexOfTo - Settings.TO_REAR_OFFSET);
        String toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        Event newEvent = new Event(description, fromText, toText);
        tasks.add(newEvent);
        try {
            fileSystem.appendTaskToFile(newEvent);
        } catch (IOException e) {
            Console.printMessage("Unable to append Event to FS. IO Error occurred");
        }
        return newEvent;
    }

    private void addDeadline (String line) throws FormatErrorException, EmptyDescriptionException {
        if (!line.contains("deadline ")) {
            throw new EmptyDescriptionException("Deadline");
        }
        int indexOfBy = line.indexOf("/by ");
        if (line.indexOf(" ") == indexOfBy - Settings.BY_REAR_OFFSET) {
            throw new FormatErrorException();
        }
        if (indexOfBy == -1) {
            throw new FormatErrorException();
        }
        Deadline newDeadline = addNewDeadline(line, indexOfBy);
        Console.printAddedMessage(newDeadline);
    }

    private Deadline addNewDeadline(String line, int indexOfBy) throws EmptyDescriptionException {
        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = indexOfBy - Settings.BY_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd);
        String byText = line.substring(indexOfBy + Settings.BY_LENGTH_OFFSET);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }

        Deadline newDeadline = new Deadline(description, byText);
        tasks.add(newDeadline);
        try {
            fileSystem.appendTaskToFile(newDeadline);
        } catch (IOException e) {
            Console.printMessage("Unable to append Deadline to FS. IO Error occurred");
        }
        return newDeadline;
    }

    private void addTodo(String line) throws EmptyDescriptionException{
        if (!line.contains("todo ")) {
            throw new EmptyDescriptionException("Todo");
        }
        Todo newTodo = new Todo(line.substring(line.indexOf(" ")+Settings.SPACE_OFFSET));
        tasks.add(newTodo);
        try {
            fileSystem.appendTaskToFile(newTodo);
        } catch (IOException e) {
            Console.printMessage("Unable to append Todo to FS. IO Error occurred");
        }
        Console.printAddedMessage(newTodo);
    }

    private void deleteEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("delete ")) {
            throw new EmptyDescriptionException("delete");
        }
        int index = extractTaskIndex(line);

        console.printDeletedMessage();
        saveChangesToFileSystem();
    }

    private void markEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("mark ")) {
            throw new EmptyDescriptionException("mark");
        }
        Task.markIndex = extractTaskIndex(line);
        if (Task.markIndex <= 0 || Task.markIndex > Task.getTaskArraySize()) {
            throw new IndexOutOfBoundsException();
        }
        if (line.contains("unmark ")) {
            tasks.get(Task.markIndex - 1).setMark(false);
            console.printUnmarkedMessage();
        } else {
            tasks.get(Task.markIndex - 1).setMark(true);
            console.printMarkedMessage();
        }
        saveChangesToFileSystem();
    }

    public Command parseInput(String line) {
        try{
            if (line.equals("bye")) {
                Console.printGoodbyeMessage();
                return true;
            } else if (line.equals("list")) {
                console.printList();
            } else if (line.contains("mark")) {
                indexOperation(line, false);
            } else if (line.contains("todo")) {
                addTodo(line);
            } else if (line.contains("deadline")) {
                addDeadline(line);
            } else if (line.contains("event")) {
                addEvent(line);
            } else if (line.contains("delete")) {
                indexOperation(line, true);
            } else {
                throw new UnknownCommandException();
            }
        } catch (UnknownCommandException e) {
            Console.printMessage("Error! Unknown Command, please try again!");
        } catch (
        FormatErrorException e) {
            Console.printMessage("Error! Invalid Command Format, please try again!");
        } catch (IndexOutOfBoundsException e) {
            Console.printMessage("Error! Task " + Task.markIndex + " is out of bounds!");
        } catch (NumberFormatException e) {
            Console.printMessage("Error! Please input a valid number!");
        } catch (
        EmptyListException e) {
            Console.printMessage("It seems that there are no tasks! Please consider adding some!");
        } catch (
        EmptyDescriptionException e) {
            Console.printMessage("Error! Description of " + e.taskType + " cannot be empty!");
        }
        return false;
    }

}
