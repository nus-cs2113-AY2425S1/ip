package XiaoMe.commands;

import XiaoMe.TaskList;
import XiaoMe.XiaoMeException;
import XiaoMe.Storage;
import XiaoMe.task.Event;

/**
 * Represents a command that adds an event task to the task list.
 * The task has a description, a start time, and an end time.
 */
public class EventCommand extends Command {

    String userInput;

    /**
     * Constructs an EventCommand with the user input specifying the event details.
     *
     * @param userInput the user input in the format 'event <description> /from <start_time> /to <end_time>'
     */
    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the command to add an event task to the list.
     * The expected format for the user input is
     * 'event <description> /from <start_time> /to <end_time>'.
     *
     * @param tasks the list of tasks to which the event will be added
     * @return a success message with details of the added task
     * @throws XiaoMeException if the user input format is invalid
     */
    @Override
    public String execute(TaskList tasks) throws XiaoMeException  {
        try {
            String[] eventWords = userInput.replace("event", "").trim().split(" /");

            for (String word : eventWords) {
                if (word.isEmpty()) {
                    throw new IllegalArgumentException();
                }
            }
            if (eventWords.length != 3 ||
                    !eventWords[1].startsWith("from ") ||
                    !eventWords[2].startsWith("to ")) {
                throw new IllegalArgumentException();
            }

            tasks.addTask(new Event(eventWords[0], eventWords[1].replace("from ", "").trim(), eventWords[2].replace("to ", "").trim())); // add task to storage

            Storage.saveFile(tasks.getTasks());

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.getLast() + "\n"
                    + "\tNow you have " + tasks.getSize() + " tasks in the list.";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create an event:
                    \tUse 'event <description> /from <start_time> /to <end_time>'""");
        }
    }
}
