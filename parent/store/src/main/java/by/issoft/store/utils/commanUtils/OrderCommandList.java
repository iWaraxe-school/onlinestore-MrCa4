package by.issoft.store.utils.commanUtils;

import by.issoft.store.utils.ReflectionGetSubTypes;

import java.util.List;

public class OrderCommandList extends FabricCommands{

    public OrderCommandList(){
        super();
    }

    @Override
    public List<CommandsInterface> findCommands(){
        return ReflectionGetSubTypes.findCommands("by.issoft.store.orderCommands");
    }





}

