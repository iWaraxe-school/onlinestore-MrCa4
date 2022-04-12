package by.issoft.store.utils.commanUtils;

import by.issoft.store.Order;
import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

abstract public class FabricCommands {
    protected   HashMap<String, Class<? extends CommandsInterface>> commandDict;

    public  FabricCommands(){
        getCommandList();
    }

    public  void exec(String command){
       try {
           CommandsInterface commandObj = this.getCommandObj(command, commandDict);
           commandObj.execute();
       }catch (Exception e){
           System.out.println("Command error try again");
       }
    }

    public  void exec(String command, Order order){

        CommandsInterface commandObj = this.getCommandObj(command, commandDict);
        commandObj.execute(order);
    }

    protected void getCommandList(){
        this.commandDict = findCommands()
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        CommandsInterface::getClass,
                        (prev, next) -> next,
                        HashMap::new));
    }

    private CommandsInterface getCommandObj(String command,
                                            HashMap<String, Class<? extends CommandsInterface>> commandDict){
        return ReflectionGetSubTypes.getCommandObject(command,commandDict);
    }
    abstract public List<CommandsInterface> findCommands();

    public  void printCommandList(){

        this.commandDict.keySet()
                .stream()
                .sorted()
                .forEach(System.out::println);
        System.out.println("quit\r\nhelp\r\n");

    }




}
