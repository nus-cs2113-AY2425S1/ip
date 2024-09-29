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

    public void getDeadlineDate(String deadlineDate) {
        String[] Date = deadlineDate.split(" ", 2);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        formattedDeadline = LocalDate.parse(Date[1], formatter);
    }

    public String getTaskInfo(){
        //return "[D]" + super.getTaskInfo() + " (" + deadlineDate + ")";
        return "[D]" + super.getTaskInfo() +
                " (by: " + formattedDeadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy")) + ")";
    }

    public String getSaveFileTask(){
        return " d " + super.getSaveFileTask() + " | " + deadlineDate;
    }
}
