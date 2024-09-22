import java.util.Scanner;

public class ChattyCharlie {

    //MAIN ALGO
    public static void ScheduleMaker() throws CharlieExceptions { //Echo as a function
        String line = null;
        //make the scanner
        Scanner in = new Scanner(System.in);

        //create an instance of list class
        List list = new List();
        CommandType command = null;
        //accept an insert
        while (true) {
            try {
                //takes in an input
                System.out.print(StringDesign.YOU);
                line = in.nextLine();
                System.out.println(StringDesign.LINE);

                //get the first word to see the command type
                String firstWord = line.split(" ")[0];

                command = CommandType.valueOf(firstWord.toUpperCase());

                //start the different command types
                switch (command) {
                case TODO:
                    //remove the words todo
                    String todoDescription = line.substring(4).trim();

                    // Check if the description is empty
                    if (todoDescription.isEmpty()) {
                        throw CharlieExceptions.missingDescription(command);
                    }

                    //add the todo task
                    list.addTask(new Todo(todoDescription));
                    //print
                    System.out.println(StringDesign.SPACE + "Added todo: " + todoDescription);
                    System.out.println(StringDesign.LINE);
                    break;
                case DEADLINE:
                    //remove the deadline word and split into description and deadline time
                    String[] deadlineParts = line.substring(8).trim().split(" by ");
                    String deadlineDescription;
                    String by;

                    //handle errors
                    if (deadlineParts[0].isEmpty()) {
                        throw CharlieExceptions.missingDescription(command);
                    } else if (deadlineParts.length < 2) {
                        throw CharlieExceptions.missingDeadline();
                    } else {
                        deadlineDescription = deadlineParts[0].trim();
                    }

                    by = deadlineParts[1].trim();

                    list.addTask(new Deadline(deadlineDescription, by));
                    System.out.println(StringDesign.SPACE + "Added deadline: " + deadlineDescription + " (by: " + by + ")");
                    System.out.println(StringDesign.LINE);
                    break;
                case EVENT:
                    //remove the event word and split into the description and event times
                    String[] eventParts = line.substring(5).trim().split("from");
                    String description;
                    String startTime;
                    String endTime;

                    // Error handling for Events
                    if (eventParts[0].isEmpty()) { //no description
                        throw CharlieExceptions.missingDescription(command);
                    } else if (eventParts.length < 2) { //no from
                        throw CharlieExceptions.missingTimes();
                    } else {
                            description = eventParts[0].trim();
                    }

                    String[] eventTimes = eventParts[1].trim().split(" to ");

                    if (eventTimes.length < 2) {
                        throw CharlieExceptions.missingTimes(); //change this to no end date
                    }

                    if (eventTimes[0].isEmpty() || eventTimes[1].isEmpty()) {
                        throw CharlieExceptions.missingTimes();
                    } else {
                        startTime = eventTimes[0].trim();
                        endTime = eventTimes[1].trim();
                    }

                    //add the event task
                    list.addTask(new Event(description, startTime, endTime));
                    //print
                    System.out.println(StringDesign.SPACE + "Added event: " + description
                            + " (from: " + startTime + ", to: " + endTime + ")");
                    System.out.println(StringDesign.LINE);
                    break;
                case MARK:
                    //remove the mark word
                    String markIndex = line.substring(4).trim();
                    //convert the string number into an int
                    int markNo = Integer.parseInt(markIndex) -1; //convert to array
                    Task markedTask = list.getTasks(markNo);
                    //mark it
                    if (markedTask.getMarkedStatus().equals(" ")) {
                        list.mark(markNo);
                    } else {
                        throw CharlieExceptions.alreadyMarked();
                    }
                    //print
                    System.out.println(StringDesign.LINE);
                    break;
                case UNMARK:
                    //remove the unmark word
                    String unmarkIndex = line.substring(6).trim();
                    //convert the string no into a int
                    int unmarkNo = Integer.parseInt(unmarkIndex) -1;
                    //mark it
                    Task unmarkedTask = list.getTasks(unmarkNo);
                    if (unmarkedTask.getMarkedStatus().equals("X")) {
                        list.unmark(unmarkNo);
                    } else {
                        throw CharlieExceptions.alreadyUnmarked();
                    }
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
            } catch (CharlieExceptions e) {
                // Handle custom exceptions and prompt for new input
                System.out.println(e.getMessage());
                System.out.println(StringDesign.LINE);
                continue;  // Continue to ask for input after throwing exception
            } catch (IllegalArgumentException e) {
                System.out.println("Oop, did you make a typo?");
                System.out.println(StringDesign.LINE);
                continue;
            }
        }
    }

    public static void main (String[]args) throws CharlieExceptions{
        System.out.println(StringDesign.LOGO + StringDesign.CHARLIE + StringDesign.GREETING);
        ScheduleMaker();
        System.out.println(StringDesign.CHARLIE + StringDesign.FAREWELL);

    }
}
