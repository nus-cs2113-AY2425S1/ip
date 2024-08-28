import java.util.Scanner;

public class Quag {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
//        String logo = " ____        _        \n"
//                + "|  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "|____/ \\__,_|_|\\_\\___|\n";

//        String logo = " ____        _        \n"
//                + "/  _ \\ _   _| | _____ \n"
//                + "| | | | | | | |/ / _ \\\n"
//                + "| |_| | |_| |   <  __/\n"
//                + "\\____ \\__,_|_|\\_\\___|\n";
        System.out.println("_______________________________________");
        System.out.println("Hello! I'm QUAG!");
        System.out.println("What can I do for you today? quag");
        System.out.println("_______________________________________");

        while (true) {
            userInput = scanner.nextLine();

            if (isExitCommand(userInput)) {
                exitProgram();
                break;
            } else if (isListCommand(userInput)) {
                displayList();
            } else if (isMarkCommand(userInput)) {
                markTask(userInput);
            }else if (isUnMarkCommand(userInput)) {
                unmarkTask(userInput);
            } else {
                addTask(userInput);
            }
        }
    }

    private static boolean isExitCommand(String userInput) {
        return userInput.equalsIgnoreCase("quag");
    }

    private static void exitProgram() {
        System.out.println("_______________________________________");
        System.out.println("See you soon! quag quag");
        System.out.println("_______________________________________");
    }

    private static boolean isListCommand(String userInput) {
        return userInput.equalsIgnoreCase("list");
    }

    private static void displayList() {
        ListUtils.displayList();
    }

    private static boolean isMarkCommand(String userInput) {
        return userInput.toLowerCase().startsWith("mark");
    }
    private static boolean isUnMarkCommand(String userInput) {
        return userInput.toLowerCase().startsWith("unmark");
    }

    private static void markTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                ListUtils.markAsDone(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println("That's not a quaggin number!!");
            }
        } else {
            System.out.println("There's no quaggin number!!");
        }
    }
    private static void unmarkTask(String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length >= 2) {
            try {
                int taskIndex = Integer.parseInt(parts[1]);
                ListUtils.markAsNotDone(taskIndex);
            } catch (NumberFormatException e) {
                System.out.println("That's not a quaggin number!!");
            }
        } else {
            System.out.println("There's no quaggin number!!");
        }
    }

    private static void addTask(String userInput) {
        ListUtils.addToList(userInput);
    }
}