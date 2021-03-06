package by.issoft.store.utils.orderUtils;

import by.issoft.store.Store;
import by.issoft.store.services.httpService.HTTPService;

public class CollectorOfCompletedOrdersUtil implements Runnable {

    private static CollectorOfCompletedOrdersUtil orderCollector = null;
    private CollectorOfCompletedOrdersUtil(){

    }

    public static CollectorOfCompletedOrdersUtil getCollectorOfCompletedOrdersUtil(){
        return orderCollector = (orderCollector == null) ? new CollectorOfCompletedOrdersUtil():orderCollector;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (Store.completedOrders) {
                Store.completedOrders.clear();

            }
            HTTPService.clearOrderMap();
            System.out.println("Clear completed order list");
            try {
                Thread.sleep(120_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
