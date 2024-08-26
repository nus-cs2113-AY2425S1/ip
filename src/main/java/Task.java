public class Task {
    private String task;
    private int id;
    private static int totalTasks = 0;

    public Task(String task) {
        setToDo(task);
        totalTasks += 1;
        setId(totalTasks);
    }

    public String getTask() {
        return task;
    }

    public void setToDo(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getTotalTasks() {
        return totalTasks;
    }
}