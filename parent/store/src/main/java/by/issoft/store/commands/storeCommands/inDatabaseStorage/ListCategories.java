package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.services.HTTPService;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ListCategories implements CommandsInterface {

    public static List<String> categoriesList= new ArrayList<>();

    @Override
    @SneakyThrows
    public  void execute() {
        ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
        System.out.println("== All categories ==");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        categoriesList.clear();
        while (categorySet.next()) {
            if(stackTrace[3].getClassName().contains("HTTPService")) {
                categoriesList.add(categorySet.getString(2));
            }
            else {
                System.out.println(String.format("Category: %s", categorySet.getString(2)));
            }
        }
        HTTPService.setCategoryListCacshe(categoriesList);
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Category";
    }
}
