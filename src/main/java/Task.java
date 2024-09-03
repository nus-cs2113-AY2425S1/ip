public class Task {
    private String taskName;
    private Boolean isDone;

    public Task(String taskName) {
        setTaskName(taskName);
        isDone = false;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(Boolean isDone) {
        this.isDone = isDone;
    }

    public String getTaskTag() {
        return "";
    }

    public static Task parseTaskString(String taskDescription) {
        if (taskDescription.indexOf("BY") > 0) {
            String by = taskDescription.substring(taskDescription.indexOf("BY") + 2);
            String taskName = taskDescription.substring(0, taskDescription.indexOf("BY"));
            return new Deadline(taskName, by);

        } else if (taskDescription.indexOf("FROM") > 0) {
            String from = taskDescription.substring(taskDescription.indexOf("FROM") + 4, taskDescription.indexOf("TO"));
            String to = taskDescription.substring(taskDescription.indexOf("TO") + 2);
            String taskName = taskDescription.substring(0, taskDescription.indexOf("FROM"));
            return new Event(taskName, from, to);

        } else {
            return new Todo(taskDescription);
        }
    }
}
