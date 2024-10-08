package nus.edu.rizzler.command;

import nus.edu.rizzler.manager.Storage;
import nus.edu.rizzler.manager.TaskList;
import nus.edu.rizzler.ui.Emoji;
import nus.edu.rizzler.ui.UserInterface;

public class TodoCommand extends Command {
    private Emoji emoji = new Emoji();
    public static final String COMMAND_WORD = "todo";

    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    public String addTask(TaskList tasks) {
        Boolean isDone = false;
        return tasks.addTodo(taskName, isDone);
    }

    public void execute(TaskList tasks, UserInterface userInterface, Storage storage) {
        String taskString = addTask(tasks);
        int taskSize = tasks.getSize();

        String message = String.format("Let's make it happen!%s %s I've added this task:%n  %s%n" +
                "Now you have %d tasks in the list.", emoji.getRockstarHandEmoji(), emoji.getFireEmoji(), taskString, taskSize);
        userInterface.displayMessage(message);
    }
}
