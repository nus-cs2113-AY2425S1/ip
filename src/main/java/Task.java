/**
 * The framework for all objects contained in the task list.
 * Contains methods for initialization, printing, and marking.
 * name (String): The user-inputted name/descriptor for the task
 * isDone (boolean): Contains whether the task is done yet.
 * symbol (String): The symbol associated with each task. Used while printing.
 */
public abstract class Task {
    protected String name;
    protected boolean isDone;
    protected String symbol;

    /**
     * Default constructor for Task
     * Note that tasks are always not done when initialized.
     */
    public Task() {
        this.name = "";
        this.isDone = false;
        this.symbol = " ";
    }

    /**
     * Constructor for Task
     * @param name the name/descriptor of the task
     * Note that tasks are always not done when initialized.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.symbol = " ";
    }

    /**
     * Overrides toString in order to create a descriptor for the task object.
     * Contains the task's symbol, whether it's done (marked with an X), and its name.
     * @return the String to print when printing the task object.
     */
    @Override
    public String toString() {
        String doneSymbol = " ";
        if(this.isDone) {
            doneSymbol = "X";
        }
        return("[" + this.symbol + "] " + "[" + doneSymbol+ "] " + this.name);
    }

    /**
     * If the task is done, marks as not done. Otherwise, mark as done.
     * Then prints the task.
     */
    public void mark() {
        if(!this.isDone) {
            this.isDone = true;
            System.out.println("Nice! I've marked this task as done:");
        } else {
            this.isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println(toString());
    }

    /**
     * Returns data to be used for saving.
     * @return an empty string.
     */
    public String dataForSave() {
        return "";
    }

}
