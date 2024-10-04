package Commands;

public abstract class Command {
    private String cmd;
    private String args;
    private static final String COMMAND_WORD = "";

    public Command(String cmd, String args) {
        this.cmd = cmd;
        this.args = args;
    }

    public boolean isExit() {
        return cmd.equals("bye");
    }

    public abstract void executeCommand();
}
