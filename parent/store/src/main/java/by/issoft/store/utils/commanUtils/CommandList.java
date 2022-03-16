package by.issoft.store.utils.commanUtils;

import by.issoft.store.Store;
import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class CommandList  {



    private static List<Commands> findCommands(){
        List<Commands> commandList = (List<Commands>) ReflectionGetSubTypes
                 .getAllSubTypes(Commands.class,"by.issoft.store.storeCommands");
        return commandList;
    }

    public static void getCommandList(){
        Store.commandDict = findCommands()
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        Commands::getClass,
                        (prev, next) -> next,
                        HashMap::new));
    }

    public static void printCommandList(){

        Store.commandDict.keySet()
                        .stream()
                        .sorted()
                        .forEach(System.out::println);
        //TODO print with stream api
        System.out.println("quit\r\nhelp\r\n");

    }
}
