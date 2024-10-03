import java.util.ArrayList;

public class TaskList {
    protected static ArrayList<Task> tasks = new ArrayList<>();

    protected static void printTaskList() {
        System.out.println(UI.DIVIDER_LINE);
        for (int i = 1; i <= TaskList.tasks.size(); i++) {
            System.out.println(i + ". " + TaskList.tasks.get(i - 1).toString());
        }
        System.out.println(UI.DIVIDER_LINE);
    }

    protected static void printSearchResult(ArrayList<Task> tasks) {
        System.out.println(UI.DIVIDER_LINE);
        System.out.println("Here is a list of matching tasks:");
        for (Task task : tasks) {
            int i = TaskList.tasks.indexOf(task) + 1;
            System.out.println(i + ". " + task.toString());
        }
        System.out.println(UI.DIVIDER_LINE);
    }
}
