import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class V {

    private static final String LINE_SEPERATOR = "____________________________________________________________";
    private static final String SAVE_FILE_PATH = "./V.txt";

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

    public static int loadSave(Task[] listOfTasks) {
        int count = 0;
        if (Files.exists(Paths.get(SAVE_FILE_PATH))) {
            try {
                File saveFile = new File(SAVE_FILE_PATH);
                Scanner fileScanner = new Scanner(saveFile);
                while (fileScanner.hasNext()) {
                    String line = fileScanner.nextLine();
                    String[] lineArr = line.trim().split(" ");
                    String description;
                    switch (lineArr[0].toLowerCase()) {
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
                        System.out.println(fileScanner.hasNext());
                        break;
                    }
                }
                fileScanner.close();
                return count;
            } catch (FileNotFoundException error) {
                System.out.println();
            } catch (NumberFormatException error) {
                printBlock("You need to input a valid integer for the task that you want to mark as done");
            } catch (InvalidDeadlineException error) {
                printBlock("You did not enter a valid deadline." + 
                        " Remember to add a \"/by\" before a valid deadline.");
            }
        } else {
            try {
                Files.createFile(Paths.get(SAVE_FILE_PATH));
            } catch (IOException error) {
                System.out.println(error);
            }
        }
        return 0;
    }

    public static void saveTasks(Task[] listOfTasks, int count) {
        try {
            File saveFile = new File(SAVE_FILE_PATH);
            FileWriter clearSaveFile = new FileWriter(saveFile);
            clearSaveFile.write("");
            clearSaveFile.close();
            FileWriter saveFileWriter = new FileWriter(saveFile, true);
            for (int i = 0; i < count; i++) {
                Task task = listOfTasks[i];
                switch(task.getType()) {
                case "T":
                    saveFileWriter.write("todo " + task.getDescription() + System.lineSeparator());
                    break;
                case "D":
                    saveFileWriter.write("deadline " + task.getDescription() + " /by " + task.getBy() + System.lineSeparator());
                    break;
                case "E":
                    saveFileWriter.write("event " + task.getDescription() + " /from " + task.getFrom() + " /by " + task.getTo() + System.lineSeparator());
                    break;
                default:
                    break;
                }
                if (task.getStatus().equals("X")) {
                    saveFileWriter.write("mark " + (i+1) + System.lineSeparator());
                }
            }
            saveFileWriter.close();
        } catch (IOException error) {
            System.out.println(error);
        }
    }

    public static void main(String[] args) {

        boolean isOnline = true;
        ArrayList<Task> listOfTasks = new Task[100];
        String description;
        String line;
        String[] lineArr;
        int position;
        Scanner input = new Scanner(System.in);

        greet();
        int count = loadSave(listOfTasks);
        
        while (isOnline) {
            try {
                line = input.nextLine();
                lineArr = line.trim().split(" ");
                switch (lineArr[0].toLowerCase()) {
                case "bye":
                    input.close();
                    isOnline = false;
                    saveTasks(listOfTasks, count);
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
