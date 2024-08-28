public class TaskList {
    private int taskNumber;
    private TaskMarker[] tasks;

    //Constructor for TaskList
    public TaskList(){
        taskNumber = 0;

        //Assume there will be no more than 100 tasks
        tasks = new TaskMarker[100];
    }

    //Storage of new task
    public void storeTask(String task){
        if (taskNumber >= tasks.length){
            return;
        }
        tasks[taskNumber] = new TaskMarker(task);
        taskNumber += 1;
    }

    //Displaying of all task in array
    public String displayTasks() {
        String taskList = "";
        for (int i = 0; i < taskNumber; i += 1) {
            taskList += (i + 1) + ". " + tasks[i] + "\n";
        }
        return taskList.toString();
    }

    //Method for marking tasks
    public String markTaskAsDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        TaskMarker taskmarker = tasks[index - 1];
        taskmarker.setAsDone();
        return "Nice! I've marked this task as done:\n" + taskmarker;
    }

    //Method for unmarking tasks
    public String markTaskAsNotDone(int index) {
        if (index < 1 || index > taskNumber) {
            return "Invalid task number.";
        }
        TaskMarker taskmarker = tasks[index - 1];
        taskmarker.setAsUndone();
        return "OK, I've marked this task as not done yet:\n" + taskmarker;
    }
}
