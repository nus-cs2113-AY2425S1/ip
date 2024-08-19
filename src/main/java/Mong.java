public class Mong {

    public static void printHorizontalLine() {
        for (int i = 0; i < 15; i += 1) {
            System.out.print("-");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String logo = "\n" +
                "\n" +
                "___  ___                  _ \n" +
                "|  \\/  |                 | |\n" +
                "| .  . | ___  _ __   __ _| |\n" +
                "| |\\/| |/ _ \\| '_ \\ / _` | |\n" +
                "| |  | | (_) | | | | (_| |_|\n" +
                "\\_|  |_/\\___/|_| |_|\\__, (_)\n" +
                "                     __/ |  \n" +
                "                    |___/   \n" +
                "\n";
        printHorizontalLine();
        System.out.println("Hello, I am\n" + logo);
        System.out.println("What can I do for you?");
        printHorizontalLine();
        System.out.println("Mong-mong... See you again next time!");
        printHorizontalLine();
    }
}
