package taskpackage;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void deleteTask(int taskIndex){
        String taskString = this.tasks.get(taskIndex).checkboxString();
        this.tasks.remove(taskIndex);
        System.out.println("Ay Caramba, Task deleted: " + taskString);
    }

    // Static method to delete the latest task in case of errors
    public void deleteLatestTask(){
        this.tasks.remove(tasks.size() - 1); // Remove the latest task from the array
    }

    public void markLatestTask(){
        this.tasks.get(tasks.size() - 1).isDone = true;
    }

    public int size() {
        return this.tasks.size();
    }

    public String dataFileEntry(int index) {
        return (this.tasks.get(index).inputString + " /isdone " + this.tasks.get(index).isDone + "\n");
    }

    // Static method to mark a task as done by index
    public void mark(int taskIndex){
        tasks.get(taskIndex).isDone = true; // Mark the task as done
        System.out.println("Fantastica!!!! I marked it:"); // Success message in Spanish
        System.out.println(tasks.get(taskIndex).checkboxString()); // Display the updated task
    }

    // Static method to unmark a task as undone by index
    public void unmark(int taskIndex){
        tasks.get(taskIndex).isDone = false; // Unmark the task as not done
        System.out.println("Ay Caramba, I unmarked it:"); // Message indicating task was unmarked
        System.out.println(tasks.get(taskIndex).checkboxString()); // Display the updated task
    }

    // Static method to print all tasks in the list
    public void printTasksList(){
        if (tasks.isEmpty()){
            System.out.println("Por Favor? Nothing Here"); // Message when there are no tasks
        } else {
            System.out.println("Si compinche, your tasks:"); // Message when displaying tasks
            for (int i = 0; i < tasks.size(); i++){
                // Print each task with its index and checkbox format
                System.out.println((i+1) + "." + tasks.get(i).checkboxString());
            }
        }
    }

}
