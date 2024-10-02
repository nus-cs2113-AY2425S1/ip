package command;

import tasklist.TaskList;
import task.Deadline;
import exception.EchoException;

public class DeadlineCommand extends Command {
    private static final int DEADLINE_WORD_LENGTH = 8;
    private static final int DEADLINE_DUE_DATE_OFFSET = 5;

    /**
     * {@inheritDoc}
     * Adds a new deadline task.
     * If the input does not contain a valid description or due date, sends an error message.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        int byIndex = userInput.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.deadlineDescriptionMissing());
            System.out.println(SEPARATOR);
            return;
        }

        String description = getDescription(userInput, byIndex);
        String dueDate = getDueDate(userInput, byIndex);
        if (description.isEmpty() || dueDate.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.deadlineDescriptionMissing());
            System.out.println(SEPARATOR);
        } else {
            addDeadline(taskList, description, dueDate);
        }
    }

    private static String getDueDate(String userInput, int byIndex) {
        return userInput.substring(byIndex + DEADLINE_DUE_DATE_OFFSET).trim();
    }

    private static String getDescription(String userInput, int byIndex) {
        return userInput.substring(DEADLINE_WORD_LENGTH, byIndex).trim();
    }

    private static void addDeadline(TaskList taskList, String description, String dueDate) {
        Deadline newTask = new Deadline(description, dueDate);
        taskList.storeTask(newTask);
        System.out.println(SEPARATOR);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        System.out.println("Now you have " + taskList.getTaskNumber() + " tasks in the list.");
        System.out.println(SEPARATOR);
    }
}
