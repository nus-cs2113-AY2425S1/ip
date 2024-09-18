import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;

public class bro {

    private static final ArrayList<Task> storer = new ArrayList<>();
    private static final String FILE_PATH = "data/duke.txt";

    public static void level0() {
        System.out.println("Hello! I'm bro");
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void saveTasks() {
        try {
            File file = new File(FILE_PATH);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);

            for (Task task : storer) {
                writer.write(task.toSave() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error while saving tasks: " + e.getMessage());
        }
    }

    public static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No previous tasks found. Please create a new task list.");
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" \\| ");
                switch(parts[0]) {
                    case "T":
                        Todo todo = new Todo(parts[2]);
                        if (parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        storer.add(todo);
                        break;
                    case "D":
                        Deadline ddl = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            ddl.markAsDone();
                        }
                        storer.add(ddl);
                        break;
                    case "E":
                        Event event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        storer.add(event);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error while loading tasks: " + e.getMessage());
        }
    }

    public static void echo() {


        String line;
        Scanner in = new Scanner(System.in);
        while (true) {
            line = in.nextLine();

            if (line.equals("Bye")) {
                break;
            }
            System.out.println(line);
        }

        System.out.println("Bye. Hope to see you again soon!");

    }

    public static void addList() {

        loadTasks();

        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();

            if (line.equals("Bye")) {
                break;

            } else if (line.equals("list")) {


                for (int i = 0; i < storer.size(); i++) {
                    System.out.println((i + 1) + ". " + storer.get(i));
                }

            } else if (line.startsWith("todo")) {

                if (line.trim().length() <= 4) {
                    System.out.println("Description of a todo cannot be empty. Please provide a task description.");
                } else {
                    String description = line.substring(5);
                    Todo todo = new Todo(description);
                    storer.add(todo);
                    System.out.println("Got it. I've added this task\n " + todo);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                    saveTasks();
                }

            } else if (line.startsWith("deadline")) {
                try {
                    String[] infos = line.substring(9).split(" /by ");
                    if (infos.length < 2) {
                        throw new Exception("Deadline description / date missing.");
                    }
                    Deadline deadline = new Deadline(infos[0], infos[1]);
                    storer.add(deadline);
                    System.out.println("Got it. I've added this task\n " + deadline);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                    saveTasks();
                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            } else if (line.startsWith("event")) {
                try {
                    String[] infos = line.substring(6).split(" /from | /to ");
                    if (infos.length < 3) {
                        throw new Exception("Event description / date missing.");
                    }
                    Event event = new Event(infos[0], infos[1], infos[2]);
                    storer.add(event);
                    System.out.println("Got it. I've added this task\n " + event);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                    saveTasks();

                } catch (Exception e){
                    System.out.println(e.getMessage());
                }

            } else if (line.startsWith("delete")){
                try {
                    int taskInt = Integer.parseInt(line.split(" ")[1]) - 1;
                    if (taskInt < 0 || taskInt >= storer.size()) {
                        throw new Exception("Invalid task number.");
                    }

                    Task tasktoRemove = storer.remove(taskInt);
                    System.out.println("I've removed this task for you: ");
                    System.out.println(tasktoRemove);
                    System.out.println("Now you have " + storer.size() + " tasks in the list.");
                    saveTasks();

                } catch (Exception e){
                    System.out.println(("Error: " + e.getMessage()));
                }

            } else if (line.startsWith("mark")) {

                int task_num = Integer.parseInt(line.split(" ")[1]) - 1;
                Task task  = storer.get(task_num);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(storer.get(task_num));
                saveTasks();

            } else if (line.startsWith("unmark")) {
                int task_num = Integer.parseInt(line.split(" ")[1]) - 1;
                Task task  = storer.get(task_num);
                task.markAsUndone();
                System.out.println("Ok, I've marked this task as not done yet:");
                System.out.println(storer.get(task_num));
                saveTasks();
            }  else {
                storer.add(new Task(line));
                saveTasks();
            }

        }

        System.out.println("Bye. Hope to see you again soon!");


    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

//        level0();
//        echo();
        addList();
//        mark();

    }
}
