package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;

public class ListCategories implements CommandsInterface {

    @Override
    @SneakyThrows
    public  void execute() {
        ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
        System.out.println("== All categories ==");
        while (categorySet.next()) {
            System.out.println(String.format("Category: %s", categorySet.getString(2)));
        }
    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "List Category";
    }
}
