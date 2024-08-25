import java.util.Scanner;
public class CodyChen {
    public static void main(String[] args) {
        String hi = "________________________\n" +
                "Hello! I'm CodyChen\n" +
                "What can I do for you?\n" +
                "________________________\n";
        System.out.println(hi);
        Scanner in = new Scanner(System.in);
        String line = "";
        int flag = 0;
        String [] arr = new String[100];
        int count = 0;
        while(!line.equals("bye")){
            line = in.nextLine();
            if(!line.equals("list")){
                if(flag == 0){
                    System.out.print("Thank You for Responding" + "\n" +
                            "__________________________________\nAddd:    "
                            + line +
                            "\n__________________________________\n");
                    flag = 1;
                    arr[count] = line;
                    count += 1;
                }
                else {
                    System.out.println("__________________________________\nAdded:     " +
                            line +
                            "\n__________________________________\n");
                    arr[count] = line;
                    count += 1;
                }

            } else {
                System.out.print("___________________________________\n" +
                        "Yor List as follows: \n");
                for(int i = 0; i < arr.length; i++){
                    if(arr[i]!=null){
                        System.out.print(i + 1 + ". " + arr[i] + "\n");
                    }
                }
                System.out.print("___________________________________\n");
            }
        }

        String bye = "Bye. Hope to see you again soon!\n" +
                "________________________\n";
        System.out.print(bye);


    }
}
