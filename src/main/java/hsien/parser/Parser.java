package hsien.parser;

import java.util.Arrays;

public class Parser {
    private String desc;
    private String command;

    public Parser() {
        this.desc = "";
        this.command = "";
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
}
