public class Task {
    private boolean done;
    private final String taskInfo;

    public Task(String taskInfo) {
        this.taskInfo = taskInfo;
        done = false;
    }

    public String getInfo() {
        return taskInfo;
    }

    public void markAsDone() {
        done = true;
    }

    public void markAsNotDone() {
        done = false;
    }

    /**
     * Prints the task in a format of [done] taskInfo
     * Useful for subclasses to build on top of using override
     */
    public void printTask() {
        System.out.printf("[%s] %s", done ? "X" : " ", taskInfo);
    }

    /**
     * Converts the task into a save format compatible type i.e. done | taskInfo
     * Useful for subclasses to build on top of using override
     * @return The converted string
     */
    public String convertToSaveFormat() {
        return (done ? "1" : "0") + " | " + taskInfo;

    }

}
