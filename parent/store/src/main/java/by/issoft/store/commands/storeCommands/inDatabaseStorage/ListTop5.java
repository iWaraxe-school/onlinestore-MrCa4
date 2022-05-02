package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.domain.Category.Product;
import by.issoft.store.services.httpService.HTTPService;
import by.issoft.store.utils.commandUtils.CommandsInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static by.issoft.store.utils.StackTraceUtil.checkStackTrace;

public class ListTop5 extends Sort implements CommandsInterface {


    List<String> productsList = new ArrayList<>();
    @Override
    public void execute() {
        List<String> stackTrace = new ArrayList();
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(i->stackTrace.add(i.getClassName()));
        List<Product> top5 = new Sort()
                    .makeProductSortingList()
                    .stream()
                    .limit(5).collect(Collectors.toList());
        if(checkStackTrace(stackTrace,"httpserver")) {
            top5.forEach(p->productsList.add(  "Product name: " + p.getName() +
                            " Price: "  + p.getPrice() +
                            " Rate: " + p.getRate()));

            HTTPService.setCategoriesAndProductsList(productsList);
        }
        else{
            top5.forEach(p->
                    System.out.println(
                            "Product name: " + p.getName() +
                                    " Price: "  + p.getPrice() +
                                    " Rate: " + p.getRate()));
        }

    }

    @Override
    public void execute(Object object) {

    }

    @Override
    public String toString() {
        return "Top5";
    }
}
