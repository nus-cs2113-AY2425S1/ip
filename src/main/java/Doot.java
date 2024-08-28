import java.util.Scanner;

public class Doot {
    private static final String DIVIDER = "____________________________________________________________\n\n";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print(DIVIDER + "Hello! I'm  Doot\nWhat can I do for you?\n" + DIVIDER);
        String currentInput = scanner.nextLine();
        while (!currentInput.equals("bye")){
            System.out.print(DIVIDER + currentInput + "\n" + DIVIDER);
            currentInput = scanner.nextLine();
        }
        System.out.print(DIVIDER + "Bye. Hope to see you again soon!" + "\n" + DIVIDER);
    }

    public static void findCommand(String command){
        switch(command){
            case ("list"):
                break;
        }
    }

}
