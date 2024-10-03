package Uranus.Tasks;

import UranusExceptions.UranusExceptions;

public class ToDos extends Task{
    private static final String TODO_TAG = "T";

    public ToDos(String description) throws UranusExceptions {
        super(description, TODO_TAG);
    }

    @Override
    public void setDescription(String description){
        super.setDescription(description);
    }
}
