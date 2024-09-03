public class Todo extends Task{
    public Todo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public void printTask(){
       System.out.print("[T]");
       super.printTask();
       System.out.println();
    }
}
