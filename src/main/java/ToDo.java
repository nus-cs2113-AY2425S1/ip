public class ToDo extends Task{

    public ToDo(String inputString){
        super(inputString.replace("todo ", ""));
        constructorMessage();
    }

    @Override
    public String checkboxString() {
        return "[T]" + super.checkboxString();
    }
}
