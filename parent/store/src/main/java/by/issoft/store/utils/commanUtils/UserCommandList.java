package by.issoft.store.utils.commanUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;


public class UserCommandList extends FabricCommands {


    public UserCommandList(){
        super();
    }

    @Override
    public   List<Commands> findCommands(){
        return ReflectionGetSubTypes.findCommands("by.issoft.store.storeCommands");
    }




}
