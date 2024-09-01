public class Deadline extends Task {
    private String dueDate;

    public Deadline() {
        this.name = "";
        this.done = " ";
        this.symbol = "D";
        this.dueDate = "";
    }

    public Deadline(String name, String dueDate) {
        this.name = name;
        this.done = " ";
        this.symbol = "D";
        this.dueDate = dueDate;
    }

    @Override
    public void printTask() {
        System.out.print("[" + this.symbol + "] " + "[" + this.done + "] ");
        System.out.println(this.name + " (by: " + this.dueDate + ")");
    }
}