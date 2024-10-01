import java.util.ArrayList;
import java.util.Objects;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void printList() {
        for (int i = 1; i <= taskList.size(); i++) {
            System.out.print(i + ".");
            taskList.get(i-1).printTask();
        }
        System.out.println("You have " + taskList.size() + " tasks in the list.");
    }

    public void markTask(String[] lineInputArr) {
        int index = InputParser.parseMark(lineInputArr, this);
        if (index < 0) {
            System.out.println("That is not a valid index.");
        } else {
            taskList.get(index).mark();
        }
    }

    public void addTask(Task task, String lineInput) {
        taskList.add(task);
        System.out.println("added: " + lineInput);
    }

    public void deleteTask(String[] lineInputArr) {
        try {
            Task taskToRemove = taskList.get(Integer.parseInt(lineInputArr[1]) - 1);
            System.out.println("Ok. I've removed this task: ");
            taskToRemove.printTask();
            taskList.remove(Integer.parseInt(lineInputArr[1])-1);
            System.out.println("You now have " + taskList.size() + " tasks in the list.");
        } catch (NumberFormatException e) {
            System.out.println("That is not a valid number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("That is not a valid index.");
        }
    }
}
