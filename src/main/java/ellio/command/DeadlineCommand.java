package ellio.command;

import ellio.BotText;
import ellio.EllioExceptions;
import ellio.storage.Storage;
import ellio.task.Deadline;
import ellio.task.TaskList;
import ellio.task.Todo;
import ellio.ui.Ui;


public class DeadlineCommand extends Command {

    public static final int DEADLINE_INPUT_SPACING = 9;

    public DeadlineCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Adds a Deadline Type Task into the list
     * @param tasks Input by user
     * @param ui Ui interface reference
     * @param storage Reference for Storage functions
     */
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            Deadline newDeadline = formatDeadline(inputCommand);
            tasks.addTask(newDeadline);
            tasks.addNumberTask();
            ui.showAddTaskMessage(newDeadline.getTaskInfo(), tasks.getNumberTask());
            storage.saveNewTask(newDeadline.getSaveFileTask());
        } catch (EllioExceptions e){
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(BotText.LINE_BORDER + BotText.MESSAGE_MISSING_DEADLINE_TIME + BotText.LINE_BORDER);
        }
    }

    /**
     * Splits the user input into description, and the deadline of the task.
     * Returns a Deadline Object to be saved into the list.
     * @param line Input from user
     * @return A deadline object to be saved into the list
     * @throws EllioExceptions Handles any error input by the user
     */
    private static Deadline formatDeadline(String line) throws EllioExceptions {
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");

        // Extract the substrings based on the slash positions
        String description = line.substring(DEADLINE_INPUT_SPACING, firstSlashIndex).trim();
        String deadline = line.substring(firstSlashIndex + 1).trim();

        if(!deadline.startsWith("by")){
            throw new EllioExceptions.WrongDeadlineFormatTimeException();
        }
        //add Semicolon behind by
        deadline = deadline.replace("by", "by:");

        return new Deadline(description, "0", deadline);
    }
}
