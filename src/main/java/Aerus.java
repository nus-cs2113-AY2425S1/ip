import java.util.ArrayList;

/**
 * The main entry point for the application.
 * Perform base operations such as activating InputHandler and DataManager,
 * start and exit the program.
 */

public class Aerus {

    public static void main(String[] args) {

        DataManager dataManager = new DataManager("./data/data.txt");
        TaskList.tasks = dataManager.loadData();
        if (TaskList.tasks == null) {
            TaskList.tasks = new ArrayList<>();
        }

        UI.greetUser();
        InputHandler.execute();

        dataManager.saveData();
        UI.exitProgram();
    }

}
