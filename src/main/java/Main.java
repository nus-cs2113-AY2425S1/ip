public class Main {
    public static void main(String[] args) {
        Niwa chatBot = new Niwa();
        while (chatBot.isIsRunning()) {
            String command = chatBot.getCommand();
            chatBot.processCommand(command);
        }
    }
}
