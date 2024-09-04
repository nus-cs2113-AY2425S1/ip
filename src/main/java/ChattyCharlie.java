import java.util.Scanner;

public class ChattyCharlie {

    //MAIN ALGO
    public static void ScheduleMaker() { //Echo as a function
        String line;
        String you = "User: ";

        //make the scanner
        Scanner in = new Scanner(System.in);

        //create an instance of list class
        List list = new List();

        //accept an insert
        while (true) {
            //takes in an input
            System.out.print(you);
            line = in.nextLine();
            System.out.println(Constants.LINE);

            //get the first word to see the command type
            String firstWord = line.split(" ")[0];

            //make the string a command
            CommandType command = CommandType.valueOf(firstWord.toUpperCase());

            //start the different command types
            switch (command) {
                case TODO:
                    String todoDescription = line.substring(5).trim();
                    list.addTask(new Todo(todoDescription));
                    System.out.println(Constants.SPACE + "Added todo: " + todoDescription);
                    System.out.println(Constants.LINE);
                    break;

                case DEADLINE:
                    String[] deadlineParts = line.substring(9).trim().split(" by ");
                    if (deadlineParts.length == 2) { //make sure that the string is only split into 2
                        String deadlineDescription = deadlineParts[0].trim();
                        String by = deadlineParts[1].trim();
                        list.addTask(new Deadline(deadlineDescription, by));
                        System.out.println(Constants.SPACE + "Added deadline: " + deadlineDescription + " (by: " + by + ")");
                        System.out.println(Constants.LINE);
                    }
                    break;

                case EVENT:
                    String[] eventParts = line.substring(6).trim().split("from");
                    String description = eventParts[0].trim();
                    //further split the array into the start and end times
                    String[] eventTimes = eventParts[1].trim().split(" to ");
                    String startTime = eventTimes[0].trim();
                    String endTime = eventTimes[1].trim();

                    //add the event
                    list.addTask(new Event(description, startTime, endTime));
                    System.out.println(Constants.SPACE + "Added event: " + description + " (from: " + startTime + ", to: " + endTime + ")");
                    System.out.println(Constants.LINE);

                    break;

                case MARK:
                    String markIndex = line.substring(5).trim();
                    int markNo = Integer.parseInt(markIndex) -1; //convert to array
                    //mark it
                    list.mark(markNo);
                    System.out.println(Constants.LINE);
                    break;

                case UNMARK:
                    String unmarkIndex = line.substring(7).trim();
                    int unmarkNo = Integer.parseInt(unmarkIndex) -1; //convert to array
                    //mark it
                    list.unmark(unmarkNo);
                    System.out.println(Constants.LINE);
                    break;

                case BYE:
                    return; //just exit

                case LIST:
                    list.printList();
                    System.out.println(Constants.LINE);
                    break;

                default:
                    //add the item
                    list.addTask(new Task(line, CommandType.TODO)); //for cases without label, it is a Todo as well
                    System.out.println(Constants.SPACE + "Added: " + line);
                    System.out.println(Constants.LINE);
                    break;
            }
        }
    }

    public static void main (String[]args){
        System.out.println(Constants.LOGO + Constants.CHARLIE + Constants.GREETING);
        ScheduleMaker();
        System.out.println(Constants.CHARLIE + Constants.FAREWELL);

    }
}
