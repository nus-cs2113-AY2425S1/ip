package blossom;

import blossom.task.Deadline;
import blossom.task.Event;
import blossom.task.Task;
import blossom.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Blossom {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String LOGO =
            """
                     _______     .---.       ,-----.       .-'''-.    .-'''-.     ,-----.    ,---.    ,---.\s
                    \\  ____  \\   | ,_|     .'  .-,  '.    / _     \\  / _     \\  .'  .-,  '.  |    \\  /    |\s
                    | |    \\ | ,-./  )    / ,-.|  \\ _ \\  (`' )/`--' (`' )/`--' / ,-.|  \\ _ \\ |  ,  \\/  ,  |\s
                    | |____/ / \\  '_ '`) ;  \\  '_ /  | :(_ o _).   (_ o _).   ;  \\  '_ /  | :|  |\\_   /|  |\s
                    |   _ _ '.  > (_)  ) |  _`,/ \\ _/  | (_,_). '.  (_,_). '. |  _`,/ \\ _/  ||  _( )_/ |  |\s
                    |  ( ' )  \\(  .  .-' : (  '\\_/ \\   ;.---.  \\  :.---.  \\  :: (  '\\_/ \\   ;| (_ o _) |  |\s
                    | (_{;}_) | `-'`-'|___\\ `"/  \\  ) / \\    `-'  |\\    `-'  | \\ `"/  \\  ) / |  (_,_)  |  |\s
                    |  (_,_)  /  |        \\'. \\_/``".'   \\       /  \\       /   '. \\_/``".'  |  |      |  |\s
                    /_______.'   `--------`  '-----'      `-...-'    `-...-'      '-----'    '--'      '--'""";
    private static final ArrayList<Task> LIST_OF_TASKS = new ArrayList<Task>();
    private static final int LENGTH_OF_TODO = 5;
    private static final int LENGTH_OF_DEADLINE = 9;
    private static final int LENGTH_OF_EVENT = 6;
    private static final String FILE_PATH = "./data/blossom.txt";

    public static void loadTasks() throws BlossomException {
        File f = new File(FILE_PATH); // create a File for the given file path
        try (Scanner s = new Scanner(f)) {
            while (s.hasNextLine()) {
                String line = s.nextLine();
                addTaskFromFile(line);
            }
        } catch (BlossomException | FileNotFoundException e) {
            System.out.println("Data file not found! Creating a new one....");
            new File("./data").mkdirs();
        }
    }

    public static void saveTasks() {
        try (FileWriter fw = new FileWriter(FILE_PATH, false)) {
            for (Task item : LIST_OF_TASKS) {
                fw.write( item.toFileFormat()+ "\n");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while trying to save tasks to file.");
        }
    }

    public static void addTaskFromFile(String fileInput) throws BlossomException {
        String[] parts = fileInput.split("\\|");
        String type = parts[0].trim();
        boolean isDone = Boolean.parseBoolean(parts[1].trim());
        String description = parts[2].trim();
        switch (type) {
        case "T":
            Todo todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            LIST_OF_TASKS.add(todo);
            break;
        case "D":
            String by = parts[3].trim();
            Deadline deadline = new Deadline(description, by);
            if (isDone) {
                deadline.markAsDone();
            }
            LIST_OF_TASKS.add(deadline);
            break;
        case "E":
            String from = parts[3].trim();
            String to = parts[4].trim();
            Event event = new Event(description, from, to);
            if (isDone) {
                event.markAsDone();
            }
            LIST_OF_TASKS.add(event);
            break;
        }
    }


    public static void printItems() {
        // Print items in order
        System.out.println("Try hard to get these tasks done~~ ");
        int orderInList = 1;
        System.out.println(HORIZONTAL_LINE);
        for(Task item : LIST_OF_TASKS) {
            System.out.println(orderInList+ ". " + item.toString());
            orderInList++;
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public static void markAndUnmarkItem(int itemIndex, String action) {
        itemIndex--;
        // If this item is marked - change boolean
        Task item = LIST_OF_TASKS.get(itemIndex);
        if (item != null) {
            System.out.println(HORIZONTAL_LINE);
            if(action.equalsIgnoreCase("mark")) {
                item.markAsDone();
                System.out.println("Yayy~~ Good job in getting this done!");
                System.out.println(item.toString());
            } else {
                item.markAsUndone();
                System.out.println("Hope you get this done soon! :D");
                System.out.println(item.toString());
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void addTask (String input) throws BlossomException {
        System.out.println(HORIZONTAL_LINE);
        catchInputErrors(input);
        Task item = createTask(input);
        System.out.println("Ok!! I've added this task :D");
        System.out.println(item.toString());
        LIST_OF_TASKS.add(item);
        printNumberOfTasks();
        System.out.println(HORIZONTAL_LINE);
    }

    public static Task createTask(String input) throws BlossomException {
        Task item = new Task(input); // Default item if unspecified task type
        if(input.contains("todo")) {
            item = new Todo(input.substring(LENGTH_OF_TODO));
        } else if (input.contains("deadline")) {
            String[] parts = input.substring(LENGTH_OF_DEADLINE).split(" /by ");
            item = new Deadline(parts[0], parts[1]);
        } else if (input.contains("event")) {
            String[] parts = input.substring(LENGTH_OF_EVENT).split(" /from | /to ");
            if(parts.length < 3) throw new BlossomException("Event must include both start and end times.");
            item = new Event(parts[0], parts[1], parts[2]);
        } else {
            throw new BlossomException("Sorry~~ idk what that means ( • ᴖ • ｡)");
        }
        return item;
    }

    public static void catchInputErrors(String input) throws BlossomException {
        if (input == null || input.trim().isEmpty()) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a task cannot be empty~~ ");
        } else if (input.trim().equals("todo")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a todo cannot be empty~~ ");
        } else if (input.trim().equals("deadline")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of a deadline cannot be empty~~ ");
        } else if (input.trim().equals("event")) {
            throw new BlossomException("Oopsie!!! (;° ロ°) The description of an event cannot be empty~~ ");
        }
    }
    public static void printIntro() {
        System.out.println(LOGO + "\n" +"Hello, I'm Blossom! ⸜(｡˃ ᵕ ˂ )⸝♡");
        System.out.println("Your wish is my command (シ_ _ )シ");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void deleteTask(int indexOfTask) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Removing this task now! ᕙ(  •̀ ᗜ •́  )ᕗ ");
        System.out.println(LIST_OF_TASKS.get(indexOfTask-1).toString());
        printNumberOfTasks();
        System.out.println(HORIZONTAL_LINE);
        LIST_OF_TASKS.remove(indexOfTask-1);
    }

    public static void printNumberOfTasks() {
        System.out.println("Now you have " + LIST_OF_TASKS.size() + " tasks in the list.");
    }


    public static void main(String[] args) {
        printIntro();
        try {
            loadTasks();
        } catch (BlossomException e) {
            throw new RuntimeException(e);
        }
        Scanner input = new Scanner(System.in);
        // Repeatedly takes in input until it's a key word
        while(input.hasNext()) {
            String line = input.nextLine();
            if(!line.equalsIgnoreCase("bye")) {
                if(line.equalsIgnoreCase("list")) {
                    printItems();
                }
                else if(line.contains("mark") || line.contains("unmark")) {
                    // Call the unmark and mark function
                    String[] parsedLine = line.split(" ");
                    markAndUnmarkItem(Integer.parseInt(parsedLine[1]), parsedLine[0]);
                }
                else if (line.contains("delete")) {
                    String[] parsedLine = line.split(" ");
                    deleteTask(Integer.parseInt(parsedLine[1]));
                }
                else {
                    try {
                        addTask(line);
                    } catch (BlossomException e) {
                        System.out.println(HORIZONTAL_LINE);
                        System.out.println(e.getMessage());
                        System.out.println(HORIZONTAL_LINE);
                    }
                }
            } else {
                System.out.println("Bye~~~ Come visit me soon! (๑>◡<๑)");
                System.out.println(HORIZONTAL_LINE);
                input.close();
                saveTasks();
                System.exit(0);
            }
        }
    }
}
