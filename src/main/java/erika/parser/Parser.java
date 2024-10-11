package erika.parser;

import erika.command.Command;
import erika.command.DeleteCommand;
import erika.command.ExitCommand;
import erika.command.FindCommand;
import erika.command.ListCommand;
import erika.command.MarkCommand;
import erika.command.UnmarkCommand;
import erika.command.addcommand.AddDeadlineCommand;
import erika.command.addcommand.AddEventCommand;
import erika.command.addcommand.AddTodoCommand;
import erika.exception.EmptyDescriptionException;
import erika.exception.ErikaException;
import erika.exception.FormatErrorException;
import erika.exception.UnknownCommandException;
import erika.settings.Settings;
import java.io.IOException;

/** Represents the interface between the application and the user (command line). */
public class Parser {

    private static int extractTaskIndex(String line) throws NumberFormatException {
        if (line.trim().endsWith("all")) {
            return -1;
        }
        String digitString = line.replaceAll("[^0-9-]", "");
        if (!digitString.isEmpty()) {
            return Integer.parseInt(digitString);
        } else {
            throw new NumberFormatException();
        }
    }

    private AddEventCommand addEvent(String line) throws FormatErrorException, EmptyDescriptionException {
        if (line.trim().equals("event")) {
            throw new EmptyDescriptionException("event");
        }
        if (!line.contains("event ")) {
            throw new FormatErrorException("Invalid event command format.\n\tDid you leave a space after keyword 'event'?");
        }
        int indexOfFrom = line.indexOf("/from");
        if (indexOfFrom == -1) {
            throw new FormatErrorException("missing /from parameter");
        }
        int indexOfTo = line.indexOf("/to");
        if (indexOfTo == -1) {
            throw new FormatErrorException("missing /to parameter");
        }

        int substringStart = line.indexOf("event ") + Settings.EVENT_LENGTH_OFFSET;
        int substringEnd;

        String fromText;
        String toText;
        if (indexOfFrom < indexOfTo) {
            fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET, indexOfTo - Settings.TO_REAR_OFFSET);
            fromText = fromText.trim();
            toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET);
            toText = toText.trim();
            substringEnd = indexOfFrom - Settings.FROM_REAR_OFFSET;
        } else {
            fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET);
            fromText = fromText.trim();
            toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET, indexOfFrom - Settings.FROM_REAR_OFFSET);
            toText = toText.trim();
            substringEnd = indexOfTo - Settings.TO_REAR_OFFSET;
        }
        String description = line.substring(substringStart, substringEnd).trim();

        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        if (fromText.isEmpty()) {
            throw new FormatErrorException("missing /from parameter");
        }
        if (toText.isEmpty()) {
            throw new FormatErrorException("missing /to parameter");
        }
        return new AddEventCommand(description, fromText, toText);
    }

    private AddDeadlineCommand addDeadline(String line) throws FormatErrorException, EmptyDescriptionException {
        if (line.trim().equals("deadline")) {
            throw new EmptyDescriptionException("deadline");
        }
        if (!line.contains("deadline ")) {
            throw new FormatErrorException("Invalid deadline command format.\n\tDid you leave a space after keyword 'deadline'?");
        }
        int indexOfBy = line.indexOf("/by");
        if (indexOfBy == -1) {
            throw new FormatErrorException("missing /by parameter");
        }
        String byText = line.substring(indexOfBy + Settings.BY_LENGTH_OFFSET).trim();
        if (byText.isEmpty()) {
            throw new FormatErrorException("missing /by parameter");
        }
        int substringStart = line.indexOf("deadline ") + Settings.DEADLINE_LENGTH_OFFSET;
        int substringEnd = indexOfBy - Settings.BY_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }
        return new AddDeadlineCommand(description, byText);
    }

    private AddTodoCommand addTodo(String line) throws EmptyDescriptionException {
        if (line.trim().equals("todo")) {
            throw new EmptyDescriptionException("todo");
        }
        if (!line.contains("todo ")) {
            throw new FormatErrorException("Invalid todo command format.\n\tDid you leave a space after keyword 'todo'?");
        }

        String description = line.substring(line.indexOf("todo ") + Settings.TODO_LENGTH_OFFSET).trim();
        return new AddTodoCommand(description);
    }

    private DeleteCommand deleteEntry(String line) throws EmptyDescriptionException, NumberFormatException {
        if (line.trim().equals("delete")) {
            throw new EmptyDescriptionException("delete");
        }
        if (!line.contains("delete ")) {
            throw new FormatErrorException("Invalid delete command format.\n\tDid you leave a space after keyword 'delete'?");
        }
        int index = extractTaskIndex(line);
        return new DeleteCommand(index);
    }

    private Command markEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("mark ")) {
            throw new EmptyDescriptionException("mark");
        }
        int markIndex = extractTaskIndex(line);
        if (line.contains("unmark ")) {
            return new UnmarkCommand(markIndex);
        } else {
            return new MarkCommand(markIndex);
        }
    }

    private FindCommand findByKey(String line) throws EmptyDescriptionException {
        if (!line.contains("find ")) {
            throw new EmptyDescriptionException("find");
        }
        int start = line.indexOf(" ");
        String key = line.substring(start).trim();
        return new FindCommand(key);
    }

    public Command parseInput(String line) throws IOException, ErikaException, NumberFormatException{
        if (line.equals("bye")) {
            return new ExitCommand();
        } else if (checkCommand(line, "find")){
            return findByKey(line);
        } else if (checkCommand(line, "list")) {
            return new ListCommand();
        } else if (checkCommand(line, "mark")) {
           return markEntry(line);
        } else if (checkCommand(line, "unmark")) {
            return markEntry(line);
        } else if (checkCommand(line, "todo")) {
            return addTodo(line);
        } else if (checkCommand(line, "deadline")) {
            return addDeadline(line);
        } else if (checkCommand(line, "event")) {
            return addEvent(line);
        } else if (checkCommand(line, "delete")) {
            return deleteEntry(line);
        } else {
            throw new UnknownCommandException();
        }
    }

    private boolean checkCommand(String line, String keyword) {
        return (line.contains(keyword) && line.indexOf(keyword) == 0);
    }

}
