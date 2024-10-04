package cuboyd;

import cuboyd.tasks.TaskList;

/**
 * Entrypoint into the program
 */
public class Cuboyd {
    // Main Loop ///////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * Runs the Cuboyd program
     * @param args Arguments - Unused
     */
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("./savedata.txt");
        try{
            storage.load(taskList);
        } catch (CuboydException cuboydException) {
            System.out.println(cuboydException.getMessage());
        }
        Ui ui = new Ui();
        ui.run(taskList, storage);
    }
}