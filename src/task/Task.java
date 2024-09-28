package task;

/**
 * The Task class represents a general task with a name and completion status.
 * It is designed to be extended by specific task types (e.g., Todo, Deadline, Event).
 */
public abstract class Task{
    private String taskName;
    private boolean isMarked;

    public Task() {
        this.taskName = "";
        this.isMarked = false;
    }
    public Task(String taskName, boolean isMarked) {
        this.taskName = taskName;
        this.isMarked = isMarked;
    }
    public String getTaskName() {
        return taskName;
    }
    public boolean isMarked() {
        return isMarked;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    public void setMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    /**
     * Returns a string representation of the task, indicating whether it is completed or not, followed by the task name.
     *
     * @return A string that shows the task's completion status and name.
     */
    @Override
    public String toString(){
        if (isMarked){
            return "[X] " + taskName;
        }
        else{
            return "[ ] " + taskName;
        }
    }
    public abstract String toFileFormat();
}
