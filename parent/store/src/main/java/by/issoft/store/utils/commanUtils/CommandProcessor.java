package by.issoft.store.utils.commanUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class CommandProcessor {

    private static String command = null;
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static void execCommand(String command, HashMap<String, Class<? extends Commands>> commandDict){
        if (commandDict.containsKey(command)){
            try {
                commandDict.get(command).getConstructor().newInstance().execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No such command!\r\nPlease try again.\r\n");
        }
    }

    public static String getCommand(){
        try {
            command = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return command;

    }

}
