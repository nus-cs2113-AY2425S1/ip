package yapper;

// Main function for Yapper
public class Yapper {
    public static final int maxCapacity = 100;

    public static void main(String[] args) {
        // Initialize
        TaskManager taskManager = new TaskManager(maxCapacity);
        // Startup ChatBot Program
        UserInterface.startYappin(taskManager);
    }
}
