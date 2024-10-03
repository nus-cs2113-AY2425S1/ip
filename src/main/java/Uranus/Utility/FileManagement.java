package Uranus.Utility;

import Uranus.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Provides file management utilities to save and load tasks to/from a file.
 * The class includes methods for saving the current task list to a file and loading tasks from a backup file.
 */
public abstract class FileManagement extends Functions{

    /**
     * Saves the current task list to a file named "tasksBackup.txt".
     * Save the task list as a command in the file.
     * If the file does not exist, a new file will be created.
     * If there is an error during the file saving process, an error message will be printed.
     */
    public static void saveFile() {
        // Checks if the file exist. If not, create new file
        try (FileWriter writer = new FileWriter("tasksBackup.txt")) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.getCommandInput() + System.lineSeparator());
                if (task.getStatusIcon().equals("X")){
                    writer.write("mark " + (i + 1) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            Ui.print("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the tasks from the file "tasksBackup.txt".
     * If the file exists, it reads each line and processes the commands to restore tasks.
     * If the file is not found, an error message is printed and a new backup file is created.
     * The method also temporarily redirects system output to prevent unwanted output during the task loading process.
     */
    public static void load() {
        File f = new File("tasksBackup.txt");
        PrintStream out = System.out;
        // Redirect System.out to a dummy stream (this solution was from gpt)
        System.setOut(new PrintStream(new OutputStream() {
            @Override
            public void write(int b) {}
        }));

        try (Scanner s = new Scanner(f)) {
            while (s.hasNext()) {
                Parser.processCommand(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.print("Error loading tasks: " + e.getMessage(), "Backup task.txt file added");
        }

        System.setOut(out);
    }
}
