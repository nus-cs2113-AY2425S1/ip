import java.util.ArrayList;

public class SearchEngine {
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

    private static boolean match(Task task, String word) {
        return !word.isEmpty() && task.description.contains(word);
    }
}
