package by.issoft.store.utils;

import by.issoft.store.utils.commanUtils.CommandsInterface;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReflectionGetSubTypes {

    public static  <T> List<? extends T>getAllSubTypes(Class<T> cls, String path){
        Reflections reflections;
        List<T> subTypesList = new ArrayList<>();
        try {
            reflections = new Reflections(path, Scanners.SubTypes);
            Set<Class<? extends T>> subTypes =
                    reflections.getSubTypesOf(cls);
            for (Class<? extends T> subType: subTypes){
                try {
                    subTypesList.add(subType.getConstructor().newInstance());
                } catch (InstantiationException
                        | IllegalAccessException
                        | InvocationTargetException
                        | NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        }
        catch (Exception ex){
           // ex.printStackTrace();
            System.out.println("Bad path Catched");
            System.out.println(path);
        }

        return subTypesList;

    }

    //TODO  delete this - redundant (must call getAllSubTypes and cast result)
    public static   List<CommandsInterface> findCommands(String path){
        return (List<CommandsInterface>)getAllSubTypes(CommandsInterface.class, path);

    }

    public static CommandsInterface getCommandObject(String command, HashMap<String, Class<? extends CommandsInterface>> commandDict){
        if (commandDict.containsKey(command)){
            try {
                return commandDict.get(command).getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("No such command!\r\nPlease try again.\r\n");
        }
        return null;
    }

}
