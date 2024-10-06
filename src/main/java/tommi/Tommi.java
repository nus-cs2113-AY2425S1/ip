package tommi;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Tommi {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(System.in);

        Ui.printIntroMessage();
        Ui.readInputStrings(scanner);
        Ui.printExitMessage();
    }
}
