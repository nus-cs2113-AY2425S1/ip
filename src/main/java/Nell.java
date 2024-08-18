public class Nell {
    /**
     * Prints a 40-character long horizontal line
     */
    public static void printLine() {
        for (int i = 0; i < 40; i++)
        {
            System.out.print('_');
        }

        System.out.println();
    }

    public static void main(String[] args) {
        // Greets user
        printLine();
        System.out.println("Hello! I'm Nell!");
        System.out.println("What can I do for you?");
        printLine();

        // Exits
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }
}
