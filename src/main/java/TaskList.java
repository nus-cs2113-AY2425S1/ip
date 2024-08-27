public class TaskList {
    private int taskNumber;
    private String[] tasks;

    //Constructor for TaskList
    public TaskList(){
        taskNumber = 0;

        //Assume there will be no more than 100 tasks
        tasks = new String[100];
    }

    //Storage of new task
    public void storeTask(String task){
        if (taskNumber >= tasks.length){
            return;
        }
        tasks[taskNumber += 1] = task;
    }

    //Displaying of all task in array
    public String displayTasks() {
        String taskList = "";
        for (int i = 0; i < taskNumber; i += 1) {
            taskList += (i + 1) + ". " + tasks[i] + "\n";
        }
        return taskList;
    }
}
