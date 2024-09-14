public class Taskmanager {
    public void mark(String input){
        String[] splitInput;
        int index;
        splitInput = input.split(" ");
        index = Integer.parseInt(splitInput[1]) - 1;
        Yappatron.taskArray.get(index).markAsDone();
    }

    public void unmark(String input){
        String[] splitInput;
        int index;
        splitInput = input.split(" ");
        index = Integer.parseInt(splitInput[1]) - 1;
        Yappatron.taskArray.get(index).markAsUndone();
    }

    public void list(){
        int i=0;
        System.out.println("Here are the tasks in your list:");
//        for (i=0; i<Yappatron.taskNumber; i++){
//            System.out.print(i+1 + ". ");
//            System.out.println(Yappatron.taskArray[i]);
//        }
        for (Task t: Yappatron.taskArray){
            System.out.print(++i + ". ");
            System.out.println(t);
        }
    }

    public void addTodo(String input) throws EmptyTaskEntry {
        if (input.trim().length() == "todo".length()){
            throw new EmptyTaskEntry();
        }
        Yappatron.taskArray.add(new Todo(input.substring(input.indexOf(" "))));
    }

    public void addDeadline(String input){
        final int STRLENGTH_DEADLINE = 8;
        final int STRLENGTH_BY = 2;
        if (input.trim().length() == "deadline".length()){
            throw new EmptyTaskEntry();
        }
        if (!input.contains("by")){
            throw new StringIndexOutOfBoundsException();
        }
        Yappatron.taskArray.add(new Deadline(input.substring(input.indexOf("deadline") +
                STRLENGTH_DEADLINE, input.indexOf("by")), input.substring(input.indexOf("by") + STRLENGTH_BY)));
    }

    public void addEvent(String input) {
        final int STRLENGTH_TO = 2;
        final int STRLENGTH_FROM = 4;
        final int STRLENGTH_EVENT = 5;
        int indexFrom = input.indexOf("from");
        int indexTo = input.indexOf("to");
        if (input.trim().length() == "event".length()){
            throw new EmptyTaskEntry();
        }
        if (indexFrom == -1 || indexTo == -1){
            throw new StringIndexOutOfBoundsException();
        }
        String activityName = input.substring(input.indexOf("event") + STRLENGTH_EVENT, indexFrom);
        String from = input.substring(indexFrom + STRLENGTH_FROM, indexTo);
        String to = input.substring(indexTo + STRLENGTH_TO);
        Yappatron.taskArray.add(new Events(activityName, from, to));
    }

    public void deleteTask(int input){
        try{
            Task task = Yappatron.taskArray.get(input-1);
            Yappatron.taskArray.remove(input-1);
            System.out.println("I have removed the following task: ");
            System.out.print(input);
            System.out.println(task);
            System.out.println("Now you have " + Yappatron.taskArray.size() + " tasks left");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Task number does not exist!");
        }
    }
}
