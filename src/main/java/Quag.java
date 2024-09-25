public class Quag {
    public static void main(String[] args) {
        UI ui = new UI();
        ui.showWelcome();
        Storage.loadFromFile();
        TaskList.displayList();
        String userInput;

        while (true) {
            // Read command from user
            userInput = ui.readCommand();

            // Parse user input to execute command
            boolean shouldContinue = Parser.parse(userInput);
            if (!shouldContinue) {
                break;
            }
        }
    }
}