import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private static String folderPath = "./data";
    private static String filePath = folderPath+ "/duke.txt";
    private TaskList taskList;


    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }



    public static void loadFileData() throws DukeException, IOException {

        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                loadLineData(line);
            }
        } catch (FileNotFoundException e) {
            File file = new File(filePath);
            boolean succeed = file.createNewFile();
        }
    }


    //load data from string to arraylist level-7
    public static void loadLineData(String line) throws DukeException {
        String[] inputComponent = line.split(" \\| ");
        switch (inputComponent[0]) {
            case "T" -> {
                TaskList.add(new ToDo(inputComponent[1]));
                taskCount++;
                if (inputComponent[2].equals("1")) {
                    TaskList.get(taskCount - 1).markAsDone();
                }
            }
            case "D" -> {
                TaskList.add(new Deadline(inputComponent[1], inputComponent[2]));
                taskCount++;
                if (inputComponent[3].equals("1")) {
                    taskList.get(taskCount - 1).markAsDone();
                }
            }
            case "E" -> {
                TaskList.add(new Event(inputComponent[1], inputComponent[2], inputComponent[3]));
                taskCount++;
                if (inputComponent[4].equals("1")) {
                    taskList.get(taskCount - 1).markAsDone();
                }
            }
        }
    }

    //helper function to create string to push to file level-7
    public static String addLineData(Task task) throws DukeException, IOException {
        String line = "";
        if(task instanceof ToDo) {
            line += "T | " + task.getDescription() + " | ";
            if(task.isDone) {
                line += "1";
            }
            else {
                line += "0";
            }
        }
        else if(task instanceof Deadline) {
            line += "D | " + task.getDescription() + " | " + ((Deadline) task).getBy() + " | ";
            if(task.isDone) {
                line += "1";
            } else {
                line += "0";
            }
        }
        else if(task instanceof Event) {
            line += "E | " + task.getDescription() + " | " + ((Event) task).getFrom() + " | " + ((Event) task).getTo() + " | ";
            if(task.isDone) {
                line += "1";
            } else {
                line += "0";
            }
        }
        return line;
    }

    //update file data after every command for level-7
    public static void updateFileData() throws DukeException, IOException {
        try (FileWriter writer = new FileWriter(filePath, false)) {
            for(int i = 0; i < taskCount; i++) {
                writer.write(addLineData(TaskList.get(i)) + System.lineSeparator());
            }
        } catch (IOException e) {
            throw new DukeException("cannot write to file");
        }
    }

}
