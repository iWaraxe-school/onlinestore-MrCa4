package by.issoft.store.utils.commanUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

abstract public class FabricCommands {
    public  HashMap<String, Class<? extends Commands>> commandDict;

    public  FabricCommands(){
        getCommandList();
    }

    public  void exec(String command){

        Commands commandObj = this.getCommandObj(command, commandDict);
        commandObj.execute();
    }

    protected void getCommandList(){
        this.commandDict = findCommands()
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        Commands::getClass,
                        (prev, next) -> next,
                        HashMap::new));
    }

    public Commands getCommandObj(String command,
                                           HashMap<String, Class<? extends Commands>> commandDict){
        return ReflectionGetSubTypes.getCommandObject(command,commandDict);
    }
    abstract public List<Commands> findCommands();

    //TODO move method....anywhere
    public  void printCommandList(){

        this.commandDict.keySet()
                .stream()
                .sorted()
                .forEach(System.out::println);
        //TODO print with stream api
        System.out.println("quit\r\nhelp\r\n");

    }




}
