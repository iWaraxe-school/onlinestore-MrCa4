package by.issoft.store.commands.storeCommands.inDatabaseStorage;

import by.issoft.domain.Category.Product;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.XmlProcessingUtil;
import by.issoft.store.utils.commandUtils.CommandsInterface;
import by.issoft.store.utils.dbUtils.DBClientUtil;
import by.issoft.store.utils.dbUtils.TemplateQueryEnum;
import lombok.SneakyThrows;

import java.sql.ResultSet;
import java.util.*;

import static by.issoft.store.utils.StackTraceUtil.checkStackTrace;

public class Sort implements CommandsInterface {

    private HashMap<String, String> xmlConfig;
    private Comparator<Product> productComparator;
    private List<Product> sortProducts = new ArrayList<>();
    private String inputCategory = null;

    //Constructor
    @SneakyThrows
    public Sort() {
        //Get xml config parameters
        xmlConfig = new XmlProcessingUtil().getXmlConfig();
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
    @SneakyThrows
    public void execute() {
        List<String> stackTrace = new ArrayList();
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(i->stackTrace.add(i.getClassName()));
            makeProductSortingList()
                    .stream()
                    .sorted(productComparator)
                    .forEach(System.out::println);


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
        List<String> stackTrace = new ArrayList();
        Arrays.stream(Thread.currentThread().getStackTrace()).forEach(i->stackTrace.add(i.getClassName()));
        inputCategory = checkStackTrace(stackTrace,"httpserver")? "All":clientSortConsoleDialog();
        if (inputCategory.equals("All")) {
                System.out.println("== All categories ==");
                categoryMap.forEach((key1, value) -> {
                    addSortList(getDataFromDB(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery()
                                                ,value));
                });
                System.out.println(sortProducts);
                return sortProducts;
        }
        if (categoryMap.containsKey(inputCategory)) {
                addSortList(getDataFromDB(TemplateQueryEnum.GET_PRODUCT_LIST_BY_CATEGORY.getQuery()
                                        , categoryMap.get(inputCategory)));
                return sortProducts;
            }
        System.out.println("Unknown category!\r\nPlease try again.");
        return null;



    }

    private String clientSortConsoleDialog(){
        System.out.println("Choose category to sort");
        //Print categories for client
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        //Add possibility to sort and print all products
        System.out.println("All");
        System.out.print("Input category --> ");
        return StreamUtil.getInputData();

    }

    private ResultSet getDataFromDB(String query,Integer param){
        return DBClientUtil.exec(String.format(query,param));
    }

    @SneakyThrows
    private void addSortList(ResultSet productSet){
        while (productSet.next()) {
            sortProducts.add(new Product(
                    productSet.getString(productSet.findColumn("product_name")),
                    (float) productSet.getDouble(productSet.findColumn("product_rate")),
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