package ellio;

import ellio.task.Deadline;
import ellio.task.Event;
import ellio.task.Task;
import ellio.task.Todo;

import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;

public class Ellio {

    public static final int DEADLINE_INPUT_SPACING = 9;
    public static final int EVENT_INPUT_SPACING = 6;
    public static final int MAX_TASK = 100;
    private static Task[] listTasks = new Task[MAX_TASK];
    private static int numberTask = 0;
    private static final String SAVED_TASK_FILEPATH = "./data/Ellio.txt";
    public static File saveFile = new File(SAVED_TASK_FILEPATH);

    public static void endProgram(){
        System.out.println(BotText.lineBorder + BotText.messageGoodbye + BotText.lineBorder);
    }

    public static void startProgram(){
        System.out.println(BotText.lineBorder + BotText.messageWelcome + BotText.lineBorder);
        openFile();
    }

    public static void openFile(){
        if(!saveFile.exists()){
            createFile();
        }
        else{
            extractFileContent();
        }
    }

    public static void createFile(){
        try{
            File parentDir = saveFile.getParentFile();
            if(!parentDir.exists() && parentDir != null){
                parentDir.mkdirs();
            }
            saveFile.createNewFile();
        } catch (IOException e){
            System.out.println("Error Occured: " + e.getMessage());
        }
    }

    public static void extractFileContent(){
        try {
            Scanner s = new Scanner(saveFile);
            while(s.hasNextLine()){
                String line = s.nextLine().trim();
                String[] parts = line.split(" \\| ");
                switch(parts[0]){
                case "t":
                    listTasks[numberTask] = new Todo(parts[2], parts[1]);
                    numberTask++;
                    break;
                case "d":
                    listTasks[numberTask] = new Deadline(parts[2], parts[1], parts[3]);
                    numberTask++;
                    break;
                case "e":
                    listTasks[numberTask] = new Event(parts[2], parts[1], parts[3], parts[4]);
                    numberTask++;
                    break;
                default:
                    System.out.println("Error Reading File");
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error Occured: " + e.getMessage());
        }
    }

    public static void saveTask(String savedTask){
        try {
            FileWriter fw = new FileWriter(saveFile, true);
            fw.write( System.lineSeparator() + savedTask);
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printList() throws IndexOutOfBoundsException{
        if (numberTask < 1){
            throw new IndexOutOfBoundsException();
        }

        System.out.print(BotText.lineBorder + BotText.messageList);
        for (int i = 0; i < numberTask; i++) {
            System.out.println((i + 1) + "." + listTasks[i].getTask());
        }
        System.out.println(BotText.lineBorder);
    }

    public static void addToDo(String line){
        String description = line.replace("todo ", "");
        Todo newTodo = new Todo(description, "0");
        listTasks[numberTask] = newTodo;
        numberTask++;
        System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newTodo.getTask());
        System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
        saveTask(newTodo.getSaveFileTask());
    }

    public static void addDeadline(String line){
        try {
            Deadline newDeadline = formatDeadline(line);
            listTasks[numberTask] = newDeadline;
            numberTask++;
            System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newDeadline.getTask());
            System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
            saveTask(newDeadline.getSaveFileTask());
        } catch (EllioExceptions e){
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException e){
            System.out.println(BotText.lineBorder + BotText.messageMissingDeadlineDate + BotText.lineBorder);
        }

    }

    private static Deadline formatDeadline(String line) throws EllioExceptions {
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");

        // Extract the substrings based on the slash positions
        String description = line.substring(DEADLINE_INPUT_SPACING, firstSlashIndex).trim();
        String deadline = line.substring(firstSlashIndex + 1).trim();

        if(!deadline.startsWith("by")){
            throw new EllioExceptions.WrongDeadlineFormatTimeException();
        }
        //add Semicolon behind by
        deadline = deadline.replace("by", "by:");

        return new Deadline(description, "0", deadline);
    }

    public static void addEvent(String line){
        try {
            Event newEvent = formatEvent(line);
            listTasks[numberTask] = newEvent;
            numberTask++;
            System.out.println(BotText.lineBorder + "Got it. I've added this task:\n  " + newEvent.getTask());
            System.out.println("Now you have " + numberTask + " tasks in the list.\n" + BotText.lineBorder);
            saveTask(newEvent.getSaveFileTask());
        }
        catch (EllioExceptions e){
            System.out.println(e.getMessage());
        }
        catch (StringIndexOutOfBoundsException e){
            System.out.println(BotText.lineBorder + BotText.messageInvalidEventTimeFormat +BotText.lineBorder);
        }

    }
    
    private static Event formatEvent(String line) throws EllioExceptions {
        // Find the indices of the first and second occurrences of "/"
        int firstSlashIndex = line.indexOf("/");
        int secondSlashIndex = line.indexOf("/", firstSlashIndex + 1);

        // Extract the substrings based on the slash positions
        String description = line.substring(EVENT_INPUT_SPACING, firstSlashIndex).trim();
        String eventStart = line.substring(firstSlashIndex + 1, secondSlashIndex).trim();
        String eventEnd = line.substring(secondSlashIndex + 1).trim();

        if(!eventStart.startsWith("from")) {
            throw new EllioExceptions.WrongEventStartFormatException();
        }
        else if(!eventEnd.startsWith("to")) {
            throw new EllioExceptions.WrongEventEndFormatException();
        }

        //add Semicolon behind from and to
        eventStart = eventStart.replace("from", "from:");
        eventEnd = eventEnd.replace("to", "to:");

        return new Event(description, "0", eventStart, eventEnd);
    }

    public static void markList(int index){
        if(index > numberTask){
            throw new IndexOutOfBoundsException();
        }
        listTasks[index-1].markTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageMarked + "  " + listTasks[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void unmarkList(int index){
        if(index > numberTask){
            throw new IndexOutOfBoundsException();
        }
        listTasks[index-1].unmarkTaskAsDone();
        System.out.println(BotText.lineBorder + BotText.messageUnmark + "  " + listTasks[index-1].getTask() + "\n" + BotText.lineBorder);
    }

    public static void getInput(){
        String input;
        Scanner in = new Scanner(System.in);
        input = (in.nextLine()).toLowerCase();

        while(!input.equals("bye")){
            try {
                printInput(input);
            } catch (IllegalArgumentException e){
                System.out.println(BotText.lineBorder + BotText.messageInvalidCommand + BotText.lineBorder);
            }
            input = in.nextLine();
        }
        in.close();
    }

    public static void printInput(String input){
        if(input.equals("list")){
            try {
                printList();
            } catch (IndexOutOfBoundsException e) {
                System.out.print(BotText.lineBorder + BotText.messageEmptyList + BotText.lineBorder);
            }
        }
        else if(input.startsWith("mark")){
            String indexNum = getTaskIndex(input);
            try {
                markList(Integer.parseInt(indexNum));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(BotText.lineBorder + BotText.messageInvalidIndex + numberTask + " \n" + BotText.lineBorder);
            }
        }
        else if(input.startsWith("unmark")){
            String indexNum = getTaskIndex(input);
            try {
                unmarkList(Integer.parseInt(indexNum));
            } catch (IndexOutOfBoundsException e) {
                System.out.print(BotText.lineBorder + BotText.messageInvalidIndex + numberTask + " \n" + BotText.lineBorder);
            }
        }
        else if(input.startsWith("todo")){
            addToDo(input);
        }
        else if(input.startsWith("deadline")){
            addDeadline(input);
        }
        else if(input.startsWith("event")){
            addEvent(input);
        }
        else{
            throw new IllegalArgumentException();
        }
    }

    private static String getTaskIndex(String input) {
        String[] words = input.split(" ");
        return words[1];
    }

    public static void main(String[] args) {
        startProgram();
        getInput();
        endProgram();
    }
}
