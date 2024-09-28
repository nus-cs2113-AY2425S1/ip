package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class Command {

    protected boolean isExit = false;
    protected String instruction;

    public Command() {
        this.instruction = "";
    }

    public Command(String instruction) {
        this.instruction = instruction;
    }

    public abstract void execute (TaskList taskList, Ui ui, Storage storage) throws AlyException;

    protected void setExit() {
        this.isExit = true;
    }

    public boolean hasExited() {
        return isExit;
    }

    public boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidDateTimeFormat(String date) {
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}