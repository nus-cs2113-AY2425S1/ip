package hsien.parser;

import hsien.exception.HsienException;
import hsien.datetime.DateTime;

import java.util.Arrays;

/**
 * Represents a command parser that processes user input and extracts
 * relevant details such as command type and associated parameters.
 */
public class Parser {
    private String desc;
    private String command;
    private String fromDate;
    private String toDate;
    private String byDate;
    DateTime datetime = new DateTime();

    /**
     * Initializes a new instance of the Parser class with empty command
     * and description fields.
     */
    public Parser() {
        this.desc = "";
        this.command = "";
        this.fromDate = "";
        this.toDate = "";
        this.byDate = "";
    }

    /**
     * Processes the input command string and extracts the command type,
     * description and relevant dates if applicable .
     *
     * @param input the command input from the user.
     * @return true if the command is processed successfully, false otherwise.
     */
    public boolean processCommand(String input) {
        String[] parts = input.split(" ");
        String tempDesc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length)).trim();
        // Initialise desc for non-tasks commands
        this.desc = tempDesc;
        this.command = parts[0];

        if (command.equals("deadline")) {
            String byDate;
            try {
                this.desc = tempDesc.split("/by")[0].trim();
                byDate = tempDesc.split("/by")[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid format for deadline. Expected format: deadline <task> /by <date>");
                return false;
            }

            try {
                datetime.checkDate(byDate);
                this.byDate = datetime.formatDate(byDate);
            } catch (HsienException e) {
                System.out.println(e.getMessage());
                return false;
            }
        } else if (command.equals("event")){
            String fromDate;
            String toDate;
            try {
                this.desc = tempDesc.split("/from")[0].trim();
                String[] dates = tempDesc.split("/from")[1].split("/to");
                fromDate = dates[0].trim();
                toDate = dates[1].trim();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid format for event. Expected format: event <task>" +
                                   " /from <start date> /to <end date>");
                return false;
            }

            try {
                datetime.checkDate(fromDate);
                datetime.checkDate(toDate);
                this.fromDate = datetime.formatDate(fromDate);
                this.toDate = datetime.formatDate(toDate);
            } catch (HsienException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }

        if (this.command.equals("deadline") || this.command.equals("event") || this.command.equals("todo")) {
            try {
                if (this.desc.isEmpty()) {
                    throw new HsienException();
                }
            } catch (HsienException e) {
                System.out.println("Description cannot be left empty");
                return false;
            }
        }
        return true;
    }

    /**
     * Retrieves the description of the command.
     *
     * @return the description associated with the command.
     */
    public String getDesc() {
        return this.desc;
    }

    /**
     * Retrieves the description of the command.
     *
     * @return the description associated with the command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Retrieves the formatted start date for the event.
     *
     * @return the formatted start date.
     */
    public String getFromDate() {
        return this.byDate;
    }

    /**
     * Retrieves the formatted end date for the event.
     *
     * @return the formatted end date.
     */
    public String getToDate() {
        return this.toDate;
    }

    /**
     * Retrieves the formatted by date for the deadline.
     *
     * @return the formatted by date.
     */
    public String getByDate() {
        return byDate;
    }
}
