package Glendon.task;

public class Task {
    public String taskName;
    public boolean isCompleted;

    public Task(String taskName) {
        this.taskName = taskName;
        this.isCompleted = false;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

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

    public String saveToFile() {
        int completionIndicator = 0;
        if (isCompleted()) {
            completionIndicator = 1;
        }
        return completionIndicator + "|" + taskName;
    }
}

