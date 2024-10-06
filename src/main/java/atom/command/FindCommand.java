package atom.command;

import atom.exception.MissingKeywordException;
import atom.storage.Storage;
import atom.task.Task;
import atom.tasklist.TaskList;
import atom.ui.Ui;

import java.util.ArrayList;

public class FindCommand extends Command{

    private static final int KEYWORD_START_INDEX = 5;

    private String[] userInputSplit;
    private String fullCommand;

    public FindCommand(String[] words, String fullCommand) {
        userInputSplit = words;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (userInputSplit.length == 1) {
                throw new MissingKeywordException();
            }

            String keyword = fullCommand.substring(KEYWORD_START_INDEX);
            ArrayList<Task> matchingTasksList = tasks.findTasksWithKeyword(keyword);
            ui.printMatchingTasksList(matchingTasksList);

        } catch (MissingKeywordException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
