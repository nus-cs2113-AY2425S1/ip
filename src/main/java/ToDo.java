public class ToDo extends Task{

    //To-Do Constructor inherits Task
    public ToDo(String description){
        super(description);
    }

    //Override toString method to show marker
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
