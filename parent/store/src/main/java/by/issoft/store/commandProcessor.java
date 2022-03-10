package by.issoft.store;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class commandProcessor {

    public static void execCommand(String command, HashMap<String, Class<? extends Commands>> commandDict){
        if (commandDict.containsKey(command)){
            try {
                commandDict.get(command).getConstructor().newInstance().execute();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No such command!\r\nPlease try again.\r\n");
        }
    }
}
