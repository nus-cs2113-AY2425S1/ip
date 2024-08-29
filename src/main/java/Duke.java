import java.util.Scanner;

public class Duke {

    public static void echo(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {
        System.out.println("Hello I'm Lambo");
        System.out.println("What can I do for you?");
        Scanner input_reader = new Scanner(System.in);
        while(true) {
            String input = input_reader.nextLine();
            if(input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else{
                echo(input);
            }
        }
    }


}
