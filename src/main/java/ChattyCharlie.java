import java.util.Scanner;

public class ChattyCharlie {

    //MAIN ALGO
    public static void ScheduleMaker() { //Echo as a function
        String line;

        //make the scanner
        Scanner in = new Scanner(System.in);

        //create an instance of list class
        List list = new List();

        //accept an insert
        while (true) {
            //takes in an input
            System.out.print(StringDesign.YOU);
            line = in.nextLine();
            System.out.println(StringDesign.LINE);

            //get the first word to see the command type
            String firstWord = line.split(" ")[0];

            //make the string a command
            CommandType command = CommandType.valueOf(firstWord.toUpperCase());

            //start the different command types
            switch (command) {
            case TODO:
                //remove the words todo
                String todoDescription = line.substring(5).trim();
                //add the todo task
                list.addTask(new Todo(todoDescription));
                //print
                System.out.println(StringDesign.SPACE + "Added todo: " + todoDescription);
                System.out.println(StringDesign.LINE);
                break;
            case DEADLINE:
                //remove the deadline word and split into description and deadline time
                String[] deadlineParts = line.substring(9).trim().split(" by ");
                if (deadlineParts.length == 2) { //make sure that the string is only split into 2
                    String deadlineDescription = deadlineParts[0].trim();
                    String by = deadlineParts[1].trim();
                    //add the deadline task
                    list.addTask(new Deadline(deadlineDescription, by));
                    //print
                    System.out.println(StringDesign.SPACE + "Added deadline: "
                            + deadlineDescription + " (by: " + by + ")");
                    System.out.println(StringDesign.LINE);
                }
                break;
            case EVENT:
                //remove the event word and split into the description and event times
                String[] eventParts = line.substring(6).trim().split("from");
                String eventDescription = eventParts[0].trim();
                //further split the array into the start and end times
                String[] eventTimes = eventParts[1].trim().split(" to ");
                String startTime = eventTimes[0].trim();
                String endTime = eventTimes[1].trim();

                //add the event task
                list.addTask(new Event(eventDescription, startTime, endTime));
                //print
                System.out.println(StringDesign.SPACE + "Added event: " + eventDescription
                        + " (from: " + startTime + ", to: " + endTime + ")");
                System.out.println(StringDesign.LINE);
                break;
            case MARK:
                //remove the mark word
                String markIndex = line.substring(5).trim();
                //convert the string number into an int
                int markNo = Integer.parseInt(markIndex) -1; //convert to array
                //mark it
                list.mark(markNo);
                //print
                System.out.println(StringDesign.LINE);
                break;
            case UNMARK:
                //remove the unmark word
                String unmarkIndex = line.substring(7).trim();
                //convert the string no into a int
                int unmarkNo = Integer.parseInt(unmarkIndex) -1;
                //mark it
                list.unmark(unmarkNo);
                //print
                System.out.println(StringDesign.LINE);
                break;
            case BYE:
                return; //just exit
            case LIST:
                list.printList();
                //print
                System.out.println(StringDesign.LINE);
                break;
            default:
                //add the item
                list.addTask(new Task(line, CommandType.TODO)); //for cases without label, it is a Todo as well
                //print
                System.out.println(StringDesign.SPACE + "Added: " + line);
                System.out.println(StringDesign.LINE);
                break;
            }
        }
    }

    public static void main (String[]args){
        System.out.println(StringDesign.LOGO + StringDesign.CHARLIE + StringDesign.GREETING);
        ScheduleMaker();
        System.out.println(StringDesign.CHARLIE + StringDesign.FAREWELL);

    }
}
