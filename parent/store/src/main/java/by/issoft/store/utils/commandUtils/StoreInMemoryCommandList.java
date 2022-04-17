package by.issoft.store.utils.commandUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;


public class StoreInMemoryCommandList extends FabricCommands {

    public StoreInMemoryCommandList(){
        super();
    }

    @Override
    public   List<CommandsInterface> findCommands(){
        return ReflectionGetSubTypes
                .findCommands("by.issoft.store.commands.storeCommands.inMemoryStorage");
    }

}
