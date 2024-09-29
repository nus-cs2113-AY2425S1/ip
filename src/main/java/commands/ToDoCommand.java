package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Todo;

public class ToDoCommand extends Command {

    String userInput;
    boolean isExit;

    public ToDoCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute() throws XiaoMeException {
        try {
            // user is creating a new  Task.Todo
            String string = userInput.replace("todo", "").trim();

            if (string.isEmpty()) {
                throw new IllegalArgumentException();
            }

            tasks.add(new Todo(string)); // add task to storage

            Storage.saveFile(tasks);

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.get(tasks.size() - 1) + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create a todo:
                    \tUse 'todo <task>'
                    """);
        }
    }

}
