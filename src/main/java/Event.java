/**
 * Derived from the Task abstract class
 * Includes the symbol "E" for event
 * Includes user-inputted Strings for the start date and end date
 */
public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event() {
        this.name = "";
        this.isDone = false;
        this.symbol = "E";
        this.startDate = "";
        this.endDate = "";
    }

    public Event(String name, String startDate, String endDate) {
        this.name = name;
        this.isDone = false;
        this.symbol = "E";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the parent class toString plus the from and to dates
     */
    @Override
    public String toString() {
        String doneSymbol = " ";
        if(this.isDone) {
            doneSymbol = "X";
        }
        return(super.toString()+ " (from: " + this.startDate + " to: " + this.endDate + ")");
    }

    /**
     * @return the from and to dates formatted for the save file
     */
    @Override
    public String dataForSave() {
        return " | " + this.startDate + " | " + this.endDate;
    }
}