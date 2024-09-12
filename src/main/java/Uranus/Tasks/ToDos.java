package Uranus;

public class ToDos extends Task{
    public ToDos(String description){
        super(description, "T");
    }

    @Override
    public void setDescription(String description){
        int separatorIndex = description.indexOf(' ');
        this.description = description.substring(separatorIndex + 1);
    }
}
