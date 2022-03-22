package by.issoft.store.storeCommands;

import by.issoft.store.utils.commanUtils.UserCommandList;
import by.issoft.store.utils.commanUtils.Commands;

public class ListCommands implements Commands {

    @Override
    public void execute() {
        UserCommandList.printCommandList();
    }

    @Override
    public String toString() {
        return "List Commands";
    }
}
