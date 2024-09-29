package ui;

import java.util.Scanner;

public class UI {
    Scanner in;

    public UI () {
        this.in = new Scanner(System.in);
    }

    public String readCommand() {
        return in.nextLine();
    }

    public void showWelcome() {
        showLine();
        System.out.println("""               
                \tHello! I'm XiaoMe
                \tWhat can I do for you?
                """);
        showLine();
    }

    public void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    public void printToUser(String commandInput) {
        showLine();
        System.out.println(commandInput);
        showLine();
    }
}
