public class Deadline extends Task {
    private String dueDate;

    public Deadline() {
        this.name = "";
        this.done = false;
        this.symbol = "D";
        this.dueDate = "";
    }

    public Deadline(String name, String dueDate) {
        this.name = name;
        this.done = false;
        this.symbol = "D";
        this.dueDate = dueDate;
    }

    @Override
    public void printTask() {
        String doneSymbol = " ";
        if(this.done) {
            doneSymbol = "X";
        }
        System.out.print("[" + this.symbol + "] " + "[" + doneSymbol + "] ");
        System.out.println(this.name + " (by: " + this.dueDate + ")");
    }

    @Override
    public String dataForSave() {
        return " | " + this.dueDate;
    }
}