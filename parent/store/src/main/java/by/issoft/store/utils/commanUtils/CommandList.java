package by.issoft.store.utils.commanUtils;

import by.issoft.store.Store;
import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandList extends Store {

    public static void main(String[] args) {
        findCommands();
    }

    private static List<Commands> findCommands(){

        List<Commands> commandList = new ArrayList<>();
         commandList = ReflectionGetSubTypes.getAllSubTypes(String,"by.issoft.store.storeCommands");
//        Reflections reflections = new Reflections("by.issoft.store.storeCommands", Scanners.SubTypes);
//        Set<Class<? extends Commands>> subTypes =
//                reflections.getSubTypesOf(Commands.class);
//
//        for (Class<? extends Commands> subType: subTypes){
//            try {
//                commandList.add(subType.getConstructor().newInstance());
//            } catch (InstantiationException
//                    | IllegalAccessException
//                    | InvocationTargetException
//                    | NoSuchMethodException e) {
//                e.printStackTrace();
//            }
//
//        }
        return commandList;

    }

    public static void getCommandList(){
        commandDict = findCommands()
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        Commands::getClass,
                        (prev, next) -> next,
                        HashMap::new));
    }

    public static void printCommandList(){

        commandDict.keySet()
                        .stream()
                        .sorted()
                        .forEach(System.out::println);
        //TODO print with stream api
        System.out.println("quit\r\nhelp\r\n");

    }
}
