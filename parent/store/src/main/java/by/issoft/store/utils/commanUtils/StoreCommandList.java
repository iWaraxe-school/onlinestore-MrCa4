package by.issoft.store.utils.commanUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;


public class StoreCommandList extends FabricCommands {

    public StoreCommandList(){
        super();
    }

    @Override
    public   List<CommandsInterface> findCommands(){
        return ReflectionGetSubTypes.findCommands("by.issoft.store.storeCommands");
    }

}
