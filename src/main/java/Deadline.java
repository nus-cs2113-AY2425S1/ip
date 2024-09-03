public class Deadline extends Task{
    String dueBy;

    public Deadline(String taskInfo,String dueBy){
        super(taskInfo);
        this.dueBy = dueBy;
    }

    @Override
    public void printTask() {
        System.out.print("[D]");
        super.printTask();
        System.out.println("(by: "+dueBy+")");
    }
}
