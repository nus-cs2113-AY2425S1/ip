package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

import java.util.HashMap;
import java.util.Map;

public abstract class Command {
    protected static final String PREFIX = "\t";
    public static final String COMMAND_WORD = "";
    public static final String COMMAND_GUIDE = "";
    public static final String[] COMMAND_KEYWORDS = {};

    protected Map<String, String> arguments = new HashMap<>();


    public abstract void execute() throws NiwaInvalidArgumentException;
    public void setArguments(Map<String, String> arguments) {
        this.arguments = arguments;
    }
}
