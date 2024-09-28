package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import java.util.InputMismatchException;

public class DeleteCommand extends Command {

    public DeleteCommand(String instruction) {
        super(instruction);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException {
        try {
            String[] splitInput = instruction.split(" ");

            if (splitInput.length != 1) {
                throw new InputMismatchException();
            }

            int indexNumToDelete = Integer.parseInt(instruction);
            ui.showDelete(taskList, indexNumToDelete-1);
            taskList.removeTask(indexNumToDelete-1);
            ui.showTaskSize(taskList.getSize());
            storage.write(taskList);

        } catch (NumberFormatException | InputMismatchException e) {
            throw new AlyException("Just type 1 number bro... So difficult meh...");
        } catch (IndexOutOfBoundsException e) {
            throw new AlyException("Index out of bounds lah bro");
        } catch (Exception e) {
            throw new AlyException("Something went wrong!");
        }
    }
}
