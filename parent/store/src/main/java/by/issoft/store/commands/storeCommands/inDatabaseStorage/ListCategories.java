package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.services.httpService.HTTPService;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.issoft.store.utils.StackTraceUtil.checkStackTrace;

public class ListCategories implements CommandsInterface {

    public static List<String> categoriesList= new ArrayList<>();

    @Override
    @SneakyThrows
    public  void execute() {
        ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
        List<String> stackTrace = new ArrayList();
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(i->stackTrace.add(i.getClassName()));

        categoriesList.clear();
        while (categorySet.next()) {
            if(checkStackTrace(stackTrace,"httpserver")) {
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
