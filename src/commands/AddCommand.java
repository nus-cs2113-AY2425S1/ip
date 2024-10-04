package commands;
import exception.IncompleteCommandException;
import exception.InvalidTaskContentException;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;
import task.*;
import static main.Sirius.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * AddCommand is responsible for adding a new task (Todo, Deadline, or Event) based on user input.
 * It processes the input and adds the appropriate task to the task list.
 */
public class AddCommand extends Command {
    private final String userInput;

    public AddCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Parses the given time string in two possible formats ("dd/MM/yyyy" or "yyyy-MM-dd").
     * If the string matches one of these formats, it will be parsed into a {@link LocalDate} and
     * formatted into the output format "MMM dd yyyy" (e.g., "Oct 15 2019").
     * If the input string does not match either format, it will be returned as-is.
     *
     * @param time The time string to be parsed and formatted.
     * @return The formatted date string in "MMM dd yyyy" format if parsing is successful,
     *         or the original string if it does not match any of the expected formats.
     */
    public String dealWithTimeFormat(String time){
        DateTimeFormatter inputFormat1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter inputFormat2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        LocalDate date = null;
        try {
            date = LocalDate.parse(time, inputFormat1);
        } catch (DateTimeParseException e) {
            // If the first format fails, try the second format (yyyy-MM-dd)
            try {
                date = LocalDate.parse(time, inputFormat2);
            } catch (DateTimeParseException ex) {
                // If both formats fail, just use the initial format
                return time;
            }
        }
        return date.format(outputFormat);
    }


    /**
     * Executes the AddCommand by parsing the user input and adding the corresponding task to the TaskList.
     * It supports three types of tasks: Todo, Deadline, and Event.
     *
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IncompleteCommandException, InvalidTaskContentException {
        String[] slashCommand = userInput.split(SLASH);
        String commandContent = slashCommand[0]; //commandContent = commandPrefix + taskName
        String commandPrefix = commandContent.split(SPACE)[0];
        String taskName = commandContent.replace(commandPrefix, EMPTY).trim();

        try {
            switch (commandPrefix) {
                case TODO:
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else{
                        tasks.addTask(new Todo(taskName, false));
                    }

                    break;
                case DEADLINE:
                    int indexOfBy = userInput.indexOf("/by");
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else if (indexOfBy == -1) {  // cannot find "/by"
                        throw new InvalidTaskContentException("You should declare '/by' for deadline");
                    } else {
                        String byTime = userInput.substring(indexOfBy).replace("/by", EMPTY).trim();;
                        tasks.addTask(new Deadline(taskName, false, dealWithTimeFormat(byTime)));
                    }
                    break;
                case EVENT:
                    int indexOfFrom = userInput.indexOf("/from");
                    int indexOfTo = userInput.indexOf("/to");
                    if (taskName.isEmpty()) {
                        throw new IncompleteCommandException(commandPrefix);
                    } else if (indexOfFrom == -1 || indexOfTo == -1) {
                        throw new InvalidTaskContentException("You should declare '/from' and '/to' for event");
                    } else {
                        String fromTime = userInput.substring(indexOfFrom, indexOfTo).replace("/from", EMPTY).trim();
                        String toTime = userInput.substring(indexOfTo).replace("/to", EMPTY).trim();;
                        tasks.addTask(new Event(taskName, false, dealWithTimeFormat(fromTime), dealWithTimeFormat(toTime)));
                    }
            }
            ui.showTaskAdded(tasks, tasks.getListSize());
            ui.showCurrentSizeOfList(tasks);
        } catch (InvalidTaskContentException | IncompleteCommandException e) {
            ui.showLine();
            ui.print(e.getMessage());
            ui.showLine();
        }
        storage.saveTaskList(tasks.getList(), ui);
    }
}