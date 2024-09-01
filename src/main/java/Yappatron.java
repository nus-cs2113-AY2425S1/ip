import java.util.Scanner;

public class Yappatron {
    public static void main(String[] args) {
        final int MAX_TASKS = 100;
        final int STRLENGTH_DEADLINE = 8;
        final int STRLENGTH_TO = 3;
        final int STRLENGTH_FROM = 5;
        final int STRLENGTH_BY = 3;
        System.out.println("Hello! I'm Yappatron");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String input;
        String[] splitInput;
        Task[] taskArray = new Task[MAX_TASKS];
        int exitStatus = 0, taskNumber = 0, i, index;
        do {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")){
                exitStatus = 1;
            }
            else if (input.startsWith("mark")){
                splitInput = input.split(" ");
                index = Integer.parseInt(splitInput[1]) - 1;
                taskArray[index].markAsDone();
            }
            else if (input.startsWith("unmark")){
                splitInput = input.split(" ");
                index = Integer.parseInt(splitInput[1]) - 1;
                taskArray[index].markAsUndone();
            }
            else if (input.equalsIgnoreCase("list")){
                System.out.println("Here are the tasks in your list:");
                for (i=0; i<taskNumber; i++){
                    System.out.print(i+1 + ". ");
                    System.out.println(taskArray[i]);
                }
            }
            else{
                System.out.println("added: " + input);
                if (input.startsWith("todo")){
                    taskArray[taskNumber++] = new Todo(input.substring(input.indexOf(" ")));
                }
                else if (input.startsWith("deadline")){
                    taskArray[taskNumber++] = new Deadline(input.substring(input.indexOf("deadline") + STRLENGTH_DEADLINE,
                            input.indexOf('/')), input.substring(input.indexOf('/') + STRLENGTH_BY));
                }
                else{
                    int firstSlash = input.indexOf("/");
                    int secondSlash = input.indexOf("/", firstSlash + 1);
                    taskArray[taskNumber++] = new Events(input.substring(input.indexOf("event") + STRLENGTH_FROM,
                            firstSlash), input.substring(firstSlash + STRLENGTH_FROM, secondSlash),
                            input.substring(secondSlash + STRLENGTH_TO));
                }
            }
        }while (exitStatus==0);
        System.out.println("Bye. Hope to see you again soon!");
    }
}
