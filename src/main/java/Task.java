public abstract class Task {
    private int id;
    private String description;
    private boolean isDone;

    public Task() {
        this.isDone = false;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isDone = false;
    }

    public void markTaskAsDone() {
        isDone = true;
    }

    public void unmarkTaskAsDone() {
        isDone = false;
    }

    public boolean isMarkAsDone() {
        return isDone;
    }

    public String getStatusMark(){
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public void printTask() {
        System.out.print("[");
        System.out.print(getStatusMark());
        System.out.print("] ");
        System.out.println(description);
    }

    @Override
    public String toString() {
        return "[" + getStatusMark() + "] " + description;
    }
}
