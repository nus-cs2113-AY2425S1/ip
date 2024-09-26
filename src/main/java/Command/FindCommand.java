package Command;

import TaskList.TaskList;

public class FindCommand extends Command{
    private static final int FIND_WORD_LENGTH = 7;

    @Override
    public void execute(TaskList taskList, String userInput) {
        String keywordInUserInput = userInput.substring(FIND_WORD_LENGTH).trim();
        System.out.println(SEPARATOR);
        System.out.println(taskList.findTasksByKeyword(keywordInUserInput));
        System.out.println(SEPARATOR);
    }
}
