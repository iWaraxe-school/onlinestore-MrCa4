package by.issoft.store.utils.commandUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;

public class AdminCommandList extends FabricCommands {


    public  AdminCommandList(){
        super();
    }
    @Override
    public List<CommandsInterface> findCommands(){
        return ReflectionGetSubTypes.findCommands("by.issoft.store.adminCommands");
    }
}
