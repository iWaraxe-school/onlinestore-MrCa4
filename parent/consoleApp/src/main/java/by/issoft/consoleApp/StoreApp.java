package by.issoft.consoleApp;


import by.issoft.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        //Попытка инициализации  и заполнения магазина

        Store store = new Store();
        store.StoreInitMethod();

        /*На данном этапе магазин проинециализирован и можно уже
          реализовывать функционал */

//        for (Category category:store.getCategoryList()){
//            category.printAllProduct();
//        }

    }
}
