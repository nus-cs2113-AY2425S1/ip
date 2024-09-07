package Taylor.task;

import Taylor.command.TaylorException;

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

    public String write(){
        StringBuilder sb = new StringBuilder();
        for(Task task : tasks){
            sb.append(task.write()).append(System.lineSeparator());
        }
        return sb.toString();
    }

    public void read(String file) throws TaylorException {
        String[] lines = file.split("\n");
        for(String line : lines){
            String[] parts = line.split(" \\| ");
            String taskType = parts[0].trim();
            boolean isCompleted = Boolean.parseBoolean(parts[1].trim());
            String description = parts[2].trim();

            switch(taskType){
                case "T" -> {
                    this.add(new Todo(description, isCompleted));
                }
                case "D" -> {
                    this.add(new Deadline(description, isCompleted,parts[3].trim()));
                }
                case "E" -> {
                    String[] time = parts[3].split("-");
                    String from = time[0].trim();
                    String to = time[1].trim();
                    this.add(new Event(description, isCompleted, from, to));
                }
                default -> throw new TaylorException("Unknown tasks in file");
            }
        }
    }

}
