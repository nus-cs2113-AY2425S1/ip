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
        System.out.println(modification[0]);
        printFenixModification(modification[1], task);
    }

    private void printFenixModification(String modification, Task task) {
        int taskIndex = fenix.indexOfTask(task) + 1;
        String taskNumber = (taskIndex == 0 ? "" : taskIndex + ". ");
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("\t\t" + modification + taskNumber + task);
        System.out.println(HORIZONTAL_LINE_FENIX_MODIFICATION);
        System.out.println("You now have " + fenix.getSize() + " tasks awaiting your attention.");
    }

    public void requestForCommand() {
        System.out.println("Please provide a command");
    }

    public void requestForValidCommand() {
        System.out.println("Please provide a valid command");
    }

    public void requestForTask() {
        System.out.println("Please provide a task");
    }

    public void requestForValidTask() {
        System.out.println("Please provide a valid task number");
    }

    public void bidFarewell() {
        System.out.println(FAREWELL);
        System.out.println(HORIZONTAL_LINE_USER_COMMAND);
        scanner.close();
    }
}
