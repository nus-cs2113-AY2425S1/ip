import java.util.List;

public class TaskDeleter {

    // Method to delete a task based on user input
    public static void deleteTask(List<Task> itemList, String input) {
        try {
            // Split the input to extract the task index
            String[] parts = input.split(" ");

            // Check if the input has the task number
            if (parts.length < 2) {
                System.out.println("Error: No task number provided. Please specify a task number.");
                return;
            }

            // Parse the task index provided by the user
            int indexToDelete = Integer.parseInt(parts[1]) - 1; // Adjust for 0-based indexing

            // Check if the index is valid within the list bounds
            if (indexToDelete < 0 || indexToDelete >= itemList.size()) {
                System.out.println("Error: Task number out of range. Please enter a valid task number.");
                return;
            }

            // Retrieve and remove the task from the list
            Task removedTask = itemList.remove(indexToDelete);

            // Provide confirmation of the removed task
            System.out.println("Noted. I've removed this task: " + removedTask);

            // Provide feedback on the remaining tasks in the list
            System.out.println("Now you have " + itemList.size() + " tasks in the list.");

            // Optionally, display the updated task list
            System.out.println("Here are your updated tasks:");
            for (int i = 0; i < itemList.size(); i++) {
                System.out.println((i + 1) + ". " + itemList.get(i));
            }

        } catch (NumberFormatException e) {
            // Handle non-integer input for the task number
            System.out.println("Error: Invalid task number. Please enter a valid number.");
        }
    }
}