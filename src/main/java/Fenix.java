import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Fenix implements SampleStrings {
    private ArrayList<Task> taskArrayList = new ArrayList<>();
    protected TaskHandler taskHandler;
    protected UserInterface ui;
    protected Parser parser;

    // Constructor
    public Fenix() {
        taskHandler = new TaskHandler(this.taskArrayList);
        ui = new UserInterface(this);
        parser = new Parser(ui, taskHandler);
        ui.setParser(parser);
    }

    public int getSize() {
        return taskArrayList.size();
    }

    public int indexOfTask(Task task) {
        return taskArrayList.indexOf(task);
    }

    // Return an unmodifiable view of the taskArrayList
    public List<Task> getTaskArrayList() {
        return Collections.unmodifiableList(taskArrayList);
    }

    protected void run() {
        ui.greet();
        ui.acceptUserInput();
    }
}