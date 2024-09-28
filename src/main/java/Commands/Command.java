package Commands;

import AlyBot.AlyException;
import AlyBot.Storage;
import AlyBot.TaskList;
import AlyBot.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an abstract command that can be executed.
 * A command can either exit the program or perform actions based on specific instructions.
 */
public abstract class Command {

    protected boolean isExit = false;
    protected String instruction;

    /**
     * Constructs a Command with no specific instructions.
     */
    public Command() {
        this.instruction = "";
    }

    /**
     * Constructs a Command with the specified instructions.
     *
     * @param instruction The instructions for the command.
     */
    public Command(String instruction) {
        this.instruction = instruction;
    }

    /**
     * Executes the command using the provided task list, UI, and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui The user interface for output messages.
     * @param storage The storage system for saving tasks.
     * @throws AlyException If any errors occur during execution.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws AlyException;

    /**
     * Sets the command to exit after execution.
     */
    protected void setExit() {
        this.isExit = true;
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return True if the command will exit the program, false otherwise.
     */
    public boolean hasExited() {
        return isExit;
    }

    /**
     * Checks if the given date string is in a valid format (yyyy-MM-dd).
     *
     * @param date The date string to validate.
     * @return True if the date is in the valid format; false otherwise.
     */
    public boolean isValidDateFormat(String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Checks if the given date and time string is in a valid format (yyyy-MM-dd HHmm).
     *
     * @param date The date and time string to validate.
     * @return True if the date and time are in the valid format; false otherwise.
     */
    public boolean isValidDateTimeFormat(String date) {
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}