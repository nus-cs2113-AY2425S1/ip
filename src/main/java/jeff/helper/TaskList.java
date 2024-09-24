package jeff.helper;

import jeff.task.Task;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.taskList = tasks;
    }

    public Task getTask(int index){
        return taskList.get(index);
    }

    public int getCount(){
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        taskList.remove(index);
    }
}
