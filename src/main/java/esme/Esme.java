package esme;

public class Esme {
    private TaskList taskList;

    public Esme() {
        taskList = new TaskList();
    }

    public void addTaskToList(Task task) {
        displayLine(true);
        taskList.addTask(task);
        System.out.println("\tThe stars have aligned and " + task.getName() + " is now part of your destiny!" );
        displayLine(true);
    }

    public boolean isIndexValid(int index) {
        return index <= taskList.numberOfTasks() && index >= 0;
    }

    public void markTaskInList(int taskIndex) {
        taskList.markTask(taskIndex - 1);
        displayLine(true);
        System.out.println("\tOutstanding! This task is marked as done, and your destiny shines brighter.");
        System.out.println("\t  [X] " + taskList.getTask(taskIndex - 1).getName());
        displayLine(true);
    }

    public void unmarkTaskInList(int taskIndex) {
        taskList.unmarkTask(taskIndex - 1);
        displayLine(true);
        System.out.println("\tFear not, for this task remains unfinished. We shall conquer it in due time!");
        System.out.println("\t  [] " + taskList.getTask(taskIndex - 1).getName());
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
            System.out.println("\t────────────────────────────────────────────────────────────────────────────────────────────────────");
        } else {
            System.out.println("────────────────────────────────────────────────────────────────────────────────────────────────────");
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

    public void promptEmptyInput() {
        System.out.println("The stars are silent... Please share your thoughts so I can guide you on your path.");
    }

}
