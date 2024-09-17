package Uranus.Utility;

import Uranus.Tasks.Task;

import java.io.*;
import java.util.Scanner;

public abstract class FileManagement extends Functions{

    public static void save() {

        // Checks if the file exist. If not, create new file
        try (FileWriter writer = new FileWriter("savedData/tasks.txt")) {
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                writer.write(task.getCommandInput() + System.lineSeparator());
                if (task.getStatusIcon().equals("X")){
                    writer.write("mark " + (i + 1) + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            print("Error saving tasks: " + e.getMessage());
        }
    }

    public static void load() {
        File f = new File("savedData/tasks.txt");
        PrintStream out = System.out;
        try (Scanner s = new Scanner(f)) {

            // Redirect System.out to a dummy stream (this solution was from gpt)
            System.setOut(new PrintStream(new OutputStream() {
                @Override
                public void write(int b) {}
            }));

            while (s.hasNext()) {
                Functions.processCommand(s.nextLine());
            }
            System.setOut(out);
        } catch (FileNotFoundException e) {
            System.setOut(out);
            print("Error loading tasks: " + e.getMessage());
            print("Backup task.txt file added");
        }
    }
}
