package Utils;

import Entity.Message;
import Entity.messageList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class saveHandler {

    private static final String path = "YukinoData.txt";

    public static String converter(Message message) {
        String output = null;
        int typeNumber = message.getType();
        String type = null;
        if (typeNumber == 1) {
            type = "T";
        }
        else if (typeNumber == 2) {
            type = "D";
        }
        else if (typeNumber == 3) {
            type = "E";
        }

        String task = message.getMessage();
        String isDone = null;
        if (message.isDone()) {
            isDone = "1";
        }
        else {
            isDone = "0";
        }
        output = type + " | " + task + " | " + isDone + " | " + message.getStartTime() + " | " + message.getEndTime();
        return output;
    }

    public static void initFile(messageList list){
        try {
            File file = new File(path);
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

    public static void writeToFile(messageList list) throws IOException {
        List<Message> messages = list.getMessages();
        FileWriter fw = new FileWriter(path);
        for(int i = 0; i < messages.size(); i++) {
            Message message = messages.get(i);
            String toWrite = converter(message);
            System.out.println(toWrite);
            fw.write(toWrite + "\n");
            fw.close();
        }
        catch(IOException e){
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }

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
                Message message = new Message(task, isDone, startTime, endTime, typeNumber);
                list.add(message);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateFile(Message message) {
        try {
            File file = new File("YukinoData.txt");
            List<String> lines = new ArrayList<String>();
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                lines.add(sc.nextLine());
            }
            String task = message.getMessage();
            String isDone = null;
            if (message.isDone()) {
                isDone = "1";
            }
            else {
                isDone = "0";
            }
            int i = 0;
            while (i < lines.size()) {
                String line = lines.get(i);
                String[] data = line.split("\\|");

                if(task.equals(data[1].trim())) {
                    String done = data[2].trim();
                    String startTime = data[3].trim();
                    String endTime = data[4].trim();
                    //line =
                    break;
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteLine(Message message) throws IOException {
        String task = message.getMessage();
        File file = new File("YukinoData.txt");
        FileWriter fw = new FileWriter("YukinoData.txt");
        List<String> lines = new ArrayList<String>();
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            lines.add(sc.nextLine());
        }
        String toDelete = converter(message);
        int i = 0;
        while(i < lines.size()) {
            String line = lines.get(i);
            if(toDelete.equals(line)) {
                lines.remove(i);
            }
            i++;
        }
        i = 0;
        while (i < lines.size()) {
            String line = lines.get(i);
            fw.write(line + "\n");
            i++;
        }
    }
}
