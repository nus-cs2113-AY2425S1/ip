public class Deadline extends Task{

    private String deadlineString;

    public Deadline(String inputString) throws DeadlineConstructorException{
        super(inputString.replace("deadline ", "").split(" /by ")[0]);
        if (!(inputString.contains(" /by ")) | this.taskString == "" | this.taskString == null){
            throw new DeadlineConstructorException(inputString);
        }
        this.deadlineString = inputString.replace("deadline ", "").split(" /by ")[1];
        constructorMessage();

    }
    @Override
    public String checkboxString(){
        return "[D]" + super.checkboxString() + " (by: " + deadlineString + ")";
    }
}
