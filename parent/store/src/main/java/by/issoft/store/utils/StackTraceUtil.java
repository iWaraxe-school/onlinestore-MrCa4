package by.issoft.store.utils;

import java.util.List;

public class StackTraceUtil {
    public static boolean checkStackTrace(List<String> stackTraceList, String pattern){

        return stackTraceList.stream().anyMatch(el->el.toLowerCase().contains(pattern.toLowerCase()));


    }
}
