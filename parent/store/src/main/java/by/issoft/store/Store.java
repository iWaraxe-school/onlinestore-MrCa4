package by.issoft.store;

import by.issoft.domain.Category.Category;
import by.issoft.store.utils.RandomStorePopulator;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//TODO Refactore and make separate class fo reflection
public class Store {

    private static List<Category> categoryList;
    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();


    /**
     *Инициализация (можно вынести в конструктор)
     */
    public void StoreInitMethod(){
        setCategoryList(getRandomStorePopulator().getAllCategories());
        setAllProducts();
        storeCycleStart();
    }
    protected static List<Category> getCategoryList() {
        return categoryList;
    }
    private void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }
    public RandomStorePopulator getRandomStorePopulator() {
        return randomStorePopulator;
    }

    /**
     * Заполнение всех категорий магазина продуктами
     */

    private void setAllProducts(){
       getCategoryList()
               .forEach(category->category.addProducts(category.getName(),randomStorePopulator.getProductsForCategory(category)));
  }
    private  List<Commands> getAllCommands(){
        //TODO make command "list commands" and "help" call "list commands first"
        List<Commands> commandList = new ArrayList<>();
        Reflections reflections = new Reflections("by.issoft.store.storeCommands", Scanners.SubTypes);
        Set<Class<? extends Commands>> subTypes =
                reflections.getSubTypesOf(Commands.class);

        for (Class<? extends Commands> subType: subTypes){
            try {
                commandList.add(subType.getConstructor().newInstance());
            } catch (InstantiationException
                    | IllegalAccessException
                    | InvocationTargetException
                    | NoSuchMethodException e) {
                e.printStackTrace();
            }

        }
        return commandList;

    }

    private  void storeCycleStart() {
        System.out.println("Available commands: ");
        HashMap<String, Class<? extends Commands>> commandDict= getAllCommands()
                .stream()
                .collect(Collectors.toMap(
                        Object::toString,
                        Commands::getClass,
                        (prev, next) -> next,
                        HashMap::new));
        commandDict.keySet().forEach(System.out::println);
        String command = null;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            System.out.print("Input command --> ");
            try {
                command = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            switch (command) {
                case "clear":
                    break;
                case "exit":
                    System.out.println("Goodbye");
                    break;
                case "help":
                    break;
                default:
                    commandProcessor.execCommand(command, commandDict);
                    break;
            }

        } while (!command.equals("exit"));
    }






}

