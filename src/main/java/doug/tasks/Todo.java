package doug.tasks;

/**
 * Class representing Todo tasks
 * Inherits from Task class
 */
public class Todo extends Task {

    public Todo(String description){
        super(description);
    }

    /**
     * Overload method to return a custom string representing the task
     *
     * @return String describing the task's details
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }

    /**
     * To return a custom string to be written in save file
     *
     * @return String describing the task's details in the proper format for save file
     */
    @Override
    public String saveString() {
        return "T | " + super.saveString() + " | " + description;
    }
}
