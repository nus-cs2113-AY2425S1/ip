import java.util.Scanner;

public class Medea {

    private static void greet(){
        System.out.println("Hello! I'm Medea.");
        System.out.println("What can I do for you?");
    }

    private static void goodbye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleInput(Todo todo, String input){
        String[] inputArguments = input.split(" ");
        String inputCommand = inputArguments[0];
        String inputIndex = inputArguments[1];
        switch(inputCommand){
            case "list":
                todo.listTodos();
                break;
            case "mark":
                int markIndex = Integer.parseInt(inputIndex) - 1;
                todo.markTodo(markIndex);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(inputIndex) - 1;
                todo.unmarkTodo(unmarkIndex);
                break;
            default:
                todo.addTodo(input);
                break;
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner scanner = new Scanner(System.in);
        Todo todo = new Todo();
        String input = scanner.nextLine();
        while(!input.equals("bye")){
            handleInput(todo, input);
            input = scanner.nextLine();
        }
        goodbye();
    }
}