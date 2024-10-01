package Uranus.Tasks;

public class ToDos extends Task{
    private static final String TODO_TAG = "T";

    public ToDos(String description){
        super(description, TODO_TAG);
    }

    @Override
    public void setDescription(String description){
        int separatorIndex = description.indexOf(' ');
        this.description = description.substring(separatorIndex + 1);
    }
}
