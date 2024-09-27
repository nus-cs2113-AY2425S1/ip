package Commands;

import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class Taskmanager {

    private ArrayList<Task> taskArray;

    public Taskmanager(){
        taskArray = new ArrayList<Task>();
    }

    public Taskmanager(ArrayList<Task> taskArray){
        this.taskArray = taskArray;
    }

    public ArrayList<Task> getTaskArray(){
        return taskArray;
    }

    public void mark(String input){
        int index = Parser.parseForMarkAndUnmarkCommand(input);
        taskArray.get(index).markAsDone();
    }

    public void unmark(String input){
        int index = Parser.parseForMarkAndUnmarkCommand(input);
        taskArray.get(index).markAsUndone();
    }

    public void list(){
        int i=0;
        System.out.println("Here are the tasks in your list:");

        for (Task t: taskArray){
            System.out.print(++i + ". ");
            System.out.println(t);
        }
    }

    public void addTodo(String input) throws EmptyTaskEntry {
        try {
            String activityName = Parser.parseForAddTodo(input);
            taskArray.add(new Todo(activityName));

            System.out.println("added: " + input);
        }catch (EmptyTaskEntry e){
            System.out.println(e.getMessage());
        }
    }

    public void addDeadline(String input){
        try {
            String[] deadlineInfo = Parser.parseForAddDeadline(input);
            String deadlineBy = deadlineInfo[1];

            String[] splitDeadlineBy = deadlineBy.split(" ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate deadlineDate = LocalDate.parse(splitDeadlineBy[0], formatter);

            String deadlineTimeString = splitDeadlineBy[1];
            int hour = Integer.parseInt(deadlineTimeString.substring(0,2));
            int min = Integer.parseInt(deadlineTimeString.substring(2));
            LocalTime deadlineTime = LocalTime.of(hour, min);

            LocalDateTime deadlineOfTask = LocalDateTime.of(deadlineDate, deadlineTime);
            taskArray.add(new Deadline(deadlineInfo[0], deadlineOfTask));

            System.out.println("added: " + input);
        }catch (StringIndexOutOfBoundsException e){
            System.out.println("Please key in valid deadline, include 'by' in string.");
        }catch (EmptyTaskEntry e){
            System.out.println(e.getMessage());
        }catch (DateTimeParseException e){
            System.out.println("Key in valid deadline using format dd/MM/yyyy Hmm");
        }
    }

    public void addEvent(String input) {
        try {
            String[] eventInfo = Parser.parseForAddEvent(input);

            taskArray.add(new Events(eventInfo[0], eventInfo[1], eventInfo[2]));
            System.out.println("added: " + input);
        }catch(StringIndexOutOfBoundsException e) {
            System.out.println("Please key in valid event. Include from and to in string.");
        } catch (EmptyTaskEntry e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(String input){
        try{
            int indexToDelete = Parser.parseForDelete(input);
            Task task = taskArray.get(indexToDelete);
            taskArray.remove(indexToDelete);

            System.out.println("I have removed the following task: ");
            System.out.print(indexToDelete+1 + ". ");
            System.out.println(task);
            System.out.println("Now you have " + taskArray.size() + " tasks left");
        }catch (IndexOutOfBoundsException e){
            System.out.println("Task number does not exist!");
        }
    }

    public void findTask(String input){
        String stringToFind = Parser.parseForFind(input);
        int index = 0;
        for (Task t : taskArray){
            if (t.getDescription().contains(stringToFind)){
                System.out.println(++index + ". " + t);
            }
        }
    }

}
