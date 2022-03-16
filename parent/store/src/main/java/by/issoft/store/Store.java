package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.RandomStorePopulator;
import by.issoft.store.utils.commanUtils.CommandList;
import by.issoft.store.utils.commanUtils.CommandProcessor;
import by.issoft.store.utils.commanUtils.Commands;

import java.util.HashMap;
import java.util.List;


//TODO Refactore and make separate class fo reflection
public class Store {


    private static Store store = null;
    private static List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();
    private static String command = null;
    public static HashMap<String, Class<? extends Commands>> commandDict;

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
        setCategoryList(getRandomStorePopulator().getAllCategories());
        setAllProducts();
        CommandList.getCommandList();
        storeCycleStart();
    }
    public static List<Category> getCategoryList() {
        return categoryList;
    }
    private void setCategoryList(List<Category> scannedCategoryList) {
        categoryList = scannedCategoryList;
    }
    private RandomStorePopulator getRandomStorePopulator() {
        return randomStorePopulator;
    }
    private void setAllProducts(){
       getCategoryList()
               .forEach(category->category.addProducts(category.getName(),
                                                        randomStorePopulator.
                                                                getProductsForCategory(category)));
    }


    private  void storeCycleStart() {
        System.out.println("Available commands: ");
        CommandList.printCommandList();
        do {
            System.out.print("Input command --> ");
            command = CommandProcessor.getCommand();
            switch (command) {
                case "quit":
                    System.out.println("Goodbye");
                    break;
                case "help":
                    System.out.println("With all questions you should contact Mr.Cat");
                    break;
                default:
                    CommandProcessor.execCommand(command, commandDict);
                    break;
            }

        } while (!command.equals("quit"));
    }

}

