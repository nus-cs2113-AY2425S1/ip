package org.ajay;

import java.util.Scanner;
import org.ajay.exceptions.EmptyArgumentException;
import org.ajay.exceptions.IllegalArgumentException;
import org.ajay.task.TaskList;
import org.ajay.ui.Ui;
import org.ajay.utils.Parser;
import org.ajay.utils.Storage;

public class Jarvis {




    public static void main(String[] args) {
        String lineBufferString = ""; // Buffer to store the input from the user
        Scanner in = new Scanner(System.in); // Scanner object to read input from the user

        Ui.printLogo();
        Ui.printGreetingMsgs();

        try {
            Storage.loadTaskList(TaskList.taskList);
        } catch (EmptyArgumentException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        Parser.readInput(in, lineBufferString); // Read the input from the user
    }
}
