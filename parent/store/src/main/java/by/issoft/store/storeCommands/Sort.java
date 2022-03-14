package by.issoft.store.storeCommands;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import by.issoft.store.Store;
import by.issoft.store.utils.XmlProcessingUtil;
import by.issoft.store.utils.commanUtils.CommandProcessor;
import by.issoft.store.utils.commanUtils.Commands;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Sort extends Store implements Commands {

    private  HashMap<String,String> xmlConfig;
    private  Comparator<Product> productComparator;
    private  List<Product> sortProducts = new ArrayList<>();
    private String inputCategory = null;

    //Constructor
    public  Sort() throws IOException, SAXException, ParserConfigurationException {
        //Get xml config parameters
       xmlConfig =  new XmlProcessingUtil().getXmlConfig();
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

    public List<Product> makeProductSortingList() {

        System.out.println("Choose category to sort");
        //Print categories for client
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        //Add possibility to sort and print all products
        System.out.println("All");
        System.out.print("Input category --> ");
        inputCategory = CommandProcessor.getCommand();
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