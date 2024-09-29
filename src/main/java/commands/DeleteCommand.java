package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Task;

public class DeleteCommand extends Command {

    String userInput;
    boolean isExit;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute() throws XiaoMeException {
        try {
            String[] markWords = userInput.split(" ");
            int index = Integer.parseInt(markWords[1]) - 1;
            Task temp = tasks.get(index);

            tasks.remove(index);

            Storage.saveFile(tasks);

            return "\tNoted. I've removed this task:\n"
                           + "\t\t" + temp + "\n"
                           + "\tNow you have " + tasks.size() + " tasks in the list.";

        } catch (NumberFormatException e) {
            throw new XiaoMeException("""
                    \tHEY delete should be followed by a valid integer
                    """);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new XiaoMeException("""
                    \tInteger provided does not have a corresponding task
                    """);
        }
    }
}
