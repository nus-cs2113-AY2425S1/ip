public class Todo extends Task {
    final static String COMMAND_STRING = "todo";


    public Todo(String description) {
        super(description);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + this.toString());
        Task.printNumberOfTasks();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
