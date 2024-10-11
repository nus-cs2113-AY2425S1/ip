import java.util.List;
import java.util.Scanner;

public class UserInterface implements SampleStrings {
    private Fenix fenix;
    private Scanner scanner;

    public UserInterface(Fenix fenix) {
        this.fenix = fenix;
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        System.out.println(GREETING);
        System.out.println(SERVICE_PROMPT);
    }

    public String getUserInput() {
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        return scanner.nextLine();
    }

    public void showAllTasks(List<Task> taskArrayList) {
        String space = "\t";
        for (Task task : taskArrayList) {
            String index = (taskArrayList.indexOf(task) + 1) + ". ";
            System.out.println(space + index + task);
        }
    }

    public void showFenixModification(String[] modification, Task task) {
        if (task == null) {
            return;
        }
        String modificationMessage = modification[0];
        String modificationType = modification[1];
        System.out.println(modificationMessage);
        printFenixModification(modificationType, task);
    }

    private void printFenixModification(String modificationType, Task task) {
        int taskIndex = fenix.indexOfTask(task) + 1;
        // taskIndex == 0 when task has been deleted
        String taskNumber = (taskIndex == 0 ? "" : taskIndex + ". ");
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + modificationType + taskNumber + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        int numberOfUnfinishedTasks = fenix.getNumberOfUnfinishedTasks();
        System.out.println("You now have " + fenix.getNumberOfUnfinishedTasks() + " tasks awaiting your attention.");
        if (numberOfUnfinishedTasks == 0) {
            printAllTasksDone();
        }
    }

    private void printAllTasksDone() {
        System.out.println(ALL_TASKS_COMPLETED);
    }

    public void printMatchedTasks() {
        System.out.println(MATCHED_TASKS);
    }

    public void requestForCommand() {
        System.out.println(COMMAND_REQUEST);
    }

    public void requestForValidCommand() {
        System.out.println(VALID_COMMAND_REQUEST);
    }

    public void requestForTask() {
        System.out.println(TASK_REQUEST);
    }

    public void requestForValidTask() {
        System.out.println(VALID_TASK_REQUEST);
    }

    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }
}
