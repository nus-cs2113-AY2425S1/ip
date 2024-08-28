import java.util.Scanner;

public class Juan {
    private static String[] Strings = new String[100];
    private static int stringsCounter = 0;
    public static void main(String[] args) {
        lineMessage();
        helloMessage();
        lineMessage();
        chatFeature();
        byeMessage();
        lineMessage();
    }
    public static void chatFeature(){
        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String line = scanner.nextLine();
            lineMessage();
            if (line.equals("bye")) {
                exit = true;
                return;
            } else if (line.equals("list")) {
                if (stringsCounter == 0) {
                    System.out.println("Por Favor? Nothing Here");
                }
                for (int i = 0; i < stringsCounter; i++) {
                    System.out.println((i + 1) + ". " + Strings[i]);
                }
            } else {
                Strings[stringsCounter] = line;
                stringsCounter++;
                System.out.println("Added: " + line);
            }

            lineMessage();
        }
    }
    public static void lineMessage() {
        String line = "____________________________________________________________\n";
        System.out.print(line);
    }
    public static void helloMessage() {
        String greeting = "Hola Amigo, I am Juan Jose Santiago from Michoacan\n"
                + "Welcome to la familia\n"
                + "What I do for you??\n";
        System.out.print(greeting);
    }
    public static void byeMessage() {
        String bye = "Adios amigo mucha gracias\n";
        System.out.print(bye);
    }

}