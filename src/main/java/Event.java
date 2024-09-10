public class Event extends Task{
    private String fromString;
    private String toString;
    public Event(String inputString) throws EventConstructorException{
        super(inputString.replace("event ", "").split(" /from ")[0]);
        if (!(inputString.contains(" /from ") & inputString.contains(" /to ")) | this.taskString == "" | this.taskString == null){
            throw new EventConstructorException(inputString);
        }
        String[] fromToStrings = inputString.replace("event ", "").split(" /from ")[1].split(" /to ");
        if (fromToStrings.length != 2) {
            throw new EventConstructorException(inputString);
        }
        this.fromString = fromToStrings[0];
        this.toString = fromToStrings[1];
        constructorMessage();
    }

    @Override
    public String checkboxString(){
        return "[E]" + super.checkboxString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
