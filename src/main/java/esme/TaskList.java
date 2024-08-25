package esme;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public String getMarkStatus (boolean mark) {
        return mark? "[X] " : "[ ] ";
    }

    public void printTaskList () {
        System.out.println("\tBy the light of the moon, these are the tasks that guide your path:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("\t" + (i + 1)  + "." + getMarkStatus(task.isCompleted()) + task.getName());
        }
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

}
