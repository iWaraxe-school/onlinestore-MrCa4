package by.issoft.consoleApp;


import by.issoft.store.Store;

public class StoreApp {

    public static void main(String[] args) {

        //Start our shop
        //String storageType = args[0];
        String storageType = "database";
        Store store = Store.getStore();
        store.StoreInitMethod(storageType);

    }


}
