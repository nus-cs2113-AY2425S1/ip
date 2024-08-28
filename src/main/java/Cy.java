import java.util.Scanner;

public class Cy {
    public static void printLine(){
        System.out.println("______________________________________");
    }
    public static void main(String[] args) {
        System.out.println("Hello I'm Cy");
        System.out.println("What can I do for you?");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        while (!input.equalsIgnoreCase("bye")){
            printLine();
            System.out.println(input);
            printLine();
            input = scan.nextLine();
        }

        printLine();
        System.out.println("Bye. Hope to see you again");
        printLine();
    }
}
