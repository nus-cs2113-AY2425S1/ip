package parser;

import exception.EchoException;
import tasklist.TaskList;
import command.ListCommand;
import command.MarkCommand;
import command.UnmarkCommand;
import command.ToDoCommand;
import command.DeadlineCommand;
import command.EventCommand;
import command.DeleteCommand;
import command.FindCommand;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {
    private static final DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
    private static final String SEPARATOR = "_".repeat(30);
    private Scanner scanner = new Scanner(System.in);

    /**
     * Converts the date from a string.
     * Accepts 3 kinds of formats (yyyy-MM-dd, d/M/yyyy, and MMM dd yyyy).
     * Returns the parsed date.
     *
     * @param date The date in string format to be parsed.
     * @return The parsed LocalDate object.
     * @throws DateTimeParseException if the date cannot be parsed.
     */
    public static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, formatter1);
        } catch (DateTimeParseException e1) {
            try {
                return LocalDate.parse(date, outputFormatter);
            } catch (DateTimeParseException e2) {
                return LocalDate.parse(date, formatter2);
            }
        }
    }
    /**
     * Reads the user input from CLI.
     *
     * @return A string of the user input.
     */
    public String getUserInput() {
        return scanner.nextLine();
    }

    /**
     * Processes and executes the user input.
     * If the input does not match any command, an error message is displayed.
     *
     * @param userInput The string from the user.
     * @param taskList The TaskList object to pass in the list of tasks.
     */
    public void processUserInput(String userInput, TaskList taskList) {
        if (userInput.startsWith("list")) {
            new ListCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("mark")) {
            new MarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("unmark")) {
            new UnmarkCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("todo")) {
            new ToDoCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("deadline")) {
            new DeadlineCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("event")) {
            new EventCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("delete")) {
            new DeleteCommand().execute(taskList, userInput);
        } else if (userInput.startsWith("find")) {
            new FindCommand().execute(taskList, userInput);
        } else {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.unknownCommand());
            System.out.println(SEPARATOR);
        }
    }
}