package tasklist;

import task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }
    public TaskList(){
        list = new ArrayList<>();
    }

    public ArrayList<Task> getList() {
        return list;
    }
    public void setList(ArrayList<Task> list) {
        this.list = list;
    }

    public int getListSize() {
        return list.size();
    }
    public void addTask(Task task){
        list.add(task);
    }
    public void deleteTask(int taskNumber){
        list.remove(taskNumber - 1);
    }
    public void markTask(int taskNumber, boolean isMarked){
        list.get(taskNumber - 1).setMarked(isMarked);
    }
}
