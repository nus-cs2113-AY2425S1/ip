package aegis;

import aegis.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> findTasksByKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void displayTasks() {
        System.out.println();
        System.out.println(" I could do much more than checking your list, but, if you wish:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("    %d. %s%n", i + 1, tasks.get(i));
        }
        System.out.println();
    }
}
