package command;

import tasklist.TaskList;
import task.Event;
import exception.EchoException;

public class EventCommand extends Command {
    private static final int EVENT_WORD_LENGTH = 6;
    private static final int START_WORD_LENGTH = 7;
    private static final int END_WORD_LENGTH = 5;

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

        String description = userInput.substring(EVENT_WORD_LENGTH, startIndex).trim();
        String start = userInput.substring(startIndex + START_WORD_LENGTH, endIndex).trim();
        String end = userInput.substring(endIndex + END_WORD_LENGTH).trim();

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.eventDescriptionMissing());
            System.out.println(SEPARATOR);
        } else {
            Event newTask = new Event(description, start, end);
            taskList.storeTask(newTask);
            System.out.println(SEPARATOR);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.getTaskNumber() + " tasks in the list.");
            System.out.println(SEPARATOR);
        }
    }
}
