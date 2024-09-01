public class Taskmanager {
    public void mark(String input){
        String[] splitInput;
        int index;
        splitInput = input.split(" ");
        index = Integer.parseInt(splitInput[1]) - 1;
        Yappatron.taskArray[index].markAsDone();
    }

    public void unmark(String input){
        String[] splitInput;
        int index;
        splitInput = input.split(" ");
        index = Integer.parseInt(splitInput[1]) - 1;
        Yappatron.taskArray[index].markAsUndone();
    }

    public void list(){
        int i;
        System.out.println("Here are the tasks in your list:");
        for (i=0; i<Yappatron.taskNumber; i++){
            System.out.print(i+1 + ". ");
            System.out.println(Yappatron.taskArray[i]);
        }
    }

    public void addTodo(String input){
        Yappatron.taskArray[Yappatron.taskNumber++] = new Todo(input.substring(input.indexOf(" ")));
    }

    public void addDeadline(String input){
        final int STRLENGTH_DEADLINE = 8;
        final int STRLENGTH_BY = 3;
        Yappatron.taskArray[Yappatron.taskNumber++] = new Deadline(input.substring(input.indexOf("deadline") +
                STRLENGTH_DEADLINE, input.indexOf('/')), input.substring(input.indexOf('/') + STRLENGTH_BY));
    }

    public void addEvent(String input) {
        final int STRLENGTH_TO = 3;
        final int STRLENGTH_FROM = 5;
        final int STRLENGTH_EVENT = 5;
        int firstSlash = input.indexOf("/");
        int secondSlash = input.indexOf("/", firstSlash + 1);
        String activityName = input.substring(input.indexOf("event") + STRLENGTH_EVENT, firstSlash);
        String from = input.substring(firstSlash + STRLENGTH_FROM, secondSlash);
        String to = input.substring(secondSlash + STRLENGTH_TO);
        Yappatron.taskArray[Yappatron.taskNumber++] = new Events(activityName, from, to);
    }
}
