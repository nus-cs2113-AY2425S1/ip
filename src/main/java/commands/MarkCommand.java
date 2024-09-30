package commands;

import exceptions.XiaoMeException;
import storage.Storage;
import task.Task;

import java.util.ArrayList;
import java.util.Objects;

public class MarkCommand extends Command {

    String userInput;

    public MarkCommand(String userInput) {
        this.userInput = userInput;
        this.isExit = false;
    }

    @Override
    public String execute(ArrayList<Task> tasks) throws XiaoMeException  {
        try {
            String string;
            // user input is mark/unmark x: mark corresponding task as done or undone
            String[] markWords = userInput.split(" ");
            int index = Integer.parseInt(markWords[1]) - 1;

            if (Objects.equals(markWords[0], "mark")) {
                tasks.get(index).setDone(true);
                Storage.saveFile(tasks);

                return "\tNice! I've marked this task as done:\n"
                        + "\t\t" + tasks.get(index);
            } else {
                tasks.get(index).setDone(false);
                Storage.saveFile(tasks);

                return "\tOK, I've marked this task as not done yet:\n"
                        + "\t\t" + tasks.get(index);
            }


        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new XiaoMeException("\tHEY mark/unmark should be followed by a valid integer");
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            throw new XiaoMeException("\tInteger provided does not have a corresponding task");
        }
    }
}
