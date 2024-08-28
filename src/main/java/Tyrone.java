import java.util.Scanner;

public class Tyrone {
    public class Constants{
        private static int listCount = 0;
        private static String[] toDoList =  new String[100];
        private static boolean[] isDone = new boolean[100];
        public static final String LINE = "    ___________________________________";
        public static void goodbye(){
            System.out.println(Constants.LINE);
            System.out.println("    see you brother");
            System.out.println(Constants.LINE);
        }
        public static void getUserInput(String userInput){
            if (userInput.startsWith("mark ")){
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                if (index >= 0 && index < listCount) {
                    isDone[index] = true;
                    System.out.println(Constants.LINE);
                    System.out.println("    TIGHT! I've marked this task as done:");
                    System.out.println("      [X] " + toDoList[index]);
                    System.out.println(Constants.LINE);
                } else {
                    System.out.println(Constants.LINE);
                    System.out.println("    Invalid task number bro.");
                    System.out.println(Constants.LINE);
                }
                return;
            } else if (userInput.startsWith("unmark ")) {
                int index = Integer.parseInt(userInput.substring(7)) - 1;
                if (index >= 0 && index < listCount) {
                    isDone[index] = false;
                    System.out.println(Constants.LINE);
                    System.out.println("    alright cuh, I've marked this task as unfinished:");
                    System.out.println("      [ ] " + toDoList[index]);
                    System.out.println(Constants.LINE);
                } else {
                    System.out.println(Constants.LINE);
                    System.out.println("    Invalid task number bro.");
                    System.out.println(Constants.LINE);
                }
                return;
            } else {
            switch (userInput){
                case "list":
                    System.out.println(Constants.LINE);
                    for (int i=1; i<listCount+1;i++){
                        String status = isDone[i - 1] ? "[X]" : "[ ]";
                        System.out.println("    " + i + ". " + status + " " + toDoList[i - 1]);
                    }
                    System.out.println(Constants.LINE);
                    break;
                default:
                    toDoList[listCount] = userInput;
                    listCount++;
                    System.out.println(Constants.LINE);
                    System.out.println("    added: " + userInput);
                    System.out.println(Constants.LINE);
                    break;
                }
                return;
            }
        }
    }
    public static void main(String[] args) {
        String logo = "    tyrone";
        Scanner in = new Scanner(System.in);
        System.out.println(Constants.LINE);
        System.out.println("    Hello from\n" + logo);
        System.out.println("    What can I do for you cuh?");
        System.out.println(Constants.LINE);
        String input = in.nextLine();
        while (!input.equals("bye")){
            Constants.getUserInput(input);
            input = in.nextLine();
        }
        Constants.goodbye();
    }
}
