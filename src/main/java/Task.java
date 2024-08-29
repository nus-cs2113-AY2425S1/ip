public class Task {
    private int id;
    private String description;
    private boolean isMarkAsDone;

    public Task() {
        this.isMarkAsDone = false;
    }

    public Task(int id, String description) {
        this.id = id;
        this.description = description;
        this.isMarkAsDone = false;
    }

    public void markTaskAsDone() {
        isMarkAsDone = true;
    }

    public void unmarkTaskAsDone() {
        isMarkAsDone = false;
    }

    public String getStatusMark(){
        if (isMarkAsDone) {
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
