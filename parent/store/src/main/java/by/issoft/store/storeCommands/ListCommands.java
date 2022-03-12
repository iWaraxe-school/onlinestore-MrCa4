package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.CommandList;
import by.issoft.store.utils.commanUtils.Commands;
import by.issoft.store.Store;

public class ListCommands extends Store implements Commands {

    @Override
    public void execute() {
        CommandList.printCommandList();
    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
