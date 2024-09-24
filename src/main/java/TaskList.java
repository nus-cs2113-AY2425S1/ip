import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TaskList {
    private ArrayList<Task> list = new ArrayList<Task>();
    private final String LINE_SEPERATOR = "____________________________________________________________";

    // Print 2 line seperators between a block of text for cleaner CLI
    public void printBlock(String text) {
        System.out.println(LINE_SEPERATOR);
        System.out.println(text);
        System.out.println(LINE_SEPERATOR);
    }

    public void displayList() {
        int count = 1;
        System.out.println(LINE_SEPERATOR);
        for (Task task: this.list) {
            System.out.println(count + "." + task);
            count++;
        }
        System.out.println(LINE_SEPERATOR);
    }

    public void markTask(int position) {
        Task task = this.list.get(position - 1);
        task.setDone();
        this.list.set(position - 1, task);
        displayList();
    }

    public void deleteTask(int position) {
        Task task = this.list.get(position - 1);
        this.list.remove(position - 1);
        printBlock("Got it. The task below was removed:\n    " + task +
                "\nNow you have " + this.list.size() + " left");
    }

    public void addToDo(String description) {
        ToDo toDo = new ToDo(description);
        this.list.add(toDo);
        printBlock(String.format("Got it. Task added\n %s", toDo));
    }

    public void addDeadline(String description) throws InvalidDeadlineException{
        String[] descriptionAndDeadline = description.split("/by");
        if (descriptionAndDeadline.length != 2) {
            throw new InvalidDeadlineException();
        }
        String descriptionText = descriptionAndDeadline[0].trim();
        String by = descriptionAndDeadline[1].trim();

        Deadline deadline = new Deadline(descriptionText, by);
        this.list.add(deadline);

        printBlock(String.format("Got it. Task added\n %s", deadline));
    }

    public void addEvent(String description) {
        String[] descriptionAndEventTimeline = description.split("/from");
        String descriptionText = descriptionAndEventTimeline[0].trim();
        String[] eventTimeline = descriptionAndEventTimeline[1].split("/to");
        String from = eventTimeline[0].trim();
        String to = eventTimeline[1].trim();
        
        Event event = new Event(descriptionText, from, to);
        this.list.add(event);

        printBlock(String.format("Got it. Task added\n %s", event));
    }

    // public void loadSave(String saveFilePath) {
    //     try {
    //         File saveFile = new File(saveFilePath);
    //         Scanner fileScanner = new Scanner(saveFile);
    //         if (!fileScanner.hasNext()) {
    //             fileScanner.close();
    //             return;
    //         }
    //         while (fileScanner.hasNext()) {
    //             String line = fileScanner.nextLine();
    //             String[] lineArr = line.trim().split(" ");
    //             String description;
    //             switch (lineArr[0].toLowerCase()) {
    //             case "mark":
    //                 int position = Integer.parseInt(lineArr[1]);
    //                 markTask(position);
    //                 break;
    //             case "todo":
    //                 description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
    //                 addToDo(description);
    //                 break;
    //             case "deadline":
    //                 description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
    //                 addDeadline(description);
    //                 break;
    //             case "event":
    //                 description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
    //                 addEvent(description);
    //                 break;
    //             default:
    //                 System.out.println("An error occured while loading the save file");
    //                 break;
    //             }
    //         }
    //         fileScanner.close();
    //         printBlock("Here are your list of tasks");
    //         displayList();
    //     } catch (FileNotFoundException error) {
    //         try {
    //             Files.createFile(Paths.get(saveFilePath));
    //         } catch (IOException e) {
    //             System.out.println(e);
    //         }
    //     } catch (NumberFormatException error) {
    //         printBlock("You need to input a valid integer for the task that you want to mark as done");
    //     } catch (InvalidDeadlineException error) {
    //         printBlock("You did not enter a valid deadline." + 
    //                 " Remember to add a \"/by\" before a valid deadline.");
    //     }
    // }

    public ArrayList<Task> getList() {
        return this.list;
    }

    public TaskList(Storage storage) {
        ArrayList<String> commandArray = storage.getCommandArray();
        commandArray.forEach((command) -> {
            try {
                String[] lineArr = command.trim().split(" ");
                String description;
                switch (lineArr[0].toLowerCase()) {
                case "mark":
                    int position = Integer.parseInt(lineArr[1]);
                    markTask(position);
                    break;
                case "todo":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addToDo(description);
                    break;
                case "deadline":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addDeadline(description);
                    break;
                case "event":
                    description = String.join(" ", Arrays.copyOfRange(lineArr, 1, lineArr.length));
                    addEvent(description);
                    break;
                default:
                    System.out.println("An error occured while loading the save file");
                    break;
                }
            } catch (InvalidDeadlineException error) {
                System.out.println(error);
            }
        });
    }

    public TaskList() {
    }
}
