package by.issoft.store;

import by.issoft.domain.Category.Product;
import by.issoft.store.utils.commanUtils.FabricCommands;
import by.issoft.store.utils.commanUtils.StoreCommandList;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static by.issoft.store.Store.getCategoryList;

public class Order {

    private String orderNumber;
    private String shopperName;
    public FabricCommands storeCommands = new StoreCommandList();
    public static List<Product> productList = new ArrayList<Product>();
    public Map<Product,Integer> cart = new HashMap<>();

    private static void getProducts(){
            getCategoryList().forEach(cat->cat.getProductList(cat)
                    .forEach(i->productList.add(i)));
    }

    public  Order(){
        getProducts();
        this.orderNumber= RandomStringUtils.random(1,1,1000000,true,true);
    }

    @Setter
    public void setShopperName(String shopperName) {
        this.shopperName = shopperName;
    }


}
