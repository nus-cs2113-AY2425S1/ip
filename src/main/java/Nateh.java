import classes.Deadlines;
import classes.Event;
import classes.Task;
import classes.Todo;
import exceptions.IllegalCommandException;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Nateh {
    
    public static void printList(ArrayList<Task> list) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ". ");
            list.get(i).print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }

    public static void handleMark(String[] splitInput, ArrayList<Task> list) {
        try {
            int index;
            index = Integer.parseInt(splitInput[1]) - 1;
            if (list.isEmpty()) {
                throw new NullPointerException();
            }
            list.get(index).setDone(true);
            FileHandler.markTask(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Wow! Great job! :)");
            list.get(index).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NumberFormatException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/ ");
            System.out.println("format: mark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NullPointerException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/ ");
            System.out.println("format: mark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (IOException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm, there seems to be a File error?");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public static void handleUnmark(String[] splitInput, ArrayList<Task> list) {
        try {
            int index;
            index = Integer.parseInt(splitInput[1]) - 1;
            if (list.isEmpty()) {
                throw new NullPointerException();
            }
            list.get(index).setDone(false);
            FileHandler.unmarkTask(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Aw you didn't get to finish. :(");
            list.get(index).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NumberFormatException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Woah, that wasn't a number?");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you didn't input a number :/ ");
            System.out.println("format: unmark <number>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (NullPointerException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Uh oh. I think you gave too big a number :/ ");
            System.out.println("format: unmark <number smaller than number of tasks>");
            System.out.print(Skeleton.LINE_BREAK);
        } catch (IOException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm, there seems to be a File error?");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }
    public static void handleTodo(ArrayList<Task> list, String input) {
        try {
            list.add(new Todo(input));
            FileHandler.addTask(list.get(Task.getLength() - 1).toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("added: " + list.get(list.size() - 1).getTask());
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to be missing a description");
            System.out.println("Format: todo <description>");
            System.out.print((Skeleton.LINE_BREAK));
        } catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public static void handleDeadline(ArrayList<Task> list, String input) {
        try {
            list.add(new Deadlines(input));
            FileHandler.addTask(list.get(Task.getLength() - 1).toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            list.get(list.size() - 1).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /by <deadline>");
            System.out.print((Skeleton.LINE_BREAK));
        } catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public static void handleEvent(ArrayList<Task> list, String input) {
        try {
            list.add(new Event(input));
            FileHandler.addTask(list.get(Task.getLength() - 1).toString());
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            list.get(list.size() - 1).print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /from <from> /to <to>");
            System.out.print((Skeleton.LINE_BREAK));
        }  catch (IOException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("Seems like an error occurred");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    public static void handleDelete(ArrayList<Task> list, int index) {
        try {
            Task temp = list.get(index);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Okay. I've deleted the task:");
            temp.print();
            System.out.printf("Now you have %d tasks\n", list.size());
            System.out.print(Skeleton.LINE_BREAK);
            list.remove(index);
        } catch (IndexOutOfBoundsException e) {
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Hmm seems like you tried to delete a task that doesn't exist");
            System.out.print(Skeleton.LINE_BREAK);
        }
    }

    public static void handleInvalid() throws IllegalCommandException {
        throw new IllegalCommandException();
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        FileHandler.createFile();
        String input = "";
        ArrayList<Task> list;
        try {
            list = FileHandler.readTasks();
        } catch (IOException e) {
            list = new ArrayList<>();
        }
        System.out.print(Skeleton.LINE_BREAK);
        System.out.println("Hello! I'm Nateh\nWhat can I do for you?");
        System.out.println(Skeleton.SKELETON);
        System.out.print(Skeleton.LINE_BREAK);
        while (!input.equals("bye")) {
            input = in.nextLine();
            String[] splitInput = input.split(" ");
            try {
                switch (splitInput[0]) {
                case "list":
                    printList(list);
                    break;
                case "mark":
                    handleMark(splitInput, list);
                    break;
                case "unmark":
                    handleUnmark(splitInput, list);
                    break;
                case "bye":
                    System.out.print(Skeleton.LINE_BREAK);
                    System.out.println("Bye. See you next time!");
                    System.out.print(Skeleton.LINE_BREAK);
                    break;
                case "todo":
                    handleTodo(list, input);
                    break;
                case "deadline":
                    handleDeadline(list, input);
                    break;
                case "event":
                    handleEvent(list, input);
                    break;
                case "delete":
                    handleDelete(list, Integer.parseInt(splitInput[1]) - 1);
                    break;
                default:
                    handleInvalid();
                    break;
                }
            } catch (IllegalCommandException e) {
                System.out.print(Skeleton.LINE_BREAK);
                System.out.println("Oops?! I don't know that one >.<");
                System.out.print(Skeleton.LINE_BREAK);
            }
        }
    }
}
