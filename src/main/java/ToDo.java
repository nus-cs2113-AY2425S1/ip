public class ToDo extends Task{

    public ToDo(String inputString){
        super(inputString.replace("todo ", ""));
    }

    @Override
    public String checkboxString() {
        return "[T]" + super.checkboxString();
    }
}
