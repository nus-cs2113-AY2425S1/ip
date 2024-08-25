import esme.Esme;

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
            } else {
                esme.echo(line);
            }
        }
    }
}
