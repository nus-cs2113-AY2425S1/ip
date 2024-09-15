package jeff.task;

public class Task {

    private static final int MAX_TASK = 100;

    protected String description;
    private boolean isDone;
    private static int count = 0; //Class level attribute, number of tasks
    private static Task[] list = new Task[MAX_TASK];

    Task(String description) { //Constructor
        this.description = description;
        this.isDone = false;
        list[count] = this;
        count++;
    }

    public String getStatusIcon() { //Returns X or " " depending on completion status
        return (isDone ? "X" : " "); // mark done task with X
    }

    public int getStatusNumber(){ //Returns 1 if task is marked as finished
        return (isDone ? 1 : 0);
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public static int getCount() { //Class level method
        return count;
    }

    public static Task[] getList() {
        return list;
    }

    public String fileContent(){
        return " | " + getStatusNumber() + " | " + description;
    }

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }
}
