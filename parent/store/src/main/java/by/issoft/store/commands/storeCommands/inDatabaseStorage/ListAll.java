package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.services.httpService.HTTPService;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.issoft.store.utils.StackTraceUtil.checkStackTrace;

public class ListAll implements CommandsInterface {

    public static List<String> categoriesAndProductsList = new ArrayList<>();


    @Override
//    @SneakyThrows
    public void execute() {
        try {
            ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
            Map<Integer, String> categoryMap = new HashMap<>();
            List<String> stackTrace = new ArrayList();
            Arrays.stream(Thread.currentThread().getStackTrace()).forEach(i -> stackTrace.add(i.getClassName()));
            categoriesAndProductsList.clear();
            while (categorySet.next()) {
                categoriesAndProductsList.add("[" + categorySet.getString(2) + "]");
                ResultSet productSet = DBClientUtil.exec(
                        String.format(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery()
                                , categorySet.getInt(1)));

                if (checkStackTrace(stackTrace, "httpserver")) {
                    List<String> products = new ArrayList<>();
                    while (productSet.next()) {
                        products.add(String.format("Product: %s, Rate: %s, Price: %s"
                                , productSet.getString("product_name")
                                , productSet.getDouble("product_rate")
                                , productSet.getDouble("product_price")));
                    }
                    categoriesAndProductsList.addAll(products);
                    products.clear();
                } else {
                    System.out.println(String.format("Category: %s", categorySet.getString(2)));
                    while (productSet.next()) {
                        System.out.println(String.format("Product: %s, Rate: %s, Price: %s"
                                , productSet.getString("product_name")
                                , productSet.getDouble("product_rate")
                                , productSet.getDouble("product_price")));
                    }
                }
            }
            HTTPService.setCategoriesAndProductsList(categoriesAndProductsList);
        }catch (SQLException e){
            e.printStackTrace();
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
