package Glendon;

import Glendon.task.Deadline;
import Glendon .task.Event;
import Glendon.task.Task;
import Glendon.task.Todo;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Glendon {
    public static final int maxNumberOfTask = 100;
    public static ArrayList<Task> taskList = new ArrayList<>();
    public static int taskCounter = 0;
    public static final String filePath = "./data/text.txt";

    public static void main(String[] args) {

        try {
            SavedList.loadSavedTasks(filePath, taskList);
        } catch (FileNotFoundException e) {
            SavedList.createTaskFile(filePath, taskList);
        }

        taskCounter = taskList.size();

        printWelcomeMessage();

        Scanner in = new Scanner(System.in);
        String response = in.nextLine();

        while (response != null) {
            switch (response.split(" ")[0]) {
            case "bye":
                printBye();
                response = null;
                SavedList.saveTasks(filePath, taskList);
                break;
            case "list":
                printList();
                response = in.nextLine();
                break;
            case "mark":
                markDone(response);
                response = in.nextLine();
                break;
            case "unmark":
                unmarkDone(response);
                response = in.nextLine();
                break;
            case "todo":
                addTodo(response);
                response = in.nextLine();
                break;
            case "deadline":
                addDeadline(response);
                response = in.nextLine();
                break;
            case "event":
                addEvent(response);
                response = in.nextLine();
                break;
            case "delete":
                deleteTask(response);
                response = in.nextLine();
                break;
            default:
                try {
                    checkResponse(response);
                } catch (GlendonException e) {
                    System.out.println("    Ayo no such commands.");
                }
                response = in.nextLine();
                break;
            }
        }
    }

    private static void deleteTask(String response) {
        try {
            int taskNumber = Integer.parseInt(response.split(" ")[1]);
            Task removedTask = taskList.get(taskNumber - 1);
            taskList.remove(taskNumber - 1);
            deletedMessage(removedTask);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("    My guy you dont have that task number");
        }
    }

    private static void deletedMessage(Task removedTask) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("        " + removedTask);
        System.out.println("    Now you have " + taskList.size() + " tasks in the list");
    }

    private static void checkResponse(String response) throws GlendonException {
        ArrayList<String> taskName = new ArrayList<String>();
        taskName.add("todo");
        taskName.add("deadline");
        taskName.add("event");
        taskName.add("mark");
        taskName.add("unmark");
        taskName.add("delete");
        if(!taskName.contains(response)) {
            throw new GlendonException();
        }
    }

    private static void addEvent(String response) {
        try{
            String[] answers = response.split("/");
            String taskInfo = answers[0].substring(6);
            String startDate = answers[1].substring(5);
            String endDate = answers[2].substring(3);
            taskList.add(new Event(taskInfo, startDate, endDate));
            printAddedTask();
            taskCounter++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("    Ayo you forgot to tell me what is the task");
        }
    }

    private static void printAddedTask() {
        System.out.println("    Got it. I've added this task:");
        System.out.println("        " + taskList.get(taskCounter));
        System.out.println("    Now you have " + (taskCounter+1) + " tasks in the list.");
    }

    private static void addDeadline(String response) {
        try {
            String[] answers = response.split("/");
            String taskInfo = answers[0].substring(9);
            String taskDeadline = answers[1].substring(3);
            taskList.add(new Deadline(taskInfo, taskDeadline));
            printAddedTask();
            taskCounter++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("    Ayo you forgot to tell me what is the task");
        }
    }

    private static void addTodo(String response) {
        try {
            String taskInfo = response.substring(5);
            taskList.add(new Todo(taskInfo));
            printAddedTask();
            taskCounter++;
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("    Ayo you forgot to tell me what is the task");
        }
    }

    private static void unmarkDone(String response) {
        try {
            int taskValue = Integer.parseInt(response.split(" ")[1]) - 1;
            taskList.get(taskValue).setCompleted(false);
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("        " + taskList.get(taskValue));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    I can't read your mind, you need to tell me what your task number is.");
        }
    }

    /**
     * mark task as done
     * @param response
     */

    private static void markDone(String response) {
        try {
            int taskValue = Integer.parseInt(response.split(" ")[1]) - 1;
            taskList.get(taskValue).setCompleted(true);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("        " + taskList.get(taskValue));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("    I can't read your mind, you need to tell me what your task number is.");
        } catch (NumberFormatException e) {
            System.out.println("    Bruh tell me a number, not the task.");
        }
    }

    private static void printList() {
        if (taskList.isEmpty()) {
            System.out.println("    You are free from task");
            return;
        }
        int taskNumber = 1;
        Task currentTask;
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            currentTask = taskList.get(i);
            if (currentTask != null) {
                System.out.println("        " + taskNumber + ". " + currentTask.toString());
                taskNumber++;
            }
        }
    }
    private static void printBye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }

    private static void printWelcomeMessage() {
        String logo = " _______   _                     _\n"
                + "| ______| | |                   | |\n"
                + "| |  ___  | |  ___   _____   ___| |  _____   _____\n"
                + "| | |__ | | | / _ \\ |  _  | |  _  | |  _  | |  _  |\n"
                + "| |___| | | | | __/ | | | | | |_| | | |_| | | | | |\n"
                + "|_______| |_| \\___| |_| |_| |_____| |_____| |_| |_|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Glendon.\n" + "What can I do for you?\n");
    }
}