public class Task {
    protected String name;
    protected boolean done;
    protected String symbol;

    public Task() {
        this.name = "";
        this.done = false;
        this.symbol = " ";
    }

    public Task(String name) {
        this.name = name;
        this.done = false;
        this.symbol = " ";
    }

    public void printTask() {
        String doneSymbol = " ";
        if(this.done) {
            doneSymbol = "X";
        }
        System.out.println("[" + this.symbol + "] " + "[" + doneSymbol+ "] " + this.name);
    }

    public void mark() {
        if(!this.done) {
            this.done = true;
            System.out.println("Nice! I've marked this task as done:");
        } else {
            this.done = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        printTask();
    }

    public String dataForSave() {
        return "";
    }
}
