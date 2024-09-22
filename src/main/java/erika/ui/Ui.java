package erika.ui;

import java.util.Scanner;

public class Ui {
    private static Scanner in = new Scanner(System.in);

    public static String collectUserInput() {
        String line = in.nextLine();
        line = line.trim();
        return line;
    }

}
