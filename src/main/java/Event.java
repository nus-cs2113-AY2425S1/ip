public class Event extends Task{
    private String fromString;
    private String toString;
    public Event(String inputString){
        super(inputString.replace("event ", "").split(" /from ")[0]);
        String[] fromToStrings = inputString.replace("event ", "").split(" /from ")[1].split(" /to ");
        this.fromString = fromToStrings[0];
        this.toString = fromToStrings[1];
    }

    @Override
    public String checkboxString(){
        return "[E]" + super.checkboxString() + " (from: " + fromString + " to: " + toString + ")";
    }
}
