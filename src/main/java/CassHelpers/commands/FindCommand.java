package CassHelpers.commands;

import CassHelpers.exceptions.NoTaskContainsMatchingPromptException;
import CassHelpers.types.Task;
import CassHelpers.util.TaskList;

import java.util.ArrayList;

public class FindCommand implements Command {
    private final ArrayList<Task> taskList;
    private final String input;
    private final int findOffset = 4;

    public FindCommand(TaskList tasks,String input){
        this.taskList = tasks.getTaskList();
        this.input = input;
    }

    @Override
    public void execute() {
        String prompt = input.substring(findOffset).trim();

        if(prompt.isEmpty()){
            throw new NoTaskContainsMatchingPromptException("Sorry! Please enter a prompt to be searched, use help to learn more");
        }

        String result = "Here are the matching tasks in your list: \n";
        int count = 0;

        for(Task task : taskList){
            String taskDescription = task.getTaskName();
            if(taskDescription.contains(prompt)){
                count += 1;
                result += count + ". " + task.toString() + " \n";
            }
        }

        if(count == 0){
            throw new NoTaskContainsMatchingPromptException("Sorry! We couldn't find any task that contains "+prompt);
        }
        System.out.println(result);
    }
}
