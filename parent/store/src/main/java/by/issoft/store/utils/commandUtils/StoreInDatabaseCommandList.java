package by.issoft.store.utils.commandUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;


public class StoreInDatabaseCommandList extends FabricCommands {

    public StoreInDatabaseCommandList(){
        super();
    }

    @Override
    public   List<CommandsInterface> findCommands(){
        return ReflectionGetSubTypes
                .findCommands("by.issoft.store.commands.storeCommands.inDatabaseStorage");
    }

}
