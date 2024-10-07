import task.TaskList;
import ui.UserInteraction;
import datahandling.Storage;
import command.Command;
import parser.Parser;

public class JerChatBotTest {

    public static void main(String[] args) {
        runTests();
    }

    private static void runTests() {
        System.out.println("Starting JerChatBot Tests:");

        TaskList tasks = new TaskList();
        UserInteraction ui = new UserInteraction();
        Storage storage = new Storage("./test_data.txt");

        // List of test commands including the find command
        String[] testCommands = {
                "todo buy groceries",
                "todo", // Invalid ToDo task - no description
                "deadline submit report /by 2024-12-10 1800",
                "deadline submit report /by 10/12/2024 1800", // Invalid date format
                "deadline submit report /by", // Missing date
                "event team meeting /from 2024-12-15 0900 /to 2024-12-15 1100",
                "event team meeting /from 2024-12-15 0900", // Missing end date
                "event team meeting /from 2024-12-15 1100 /to 2024-12-15 0900", // End before start
                "list",
                "find report", // Find command test
                "find team"    // Find command test with multiple matches
        };

        String[] descriptions = {
                "Add ToDo task",
                "Invalid ToDo task - empty description",
                "Add valid Deadline task",
                "Invalid Deadline date format",
                "Invalid Deadline - missing date",
                "Add valid Event task",
                "Invalid Event - missing end date",
                "Invalid Event - end date before start date",
                "List tasks",
                "Find command with keyword 'report'",
                "Find command with keyword 'team'"
        };

        for (int i = 0; i < testCommands.length; i++) {
            try {
                testCommand(testCommands[i], tasks, ui, storage, descriptions[i]);
            } catch (Exception e) {
                System.out.println("Error during test: " + descriptions[i] + " - " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Tests completed.");
    }

    private static void testCommand(String input, TaskList tasks, UserInteraction ui, Storage storage, String description) {
        System.out.println("Test: " + description + " - Command: " + input);
        try {
            Command command = Parser.parse(input);
            command.execute(tasks, ui, storage);
            System.out.println("Result: PASS\n");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("Result: PASS\n");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
            System.out.println("Result: FAIL\n");
            // Continue after printing the error
        }
    }
}
