public class ByeCommand extends Command {
    Niwa chatbot;

    public ByeCommand(Niwa chatbot) {
        setFormat("");
        setWord("bye");
        setGuide("bye: End the chat.");
        setChatbot(chatbot);
    }

    public void setChatbot(Niwa chatbot) {
        this.chatbot = chatbot;
    }

    @Override
    public String[] convertArguments(String command) {
        if (!command.isEmpty()) {
            return null;
        }
        return new String[0];
    }

    /**
     * Prints a farewell message and inactivate Niwa chatbot.
     *
     * @param rawArgumentString should be null.
     */
    @Override
    public void execute(String rawArgumentString) {
        super.execute(rawArgumentString);
        System.out.println(PREFIX + "Bye bae. Hope to see you again! Moah~");
        chatbot.setRunning(false);
    }

}
