package medea.core;

import medea.command.*;
import medea.command.createTask.DeadlineCommand;
import medea.command.createTask.EventCommand;
import medea.command.createTask.TodoCommand;
import medea.command.updateDone.MarkDoneCommand;
import medea.command.updateDone.UnmarkDoneCommand;
import medea.exceptions.MedeaException;

public class Parser {

    public Command parse(String fullCommand){
        String[] inputArguments = fullCommand.split(" ", 2);
        String commandString = inputArguments[0];
        String argumentString = inputArguments.length > 1 ? inputArguments[1] : "";

        return switch (commandString) {
            case ListCommand.COMMAND_WORD -> createListCommand();
            case ExitCommand.COMMAND_WORD -> createExitCommand();
            case DeleteCommand.COMMAND_WORD -> createDeleteCommand(argumentString);
            case MarkDoneCommand.COMMAND_WORD -> createMarkCommand(argumentString);
            case UnmarkDoneCommand.COMMAND_WORD -> createUnmarkCommand(argumentString);
            case TodoCommand.COMMAND_WORD ->  createTodoCommand(argumentString);
            case DeadlineCommand.COMMAND_WORD -> createDeadlineCommand(argumentString);
            case EventCommand.COMMAND_WORD -> createEventCommand(argumentString);
            case FindCommand.COMMAND_WORD -> createFindCommand(argumentString);
            default -> createInvalidCommand();
        };
    }

    private Command createInvalidCommand(){
        return new InvalidCommand();
    }

    private Command createListCommand() {
        return new ListCommand();
    }

    private Command createExitCommand() {
        return new ExitCommand();
    }

    private Command createDeleteCommand(String argumentString){
        int index = parseTaskIndex(argumentString);
        return new DeleteCommand(index);
    }

    private Command createMarkCommand(String argumentString) {
        int index = parseTaskIndex(argumentString);
        return new MarkDoneCommand(index);
    }

    private Command createUnmarkCommand(String argumentString) {
        int index = parseTaskIndex(argumentString);
        return new UnmarkDoneCommand(index);
    }

    private int parseTaskIndex(String taskIndex) {
        try {
            return Integer.parseInt(taskIndex.trim()) - 1;
        } catch (Error e) {
            throw new MedeaException("Command failed. Task Index out of range.");
        }
    }

    private Command createTodoCommand(String description){
        return new TodoCommand(description);
    }

    private Command createFindCommand(String filterWord){
        return new FindCommand(filterWord);
    }

    private Command createDeadlineCommand(String argumentString){
        String[] arguments = parseArguments(argumentString, " /by ");
        if (arguments.length != 2) {
            throw new MedeaException("Invalid deadline command. Please provide a task description and a deadline using '/by'.");
        }

        String description = arguments[0];
        String by = arguments[1];

        return new DeadlineCommand(description, by);
    }

    private Command createEventCommand(String argumentString){
        String[] arguments = parseArguments(argumentString, " /from ", " /to ");

        if (arguments.length != 3) {
            throw new MedeaException("Invalid event command. Please provide a task, start time, and end time using '/from' and '/to'.");
        }

        String description = arguments[0];
        String from = arguments[1];
        String to = arguments[2];

        return new EventCommand(description, from, to);
    }

    private String[] parseArguments(String argumentString, String... delimiters) {
        String delimiterPattern = String.join("|", delimiters);
        return argumentString.split(delimiterPattern);
    }
}
