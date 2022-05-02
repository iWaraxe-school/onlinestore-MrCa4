package by.issoft.store;

import by.issoft.domain.Category.Product;
import by.issoft.store.utils.commandUtils.FabricCommands;
import by.issoft.store.utils.commandUtils.StoreInMemoryCommandList;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.issoft.store.Store.getCategoryList;

public class Order {

    private String orderNumber;
    private String shopperName;
    public FabricCommands storeCommands = new StoreInMemoryCommandList();
    public static List<Product> productList = new ArrayList<>();
    public Map<Product,Integer> cart = new HashMap<>();

    private static void getProducts(){
            getCategoryList().forEach(cat-> productList.addAll(cat.getProductList(cat)));
    }

    public  Order(){
        getProducts();
        this.orderNumber= RandomStringUtils.random(1,1,1000000,true,true);
    }
    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }


}
