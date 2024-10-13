package main.java;

import java.util.Scanner;

public class KenChat {
    public static void printLine() {
        String line = "____________________________________" ;
        System.out.println(line);
    }

    public static void startProgramme() {
        String chatBotName = "KenChat";
        printLine();
        System.out.println("Hello! I'm " + chatBotName);
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void endProgramme() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void displayList(Task[] doList){
        printLine();
        for (int i=0; i< doList.length; i++){
            if(doList[i] != null){
                System.out.println((i+1)+". "+doList[i]);
            }
        }
        printLine();
    }

    public static void addList(Task[] doList, String item){
        printLine();
        System.out.println("added: "+item);
        printLine();

        for (int i=0; i< doList.length; i++){
            if(doList[i] == null){
                Task doItem = new Task(item);
                doList[i] = doItem;
                break;
            }
        }
    }

    public static void setTaskStatus(boolean isMark, Task item){
        printLine();
        if(isMark){
            item.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
        }
        else {
            item.markAsUndone();
            System.out.println("OK, I've marked this task as not done yet:");
        }
        System.out.println("  "+item);
        printLine();
    }

    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        boolean running = true;
        int arraySize = 100;
        Task[] doList = new Task[arraySize];
        startProgramme();
        while (running){
            System.out.println();
            String str= sc.nextLine();
            String[] command = str.split(" ");
            if (command[0].equalsIgnoreCase("bye"))
                running = false;
            else if(command[0].equalsIgnoreCase("list"))
                displayList(doList);
            else if(command[0].equalsIgnoreCase("mark")){
                int itemNumber = Integer.parseInt(command[1]) - 1;
                setTaskStatus(true, doList[itemNumber]);
            }
            else if(command[0].equalsIgnoreCase("unmark")){
                int itemNumber = Integer.parseInt(command[1]) - 1;
                setTaskStatus(false, doList[itemNumber]);
            }
            else
                addList(doList, str);
        }
        endProgramme();
    }
}
