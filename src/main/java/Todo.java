public class Todo {
    private final Task[] todoList;
    private int currTodo;

    public Todo(){
        currTodo = 0;
        todoList = new Task[100];
    }

    public void listTodos(){
        for (int i = 0; i < currTodo; i++){
            Task currentTask = todoList[i];
            String content = currentTask.getContent();
            String status = currentTask.getStatusIcon();
            System.out.printf("%d.[%s] %s%n", i + 1, status, content);
        }
    }

    public void addTodo(String todo){
        System.out.printf("Added: %s%n", todo);
        todoList[currTodo++] = new Task(todo);
    }


}
