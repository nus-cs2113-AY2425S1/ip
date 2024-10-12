package command;

import exception.EchoException;
import tasklist.TaskList;

public class FindCommand extends Command {
    private static final int FIND_WORD_LENGTH = 5;

    /**
     * {@inheritDoc}
     * Finds tasks in the task list using the keyword.
     *
     * @param taskList  The task list containing tasks.
     * @param userInput The user input containing the command description.
     */
    @Override
    public void execute(TaskList taskList, String userInput) {
        try {
            String keywordInUserInput = userInput.substring(FIND_WORD_LENGTH).trim();
            System.out.println(SEPARATOR);
            System.out.println(taskList.findTasksByKeyword(keywordInUserInput));
            System.out.println(SEPARATOR);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println(SEPARATOR);
            System.out.println(EchoException.findInputEmpty());
            System.out.println(SEPARATOR);
        }
    }
}
