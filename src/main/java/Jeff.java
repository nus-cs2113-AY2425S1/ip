import java.util.Scanner;
import java.util.Random;

public class Jeff {

    private static String randomlyCapitalise(String line){ //Randomly capitalizes a string
        Random randomBool = new Random();
        StringBuilder randomString = new StringBuilder(line.length());
        for(int i = 0; i < line.length(); i++){
            if(randomBool.nextBoolean()) {
                randomString.append(Character.toUpperCase(line.charAt(i)));
            } else {
                randomString.append(Character.toLowerCase(line.charAt(i)));
            }
        }
        return randomString.toString();
    }

    public static void printList(){ //Prints list of tasks
        for(int i = 1; i <= Task.getCount(); i++){
            System.out.println(i + ". [" + Task.getList()[i-1].getStatusIcon() + "] " + Task.getList()[i-1].getDescription());
        }
    }

    public static void markTask(String line){ //Marks task as complete/uncomplete
        int dividerPosition = line.indexOf(" ");
        int taskNumber = Integer.parseInt(line.substring(dividerPosition + 1, dividerPosition + 2));
        Task t = Task.getList()[taskNumber - 1];
        if(line.startsWith("mark")) {
            t.isDone = true;
            System.out.println("ogei marked task dOnE");
        }
        else{
            t.isDone = false;
            System.out.println("womp womp task not finished :(");
        }
        System.out.println("[" + t.getStatusIcon() + "] " + t.getDescription());
    }

    public static void echo(){
        String divider = "____________________________________________________________";

        Scanner in = new Scanner(System.in);
        String line;
        System.out.println("You say:");
        while(!(line = in.nextLine()).equals("bye")){
            System.out.print(divider + System.lineSeparator());

            if(line.equals("list")){ //List all tasks
                System.out.println("orh hor never finish ur tasks:");
                printList();
                System.out.print(divider + System.lineSeparator());
            }
            else if (line.startsWith("mark") || line.startsWith("unmark")) { //Marks tasks
                markTask(line);
                System.out.print(divider + System.lineSeparator());
            }
            else {
                Task t = new Task(line);
                String echo = randomlyCapitalise(line);
                System.out.println("I echo:" + System.lineSeparator() + echo + System.lineSeparator() + divider);
            }
            System.out.println("You say:");

        }
    }

    public static void main(String[] args) {
        String  introText = """
                ____________________________________________________________
                Hello! I'm JEFF
                I will echo whatever you say, and store them in a list!
                However, I will echo what you say with random capitalisation!
                
                Type 'list' to display everything you've said!
                Type 'mark'/'unmark' to change the status of inputted tasks!
                Type 'bye' to exit!
                ____________________________________________________________
                """;

        String exitText = """
                ____________________________________________________________
                Bye. Hope to see you again soon!
                ____________________________________________________________
                """;

        System.out.print(introText);
        echo();
        System.out.println(exitText);
    }
}
