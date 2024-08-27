public class TaskList {
    private int taskNumber;
    private String[] tasks;

    //Constructor for TaskList
    public TaskList(){
        taskNumber = 0;

        //Assume there will be no more than 100 tasks
        tasks = new String[100];
    }
}
