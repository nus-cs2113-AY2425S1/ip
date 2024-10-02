package nateh;

import nateh.classes.Deadlines;
import nateh.classes.Event;
import nateh.classes.Task;
import nateh.classes.Todo;
import nateh.commands.Command;
import nateh.exceptions.IllegalCommandException;
import nateh.parser.Parser;
import nateh.storage.TaskDecoder;
import nateh.storage.TaskEncoder;

import java.util.ArrayList;
import java.io.IOException;
import java.util.Scanner;

public class Nateh {

    public static void handleDelete(ArrayList<Task> list, int index) {

    }
    

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
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hello! I'm Nateh.Nateh\nWhat can I do for you?");
        System.out.println(Skeleton.SKELETON);
        System.out.print(Skeleton.LINE_BREAK);
        try {
            while (!input.equals("bye")) {
                input = in.nextLine();
                Command command = Parser.parse(input, list);
                command.execute(list);
            }
        } catch (IllegalCommandException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Oops?! I don't know that one >.<");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
}
