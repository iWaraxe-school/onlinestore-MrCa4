package by.issoft.store.utils;

import by.issoft.store.Order;
import by.issoft.store.utils.commanUtils.FabricCommands;
import by.issoft.store.utils.commanUtils.OrderCommandList;

public class OrderProcessingUtil implements Runnable {

    FabricCommands storeFabricCommands;
    public OrderProcessingUtil(FabricCommands fabricCommands) {
        this.storeFabricCommands = fabricCommands;
    }


    // if we want interactive cart process...
    @Override
    public void run() {
        String command;
        Order order = new Order();
        order.setShopperName("Trololo");
        storeFabricCommands.exec("List Products");
        FabricCommands fabricCommands = new OrderCommandList();
        fabricCommands.printCommandList();
        do{
            System.out.println("Go to main menu -> write \"back\"");
            System.out.print("Input order command -->");
            command = StreamUtil.getInputData();
            fabricCommands.exec(command);
        }while(!command.equals("back"));




    }
}
