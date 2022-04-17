package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.domain.Category.Product;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.XmlProcessingUtil;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.*;

public class Sort implements CommandsInterface {

    private HashMap<String, String> xmlConfig;
    private Comparator<Product> productComparator;
    private List<Product> sortProducts = new ArrayList<>();
    private String inputCategory = null;

    //Constructor

    public Sort() {
        //Get xml config parameters
        try {
            xmlConfig = new XmlProcessingUtil().getXmlConfig();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        //Init comparator
        productComparator = comparatorReverser(new NameComparator())
                .thenComparing(comparatorReverser(new PriceComparator())
                        .thenComparing(comparatorReverser(new RateComparator())));

    }

    //Set comparator sort to necessary way
    private Comparator<Product> comparatorReverser(Comparator<Product> comp) {
        return this.xmlConfig.get(comp.toString()).equals("asc") ? comp : comp.reversed();
    }

    @Override
    public void execute() {
        try {
            makeProductSortingList()
                    .stream()
                    .sorted(productComparator)
                    .forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sorry we have some problems!\r\nTry again.");
        }

    }

    @Override
    public void execute(Object object) {

    }

    @SneakyThrows
    protected List<Product> makeProductSortingList() {
        ResultSet categorySet = DBClientUtil.exec(TemplateQueryEnum.GET_CATEGORY_LIST.getQuery());
        Map<String, Integer> categoryMap = new HashMap<>();
        while (categorySet.next()) {
                categoryMap.put(categorySet.getString(2),categorySet.getInt(1));
            }
        System.out.println("Choose category to sort");
        //Print categories for client
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        //Add possibility to sort and print all products
        System.out.println("All");
        System.out.print("Input category --> ");
        inputCategory = StreamUtil.getInputData();
        if (inputCategory.equals("All")) {
            System.out.println("== All categories ==");
             categoryMap.forEach((key1, value) -> {
                 ResultSet productSet = DBClientUtil.exec(
                         String.format(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery(), value));
                 addSortList(productSet);
             });
            return sortProducts;
        }
        if (categoryMap.containsKey(inputCategory)) {
            ResultSet productSet = DBClientUtil.exec(String.format(
                    TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery(), categoryMap.get(inputCategory)));
            addSortList(productSet);
        } else {
            System.out.println("Unknown category!\r\nPlease try again.");
            return null;
        }
        return sortProducts;
    }
    @SneakyThrows
    private void addSortList(ResultSet productSet){
        while (productSet.next()) {
            sortProducts.add(new Product(
                    productSet.getString(productSet.findColumn("product_name")),
                    (float) productSet.getInt(productSet.findColumn("product_rate")),
                    (float) productSet.getDouble(productSet.findColumn("product_price"))));
        }
    }

    @Override
    public String toString() {
        return "Sort";
    }
}

//Comparators


class RateComparator implements Comparator<Product> {

        @Override
        public int compare(Product e1, Product e2) { return (int) (e1.getRate() - e2.getRate()); }

        @Override
        public String toString() { return "rate"; }
}


class NameComparator implements Comparator<Product> {

    @Override
    public int compare(Product e1, Product e2) {
        return e1.getName().compareTo(e2.getName());
    }

    @Override
    public String toString() {
        return "name";
    }
}
    class PriceComparator implements Comparator<Product> {

        @Override
        public int compare(Product e1, Product e2) {
            return (int) (e1.getPrice() - e2.getPrice());
        }

        @Override
        public String toString() {
            return "price";
        }
    }