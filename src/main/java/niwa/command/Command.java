package niwa.command;

import niwa.exception.NiwaInvalidArgumentException;

public abstract class Command {
    protected static final String PREFIX = "\t";
    protected String argumentFormat;
    protected String word;
    protected String guide;
    protected String[] arguments;

    public abstract String[] parseArguments(String command);

    public void execute(String rawArgumentString) throws NiwaInvalidArgumentException {
        setArguments(parseArguments(rawArgumentString));
        if (arguments == null) {
            throw new NiwaInvalidArgumentException(guide);
        }
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public void setFormat(String format) {
        this.argumentFormat = format;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }
}
