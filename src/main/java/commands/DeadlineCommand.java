package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Deadline;
import task.Task;

import java.util.ArrayList;

public class DeadlineCommand extends Command {
    String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException {
        try {
            // user is creating a new deadline
            String[] lineWords = userInput.split("/by");
            if (lineWords.length != 2) {
                throw new IllegalArgumentException();
            }

            tasks.add(new Deadline(lineWords[0].replace("deadline", "").trim(), lineWords[1].trim())); // add task to storage

            Storage.saveFile(tasks);

            return "\tGot it. I've added this task:\n"
                    + "\t\t" + tasks.get(tasks.size() - 1) + "\n"
                    + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (Exception e) {
            throw new XiaoMeException("""
                    \tInvalid format to create a deadline:
                    \tUse 'deadline <description> /by <due_date>'""");
        }
    }
}
