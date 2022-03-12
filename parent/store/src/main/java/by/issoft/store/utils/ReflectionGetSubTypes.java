package by.issoft.store.utils;

import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ReflectionGetSubTypes {

    public static  <T> List<? extends T> getAllSubTypes(Class cls , String path){
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

}
