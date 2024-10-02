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
        System.out.println(Arrays.toString(parts));
        switch (parts[0]) {
        case "T":
            addTask(new Todo(parts[2]));
            if (parts[1].trim().equals("1")) {
                attemptToMarkTask(String.valueOf(list.size()));
            }
            break;
        case "D":
            addTask(new Deadline(parts[2], parts[3]));
            if (parts[1].trim().equals("1")) {
                attemptToMarkTask(String.valueOf(list.size()));
            }
            break;
        case "E":
            addTask(new Event(parts[2], parts[3], parts[4]));
            if (parts[1].trim().equals("1")) {
                attemptToMarkTask(String.valueOf(list.size()));
            }
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

    public void addTask(Task newTask) {

        System.out.println("I've added this to your list: ");
        newTask.printTask();
        list.add(newTask);
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
        } catch (Exception e) {
            //Treat invalid command as a task
            System.out.println("Please use a valid index");
        }
    }

    public void attemptToUnmarkTask(String listIndex) {
        try {
            int index = Integer.parseInt(listIndex);
            list.get(index - 1).markAsNotDone();
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
