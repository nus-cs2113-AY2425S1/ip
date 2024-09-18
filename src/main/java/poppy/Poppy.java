package poppy;

import exceptions.CustomExceptions;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import tasks.*;
import commands.*;
import exceptions.*;
import static commands.Commands.*;
import static poppy.Printer.Print;

public class Poppy {

    public static void main(String[] args) {
        String path = "./data/Poppy.txt";

        File file = new File(path);
       try{
           File parentDir = file.getParentFile();
           if (parentDir != null && !parentDir.exists()){
               parentDir.mkdirs();
           }
           if(!file.exists()){
               file.createNewFile();
           }
           Print(file);
       }catch(FileNotFoundException e){
           System.out.println("File not found");
       } catch (IOException e) {
           System.out.println("Error updating file");
       }
    }
}

