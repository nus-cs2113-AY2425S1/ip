import java.util.Scanner;

public class Medea {

    private static void greet(){
        System.out.println("Hello! I'm Medea.");
        System.out.println("What can I do for you?");
    }

    private static void leave(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    private static void handleInput(Todo todo, String input){
        String[] args = input.split(" ");
        String command = args[0];
        switch(command){
            case "list":
                todo.listTodos();
                break;
            case "mark":
                int markIndex = Integer.parseInt(args[1]) - 1;
                todo.markTodo(markIndex);
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(args[1]) - 1;
                todo.unmarkTodo(unmarkIndex);
                break;
            default:
                todo.addTodo(input);
                break;
        }
    }

    public static void main(String[] args) {
        greet();
        Scanner sc = new Scanner(System.in);
        Todo todo = new Todo();
        String input = sc.nextLine();
        while(!input.equals("bye")){
            handleInput(todo, input);
            input = sc.nextLine();
        }
        leave();
    }
}