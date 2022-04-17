package by.issoft.store.commands.orderCommands;

import by.issoft.store.Order;
import by.issoft.store.utils.commandUtils.CommandsInterface;

public class SubmitOrder  implements CommandsInterface {

    @Override
    public void execute() {

    }

    @Override
    public void execute(Object object) {
        Order order = (Order) object;


    }
}
