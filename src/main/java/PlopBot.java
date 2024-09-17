import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAdjusters;
import java.util.Scanner;
import java.util.ArrayList;

public class PlopBot {
    // Static variables
    private static final String name = "plopBot";
    private static final String HORIZONTAL_LINE = "//" + "\u2500".repeat(50);
    private static final String ECHO_LINE = "    " + "\u2500".repeat(48);
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks;
    private static String userName;

    public static void main(String[] args) {
        welcomeMessage();
        tasks = new ArrayList<>();
        inputReader();
    }

    // Welcome message when the user runs the program.
    static void welcomeMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm " + name + ".");
        System.out.println("What can I do for you today?\n");
    }

    // Farewell message when the user runs the program.
    static void farewellMessage() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Thank you for choosing " + name + ". Have a great day!\n");
    }

    /**
     * Reads the user's inputs and calls processInput().
     */
    static void inputReader() {
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            // processInput(userInput);

            if (processInput(userInput)) {
                break;
            }
        }

        scanner.close();
    }

    /**
     * Processes the user's inputs.
     * @param userInput
     */
    static boolean processInput(String userInput) {
       if (userInput.isEmpty()) {
           printError("The input cannot be empty.");
           return false;
       }

        if (userInput.toLowerCase().startsWith("mark") || userInput.toLowerCase().startsWith("unmark")) {
            toggleMark(userInput);
            return false;
        }

        String[] parts = userInput.split(" ", 2);
        String command = parts[0].toLowerCase();
        String details = parts.length > 1 ? parts[1] : "";

        switch (command) {
            case "todo":
                addTodo(details);
                break;
            case "deadline":
                addDeadline(details);
                break;
            case "event":
                addEvent(details);
                break;
            case "list":
                list();
                break;
            case "help":
                showHelp();
                break;
            case "clear":
                tasks.clear();
                System.out.println("All tasks cleared.");
                break;
            case "bye", "exit", "quit":
                farewellMessage();
                return true;
            default:
                printError("Unknown command. Type 'help' for a list of commands.");
                break;
        }
        return false;
    }

    private static void addTodo(String details) {
        if (details.isEmpty()) {
            printError("The description of a To-Do task cannot be empty.");
            return;
        }

        Task newToDo = new Task(details);
        tasks.add(newToDo);
        System.out.println(ECHO_LINE);
        System.out.println("    Added the To-Do task: \n      " + newToDo.toString());
        System.out.printf("    You now have %d tasks in your list.\n", tasks.size());
        System.out.println(ECHO_LINE);
        System.out.println("");
    }

    private static void addDeadline(String details) {
        try {
            String[] parts = details.split(" /by ", 2);

            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid deadline format.");
            }

            String description = parts[0].trim();
            String dueDateString = parts[1].trim();

            if (description.isEmpty() || dueDateString.isEmpty()) {
                printError("The descriptions of a Deadline task cannot be empty.");
                return;
            }


            LocalDate dueDate = parseDateString(dueDateString);
            Task newDeadline = new Task(description, dueDate);
            tasks.add(newDeadline);

            System.out.println(ECHO_LINE);
            System.out.println("    Added the Deadline task: \n      " + newDeadline.toString());
            System.out.printf("    You now have %d tasks in your list.\n", tasks.size());
            System.out.println(ECHO_LINE);
        }
        catch (Exception e) {
            printError(e.getMessage() + "\n    Usage: deadline description /by DATE" +
                                        "\n    DATE can be 'Sunday', 'Mon', 'Tuesday', or 'YYYY-MM-DD'");
        }
        System.out.println("");
    }


    private static void addEvent(String details) {
        try {
            String[] parts = details.split(" /from | /to ");

            if (parts.length != 3) {
                throw new IllegalArgumentException("Invalid event format.");
            }

            String description = parts[0].trim();
            String startTimeString = parts[1].trim();
            String endTimeString = parts[2].trim();

            if (description.isEmpty() || startTimeString.isEmpty() || endTimeString.isEmpty()) {
                printError("The descriptions of an Event task cannot be empty.");
                return;
            }

            LocalDateTime startTime = parseDateTimeString(startTimeString, null);
            LocalDateTime endTime;

            if (!endTimeString.contains(" ")) {
                LocalDate startDate = startTime.toLocalDate();
                LocalTime endTime2 = parseTimeString(endTimeString);
                endTime = LocalDateTime.of(startDate, endTime2);
            }
            else {
                endTime = parseDateTimeString(endTimeString, startTime);
            }

            Task newEvent = new Task(description, startTime, endTime);
            tasks.add(newEvent);
            System.out.println(ECHO_LINE);
            System.out.println("    Added the Event task: \n      " + newEvent.toString());
            System.out.printf("    You now have %d tasks in your list.\n", tasks.size());
            System.out.println(ECHO_LINE);
            System.out.println("");
        }
        catch (Exception e) {
            printError(e.getMessage() + "\n    Usage: event description /from START_TIME /to END_TIME" +
                                        "\n    TIME can be 'Mon 2pm', 'Tuesday 14:00', or 'YYYY-MM-DD HH:MM'");
        }
    }

    /**
     * Lists the user's inputs.
     */
    static void list() {
        System.out.println(ECHO_LINE);
        System.out.println("    Your List:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("    " + task.toString());
        }
        System.out.println("");
    }

    /**
     * Toggles the status of the task specified by the user.
     * @param userInput
     */
    static void toggleMark(String userInput) {
        try {
            int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

            if (taskNumber >= 0 && taskNumber < tasks.size()) {
                Task task = tasks.get(taskNumber);
                task.toggleStatus();
                System.out.println(ECHO_LINE);
                System.out.printf("    Successfully updated task %d.\n", taskNumber + 1);
                System.out.println("    Your Updated List:");

                for (int i = 0; i < tasks.size(); i++) {
                    Task t = tasks.get(i);
                    System.out.println("    " + t.toString());
                }

                System.out.println("");
            }
        }
        catch (NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("Invalid command. Please use 'mark <task number>'.");
        }
    }

    /**
     * Parse helper method to parse date strings for deadline tasks.
     * @param dateString
     * @return
     */
    private static LocalDate parseDateString(String dateString) {
        LocalDate now = LocalDate.now();
        try {
            return LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
        }
        catch (DateTimeParseException e) {
            String[] shortDays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

            for (int i = 0; i < shortDays.length; i++) {
                if (shortDays[i].equalsIgnoreCase(dateString)) {
                    return now.with(TemporalAdjusters.next(DayOfWeek.of(i + 1)));
                }
            }
            try {
                return now.with(TemporalAdjusters.next(DayOfWeek.valueOf(dateString.toUpperCase())));
            }
            catch (IllegalArgumentException ex) {
                switch (dateString.toLowerCase()) {
                    case "today":
                        return now;
                    case "tomorrow":
                        return now.plusDays(1);
                    default:
                        throw new IllegalArgumentException("Unable to parse date: " + dateString);
                }
            }
        }
    }

    /**
     * Parse helper methods to parse date and time strings for event tasks.
     * @param dateTimeString
     * @return
     */
    private static LocalDateTime parseDateTimeString(String dateTimeString) {
        return parseDateTimeString(dateTimeString, null);
    }

    private static LocalDateTime parseDateTimeString(String dateTimeString, LocalDateTime referenceTime) {
        LocalDate now = LocalDate.now();
        try {
            return LocalDateTime.parse(dateTimeString, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        }
        catch (DateTimeParseException e) {
            String[] parts = dateTimeString.split(" ", 2);

            if (parts.length == 2) {
                LocalDate date = parseDateString(parts[0]);
                LocalTime time = parseTimeString(parts[1]);
                return LocalDateTime.of(date, time);
            }
            else if (parts.length == 1) {
                LocalTime time = parseTimeString(parts[0]);
                LocalDate date = (referenceTime != null) ? referenceTime.toLocalDate() : now;
                return LocalDateTime.of(date, time);
            }
        }
        throw new IllegalArgumentException("Unable to parse date and time: " + dateTimeString);
    }

    private static LocalTime parseTimeString(String timeString) {
        try {
            return LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm"));
        }
        catch (DateTimeParseException e) {
            try {
                return LocalTime.parse(timeString.toUpperCase(), DateTimeFormatter.ofPattern("ha"));
            }
            catch (DateTimeParseException e2) {
                // Handle "2pm" format
                String formattedTime = timeString.toLowerCase();
                if (formattedTime.endsWith("am") || formattedTime.endsWith("pm")) {
                    int hour = Integer.parseInt(formattedTime.substring(0, formattedTime.length() - 2));

                    if (formattedTime.endsWith("pm") && hour < 12) {
                        hour += 12;
                    }
                    else if (formattedTime.endsWith("am") && hour == 12) {
                        hour = 0;
                    }
                    return LocalTime.of(hour, 0);
                }
                throw new IllegalArgumentException("Unable to parse time: " + timeString);
            }
        }
    }

    private static void printError (String message) {
        System.out.println(ECHO_LINE);
        System.out.println("    Oops! " + message);
        System.out.println(ECHO_LINE);
    }

    /**
     * W.I.P Method that displays a 'help menu.'
     */
    static void showHelp() {
        System.out.println("");
    }
}

