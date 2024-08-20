package esme;

public class Esme {
    public String esmeLogo = " _____                    \n" +
            "| ____|___ _ __ ___   ___ \n" +
            "|  _| / __| '_ ` _ \\ / _ \\ \n" +
            "| |___\\__ \\ | | | | |  __/ \n" +
            "|_____|___/_| |_| |_|\\___| \n";

    public void displayLine () {
        System.out.println("----------------------------------------------");
    }
    public void greet () {
        displayLine();
        System.out.println("Hello! I'm");
        System.out.println(esmeLogo);
        System.out.println("What can I do for you?");
        displayLine();
        System.out.println("Bye. Hope to see you again soon!");
        displayLine();
    }
}
