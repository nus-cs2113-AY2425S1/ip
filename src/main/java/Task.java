public class Task {
    private String name;
    private String done;

    public Task() {
        this.name = "";
        this.done = " ";
        this.symbol = " ";
    }

    public Task(String name) {
        this.name = name;
        this.done = " ";
        this.symbol = " ";
    }

    public void printTask() {
        System.out.println("[" + this.symbol + "] " + "[" + this.done + "] " + this.name);
    }

    public void markDone() {
        this.done = "X";
        System.out.println("Nice! I've marked this task as done:");
        printTask();
    }

    public void markNotDone() {
        this.done = " ";
        System.out.println("OK, I've marked this task as not done yet:");
        printTask();
    }
}
