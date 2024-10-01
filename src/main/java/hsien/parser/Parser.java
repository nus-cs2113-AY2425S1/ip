package hsien.parser;

import hsien.exception.HsienException;
import hsien.datetime.DateTime;


import java.util.Arrays;

public class Parser {
    private String desc;
    private String command;
    private String fromDate;
    private String toDate;
    private String byDate;
    DateTime datetime = new DateTime();


    public Parser() {
        this.desc = "";
        this.command = "";
        this.fromDate = "";
        this.toDate = "";
        this.byDate = "";
    }

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
                System.out.println("Invalid format for event. Expected format: event <task> /from <start date> /to <end date>");
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

    public void processCommand(String input) {
        String[] parts = input.split(" ");
        this.desc = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        this.command = parts[0];
    }

    public String getDesc() {
        return this.desc;
    }

    public String getCommand() {
        return this.command;
    }

    public String getFromDate() {
        return this.byDate;
    }

    public String getToDate() {
        return this.toDate;
    }

    public String getByDate() {
        return byDate;
    }
}
