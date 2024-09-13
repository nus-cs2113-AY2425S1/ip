package niwa.command;

public class EchoCommand extends Command {
    public EchoCommand() {
        setFormat("(.*?)");
        setWord("echo");
        setGuide("echo [string]: Echo the string.");
    }

    @Override
    public String[] parseArguments(String command) {
        return new String[0];
    }

    /**
     * Echo a String.
     *
     * @param inputString the line to be echoed.
     */
    @Override
    public void execute(String inputString) {
        System.out.println(PREFIX + inputString);
    }

}
