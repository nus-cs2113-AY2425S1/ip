package Uranus.Utility;

import java.util.Scanner;
import java.util.ArrayList;
import Uranus.Tasks.Task;

public abstract class Functions {

    protected static final Scanner in = new Scanner(System.in);
    protected static final String SEPARATOR = "_________________________________________________________";
    protected static ArrayList<Task> taskList = new ArrayList<>();

    public Functions() {}

    public static void manageTasks() {
        FileManagement.load();
        while (true) {
            String input = in.nextLine();
            Parser.processCommand(input);
            FileManagement.saveFile();
        }
    }
}
