public class Todo {
    private final Task[] todoList;
    private int currentTodo;

    public Todo(){
        currentTodo = 0;
        todoList = new Task[100];
    }

    public void listTodos(){
        for (int i = 0; i < currentTodo; i++){
            Task currentTask = todoList[i];
            String taskContents = currentTask.getContents();
            String taskStatus = currentTask.getStatusIcon();
            System.out.printf("%d.[%s] %s%n", i + 1, taskStatus, taskContents);
        }
    }

    public void addTodo(String todo){
        System.out.printf("Added: %s%n", todo);
        todoList[currentTodo++] = new Task(todo);
    }

    public void markTodo(int index){
        todoList[index].markDone();
        String taskContents = todoList[index].getContents();
        System.out.printf("Nice! I've marked this task as done: %n [X] %s%n", taskContents);
    }

    public void unmarkTodo(int index){
        todoList[index].unmarkDone();
        String taskContents = todoList[index].getContents();
        System.out.printf("OK, I've marked this task as not done yet: %n [ ] %s%n", taskContents);
    }
}
