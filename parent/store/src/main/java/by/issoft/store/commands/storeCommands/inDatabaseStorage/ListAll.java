package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.services.HTTPService;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ListAll implements CommandsInterface {

    public static Map<String,List<String>> categoriesAndProductsMap = new HashMap<>();


    @Override
    @SneakyThrows
    public void execute() {
        ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
        Map<Integer,String> categoryMap = new HashMap<>();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        categoriesAndProductsMap.clear();
        while (categorySet.next()){
            categoryMap.put(categorySet.getInt(1),categorySet.getString(2));
        }
        for (Entry<Integer,String> key:categoryMap.entrySet()) {
            if(stackTrace[3].getClassName().contains("HTTPService")) {
                List<String> products = new ArrayList<>();
                ResultSet productSet = DBClientUtil.exec(
                        String.format(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery(), key.getKey()));
                while (productSet.next()) {
                    products.add(String.format("Product: %s, Rate: %s, Price: %s"
                            , productSet.getString("product_name")
                            , productSet.getInt("product_rate")
                            , productSet.getDouble("product_price")));
                }
                categoriesAndProductsMap.put(key.getValue(),products);
                HTTPService.setCategoriesAndProductsMap(categoriesAndProductsMap);
            }
            else {
                System.out.println(String.format("Category: %s", key.getValue()));
                ResultSet productSet = DBClientUtil.exec(
                        String.format(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery(), key.getKey()));
                while (productSet.next()) {
                    System.out.println(String.format("Product: %s, Rate: %s, Price: %s"
                            , productSet.getString("product_name")
                            , productSet.getInt("product_rate")
                            , productSet.getDouble("product_price")));
                }
            }
        }
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List All";
    }
}
