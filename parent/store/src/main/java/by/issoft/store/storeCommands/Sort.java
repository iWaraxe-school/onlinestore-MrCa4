package by.issoft.store.storeCommands;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import by.issoft.store.Commands;
import by.issoft.store.Store;
import by.issoft.store.utils.XmlProcessingUtil;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Sort extends Store implements Commands {

    private  HashMap<String,String> xmlConfig;
    private  Comparator<Product> productComparator;
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private  List<Product> sortProducts = new ArrayList<>();
    private String command = null;

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
        makeProductSortingList().stream().sorted(productComparator).forEach(System.out::println);

    }

    public List<Product> makeProductSortingList() {

        System.out.println("Choose category to sort");
        //Print categories for client
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        //Add possibility to sort and print all products
        System.out.println("All");
        System.out.print("Input category --> ");
        try {
            command = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (command.isEmpty()){
                return sortProducts;
            }
        }
        if (command.equals("All")){
             Category.productsDict.values().forEach(category->sortProducts.addAll(category));
        }
        else {
            sortProducts.addAll(Category.productsDict.get(command));
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