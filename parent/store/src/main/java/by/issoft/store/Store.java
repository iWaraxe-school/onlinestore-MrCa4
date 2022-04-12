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
    public static FabricCommands storeFabricCommands = new StoreCommandList();
    public static FabricCommands admFabricCommands = new AdminCommandList();
    public static List<Order> completedOrders = new ArrayList<>();

    //Init shop
    private Store(){
    }

    public static Store getStore(){
        return store = (store == null) ? new Store():store;
    }

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
               .forEach(category->category
                       .addProducts(category.getName(),
                                                        randomStorePopulator.
                                                                getProductsForCategory(category)));
    }


    private  void storeCycleStart() {
        System.out.println("Available commands: ");
        storeFabricCommands.printCommandList();
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
                    admFabricCommands.printCommandList();
                    System.out.print("Input Admin command -->");
                    admFabricCommands.exec(StreamUtil.getInputData());
                    break;
                default:
                    storeFabricCommands.exec(command);
                    break;
            }

        } while (!command.equals("quit"));
    }

}

