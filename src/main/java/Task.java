public class Task {
    protected String description;
    protected boolean isDone;
    protected int taskType; // 0 for ToDos, 1 for Deadlines, 2 for Events
    protected String startTime;
    protected String endTime;

    public Task(String description, int taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    public Task(String description, int taskType, String endTime) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.endTime = endTime;
    }

    public Task(String description, int taskType, String startTime, String endTime) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Task(String description) {
        this.description = description;
    }

    public String getTaskTypeIcon() {
        return switch (taskType) {
            case 0 -> "[T]";
            case 1 -> "[D]";
            case 2 -> "[E]";
            default -> "[ ]";
        };
    }

    public String getStatusIcon() {
        return (isDone ? "[X] " : "[ ] ");
    }

    public String getTaskDescription() {
        if (this.taskType == 1) {
            return getTaskTypeIcon() + getStatusIcon() + description
                    + " (by: " + endTime + ")";
        } else if (this.taskType == 2) {
            return getTaskTypeIcon() + getStatusIcon() + description
                    + " (from: " + startTime + " to: " + endTime + ")";
        }
        return getTaskTypeIcon() + getStatusIcon() + description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n" + getTaskDescription());

        System.out.println("____________________________________________________________");
    }

    public void markAsUndone() {
        this.isDone = false;
        System.out.println("____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n" + getTaskDescription());

        System.out.println("____________________________________________________________");
    }

    @Override
    public String toString() {
        return getTaskDescription();
    }
}