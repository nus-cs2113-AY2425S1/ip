import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import commands.Command;
import exceptions.IllegalCommandException;
import parser.Parser;
import storage.TaskDecoder;
import storage.TaskEncoder;
import ui.Ui;


public class Nateh {
    private static Ui ui;
    public static void main(String[] args) {
        TaskEncoder.createFile();
        String input = "";
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list;
        try {
            list = TaskDecoder.readTasks();
        } catch (IOException e) {
            list = new ArrayList<>();
        }
        while (!input.equals("bye")) {
            try {
                input = ui.receiveCommand();
                Command command = Parser.parse(input, list);
                command.execute(list, );
            } catch (IllegalCommandException e) {

            }
        }
    }
}
