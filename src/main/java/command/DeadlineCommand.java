package command;

import task.TaskList;
import task.Deadline;
import exception.EchoException;

public class DeadlineCommand extends Command {
    private static final int DEADLINE_WORD_LENGTH = 9;
    private static final int DEADLINE_DUE_DATE_OFFSET = 5;

    @Override
    public void execute(TaskList taskList, String userInput) {
        int byIndex = userInput.indexOf(" /by ");
        if (byIndex == -1) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.deadlineDescriptionMissing());
            System.out.println(SEPARATOR);
            return;
        }

        String description = userInput.substring(DEADLINE_WORD_LENGTH, byIndex).trim();
        String dueDate = userInput.substring(byIndex + DEADLINE_DUE_DATE_OFFSET).trim();
        if (description.isEmpty() || dueDate.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.deadlineDescriptionMissing());
            System.out.println(SEPARATOR);
        } else {
            Deadline newTask = new Deadline(description, dueDate);
            taskList.storeTask(newTask);
            System.out.println(SEPARATOR);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.getTaskNumber() + " tasks in the list.");
            System.out.println(SEPARATOR);
        }
    }
}
