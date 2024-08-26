import java.util.Scanner;

public class Yappatron {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        String[] splitInput = new String[100];
        Task[] arrayInput = new Task[100];
        int flag = 0, position = 0, i, index;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                flag = 1;
            }
            else if (input.startsWith("mark")){
                splitInput = input.split(" ");
                index = Integer.parseInt(splitInput[1]) - 1;
                arrayInput[index].markAsDone();
            }
            else if (input.startsWith("unmark")){
                splitInput = input.split(" ");
                index = Integer.parseInt(splitInput[1]) - 1;
                arrayInput[index].markAsUndone();
            }
            else if (!input.equalsIgnoreCase("list")){
                System.out.println("added: " + input);
                arrayInput[position++] = new Task(input);
            }

            else{
                System.out.println("Here are the tasks in your list:");
                for (i=0; i<position; i++){
                    System.out.println(i+1 + "." + "[" + arrayInput[i].getStatusIcon() + "] " + arrayInput[i].getDescription());
                }
            }
        }while (flag==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
