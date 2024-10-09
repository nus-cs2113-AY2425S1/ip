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
        try {
            File dataFile = new File(dataFilePath);
            Scanner scanner = new Scanner(dataFile);
            while (scanner.hasNextLine()) {
                String inputLine = scanner.nextLine();
                try {
                    tempTaskList = handleDataLine(inputLine, tempTaskList);

                } catch (Exception e) {
                    ui.printMessage("ERROR READING LINE: " + inputLine);
                }
            }
            ui.printMessage("Data File Read");
        } catch (FileNotFoundException e) {
            ui.printMessage("Data File does not exist");
            return null;
        }

        return tempTaskList;
    }

    private TaskList handleDataLine(String inputLine, TaskList tempTaskList) {
        String[] lineSegments = inputLine.split(" /isdone ");
        String line = lineSegments[0];
        boolean isDone;
        if (lineSegments[1].trim().equals("true")) {
            isDone = true;
        } else if (lineSegments[1].trim().equals("false")) {
            isDone = false;
        } else {
            ui.printMessage("ERROR READING LINE: " + inputLine);
            return tempTaskList;
        }
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
