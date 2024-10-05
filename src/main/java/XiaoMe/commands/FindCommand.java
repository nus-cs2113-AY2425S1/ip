package XiaoMe.commands;

import XiaoMe.TaskList;
import XiaoMe.XiaoMeException;

public class FindCommand extends Command {

    String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

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
                string.append("\t\t").append(i + 1).append(".").append(tasks.getTask(i));
                matchCount += 1;
                if (i != tasks.getSize() - 1) {
                    string.append("\n");
                }
            }
        }

        if (matchCount == 0) {
            return "\tText provided does not have a corresponding task";
        }

        System.out.println(matchCount);
        return string.toString();
    }

}