package AlyBot;

import Commands.*;
import java.util.InputMismatchException;

/**
 * The Parser class interprets user commands and converts them into executable Command objects.
 */
public class Parser {

    /**
     * Parses the user's input command and returns the corresponding Command object.
     *
     * @param fullCommand The user's input command as a string.
     * @return The Command object that corresponds to the input.
     * @throws AlyException If the command is invalid or an error occurs during parsing.
     */
    public static Command parse(String fullCommand) throws AlyException {
        Command commandToExecute;
        try {
            String[] commandWords = fullCommand.split(" ");
            String firstWord = commandWords[0].trim();
            String instruction = fullCommand.replace(firstWord, "");

            switch (firstWord) {
            case "exit":
                commandToExecute = new ExitCommand();
                break;
            case "help":
                commandToExecute = new HelpCommand();
                break;
            case "echo":
                commandToExecute = new EchoCommand(instruction.trim());
                break;
            case "list":
                commandToExecute = new ListCommand();
                break;
            case "todo":
            case "deadline":
            case "event":
                commandToExecute = new AddCommand(firstWord, instruction.trim());
                break;
            case "mark":
            case "unmark":
                commandToExecute = new MarkCommand(firstWord, instruction.trim());
                break;
            case "delete":
                commandToExecute = new DeleteCommand(instruction.trim());
                break;
            default:
                throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            throw new AlyException("Can follow instructions properly anot? Read carefully and try again!", e);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AlyException("You got too much work already bro, task list full liao!", e);
        } catch (Exception e) {
            throw new AlyException("An unexpected error occurred!", e);
        }
        return commandToExecute;
    }
}