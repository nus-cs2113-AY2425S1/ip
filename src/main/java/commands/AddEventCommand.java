package commands;

import exception.InvalidDateFormatException;
import exception.InvalidEventException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static constants.Command.EVENT_COMMAND;
import static constants.Regex.FROM_PREFIX;
import static constants.Regex.TO_PREFIX;

public class AddEventCommand extends Command {
    private String userInput;
    private boolean fromUserInput;
    private DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AddEventCommand(String userInput, boolean fromUserInput) {
        super(EVENT_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    public boolean isValidFromAndTo(LocalDate from, LocalDate to) {
        return (to.isAfter(from) || to.isEqual(from));
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidEventException, SaveFileErrorException, InvalidDateFormatException {
        userInput = parser.removeEventPrefix(userInput);
        int indexOfFrom = userInput.indexOf(FROM_PREFIX);
        int indexOfTo = userInput.indexOf(TO_PREFIX);

        if (indexOfFrom == -1 || indexOfTo == -1) {
            throw new InvalidEventException();
        }

        String eventName = parser.extractEventName(userInput, indexOfFrom);
        String fromString = parser.extractFromString(userInput, indexOfFrom, indexOfTo);
        String toString = parser.extractToString(userInput, indexOfTo);

        if (eventName.isEmpty() || fromString.isEmpty() || toString.isEmpty()) {
            throw new InvalidEventException();
        }
        try {
            LocalDate fromDate = LocalDate.parse(fromString, inputFormatter);
            LocalDate toDate = LocalDate.parse(toString, inputFormatter);

            if (!isValidFromAndTo(fromDate, toDate)) {
                throw new InvalidEventException();
            }

            Event toAdd = new Event(eventName, fromDate, toDate);
            tasks.addTask(toAdd);

            if (fromUserInput) {
                ui.printAddTaskSuccessMessage(toAdd.toString(), tasks);
            }

            saveTask(storage, tasks, ui);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }
}
