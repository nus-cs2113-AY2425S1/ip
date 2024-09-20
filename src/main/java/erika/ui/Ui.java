package erika.ui;

import erika.console.Console;
import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);
    private Console console = new Console();

    public static String collectUserInput() {
        String line = in.nextLine();
        line = line.trim();
        return line;
    }

}
