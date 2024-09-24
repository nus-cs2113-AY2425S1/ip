package jeff.command;

import jeff.exception.InvalidFormatException;
import jeff.helper.Storage;
import jeff.helper.TaskList;
import jeff.helper.Ui;
import jeff.task.Task;

import java.io.IOException;

public class FindCommand extends Command{

    public FindCommand(String firstWord, String line) {
        super(firstWord, line);
    }

    private String findKeyword(String line) throws InvalidFormatException {
        //Split the string by whitespace
        String[] words = line.split("\\s+");

        //Check if there is a second word
        if (words.length == 2) {
            return words[1];
        } else{
            throw new InvalidFormatException("why u never give me 1 keyword...");
        }
    }

    private boolean checkMatch(String keyword, Task task){
        return task.getDescription().contains(keyword);
    }

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
