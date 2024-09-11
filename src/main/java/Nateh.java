import Exceptions.IllegalCommandException;
import java.util.Scanner;

public class Nateh {
    
    public static void printList(Task[] list, int length) {
        System.out.print(Skeleton.LINE_BREAK);
        for (int i = 0; i < length; i++) {
            System.out.print(i + 1 + ". ");
            list[i].print();
        }
        System.out.print(Skeleton.LINE_BREAK);
    }

    public static void handleMark(String[] splitInput, Task[] list) {
        try {
            int index;
            index = Integer.parseInt(splitInput[1]) - 1;
            if (Task.getLength() == 0) {
                throw new NullPointerException();
            }
            list[index].setDone(true);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Wow! Great job! :)");
            list[index].print();
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
        }
    }
    private static void handleUnmark(String[] splitInput, Task[] list) {
        try {
            int index;
            index = Integer.parseInt(splitInput[1]) - 1;
            if (Task.getLength() == 0) {
                throw new NullPointerException();
            }
            list[index].setDone(false);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("Aw you didn't get to finish. :(");
            list[index].print();
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
        }
    }
    private static void handleTodo(Task[] list, String input) {
        try {
            list[Task.getLength()] = new Todo(input);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.println("added: " + list[Task.getLength() - 1].getTask());
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to be missing a description");
            System.out.println("Format: todo <description>");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    private static void handleDeadline(Task[] list, String input) {
        try {
            list[Task.getLength()] = new Deadlines(input);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            list[Task.getLength() - 1].print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /by <deadline>");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    private static void handleEvent(Task[] list, String input) {
        try {
            list[Task.getLength()] = new Event(input);
            System.out.print(Skeleton.LINE_BREAK);
            System.out.print("added: ");
            list[Task.getLength() - 1].print();
            System.out.print(Skeleton.LINE_BREAK);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.print((Skeleton.LINE_BREAK));
            System.out.println("OH NO! You seem to have an invalid input!");
            System.out.println("Format: deadline <description> /from <from> /to <to>");
            System.out.print((Skeleton.LINE_BREAK));
        }
    }
    private static void handleInvalid() throws IllegalCommandException{
        throw new IllegalCommandException();
    }
    

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = "";
        Task[] list = new Task[100];
        list[0] = new Task();
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
                    printList(list, Task.getLength());
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
