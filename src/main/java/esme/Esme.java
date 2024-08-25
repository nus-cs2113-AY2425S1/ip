package esme;

public class Esme {
    private TaskList taskList;

    public Esme() {
        taskList = new TaskList();
    }

    public void addTask(Task task) {
        displayLine(true);
        taskList.addTask(task);
        System.out.println("\tThe stars have aligned and " + task.getName() + " is now part of your destiny!" );
        displayLine(true);
    }

    public void printTaskList () {
        taskList.printTaskList();
        displayLine(true);
    }

    public String esmeLogo = " _____                    \n" +
            "| ____|___ _ __ ___   ___ \n" +
            "|  _| / __| '_ ` _ \\ / _ \\ \n" +
            "| |___\\__ \\ | | | | |  __/ \n" +
            "|_____|___/_| |_| |_|\\___| ";

    public void displayLine (boolean hasIndent) {
        if (hasIndent) {
            System.out.println("\t──────────────────────────────────────────────────");
        } else {
            System.out.println("──────────────────────────────────────────────────");
        }
    }
    public void greet () {
        displayLine(false);
        System.out.println("Hello! I'm");
        System.out.print(esmeLogo);
        System.out.println(", the Astrologer. The cosmos whispers its secrets to me.");
        displayLine(false);
        System.out.println("How may I assist you today? The stars and I are at your service.");
        displayLine(false);
    }
    public void farewell () {
        displayLine(true);
        System.out.println("\tAu revoir, mon ami! May the cosmos continue to weave a tapestry of fortune in your favor!");
        displayLine(true);
    }

    public void echo (String message) {
        displayLine(true);
        System.out.println("\t" + message);
        displayLine(true);
    }
}
