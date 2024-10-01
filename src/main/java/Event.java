public class Event extends Task {
    private String startDate;
    private String endDate;

    public Event() {
        this.name = "";
        this.done = false;
        this.symbol = "E";
        this.startDate = "";
        this.endDate = "";
    }

    public Event(String name, String startDate, String endDate) {
        this.name = name;
        this.done = false;
        this.symbol = "E";
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void printTask() {
        String doneSymbol = " ";
        if(this.done) {
            doneSymbol = "X";
        }
        System.out.print("[" + this.symbol + "] " + "[" + doneSymbol + "] " + this.name);
        System.out.println(" (from: " + this.startDate + " to: " + this.endDate + ")");
    }

    @Override
    public String dataForSave() {
        return " | " + this.startDate + " | " + this.endDate;
    }
}