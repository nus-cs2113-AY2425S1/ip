package Utils;

import Entity.Message;
import Entity.messageList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class saveHandler {

    private static int todo = 1;
    private static int deadline = 2;
    private static int event = 3;
    private static final String path = "YukinoData.txt";

    /**
     * Converts message to String.
     *
     * <p>This method takes message stored and convert to form that is
     * ready for writing into local data file</p>
     * @param message The message stored
     * @return The String form of the message ready for writing into file.
     */

    public static String converter(Message message) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH-dd-MM-yyyy");
        String output = null;
        int typeNumber = message.getType();
        String type = null;
        String startTime = "";
        String endTime = "";
        if (typeNumber == todo) {
            type = "T";
        }
        else if (typeNumber == deadline) {
            type = "D";
            endTime = message.getEndTime().format(formatter);
        }
        else if (typeNumber == event) {
            type = "E";
            startTime = message.getStartTime().format(formatter);
            endTime = message.getEndTime().format(formatter);
        }

        String task = message.getMessage();
        String isDone = null;
        if (message.isDone()) {
            isDone = "1";
        }
        else {
            isDone = "0";
        }
        output = type + " | " + task + " | " + isDone + " | " + startTime + " | " + endTime;
        return output;
    }

    /**
     * Initialise message list when program starts
     *
     * <p>This method calls the retrieve data method to retrieve data if the
     * file is already created at designated path</p>
     * @param list The message list to store retrieved data into.
     * @throws Exception If any error occurs.
     * @see java.io.File;
     */

    public static void initFile(messageList list){
        try {
            File file = new File("YukinoData.txt");
            if(file.createNewFile()) {
            }
            else {
                retrieveData(list);
            }
        }
        catch(Exception e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    /**
     * writes converted String into file
     *
     * <p>This method calls converter to convert message
     * in the list and writes the converted message into local data file</p>
     * @param list The message list to convert and write.
     * @see java.io.FileWriter;
     */

    public static void writeToFile(messageList list) throws IOException {
        try {
            List<Message> messages = list.getMessages();
            FileWriter fw = new FileWriter(path);
            for (int i = 0; i < messages.size(); i++) {
                Message message = messages.get(i);
                String toWrite = converter(message);
                fw.write(toWrite + "\n");
            }
            fw.close();
        }
        catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

    /**
     * Retrieves data
     *
     * <p>This method retrieves data from the local data file
     * and store into a new message list when the program starts</p>
     * @param list The message list to store retrieved data.
     * @throws FileNotFoundException If no data file is found.
     * @see java.io.File;
     */

    public static void retrieveData(messageList list){
        try {
            File file = new File(path);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("\\|");
                String type = data[0];
                int typeNumber = -1;
                if (type.trim().equals("T")) {
                    typeNumber = 1;
                }
                else if (type.trim().equals("D")) {
                    typeNumber = 2;
                }
                else if (type.trim().equals("E")) {
                    typeNumber = 3;
                }
                String task = data[1].trim();
                String done = data[2].trim();
                boolean isDone = false;
                if(done == "1") {
                    isDone = true;
                }
                else if (done == "0") {
                    isDone = false;
                }
                String startTime = data[3].trim();
                String endTime = data[4].trim();
                LocalDateTime startDate = null;
                LocalDateTime endDate = null;
                if (typeNumber == todo) {
                    startDate = null;
                    endDate = null;
                }
                else if (typeNumber == deadline) {
                    startDate = null;
                    endDate = messageHandler.convertToDate(endTime);;
                }
                else if (typeNumber == event) {
                    startDate = messageHandler.convertToDate(startTime);
                    endDate = messageHandler.convertToDate(endTime);
                }
                Message message = new Message(task, isDone, startDate, endDate, typeNumber);
                list.add(message);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
