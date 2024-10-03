import java.util.ArrayList;

/**
 * The SearchEngine allows for searching Tasks with a query string based on their description.
 */

public class SearchEngine {
    /**
     * Searches for tasks that match the given query.
     * The query is broken into single words, and the tasks are matched
     * if their description contains any of the words.
     *
     * @param query The search query string.
     * @return An ArrayList of tasks that match the query.
     */
    public static ArrayList<Task> search (String query) {
        ArrayList<Task> result = new ArrayList<>();
            String[] wordsOfQuery = query.split(" ");
            for (String word : wordsOfQuery) {
                for (Task task : TaskList.tasks) {
                    if (match(task, word) && !result.contains(task)) {
                        result.add(task);
                    }
                }
            }
        return result;
    }

    /**
     * Checks if a task's description contains the specified non-empty word.
     *
     * @param task The task to check.
     * @param word The word to match against the task's description.
     * @return true if the task's description contains the non-empty word, false otherwise.
     */
    private static boolean match(Task task, String word) {
        return !word.isEmpty() && task.description.contains(word);
    }
}
