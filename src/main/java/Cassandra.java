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

    private static void save(ArrayList<String> ram,String input){
        ram.add(input);
        System.out.println("Added :"+ input);
        line();
    }

    private static void printRam(ArrayList<String> ram){
        if(ram.size()==0){
            System.out.println("List is empty");
            return;
        }
        for(int i=0;i<ram.size();i++){
            System.out.println(i + ". " + ram.get(i));
        }
    }

    private  static  void input(ArrayList<String> ram){
        String input = new Scanner(System.in).nextLine();
        line();
        if(input.equals("list")){
            printRam(ram);
        } else if(input.equals("bye")){
            return;
        }else {
            save(ram, input);
        }
        input(ram);
    }

    public static void main(String[] args) {
        intro();
        ArrayList<String> ram = new ArrayList<String>();
        input(ram);
        exit();
    }
}
