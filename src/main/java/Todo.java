public class Todo {
    private final String[] todoList;
    private int currTodo;

    public Todo(){
        currTodo = 0;
        todoList = new String[100];
    }

    public void listTodos(){
        for (int i = 0; i < currTodo; i++){
            System.out.printf("%d. %s%n", i + 1 , todoList[i]);
        }
    }

    public void addTodo(String todo){
        System.out.printf("Added: %s%n",todo);
        todoList[currTodo++] = todo;
    }
}
