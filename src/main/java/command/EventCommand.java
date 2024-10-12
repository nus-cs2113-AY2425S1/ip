package command;

import tasklist.TaskList;
import task.Event;
import exception.EchoException;

public class EventCommand extends Command {
    private static final int EVENT_WORD_LENGTH = 6;
    private static final int START_WORD_LENGTH = 7;
    private static final int END_WORD_LENGTH = 5;

    /**
     * {@inheritDoc}
     * Adds a new event task.
     * If the input does not contain valid descriptions or time frames, sends an error message.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        int startIndex = userInput.indexOf(" /from ");
        int endIndex = userInput.indexOf(" /to ", startIndex + START_WORD_LENGTH);

        if (startIndex == -1 || endIndex == -1) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.eventDescriptionMissing());
            System.out.println(SEPARATOR);
            return;
        }

        String description = getDescription(userInput, startIndex);
        String start = getStart(userInput, startIndex, endIndex);
        String end = getEnd(userInput, endIndex);

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.eventDescriptionMissing());
            System.out.println(SEPARATOR);
        } else {
            addEvent(taskList, description, start, end);
        }
    }

    private static void addEvent(TaskList taskList, String description, String start, String end) {
        Event newTask = new Event(description, start, end);
        taskList.storeTask(newTask);
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.getTaskNumber() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }

    private static String getEnd(String userInput, int endIndex) {
        return userInput.substring(endIndex + END_WORD_LENGTH).trim();
    }

    private static String getStart(String userInput, int startIndex, int endIndex) {
        return userInput.substring(startIndex + START_WORD_LENGTH, endIndex).trim();
    }

    private static String getDescription(String userInput, int startIndex) {
        return userInput.substring(EVENT_WORD_LENGTH, startIndex).trim();
    }
}
