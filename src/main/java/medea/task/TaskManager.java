package medea.task;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList;

    public TaskManager(){

        taskList = new ArrayList<Task>();
    }

    public int getSize() {
        return taskList.size();
    }

    public String toCSVString(){
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < getSize(); index++){
            Task currentTask = taskList.get(index);

            if (index != 0) output.append(System.lineSeparator());
            output.append(currentTask.toCSV());
        }
        return output.toString();
    }

    public Task addTodo(String description){
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);
        return newTodo;
    }

    public Task addDeadline(String description, String deadlineDate){
        Deadline newDeadline = new Deadline(description, deadlineDate);
        taskList.add(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String description, String eventStart, String eventEnd){
        Event newEvent = new Event(description, eventStart, eventEnd);
        taskList.add(newEvent);
        return newEvent;
    }

    public Task updateTaskDoneStatus(int index, boolean isDone){
        Task selectedTask = taskList.get(index);
        selectedTask.setIsDone(isDone);
        return selectedTask;
    }

    public String getTaskListString(){
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < getSize(); index++){
            Task currentTask = taskList.get(index);
            String currentTaskString = String.format("%d. %s%n", index + 1, currentTask);
            output.append(currentTaskString);
        }
        return output.toString();
    }

    public Task deleteTask(int index){
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        return deletedTask;
    }
}
