public class TaskList {
    private final Task[] taskList;
    private int currentTask;

    public TaskList(){
        currentTask = 0;
        taskList = new Task[100];
    }

    public void listTasks(){
        for (int i = 0; i < currentTask; i++){
            Task currentTask = taskList[i];
            String taskContents = currentTask.getContents();
            String taskStatus = currentTask.getStatusIcon();
            System.out.printf("%d.[%s] %s%n", i + 1, taskStatus, taskContents);
        }
    }

    public void addTask(String contents){
        System.out.printf("Added: %s%n", contents);
        taskList[currentTask++] = new Task(contents);
    }

    public void markTask(int index){
        taskList[index].markDone();
        String taskContents = taskList[index].getContents();
        System.out.printf("Nice! I've marked this task as done: %n [X] %s%n", taskContents);
    }

    public void unmarkTask(int index){
        taskList[index].unmarkDone();
        String taskContents = taskList[index].getContents();
        System.out.printf("OK, I've marked this task as not done yet: %n [ ] %s%n", taskContents);
    }
}
