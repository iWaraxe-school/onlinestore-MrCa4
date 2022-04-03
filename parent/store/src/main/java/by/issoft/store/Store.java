package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.CollectorOfCompletedOrdersUtil;
import by.issoft.store.utils.RandomStorePopulator;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.commanUtils.AdminCommandList;
import by.issoft.store.utils.commanUtils.FabricCommands;
import by.issoft.store.utils.commanUtils.StoreCommandList;

import java.util.ArrayList;
import java.util.List;


//TODO Refactore and make separate class fo reflection
public class Store {


    private static Store store = null;
    private static List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
    private static String command = null;
    public static FabricCommands fabricCommands;
    public static List<Order> completedOrders = new ArrayList<>();

    //Init shop
    private Store(){
    }

    public static Store getStore(){
        return store = (store == null) ? new Store():store;
    }

    //TODO chain of r.....
    public void StoreInitMethod(){
        setCategoryList(randomStorePopulator.getAllCategories());
        setAllProducts();
        new Thread(CollectorOfCompletedOrdersUtil.getCollectorOfCompletedOrdersUtil()).start();
        storeCycleStart();

    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    private void setCategoryList(List<Category> scannedCategoryList) {
        categoryList = scannedCategoryList;
    }

    private void setAllProducts(){
       getCategoryList()
               .forEach(category->category.addProducts(category.getName(),
                                                        randomStorePopulator.
                                                                getProductsForCategory(category)));
    }


    private  void storeCycleStart() {
        System.out.println("Available commands: ");
        fabricCommands = new StoreCommandList();
        fabricCommands.printCommandList();
        do {

            System.out.print("Input command --> ");
            command = StreamUtil.getInputData();
            switch (command) {
                case "quit":
                    System.out.println("Goodbye");
                    break;
                case "help":
                    System.out.println("With all questions you should contact Mr.Cat");
                    break;
                case "secret":
                   //TODO it is dangerouse to use one link to 2 objects because i call store fabric command from another class

                    FabricCommands admfabricCommands = new AdminCommandList();
                    admfabricCommands.printCommandList();
                    System.out.print("Input Admin command -->");
                    admfabricCommands.exec(StreamUtil.getInputData());
                    break;
                default:
                    fabricCommands.exec(command);
                    break;
            }

        } while (!command.equals("quit"));
    }

}

