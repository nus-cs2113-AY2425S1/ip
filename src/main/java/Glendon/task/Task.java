package Glendon.task;

public class Task {
    public String taskName;
    public boolean isCompleted;

    /**
     * Constructs the task with the given name and default completion status of the task
     * is always set to false
     *
     * @param taskName the name of the task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    /**
     * Return the completion status of task
     *
     * @return the completion status of the task
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the completion status of the task, true if completed, false if not completed
     *
     * @param isCompleted, the new completion status of the task
     */
    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the completion status, [X] represent completion of
     * task, task info, completion status and date (if any)
     *
     * @return the task info, completion status and date (if any)
     */
    @Override
    public String toString() {
        String answer;
        String marked = " ";
        if (this.isCompleted) {
            marked = "X";
        }
        answer = "[" + marked + "] " + taskName;
        return answer;
    }

    /**
     * Return the String format to save the list of task
     *
     * @return the format for saving of list of task
     */
    public String saveToFile() {
        int completionIndicator = 0;
        if (isCompleted()) {
            completionIndicator = 1;
        }
        return completionIndicator + "|" + taskName;
    }
}

