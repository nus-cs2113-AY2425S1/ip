package commands;

import exceptions.XiaoMeException;
import task.Task;

import java.util.ArrayList;

public class FindCommand extends Command {

    String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        String input = userInput.replace("find", "").trim();

        if (input.isEmpty()) {
            throw new XiaoMeException("\tPlease provide a valid text input after find");
        }

        StringBuilder string = new StringBuilder("\tHere are the matching tasks in your list:\n");
        int matchCount = 0;

        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(input)) {
                string.append("\t\t").append(i + 1).append(".").append(tasks.get(i));
                matchCount += 1;
                if (i != tasks.size() - 1) {
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