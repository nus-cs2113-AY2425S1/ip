package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Event;

public class EventCommand extends Command {

    String userInput;
    boolean isExit;

    public EventCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute() throws XiaoMeException  {
        try {
            // user is creating a new event
            String[] eventWords = userInput.split("/");

            if (eventWords.length != 3 ||
                    !eventWords[1].startsWith("from ") ||
                    !eventWords[2].startsWith("to ")) {
                throw new IllegalArgumentException();
            }

            tasks.add(new Event(eventWords[0].replace("event", "").trim(), eventWords[1].replace("from ", "").trim(), eventWords[2].replace("to ", "").trim())); // add task to storage

            Storage.saveFile(tasks);

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.get(tasks.size() - 1) + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.\n";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create an event:
                    \tUse 'event <description> /from <start_time> /to <end_time>'
                    """);
        }
    }
}
