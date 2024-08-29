import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Cassandra {

    private static void line(){
        System.out.println("____________________________________________________________");
    }

    private static void intro(){
        line();
        System.out.println(" Hello! I'm Cassandra");
        System.out.println(" What can I do for you?");
        line();
    }

    private static void exit(){
        System.out.println(" Bye. Hope to see you again soon!");
        line();
    }

    private static void saveTask(ArrayList<String> taksList,String input){
        taksList.add(input);
        System.out.println("Added :"+ input);
        line();
    }

    private static void printtaksList(ArrayList<String> taksList){
        if(taksList.size()==0){
            System.out.println("List is empty");
            return;
        }
        for(int i=0;i<taksList.size();i++){
            System.out.println((i+1) + ". " + taksList.get(i));
        }
        line();
    }

    private  static  void input(ArrayList<String> taksList){
        String input = new Scanner(System.in).nextLine();
        line();
        if(input.equals("list")){
            printtaksList(taksList);
        } else if(input.equals("bye")){
            return;
        }else {
            saveTask(taksList, input);
        }
        input(taksList);
    }

    public static void main(String[] args) {
        intro();
        ArrayList<String> taksList = new ArrayList<String>();
        input(taksList);
        exit();
    }
}
