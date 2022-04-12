package by.issoft.store.storeCommands;

import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.utils.commanUtils.CommandsInterface;
import by.issoft.store.utils.commanUtils.FabricCommands;
import by.issoft.store.utils.commanUtils.OrderCommandList;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static by.issoft.store.utils.RandomStorePopulator.faker;

public class CreateOrder implements CommandsInterface {



//For real orders
//    @Override
//    public void execute() {
//        OrderProcessingUtil orderProcessingUtil = new OrderProcessingUtil(Store.fabricCommands);
//        Thread thread = new Thread(orderProcessingUtil);
//        thread.start();
//        System.out.printf(Thread.currentThread().getName());
//    }
//----------------------------------------------------------------------------------------------------------------
    //thread pool for fake orders

    //For fake orders
    @Override
    public void execute() {
        List<Thread> threadList = IntStream.range(1,3)
                .mapToObj(i->new Thread(()->{
                    System.out.println("Start new order..");
                    String command;
                    Order order = new Order();
                    order.setShopperName("Trololo");
                    FabricCommands orderCommands = new OrderCommandList();
                    orderCommands.exec("Add", order);
                    try {
                        Thread.sleep(faker.number().numberBetween(1_000,3_000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Store.completedOrders.add(order);
                    System.out.println("Order completed and added to list..");


                })).collect(Collectors.toList());
        threadList.forEach(Thread::start);


    }



    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "Create order";

    }
}
