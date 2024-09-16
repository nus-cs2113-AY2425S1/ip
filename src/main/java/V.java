import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

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

    public static void displayList(ArrayList<Task> listOfTasks) {
        int count = 1;
        System.out.println(LINE_SEPERATOR);
        for (Task task: listOfTasks) {
            System.out.println(count + "." + task);
            count++;
        }
        System.out.println(LINE_SEPERATOR);
    }

    public static void markTask(ArrayList<Task> listOfTasks, int position) {
        Task task = listOfTasks.get(position - 1);
        task.setDone();
        listOfTasks.set(position - 1, task);
        displayList(listOfTasks);
    }

    public static void deleteTask(ArrayList<Task> listOfTasks, int position) {
        Task task = listOfTasks.get(position - 1);
        printBlock("Got it. The task below was removed:\n    " + task +
                "\nNow you have " + listOfTasks.size() + " left");
        listOfTasks.remove(position - 1);
    }

    public static void addToDo(ArrayList<Task> listOfTasks, String description) {
        ToDo toDo = new ToDo(description);
        listOfTasks.add(toDo);
        printBlock(String.format("Got it. Task added\n %s", toDo));
    }

    public static void addDeadline(ArrayList<Task> listOfTasks, String description) throws InvalidDeadlineException{
        String[] descriptionAndDeadline = description.split("/by");
        if (descriptionAndDeadline.length != 2) {
            throw new InvalidDeadlineException();
        }
        String descriptionText = descriptionAndDeadline[0].trim();
        String by = descriptionAndDeadline[1].trim();

        Deadline deadline = new Deadline(descriptionText, by);
        listOfTasks.add(deadline);

        printBlock(String.format("Got it. Task added\n %s", deadline));
    }

    public static void addEvent(ArrayList<Task> listOfTasks, String description) {
        String[] descriptionAndEventTimeline = description.split("/from");
        String descriptionText = descriptionAndEventTimeline[0].trim();
        String[] eventTimeline = descriptionAndEventTimeline[1].split("/to");
        String from = eventTimeline[0].trim();
        String to = eventTimeline[1].trim();
        
        Event event = new Event(descriptionText, from, to);
        listOfTasks.add(event);

        printBlock(String.format("Got it. Task added\n %s", event));
    }

    public static void main(String[] args) {

        boolean isOnline = true;
        ArrayList<Task> listOfTasks = new ArrayList<>();
        String description;
        String line;
        String[] lineArr;
        int position;
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
                    displayList(listOfTasks);
                    break;
                case "mark":
                    position = Integer.parseInt(lineArr[1]);
                    markTask(listOfTasks, position);
                    break;
                case "delete":
                    position = Integer.parseInt(lineArr[1]);
                    deleteTask(listOfTasks, position);
                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addToDo(listOfTasks, description);
                    break;
                case "deadline":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addDeadline(listOfTasks, description);
                    break;
                case "event":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addEvent(listOfTasks, description);
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
