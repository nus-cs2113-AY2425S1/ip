package commands;

import exception.InvalidDateFormatException;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.SHOW_COMMAND;
import static constants.Regex.INPUT_DATE_FORMAT;

public class ShowCommand extends Command {
    private String dateOfInterestString;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(INPUT_DATE_FORMAT);

    public ShowCommand(String userInput) {
        super(SHOW_COMMAND);
        this.dateOfInterestString = userInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidDateFormatException {
        try {
            dateOfInterestString = parser.removeShowPrefix(dateOfInterestString);
            LocalDate dateOfInterest = LocalDate.parse(dateOfInterestString, inputFormatter);
            ui.listTasksOfInterest(dateOfInterest, tasks);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
