package xiaome.commands;

import xiaome.TaskList;
import xiaome.XiaoMeException;

/**
 * Represents a command to find tasks in the task list that match the given input.
 */

public class FindCommand extends Command {

    String userInput;

    /**
     * Constructs a FindCommand with the given user input.
     *
     * @param userInput The input string used to search for tasks.
     */

    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    /**
     * Executes the FindCommand, searching for tasks in the task list that match the user's input.
     *
     * @param tasks The TaskList to search for matching tasks.
     * @return A string displaying the matching tasks or an appropriate message if no tasks are found.
     * @throws XiaoMeException If the input string after the "find" command is empty.
     */
    @Override
    public String execute(TaskList tasks) throws XiaoMeException {
        String input = userInput.replace("find", "").trim();

        if (input.isEmpty()) {
            throw new XiaoMeException("\tPlease provide a valid text input after find");
        }

        StringBuilder string = new StringBuilder("\tHere are the matching tasks in your list:\n");
        int matchCount = 0;

        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getTask(i).toString().contains(input)) {
                if (matchCount != 0) {
                    string.append("\n");
                }

                string.append("\t\t").append(matchCount + 1).append(".").append(tasks.getTask(i));
                matchCount += 1;
            }
        }
        if (matchCount == 0) {
            return "\tText provided does not have a corresponding task";
        }
        return string.toString();
    }
}