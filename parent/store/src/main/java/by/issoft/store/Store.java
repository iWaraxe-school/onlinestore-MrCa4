package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.services.HTTPService;
import by.issoft.store.utils.RandomStoreDBPopulator;
import by.issoft.store.utils.RandomStorePopulator;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.commandUtils.AdminCommandList;
import by.issoft.store.utils.commandUtils.FabricCommands;
import by.issoft.store.utils.commandUtils.StoreInDatabaseCommandList;
import by.issoft.store.utils.orderUtils.CollectorOfCompletedOrdersUtil;

import java.util.ArrayList;
import java.util.List;


//TODO Refactore and make separate class fo reflection
public class Store {


    private static Store store = null;
    private static List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
    private RandomStoreDBPopulator randomStoreDBPopulator = new RandomStoreDBPopulator();
    private static String command = null;
    public static FabricCommands storeFabricCommands;
    public static FabricCommands admFabricCommands = new AdminCommandList();
    public static List<Order> completedOrders = new ArrayList<>();

    //Init shop
    private Store(){
    }

    public static Store getStore(){
        return store = (store == null) ? new Store():store;
    }

    public void StoreInitMethod(String storageType){
//        if (storageType.equals("reflection")) {
//            storeFabricCommands = new StoreInMemoryCommandList();
//             setCategoryList(randomStorePopulator.getAllCategories());
//             setAllProducts();
//        }
        if (storageType.equals("database")){
        storeFabricCommands = new StoreInDatabaseCommandList();
        randomStoreDBPopulator.process();
            HTTPService httpService = HTTPService.getHTTPService(storeFabricCommands);
            httpService.startHttpServer();
        }
        else{
            System.out.println("Invalid store Type");
            System.exit(1);
        }
        new Thread(CollectorOfCompletedOrdersUtil.getCollectorOfCompletedOrdersUtil()).start();
        storeCycleStart();
    }

    public static List<Category> getCategoryList() {
        return categoryList;
    }

    @Deprecated
    private void setCategoryList(List<Category> scannedCategoryList) {
        categoryList = scannedCategoryList;
    }


    @Deprecated
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

