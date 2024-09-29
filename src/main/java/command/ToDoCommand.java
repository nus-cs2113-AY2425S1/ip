package command;

import tasklist.TaskList;
import task.ToDo;
import exception.EchoException;

public class ToDoCommand extends Command {
    private static final int TODO_WORD_LENGTH = 4;

    /**
     * {@inheritDoc}
     * Adds a new ToDo task.
     * If the input does not contain a valid description, sends an error message.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        String description = userInput.substring(TODO_WORD_LENGTH).trim();
        if (description.isEmpty()) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.todoDescriptionMissing());
            System.out.println(SEPARATOR);
        } else {
            ToDo newTask = new ToDo(description);
            taskList.storeTask(newTask);
            System.out.println(SEPARATOR);
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.println("Now you have " + taskList.getTaskNumber() + " tasks in the list.");
            System.out.println(SEPARATOR);
        }
    }
}
