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
        System.out.println("[" + this.symbol + "] " + "[" + this.done + "] " + this.name + " (by: " + this.dueDate + ")");
    }
}