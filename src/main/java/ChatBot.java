import esme.Esme;
import esme.Task;

import java.util.Scanner;

public class ChatBot {
    public static void main(String[] args) {
        Esme esme = new Esme();
        esme.greet();
        String line;
        Scanner in = new Scanner(System.in);

        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                esme.farewell();
                break;
            } else if (line.equals("list")) {
                esme.printTaskList();
            } else {
                esme.addTask(new Task(line));
            }
        }
    }
}
