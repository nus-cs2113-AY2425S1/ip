package ellio.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Deadline extends Task{

    protected String deadlineDate;
    protected LocalDate formattedDeadline;

    public Deadline(String description, String isDone, String deadlineDate) {
        super(description, isDone);
        this.deadlineDate = deadlineDate;
        getDeadlineDate(deadlineDate);
    }

    /**
     * Formats the deadlineDate from input and formats it into LocalDate Format
     * Supports java.time library functions
     * @param deadlineDate String format of Date of deadline
     */
    public void getDeadlineDate(String deadlineDate) {
        String[] Date = deadlineDate.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDeadline = LocalDate.parse(Date[1], formatter);
    }

    /**
     * Gets deadline description, and formatted time and returns it as a string.
     * Date of Deadline can be furthered amended with change of .ofPattern() field
     * @return Formatted String to be printed to output
     */
    public String getTaskInfo(){
        return "[D]" + super.getTaskInfo() +
                " (by: " + formattedDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String getSaveFileTask(){
        return " d " + super.getSaveFileTask() + " | " + deadlineDate;
    }
}
