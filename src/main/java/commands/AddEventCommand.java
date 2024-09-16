package commands;

import exception.InvalidEventException;
import exception.SaveFileErrorException;
import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

import static constants.Command.EVENT_COMMAND;
import static constants.Regex.FROM_PREFIX;
import static constants.Regex.TO_PREFIX;

public class AddEventCommand extends Command {
    private String userInput;
    private boolean fromUserInput;

    public AddEventCommand(String userInput, boolean fromUserInput) {
        super(EVENT_COMMAND);
        this.userInput = userInput;
        this.fromUserInput = fromUserInput;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidEventException, SaveFileErrorException {
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

        Event toAdd = new Event(eventName, fromString, toString);
        tasks.addTask(toAdd);

        if (fromUserInput) {
            ui.printAddTaskSuccessMessage(toAdd.toString(), tasks);
        }

        saveTask(storage, tasks, ui);
    }
}
