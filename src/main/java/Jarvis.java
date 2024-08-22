public class Jarvis {
    static String chatBotName = "Jarvis"; // Name of the chatbot

    public static void printBreakLine() {
        System.out.println("────────────────────────────────────────────────────────────");
    }

    public static void exit(int status) {
        System.exit(status);
    }

    public static void printGreetingMsgs() {
        String[] greetings = {"Hello! I'm " + chatBotName + "\nWhat can I do for you?",
                "Bye. Hope to see you again soon!",};

        for (String greeting : greetings) {
            printBreakLine();
            System.out.println(greeting);
        }
        printBreakLine();
        exit(0);
    }

    public static void main(String[] args) {
        String logo = """
                     ██╗    █████╗    ██████╗   ██╗   ██╗  ██╗   ███████╗
                     ██║   ██╔══██╗   ██╔══██╗  ██║   ██║  ██║   ██╔════╝
                     ██║   ███████║   ██████╔╝  ██║   ██║  ██║   ███████╗
                ██   ██║   ██╔══██║   ██╔══██╗  ╚██╗ ██╔╝  ██║   ╚════██║
                ╚█████╔╝██╗██║  ██║██╗██║  ██║██╗╚████╔╝██╗██║██╗███████║██╗
                 ╚════╝ ╚═╝╚═╝  ╚═╝╚═╝╚═╝  ╚═╝╚═╝ ╚═══╝ ╚═╝╚═╝╚═╝╚══════╝╚═╝
                """;
        System.out.println("Hello from\n" + logo);
        printGreetingMsgs();
    }
}
