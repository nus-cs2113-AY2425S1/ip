public class TaskList {
    private final Task[] taskList;
    private int currentTaskIndex;

    public TaskList(){
        currentTaskIndex = 0;
        taskList = new Task[100];
    }

    private void addTask(Task newTask){
        taskList[currentTaskIndex++] = newTask;
        System.out.printf("Got it. I've added this task:%n  %s%nNow you have %d tasks in the list.%n", newTask, currentTaskIndex);
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

    public void markTask(int index){
        Task selectedTask = taskList[index];
        selectedTask.markDone();
        System.out.printf("Nice! I've marked this task as done:%n  %s%n", selectedTask);
    }

    public void unmarkTask(int index){
        Task selectedTask = taskList[index];
        selectedTask.unmarkDone();
        System.out.printf("OK, I've marked this task as not done yet:%n %s%n", selectedTask);
    }

    public void listTasks(){
        for (int index = 0; index < currentTaskIndex; index++){
            Task currentTask = taskList[index];
            System.out.printf("%d. %s%n", index + 1, currentTask);
        }
    }
}
