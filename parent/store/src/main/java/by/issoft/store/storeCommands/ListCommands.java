package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.CommandList;
import by.issoft.store.utils.commanUtils.Commands;

public class ListCommands implements Commands {

    @Override
    public void execute() {
        CommandList.printCommandList();
    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
