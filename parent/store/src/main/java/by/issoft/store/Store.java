package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.RandomStorePopulator;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.commanUtils.AdminCommandList;
import by.issoft.store.utils.commanUtils.FabricCommands;
import by.issoft.store.utils.commanUtils.UserCommandList;

import java.util.List;


//TODO Refactore and make separate class fo reflection
public class Store {


    private static Store store = null;
    private static List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
    private static String command = null;
    private FabricCommands fabricCommands;
 //   public static HashMap<String, Class<? extends Commands>> commandDict;

    //Init shop
    private Store(){

    }


    public static Store getStore(){
        if(store==null){
            store = new Store();
        }
        return store;
    }

    public void StoreInitMethod(){

        setCategoryList(randomStorePopulator.getAllCategories());
        setAllProducts();
      //  CommandList.getCommandList();
        storeCycleStart();
    }
    public static List<Category> getCategoryList() {
        return categoryList;
    }
    private void setCategoryList(List<Category> scannedCategoryList) {
        categoryList = scannedCategoryList;
    }
//    private RandomStorePopulator getRandomStorePopulator() {
//        return randomStorePopulator;
//    }
    private void setAllProducts(){
       getCategoryList()
               .forEach(category->category.addProducts(category.getName(),
                                                        randomStorePopulator.
                                                                getProductsForCategory(category)));
    }


    private  void storeCycleStart() {
        System.out.println("Available commands: ");
        fabricCommands= new UserCommandList();
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
                    fabricCommands = new AdminCommandList();
                    fabricCommands.printCommandList();
                    System.out.println("Input Admin command -->");
                    fabricCommands.exec(StreamUtil.getInputData());
                    break;
                default:
                    fabricCommands.exec(command);
                    break;
            }

        } while (!command.equals("quit"));
    }

}

