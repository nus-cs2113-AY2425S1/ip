public class Andy {

    public static void main(String[] args) {
        greetUser(); // method to greet the user
        // Simulate a simple loop where the bot waits for user input
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = "";

        while (!input.equals("bye")) {
            input = scanner.nextLine();
            System.out.println("You said: " + input);
        }

        exit();
    }

    private static void greetUser() {
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm ANDY");
        System.out.println("What can I do for you?");
        System.out.println("_______________________________________");
    }

    private static void exit() {
        System.out.println("_______________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("_______________________________________");
    }
}

