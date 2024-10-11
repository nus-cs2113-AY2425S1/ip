package jeff.command;

import jeff.exception.InvalidFormatException;
import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

/**
 * The FindCommand class is responsible for searching through a TaskList
 * to find tasks that match a given keyword.
 */
public class FindCommand extends Command{

    /**
     * Constructs a FindCommand with the specified first word and full command line.
     *
     * @param firstWord the first word of the command.
     * @param line the full line of input provided by the user.
     */
    public FindCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    /**
     * Extracts the keyword from the user's command.
     *
     * @param line the full command input from which the keyword will be extracted.
     * @return the keyword to search for in the tasks.
     * @throws InvalidFormatException if the keyword is missing or the format is incorrect.
     */
    private String findKeyword(String line) throws InvalidFormatException {
        //Split the string by whitespace
        String[] words = line.trim().split("\\s+");

        //Check if there is a second word
        if (words.length == 2) {
            return words[1];
        } else{
            throw new InvalidFormatException("why u never give me 1 keyword...");
        }
    }

    /**
     * Checks if the given task's description contains the specified keyword.
     *
     * @param keyword the keyword to search for.
     * @param task the task whose description will be checked.
     * @return true if the task description contains the keyword, false otherwise.
     */
    private boolean checkMatch(String keyword, Task task){
        return task.getDescription().contains(keyword);
    }

    /**
     * Prints all tasks from the TaskList that contain the specified keyword in their descriptions.
     *
     * @param keyword the keyword to search for.
     * @param tasks the list of tasks to search through.
     */
    private void printMatch(String keyword, TaskList tasks){
        int matchCount = 0;
        System.out.print("here's all ur matches:");
        for(int i = 0; i < tasks.getCount(); i++){
            if(checkMatch(keyword, tasks.getTask(i))){
                System.out.print(System.lineSeparator() + (matchCount + 1) + "." + tasks.getTask(i));
                matchCount++;
            }
        }
    }

    /**
     * Executes the find command, searching for tasks that contain the specified keyword.
     * If the format is invalid, an error message is displayed.
     *
     * @param tasks the TaskList to search through.
     * @param ui the Ui object responsible for interacting with the user.
     * @param storage the Storage object responsible for handling data.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try{
            String keyword = findKeyword(this.line);
            printMatch(keyword, tasks);
        } catch (InvalidFormatException errorMessage) {
            System.out.print(errorMessage.getMessage());
        }
    }
}
