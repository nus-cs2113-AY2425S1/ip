import java.util.Scanner;
import java.util.Arrays;

public class V {

    private static final String LINE_SEPERATOR = "____________________________________________________________";

    // Print 2 line seperators between a block of text for cleaner CLI
    public static void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    public static void greet() {
        final String LOGO = " _       _ \n"
                + "\\ \\     / / \n"
                + " \\ \\   / / \n"
                + "  \\ \\_/ / \n"
                + "   \\___/ \n";
        System.out.print(LOGO);
        printBlock("Hi I'm V\nWhat can I do for you?");
    }

    public static void displayList(Task[] listOfTasks, int length) {
        System.out.println(LINE_SEPERATOR);
        for (int i = 0; i < length; i++) {
            System.out.println(String.format("%d.%s", i + 1, listOfTasks[i]));
        }
        System.out.println(LINE_SEPERATOR);
    }

    public static void markTask(Task[] listOfTasks, int position, int count) {
        listOfTasks[position - 1].setDone();
        displayList(listOfTasks, count);
    }

    public static void addToDo(Task[] listOfTasks, String description, int count) {
        listOfTasks[count] = new ToDo(description);
        printBlock(String.format("Got it. Task added\n %s", listOfTasks[count]));
    }

    public static void addDeadline(Task[] listOfTasks, String description, int count) throws InvalidDeadlineException{
        String[] descriptionAndDeadline = description.split("/by");
        if (descriptionAndDeadline.length != 2) {
            throw new InvalidDeadlineException();
        }
        String descriptionText = descriptionAndDeadline[0].trim();
        String by = descriptionAndDeadline[1].trim();
        listOfTasks[count] = new Deadline(descriptionText, by);
        printBlock(String.format("Got it. Task added\n %s", listOfTasks[count]));
    }

    public static void addEvent(Task[] listOfTasks, String description, int count) {
        String[] descriptionAndEventTimeline = description.split("/from");
        String descriptionText = descriptionAndEventTimeline[0].trim();
        String[] eventTimeline = descriptionAndEventTimeline[1].split("/to");
        String from = eventTimeline[0].trim();
        String to = eventTimeline[1].trim();
        listOfTasks[count] = new Event(descriptionText, from, to);
        printBlock(String.format("Got it. Task added\n %s", listOfTasks[count]));
    }

    public static void main(String[] args) {

        boolean isOnline = true;
        Task[] listOfTasks = new Task[100];
        int count = 0;
        String description;
        String line;
        String[] lineArr;
        Scanner input = new Scanner(System.in);

        greet();
        
        while (isOnline) {
            try {
                line = input.nextLine();
                lineArr = line.trim().split(" ");
                switch (lineArr[0].toLowerCase()) {
                case "bye":
                    input.close();
                    isOnline = false;
                    break;
                case "list":
                    displayList(listOfTasks, count);
                    break;
                case "mark":
                    int position = Integer.parseInt(lineArr[1]);
                    markTask(listOfTasks, position, count);
                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addToDo(listOfTasks, description, count);
                    count++;
                    break;
                case "deadline":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addDeadline(listOfTasks, description, count);
                    count++;
                    break;
                case "event":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addEvent(listOfTasks, description, count);
                    count++;
                    break;
                default:
                    System.out.println("Try again");
                    break;
                }
            } catch (NumberFormatException error) {
                printBlock("You need to input a valid integer for the task that you want to mark as done");
            } catch (InvalidDeadlineException error) {
                printBlock("You did not enter a valid deadline." + 
                        " Remember to add a \"/by\" before a valid deadline.");
            }
        }
        printBlock("See Ya");
    }
}
