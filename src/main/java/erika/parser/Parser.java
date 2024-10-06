package erika.parser;

import erika.command.*;
import erika.command.addcommand.AddDeadlineCommand;
import erika.command.addcommand.AddEventCommand;
import erika.command.addcommand.AddTodoCommand;
import erika.exception.*;
import erika.settings.Settings;


import java.io.IOException;

public class Parser {

    private static int extractTaskIndex(String line) throws NumberFormatException {
        if (line.contains(" all")) {
            return -1;
        }
        String digitString = line.replaceAll("[^0-9-]", "");
        if (!digitString.isEmpty()) {
            return Integer.parseInt(digitString);
        } else {
            throw new NumberFormatException();
        }
    }

    private Command indexOperation(String line, boolean isDelete) throws IndexOutOfBoundsException, EmptyDescriptionException{
        if(!isDelete) {
            return markEntry(line);
        } else {
            return deleteEntry(line);
        }
    }

    private AddEventCommand addEvent(String line) throws FormatErrorException, EmptyDescriptionException{
        if(!line.contains("event ")) {
            throw new EmptyDescriptionException("Event");
        }
        int indexOfFrom = line.indexOf(" /from ");
        if (indexOfFrom == -1) {
            throw new FormatErrorException("missing /from parameter");
        }
        if(line.indexOf(" ") == indexOfFrom - Settings.FROM_REAR_OFFSET) {
            throw new EmptyDescriptionException("Event");
        }
        int indexOfTo = line.indexOf(" /to ");
        if(indexOfTo == -1) {
            throw new FormatErrorException("missing /to parameter");
        }
        if(line.substring(line.indexOf(" ")).indexOf(" ") == indexOfFrom - Settings.FROM_REAR_OFFSET) {
            throw new FormatErrorException("missing /from parameter");
        }

        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = 0;

        String fromText;
        String toText;
        if (indexOfFrom < indexOfTo) {
            fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET, indexOfTo - Settings.TO_REAR_OFFSET);
            toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET);
            substringEnd = indexOfFrom - Settings.FROM_REAR_OFFSET;
        } else {
            fromText = line.substring(indexOfFrom + Settings.FROM_LENGTH_OFFSET);
            toText = line.substring(indexOfTo + Settings.TO_LENGTH_OFFSET, indexOfFrom - Settings.FROM_REAR_OFFSET);
            substringEnd = indexOfTo - Settings.TO_REAR_OFFSET;
        }

        String description = line.substring(substringStart, substringEnd);

        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Event");
        }
        return new AddEventCommand(description, fromText, toText);
    }

    private AddDeadlineCommand addDeadline (String line) throws FormatErrorException, EmptyDescriptionException {
        if (!line.contains("deadline ")) {
            throw new EmptyDescriptionException("Deadline");
        }
        int indexOfBy = line.indexOf(" /by ");
        if (indexOfBy == -1) {
            throw new FormatErrorException("missing /by parameter");
        }
        if (line.indexOf(" ") == indexOfBy - Settings.BY_REAR_OFFSET) {
            throw new EmptyDescriptionException("Deadline");
        }
        int substringStart = line.indexOf(" ") + Settings.SPACE_OFFSET;
        int substringEnd = indexOfBy - Settings.BY_REAR_OFFSET;
        String description = line.substring(substringStart, substringEnd);
        String byText = line.substring(indexOfBy + Settings.BY_LENGTH_OFFSET);
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("Deadline");
        }
        return new AddDeadlineCommand(description,byText);
    }

    private AddTodoCommand addTodo(String line) throws EmptyDescriptionException{
        if (!line.contains("todo ")) {
            throw new EmptyDescriptionException("Todo");
        }

        String description = line.substring(line.indexOf(" ")+Settings.SPACE_OFFSET);
        return new AddTodoCommand(description);
    }

    private DeleteCommand deleteEntry(String line) throws EmptyDescriptionException {
        if (!line.contains("delete ")) {
            throw new EmptyDescriptionException("delete");
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

    public Command parseInput(String line) throws IOException, ErikaException{
        String errMsg = "";
        if (line.equals("bye")) {
            return new ExitCommand();
        } else if (checkCommand(line, "find")){
            return findByKey(line);
        } else if (checkCommand(line, "list")) {
            return new ListCommand();
        } else if (checkCommand(line, "mark")) {
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
