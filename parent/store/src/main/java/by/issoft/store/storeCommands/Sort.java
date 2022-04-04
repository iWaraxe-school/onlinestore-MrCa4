package by.issoft.store.storeCommands;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import by.issoft.store.utils.StreamUtil;
import by.issoft.store.utils.XmlProcessingUtil;
import by.issoft.store.utils.commanUtils.CommandsInterface;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static by.issoft.store.Store.getCategoryList;

public class Sort implements CommandsInterface {

    private  HashMap<String,String> xmlConfig;
    private  Comparator<Product> productComparator;
    private  List<Product> sortProducts = new ArrayList<>();
    private String inputCategory = null;

    //Constructor

    public  Sort()  {
        //Get xml config parameters
        try {
            xmlConfig =  new XmlProcessingUtil().getXmlConfig();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        //Init comparator
       productComparator = comparatorReverser(new NameComparator())
                .thenComparing(comparatorReverser(new PriceComparator())
                        .thenComparing(comparatorReverser(new RateComparator())));

    }

    //Set comparator sort to necessary way
    private  Comparator<Product> comparatorReverser(Comparator<Product> comp){
        return this.xmlConfig.get(comp.toString()).equals("asc") ? comp : comp.reversed();
    }

    @Override
    public void execute() {
        try {
            makeProductSortingList()
                    .stream()
                    .sorted(productComparator)
                    .forEach(System.out::println);
        }
        catch (Exception e){
            System.out.println("Sorry we have some problems!\r\nTry again.");
        }

    }

    @Override
    public void execute(Object object) {

    }

    protected List<Product> makeProductSortingList() {

        System.out.println("Choose category to sort");
        //Print categories for client
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        //Add possibility to sort and print all products
        System.out.println("All");
        System.out.print("Input category --> ");
        inputCategory = StreamUtil.getInputData();
        if (inputCategory.equals("All")){
             Category.productsDict.values().forEach(category->sortProducts.addAll(category));
        }
        if (getCategoryList().stream()
                .map(i->i.getName())
                .collect(Collectors.toList())
                .contains(inputCategory)) {

            sortProducts.addAll(Category.productsDict.get(inputCategory));
        }
        else{
            System.out.println("Unknown category!\r\nPlease try again.");
            return null;
        }
        return sortProducts;

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