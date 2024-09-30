package Commands;

import Tasks.Task;
import java.util.ArrayList;
import java.util.List;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds tasks that contain the keyword. "
            + "Keyword must be provided.\n"
            + "\tUsage: " + COMMAND_WORD + " <keyword>\n"
            + "\tExample: " + COMMAND_WORD + " assignment";

    public static final String MESSAGE_SUCCESS = "Here are the matching tasks in your list:\n";

    private final String toFind;

    public FindCommand(String keyword) {
        this.toFind = keyword;
    }

    @Override
    public CommandResult execute() {
        try{
            List<Task> foundedTask = new ArrayList<>();
            List<Integer> foundedTaskIndex = new ArrayList<>();
            Integer maxIndex = 0;
            for (int i = 0; i < tasksList.size(); i++) {
                if (tasksList.get(i).getDescription().contains(this.toFind)) {
                    foundedTask.add(tasksList.get(i));
                    foundedTaskIndex.add(i);
                    if (i > maxIndex) {
                        maxIndex = i;
                    } 
                }
            }
            String[] foundedTaskText = new String[foundedTask.size()];
            int maxLenght = maxIndex/10 + 3;
            for (int i = 0; i < foundedTask.size(); i++) {
                foundedTaskText[i] = String.format("%" + maxLenght + "s", (foundedTaskIndex.get(i) + 1) + ". ") + foundedTask.get(i).toString();
            }
            return new CommandResult("keyword: "+ this.toFind + "\n" + MESSAGE_SUCCESS + String.join("\n", foundedTaskText));
        } catch (IndexOutOfBoundsException e) {
            return new CommandResult("An error occurred: " + e.getMessage());
        } 
    }
}