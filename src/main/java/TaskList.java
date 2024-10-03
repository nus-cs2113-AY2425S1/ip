import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public void loadFile(String path) throws IOException {
        File f = new File(path);
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            parseLine(s.nextLine());
        }
    }

    public void parseLine(String line) {
        String[] parts = line.split(" \\| ");
        switch (parts[0]) {
        case "T":
            Todo todoTask = new Todo(parts[2]);
            if (parts[1].trim().equals("1")) {
                todoTask.markAsDone();
            }
            loadTask(todoTask);
            break;
        case "D":
            Deadline deadlineTask = new Deadline(parts[2], parts[3]);
            if (parts[1].trim().equals("1")) {
                deadlineTask.markAsDone();
            }
            loadTask(deadlineTask);
            break;
        case "E":
            Event eventTask = new Event(parts[2], parts[3], parts[4]);
            if (parts[1].trim().equals("1")) {
                eventTask.markAsDone();
            }
            loadTask(eventTask);
            break;
        }
    }

    public void saveFile(String path) throws IOException{
        FileWriter fw = new FileWriter(path,false);
        StringBuilder toWrite = new StringBuilder();
        for (Task task:list){
            toWrite.append(task.convertToSaveFormat()).append(System.lineSeparator());
        }
        fw.write(toWrite.toString());
        fw.close();
    }

    public void loadTask(Task task) {
        list.add(task);
    }

    public void addTask(Task newTask) {
        System.out.println("I've added this to your list: ");
        newTask.printTask();
        loadTask(newTask);
    }

    public void printTaskList() {
        System.out.println("Here is your list of tasks:");
        for (int i = 1; i <= list.size(); i++) {
            System.out.printf("%d. ", i);
            list.get(i - 1).printTask();
        }
    }

    public void attemptToMarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            list.get(index-1).printTask();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToUnmarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsNotDone();
            System.out.println("Nice! I've marked this task as not done:");
            list.get(index-1).printTask();
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToDelete(String listIndex){
        try {
            int index = Integer.parseInt(listIndex);
            list.remove(index-1);
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }
}
