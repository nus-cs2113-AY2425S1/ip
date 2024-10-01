package Uranus.Utility;

import Uranus.Tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public abstract class FileManagement extends Functions{

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
                Functions.processCommand(s.nextLine());
            }
        } catch (FileNotFoundException e) {
            Ui.print("Error loading tasks: " + e.getMessage(), "Backup task.txt file added");
        }

        System.setOut(out);
    }
}
