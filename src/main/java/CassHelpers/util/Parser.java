package CassHelpers.util;

import CassHelpers.commands.*;
import CassHelpers.exceptions.InvalidCommandException;
import CassHelpers.exceptions.NoCommandException;
import CassHelpers.types.Task;

import java.util.Scanner;

public class Parser {

    public static String getUserInput(){
        String input = new Scanner(System.in).nextLine().trim();
        return input;
    }
    
    public static String[] getCommandArgsFromUserInput(String input){
        String[] commandArgs = input.split(" ");
        return commandArgs;
    }

    public static Command parse(String input, String[] commandArgs, UI ui, TaskList tasks) throws NoCommandException {
        if (commandArgs.length == 0 || commandArgs[0].isEmpty()) {
            throw new NoCommandException("Sorry, you haven't entered any command.");
        }
        return switch (commandArgs[0].toLowerCase()) {
            case "mark" -> new MarkCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "unmark" -> new UnmarkCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "delete" -> new DeleteCommand(tasks, Integer.parseInt(commandArgs[1]));
            case "list" -> new ListCommand(tasks);
            case "bye" -> new ExitCommand(tasks);
            case "todo" -> new AddTodoCommand(tasks, input);
            case "deadline" -> new AddDeadlineCommand(tasks, input);
            case "event" -> new AddEventCommand(tasks, input);
            case "help" -> new HelpCommand(ui);
            default -> throw new InvalidCommandException("Sorry, unknown command.");
        };
    }

}

