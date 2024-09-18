package commands;

import exception.BentoException;
import exception.InvalidDateFormatException;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.SHOW_COMMAND;

public class ShowCommand extends Command {
    private String dateOfInterestString;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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
