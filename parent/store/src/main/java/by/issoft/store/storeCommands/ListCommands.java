package by.issoft.store.storeCommands;

import by.issoft.store.Store;
import by.issoft.store.utils.commandUtils.CommandsInterface;

public class ListCommands implements CommandsInterface {

    @Override
    public void execute() {
        Store.storeFabricCommands.printCommandList();
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
