package main.java;

import main.java.Ui;
import main.java.Parser;
import main.java.TaskList;
import main.java.Storage;

import java.util.Scanner;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Ran {
    private Storage storage; 
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    public Ran(String directory) {
        tasks = new TaskList();
        ui = new Ui();
        ui.greet();

        try {
            storage = new Storage(directory);
        } catch (IOException e) {
            System.out.println("Unfortunately I, Ran, have ran into an issue accessing your data files.");
            return;
        } 
        try {
            storage.loadTasks(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("That is strange, I swear I thought your data file exists...");
            return;
        }
    }

    public void run() {
        boolean isTerminated = false;
        parser = new Parser(storage);
        // Take in user input from the terminal
        String input;
        Scanner in = new Scanner(System.in);

        while (!isTerminated) {
            input = in.nextLine();
            isTerminated = parser.processInput(input, tasks);
        }

        ui.bidFarewell();
    }

    public static void main(String[] args) {
        new Ran("./data").run();
    }
}
