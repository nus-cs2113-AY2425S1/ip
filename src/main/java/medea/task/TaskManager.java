package medea.task;

public class TaskManager {
    private final Task[] taskList;
    private int currentTaskIndex;

    public TaskManager(){
        currentTaskIndex = 0;
        taskList = new Task[100];
    }

    public String toCSVString(){
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < currentTaskIndex; index++){
            Task currentTask = taskList[index];

            if (index != 0) output.append(System.lineSeparator());
            output.append(currentTask.toCSV());
        }
        return output.toString();
    }

    public int getCurrentTaskIndex(){
        return currentTaskIndex;
    }

    private void addTask(Task newTask){
        taskList[currentTaskIndex++] = newTask;
    }

    public Task addTodo(String description){
        Todo newTodo = new Todo(description);
        addTask(newTodo);
        return newTodo;
    }

    public Task addDeadline(String description, String deadlineDate){
        Deadline newDeadline = new Deadline(description, deadlineDate);
        addTask(newDeadline);
        return newDeadline;
    }

    public Task addEvent(String description, String eventStart, String eventEnd){
        Event newEvent = new Event(description, eventStart, eventEnd);
        addTask(newEvent);
        return newEvent;
    }

    public Task updateTaskDoneStatus(int index, boolean isDone){
        Task selectedTask = taskList[index];
        selectedTask.setIsDone(isDone);
        return selectedTask;
    }

    public String getTaskListString(){
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < currentTaskIndex; index++){
            Task currentTask = taskList[index];
            String currentTaskString = String.format("%d. %s%n", index + 1, currentTask);
            output.append(currentTaskString);
        }
        return output.toString();
    }
}
