package command;

import tasklist.TaskList;
import exception.EchoException;

public class MarkCommand extends Command {
    private static final int MARK_WORD_LENGTH = 5;

    /**
     * {@inheritDoc}
     * Marks a specified task as done.
     * Sends an error message if the task number is invalid.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.substring(MARK_WORD_LENGTH).trim());
            if (taskNumber < 1 || taskNumber > taskList.getTaskNumber()) {
                System.out.println(SEPARATOR);
                System.out.println(EchoException.taskNumberOutOfRange());
                System.out.println(SEPARATOR);
                return;
            }
            System.out.println(SEPARATOR);
            System.out.println(taskList.markTaskAsDone(taskNumber));
            System.out.println(SEPARATOR);
        } catch (NumberFormatException e) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.invalidTaskNumberFormat());
            System.out.println(SEPARATOR);
        }
    }
}
