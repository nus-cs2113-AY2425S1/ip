import java.io.IOException;
import java.util.Scanner;

public class AnBot {

    private Storage storage; 
    private TaskList taskList; 
    private Ui ui; 
    private Parser parser; 
    private static final String FILE_PATH = "data/AnBot.txt"; 
    
    public AnBot(String filePath) {
        ui = new Ui(); 
        storage = new Storage(filePath); 
        taskList = new TaskList(storage); 
        parser = new Parser(); 
    }

    public void run() {
        ui.printWelcome();
        while (true) {
            try {
                String input = ui.readInput();
                String[] parsedInput = parser.parse(input); 

                String command = parsedInput[0]; 

                switch (command) {
                    case "bye": 
                        ui.printExit(); 
                        try {
                            storage.write(taskList.getTasks()); 
                        } catch (IOException e) {
                            System.out.println("Error saving tasks to file.");
                        }
                        break; 
                    case "list": 
                        taskList.displayEntries(); 
                        break; 
                    case "todo": 
                        String descriptionTodo = parsedInput[1]; 
                        taskList.addTodo(descriptionTodo); 
                        storage.write(taskList.getTasks()); 
                        break; 
                    case "deadline": 
                        String descriptionDeadline = parsedInput[1]; 
                        String deadline = parsedInput[2]; 
                        taskList.addDeadline(descriptionDeadline, deadline);
                        storage.write(taskList.getTasks()); 
                        break; 
                    case "event": 
                        String descriptionEvent = parsedInput[1]; 
                        String begin = parsedInput[2]; 
                        String end = parsedInput[3]; 
                        taskList.addEvent(descriptionEvent, begin, end); 
                        storage.write(taskList.getTasks()); 
                        break; 
                    case "mark": 
                        int indexMark = Integer.parseInt(parsedInput[1]); 
                        taskList.markAsDone(indexMark);
                        storage.write(taskList.getTasks()); 
                        break; 
                    case "unmark": 
                        int indexUnmark = Integer.parseInt(parsedInput[1]); 
                        taskList.unmarkAsDone(indexUnmark);
                        storage.write(taskList.getTasks());
                        break; 
                    case "delete": 
                        int indexDelete = Integer.parseInt(parsedInput[1]); 
                        taskList.delete(indexDelete); 
                        storage.write(taskList.getTasks()); 
                        break; 
                    case "find": 
                        String keyword = parsedInput[1]; 
                        taskList.find(keyword); 
                        break; 
                    default: 
                        throw new AnBotException("Error command. Please enter another input."); 
                }
            } catch(AnBotException e) {
                System.out.println(e.getMessage());
            } catch(IOException e) {
                System.out.println("Error saving tasks: " + e.getMessage());
            }
        }

    }
    
    public static void main(String[] args) {
        new AnBot(FILE_PATH).run(); 
    }
}
