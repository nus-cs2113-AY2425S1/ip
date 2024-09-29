package ran.exception;

import ran.command.CommandType;

public class MissingArgumentException extends RanException {
    private CommandType type;

    public MissingArgumentException(CommandType type) {
        this.type = type;
    }

    public CommandType getType() {
        return type;
    }
}
