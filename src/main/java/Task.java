public class Task {
    protected String description;
    protected boolean isDone;
    private static int count = 0; //Class level attribute, number of tasks
    private static Task[] list = new Task[100];

    Task(String description) { //Constructor
        this.description = description;
        this.isDone = false;
        list[count] = this;
        count++;
    }

    public String getStatusIcon() { //Returns X or " " depending on completion status
        return (isDone ? "X" : " "); // mark done task with X
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

    @Override
    public String toString() {
        return  "[" + getStatusIcon() + "] " + description;
    }
}
