package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import java.util.InputMismatchException;

public class MarkCommand extends Command {

    private final String firstWord;

    public MarkCommand(String firstWord, String instruction) {
        super(instruction);
        this.firstWord = firstWord;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            String[] splitInput = instruction.split(" ");

            if (splitInput.length != 1) {
                throw new InputMismatchException();
            }

            int indexNumToToggle = Integer.parseInt(instruction);
            markAsDone(taskList, ui, indexNumToToggle - 1);
            storage.write(taskList);

        } catch (NumberFormatException | InputMismatchException e) {
            throw new AlyException("Just type 1 number bro... So difficult meh...");
        } catch (AlyException e) {
            ui.showError(e.getMessage());
        }
    }

    private void markAsDone(TaskList taskList, Ui ui, int indexNumToToggle) throws AlyException {
        try {
            if (firstWord.equals("mark")) {
                taskList.toggleStatus(indexNumToToggle, true);
                ui.showStatusChange(taskList, indexNumToToggle, firstWord);
            } else if (firstWord.equals("unmark")) {
                taskList.toggleStatus(indexNumToToggle, false);
                ui.showStatusChange(taskList, indexNumToToggle, firstWord);
            } else {
                throw new AlyException("Something went wrong!");
            }
        } catch (IndexOutOfBoundsException e) {
            throw new AlyException("Index out of bounds lah bro");
        }
    }
}
