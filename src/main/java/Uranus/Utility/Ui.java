package Uranus.Utility;

public class Ui extends Functions{
    public static void printFunctions(){
        print(
                "Currently, I am able to execute the following functions:",
                "1. Add tasks: I can add tasks to your task list.",
                "   - Type command: <task>",
                "   - Type command: todo <task>",
                "   - Type command: deadline <task> /by <deadline>",
                "   - Type command: deadline <task> /by <date in dd-MM-yyyy> <time in HHmm>",
                "   - Type command: event <task> /from <start> /to <end>",
                "   - Type command: event <task> /from <date in dd-MM-yyyy> <time in HHmm>" +
                        " /to <date in dd-MM-yyyy> <time in HHmm>",
                "2. Mark tasks as done:",
                "   - Type command: mark <task number>",
                "3. Mark tasks as not done:",
                "   - Type command: unmark <task number>",
                "4. List all tasks.",
                "   - Type command: list",
                "5. Be a parrot!",
                "   - Type command: echo",
                "6. Shut myself down. Use this if you no longer need me :(",
                "   - Type command: bye",
                "7. Show you what assistance I can provide",
                "   - Type command: help",
                "8. Simulate clearing of screen",
                "   - Type command: clear"
        );
    }

    public static void print(String... messages){
        System.out.println(LINE_SEPARATOR);
        for (String message : messages) {
            System.out.println(message);
        }
        System.out.println(LINE_SEPARATOR);
    }

    public static void echo(){
        while(true){
            System.out.println("Say anything! If you are no longer bored, type exit !");
            String input = in.nextLine();
            if(input.equals("exit")){
                System.out.println(LINE_SEPARATOR);
                Ui.printFunctions();
                break;
            }
            Ui.print(input);
        }
    }

    // Welcome Screen
    public static void printWelcomeMessage() {
        Ui.print(
                "Hello! I'm Uranus, the only Chatbot you'll ever need.",
                "How can I be of service?"
        );
    }

    // Goodbye Screen
    public static void printByeMessage() {
        Ui.print("Bye. Hope to see you again!");
    }

    // Simulate Clearing of Screen
    public static void clearScreen(){
        for (int i = 0; i < 35; i++) {
            System.out.println();
        }
    }
}
