package medea;
import medea.exceptions.MedeaException;
import medea.task.*;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList(){
        tasks = new ArrayList<Task>();
    }

    public TaskList(String csvString){
        tasks = new ArrayList<Task>();
        loadTasks(csvString);
    }

    public int getSize() {
        return tasks.size();
    }

    public String toString(){
        int listSize = getSize();

        if (listSize == 0){
            return "No items currently in your task list.\n";
        }

        StringBuilder output = new StringBuilder();
        for (int index = 0; index < listSize; index++){
            Task currentTask = tasks.get(index);
            boolean isLastIndex = index == listSize - 1;
            String currentTaskString = String.format("%d. %s" + (isLastIndex ? "":"%n"), index + 1, currentTask);
            output.append(currentTaskString);
        }
        return output.toString();
    }

    public String toCSVString(){
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < getSize(); index++){
            Task currentTask = tasks.get(index);
            if (index != 0) output.append(System.lineSeparator());
            output.append(currentTask.toCSV());
        }
        return output.toString();
    }

    public void loadTasks(String csvString){
        String[] taskStrings = csvString.split("\n");
        for(String taskString : taskStrings){
            String[] taskArguments = taskString.split(",");
            String taskType = taskArguments[0];
            switch(taskType){
                case "T":
                    addTodo(taskArguments[1]);
                    break;
                case "D":
                    addDeadline(taskArguments[1], taskArguments[2]);
                    break;
                case "E":
                    addEvent(taskArguments[1], taskArguments[2], taskArguments[3]);
                    break;
                default:
                    throw new MedeaException("Corrupted task data.");
            }
        }
    }

    public String addTodo(String description){
        Todo newTodo = new Todo(description);
        tasks.add(newTodo);
        return newTodo.toString();
    }

    public String addDeadline(String description, String deadlineDate){
        Deadline newDeadline = new Deadline(description, deadlineDate);
        tasks.add(newDeadline);
        return newDeadline.toString();
    }

    public String addEvent(String description, String eventStart, String eventEnd){
        Event newEvent = new Event(description, eventStart, eventEnd);
        tasks.add(newEvent);
        return newEvent.toString();
    }

    public String updateTaskDoneStatus(int index, boolean isDone){
        if (index < 0 || index > this.getSize()){
            throw new MedeaException(isDone ? "Mark" : "Unmark" + " Task failed.Index out of range.");
        }

        Task selectedTask = tasks.get(index);
        selectedTask.setIsDone(isDone);
        return selectedTask.toString();
    }

    public String deleteTask(int index){
        if (index < 0 || index > this.getSize()){
            throw new MedeaException("Delete Task failed.Index out of range.");
        }

        Task selectedTask = tasks.get(index);
        tasks.remove(index);
        return selectedTask.toString();
    }
}
