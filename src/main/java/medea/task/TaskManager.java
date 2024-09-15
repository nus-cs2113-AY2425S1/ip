package medea.task;
import java.util.ArrayList;

public class TaskManager {
    private final ArrayList<Task> taskList;

    public TaskManager(){
        taskList = new ArrayList<Task>();
    }

    public int getSize(){
        return taskList.size();
    }

    private void addTask(Task newTask){
        taskList.add(newTask);
        String formatString = "Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n";
        System.out.printf(formatString, newTask, taskList.size());
    }

    public void addTodo(String description){
        Todo newTodo = new Todo(description);
        addTask(newTodo);
    }

    public void addDeadline(String description, String deadlineDate){
        Deadline newDeadline = new Deadline(description, deadlineDate);
        addTask(newDeadline);
    }

    public void addEvent(String description, String eventStart, String eventEnd){
        Event newEvent = new Event(description, eventStart, eventEnd);
        addTask(newEvent);
    }

    public void updateTaskDoneStatus(int index, boolean isDone){
        Task selectedTask = taskList.get(index);
        String notification = isDone ? "Nice! I've marked this task as done:%n  %s%n"
                                     : "OK, I've marked this task as not done yet:%n %s%n";
        selectedTask.setIsDone(isDone);
        System.out.printf(notification, selectedTask);
    }

    public void listTasks(){
        for (int index = 0; index < taskList.size(); index++){
            Task currentTask = taskList.get(index);
            System.out.printf("%d. %s%n", index + 1, currentTask);
        }
    }

    public void deleteTask(int index){
        Task deletedTask = taskList.get(index);
        taskList.remove(index);
        String formatString = "Got it. I've deleted this task:%n  %s%nNow you have %d tasks in the list.%n";
        System.out.printf(formatString, deletedTask, taskList.size());
    }
}
