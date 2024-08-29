public class Task {
    String taskName;
    boolean completion;

    public Task(String taskName) {
        this.taskName = taskName;
        this.completion = false;
    }

    public Task(String taskName, boolean completion) {
        this.taskName = taskName;
        this.completion = completion;
    }

    public boolean isCompletion() {
        return completion;
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }

    @Override
    public String toString() {
        String answer;
        String marked = " ";
        if (this.isCompletion()) {
            marked = "X";
        }
        answer = "[" + marked + "] " + taskName;
        return answer;
    }
}