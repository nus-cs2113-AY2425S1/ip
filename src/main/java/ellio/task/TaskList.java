package ellio.task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> tasks;
    private static int numberTask;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.numberTask = tasks.size();
    }

    public TaskList(){
        this.tasks = new ArrayList<>();
        this.numberTask = 0;
    }

    public static int getNumberTask() {
        return numberTask;
    }

    public static void addNumberTask(){
        numberTask++;
    }

    public static void decrementNumberTask(){
        numberTask--;
    }

    public static void addTask(Task task){
        tasks.add(task);
    }

    public static void removeTask(int index){
        tasks.remove(index);
    }

    public static Task getTask(int index){
        return tasks.get(index);
    }

    /**
     * Takes in current line input by user and extracts the task Index
     * Splits the command String and extract the second word in the string (index)
     * @param input current line input being read
     * @return index in String format
     */
    public static String getTaskIndex(String input) {
        String[] words = input.split(" ");
        return words[1];
    }
}
