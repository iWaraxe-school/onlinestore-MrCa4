package by.issoft.store.utils.commanUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;

public class AdminCommandList extends FabricCommands {


    public  AdminCommandList(){
        super();
        getCommandList();
    }
    @Override
    public List<Commands> findCommands(){
        return ReflectionGetSubTypes.findCommands("by.issoft.store.adminCommands");
    }
}
