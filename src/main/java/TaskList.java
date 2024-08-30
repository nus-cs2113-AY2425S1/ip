public class TaskList {
    private Task[] list;
    private int size;

    public TaskList(){
        list = new Task[100]; // Constraint given that maximum number of tasks is 100
        size=0;
    }

    public void addTask(String line){
        System.out.println("I've added this to your list: "+line);
        Task newTask = new Task(line);
        list[size]=newTask;
        size++;
    }

    public void printTaskList(){
        System.out.println("Here is your list of tasks:");
        for (int i=1;i<=size;i++){
            System.out.printf("%d. ",i);
            list[i-1].printTask();
        }
    }

    public void attemptToMarkTask(String line){
        try{
            if (line.charAt(4) == ' '){
                int index = Integer.parseInt(line.substring(5));
                list[index-1].markAsDone();
            }
        }catch (Exception e){
            //Treat invalid command as a task
            addTask(line);
        }
    }

    public void attemptToUnmarkTask(String line){
        try{
            if (line.charAt(6) == ' '){
                int index = Integer.parseInt(line.substring(7));
                list[index-1].markAsNotDone();
            }
        }catch (Exception e){
            //Treat invalid command as a task
            addTask(line);
        }
    }
}
