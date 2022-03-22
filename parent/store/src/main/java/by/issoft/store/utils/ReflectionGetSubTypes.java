package by.issoft.store.utils;

import by.issoft.store.utils.commanUtils.Commands;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ReflectionGetSubTypes {

    public static  <T> List<? extends T> getAllSubTypes(Class<T> cls, String path){
        List<T> subTypesList = new ArrayList<>();
        Reflections reflections = new Reflections(path, Scanners.SubTypes);
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
        return subTypesList;

    }

    public static   List<Commands> findCommands(String pack){
        List<Commands> commandList = (List<Commands>) ReflectionGetSubTypes
                .getAllSubTypes(Commands.class,pack);
        return commandList;
    }

    public static Commands getCommandObject(String command, HashMap<String, Class<? extends Commands>> commandDict){
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
