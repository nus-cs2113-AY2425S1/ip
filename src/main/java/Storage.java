import customexceptions.DeadlineConstructorException;
import customexceptions.EventConstructorException;
import customexceptions.ToDoConstructorException;
import taskpackage.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String dataFilePath;
    private UI ui;

    public Storage(String dataFilePath, UI ui) {
        this.dataFilePath = dataFilePath;
        this.ui = ui;
    }

    public TaskList readData() {

        TaskList tempTaskList = new TaskList();

        File dataFile = new File(dataFilePath);
        try {
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                String[] lineSegments = inputLine.split(" /isdone ");
                String line = lineSegments[0];
                boolean isDone = Boolean.parseBoolean(lineSegments[1]);
                if (line.startsWith("todo ")) {
                    try {
                        new ToDo(line, tempTaskList, false); // Create a new ToDo object
                    } catch (ToDoConstructorException e) {
                        // Handle custom ToDo exception
                        ui.printMessage("CORRUPTED: " + line);
                        tempTaskList.deleteLatestTask();
                    }
                }
                // Handle "deadline" command to create a new Deadline task
                else if (line.startsWith("deadline ")) {
                    try {
                        new Deadline(line, tempTaskList, false); // Create a new Deadline object
                    } catch (DeadlineConstructorException e) {
                        // Handle custom Deadline exception
                        ui.printMessage("CORRUPTED: " + line);
                        tempTaskList.deleteLatestTask();
                    }
                }
                // Handle "event" command to create a new Event task
                else if (line.startsWith("event ")) {
                    try {
                        new Event(line, tempTaskList, false); // Create a new Event object
                    } catch (EventConstructorException e) {
                        // Handle custom Event exception
                        ui.printMessage("CORRUPTED: " + line);
                        tempTaskList.deleteLatestTask();
                    }
                }
                else {
                    ui.printMessage("CORRUPTED: " + line);
                }

                if (isDone) {
                    tempTaskList.markLatestTask();
                }
            }
            ui.printMessage("Data File Read");
        } catch (FileNotFoundException e) {
            ui.printMessage("Data File does not exist");
            return null;
        }

        return tempTaskList;
    }

    public void writeDate(TaskList taskList) {
        try {
            FileWriter writer = new FileWriter(dataFilePath);
            for (int i = 0; i < taskList.size(); i++) {
                writer.write(taskList.dataFileEntry(i));
            }
            writer.close();
            ui.printMessage("Data File Written");
        } catch (IOException e) {
            ui.porFavorMessage(e.getMessage());
        }
    }
}
