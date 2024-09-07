package Taylor.task;

import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public int size(){
        return tasks.size();
    }

    public Task get(int index){
        return tasks.get(index);
    }

    public void remove(int index){
        if(index < tasks.size()&&index >= 0){
            tasks.remove(index);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void markTask(int index) {
        if (index < tasks.size() && index >= 0) {
            tasks.get(index).setCompleted(true);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    public void unmarkTask(int index) {
        if (index < tasks.size() && index >= 0) {
            tasks.get(index).setCompleted(false);
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

}
