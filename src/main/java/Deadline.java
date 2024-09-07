public class Deadline extends Task{

    private String deadlineString;

    public Deadline(String inputString){
        super(inputString.replace("deadline ", "").split(" /by ")[0]);
        this.deadlineString = inputString.replace("deadline ", "").split(" /by ")[1];
        constructorMessage();

    }
    @Override
    public String checkboxString(){
        return "[D]" + super.checkboxString() + " (by: " + deadlineString + ")";
    }
}
