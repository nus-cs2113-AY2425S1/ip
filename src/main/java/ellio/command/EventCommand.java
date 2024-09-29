package ellio.command;

import ellio.BotText;
import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.Event;
import ellio.task.Task;
import ellio.task.TaskList;
import ellio.ui.Ui;

import static ellio.Ellio.storage;

public class EventCommand extends Command {

    public static final int EVENT_INPUT_SPACING = 6;

    public EventCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Adds a new Event Task into the list
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            Event newEvent = formatEvent(inputCommand);
            tasks.addTask(newEvent);
            tasks.addNumberTask();
            System.out.println(BotText.LINE_BORDER + "Got it. I've added this task:\n  " + newEvent.getTaskInfo());
            System.out.println("Now you have " + tasks.getNumberTask() + " tasks in the list.\n" + BotText.LINE_BORDER);
            storage.saveNewTask(newEvent.getSaveFileTask());
        } catch (EllioExceptions e){
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(BotText.LINE_BORDER + BotText.MESSAGE_INVALID_EVENT_TIME_FORMAT +BotText.LINE_BORDER);
        }
    }

    /**
     * Splits the user input into description, event start time and event end time
     * Returns an Event Object to be saved into the list.
     * @param line Input from user
     * @return An Event object to be saved into the list
     * @throws EllioExceptions Handles any error input by the user
     */
    private static Event formatEvent(String line) throws EllioExceptions {
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");
        int secondSlashIndex = line.indexOf("/", firstSlashIndex + 1);

        // Extract the substrings based on the slash positions
        String description = line.substring(EVENT_INPUT_SPACING, firstSlashIndex).trim();
        String eventStart = line.substring(firstSlashIndex + 1, secondSlashIndex).trim();
        String eventEnd = line.substring(secondSlashIndex + 1).trim();

        if(!eventStart.startsWith("from")) {
            throw new EllioExceptions.WrongEventStartFormatException();
        } else if(!eventEnd.startsWith("to")) {
            throw new EllioExceptions.WrongEventEndFormatException();
        }

        //add Semicolon behind from and to
        eventStart = eventStart.replace("from", "from:");
        eventEnd = eventEnd.replace("to", "to:");

        return new Event(description, "0", eventStart, eventEnd);
    }
}
