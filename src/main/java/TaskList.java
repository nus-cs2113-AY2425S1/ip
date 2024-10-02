import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void addTask(Task newTask) {

        System.out.println("I've added this to your list: ");
        newTask.printTask();
        list.add(newTask);
    }

    public void printTaskList() {
        System.out.println("Here is your list of tasks:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%d. ", i);
            list.get(i - 1).printTask();
        }
    }

    public void attemptToMarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsDone();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToUnmarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsNotDone();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToDelete(String listIndex){
        try {
            int index = Integer.parseInt(listIndex);
            list.remove(index-1);
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }
}
