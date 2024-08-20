import java.util.Scanner;

public class Medea {

    private static void greet(){
        System.out.println("Hello! I'm Medea.");
        System.out.println("What can I do for you?");
    }

    private static void leave(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void echo(String command){
        System.out.println(command);
    }

    private static void handleInput(String command){
        echo(command);
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while(!input.equals("bye")){
            handleInput(input);
            input = sc.nextLine();
        }
        leave();
    }
}