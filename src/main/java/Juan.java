import java.util.Scanner;

public class Juan {
    public static void main(String[] args) {
        lineMessage();
        helloMessage();
        lineMessage();

        boolean exit = false;
        Scanner scanner = new Scanner(System.in);
        while (!exit) {
            String line = scanner.nextLine();
            lineMessage();
            if (line.equals("bye")) {
                exit = true;
            } else {
                System.out.println(line);
                lineMessage();
            }
        }
        byeMessage();
        lineMessage();
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