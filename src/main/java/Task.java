public class Task {
    static Emoji emoji = new Emoji();
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
        try {
            if (taskDescription.startsWith("deadline")) {
                taskDescription = taskDescription.substring(9);
                String by = taskDescription.substring(taskDescription.indexOf("BY") + 3);
                String taskName = taskDescription.substring(0, taskDescription.indexOf("BY"));
                return new Deadline(taskName, by);

            } else if (taskDescription.startsWith("event")) {
                taskDescription = taskDescription.substring(6);
                String from = taskDescription.substring(taskDescription.indexOf("FROM") + 5, taskDescription.indexOf("TO"));
                String to = taskDescription.substring(taskDescription.indexOf("TO") + 3);
                String taskName = taskDescription.substring(0, taskDescription.indexOf("FROM"));
                return new Event(taskName, from, to);

            } else {
                taskDescription = taskDescription.substring(5);
                return new Todo(taskDescription);
            }
        }  catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: You are missing task arguments" + emoji.getExclamationMarkEmoji());
        }
        return null;
    }
}
