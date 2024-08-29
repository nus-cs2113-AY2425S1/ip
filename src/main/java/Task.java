public class Task {
    private int id;
    private String description;
    private boolean isMarkDone;

    public Task() {
        this.isMarkDone = false;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isMarkDone = false;
    }

    public void markTaskDone() {
        isMarkDone = true;
    }

    public void unmarkTaskDone() {
        isMarkDone = false;
    }

    public String getStatusMark(){
        if (isMarkDone) {
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
}
