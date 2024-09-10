public class MissingDescriptionException extends RanException {
    private TaskType type;

    public MissingDescriptionException (TaskType type) {
        this.type = type;
    }

    public String getTypeString () {
        switch (type) {
        case TODO: 
            return "todo";
        case DEADLINE:
            return "deadline";
        case EVENT:
            return "event";
        case UNDEFINED:
        default:
        }
        return "undefined";
    }
}
