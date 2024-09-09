import java.util.Scanner;

public class Tyrone {
    public static void getUserInput(String userInput) throws TyroneException {
        if (userInput.startsWith("mark ")) {
            int index = Integer.parseInt(userInput.substring(5)) - 1;
            if (index >= 0 && index < Task.listCount) {
                Constants.markAsDone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
            return;
        } else if (userInput.startsWith("unmark ")) {
            int index = Integer.parseInt(userInput.substring(7)) - 1;
            if (index >= 0 && index < Task.listCount) {
                Constants.unmarkAsUndone(index);
            } else {
                System.out.println(Constants.LINE);
                System.out.println("    Invalid task number bro.");
                System.out.println(Constants.LINE);
            }
            return;
        } else if (userInput.startsWith("todo ")) {
            ToDo.createToDo(userInput);
        } else if (userInput.startsWith("deadline ")) {
            try{
                Deadline.createDeadline(userInput);
            }catch (WrongDeadlineFormatException e){
                System.out.println(Constants.LINE);
                System.out.println("    WRONG WAY CUH!! Use:  the format: deadline <description> /by <due by>");
                System.out.println(Constants.LINE);
            } catch (MissingTimeInfoException e) {
                Constants.missingTimeInfo();
            }
        } else if (userInput.startsWith("event ")) {
            try{
                Event.createEvent(userInput);
            } catch (WrongEventFormatException e){
            System.out.println(Constants.LINE);
            System.out.println("    WRONG WAY CUH!! Use: event <description> /from <start_time> /to <end_time>");
            System.out.println(Constants.LINE);
            } catch (MissingTimeInfoException e) {
                Constants.missingTimeInfo();
            }
        } else if (userInput.equals("list")) {
            Constants.getList();
        } else {
            System.out.println(Constants.LINE);
            System.out.println("    Invalid command my brother.");
            System.out.println(Constants.LINE);
        }
    }

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println(Constants.LINE);
            System.out.println("    Hello from\n" + Constants.logo + "\n");
            System.out.println("    What can I do for you cuh?\n");
            System.out.println(Constants.LINE);
            String input = in.nextLine();
            while (!input.equals("bye")) {
                try {
                    getUserInput(input); 
                } catch (TyroneException e) {
                    // General exception handling for TyroneException
                    System.out.println("An error occurred: " + e.getMessage());
                }
                input = in.nextLine();
            }
        }
        Constants.goodbye();
    }
}