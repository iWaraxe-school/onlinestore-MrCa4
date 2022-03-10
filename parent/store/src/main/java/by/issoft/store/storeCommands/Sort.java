package by.issoft.store.storeCommands;

import by.issoft.domain.Category.Category;
import by.issoft.domain.Category.Product;
import by.issoft.store.Commands;
import by.issoft.store.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.TreeSet;

import static by.issoft.store.utils.XmlProcessingUtil.getXmlConfig;


//TODO Refactore, use xml asc,desc

public class Sort extends Store implements Commands {

    public static HashMap<String,String> xmlConfig;
    private static Comparator<Product> productComparator;
    private static TreeSet<Product> sortProducts;
    private String command = null;


    public  Sort() {
       try {
           xmlConfig =  getXmlConfig();
       }catch (Exception e){
           e.printStackTrace();
       }
       productComparator = new NameComparator()
                .thenComparing(new PriceComparator()
                        .thenComparing(new RateComparator()));
       sortProducts = new TreeSet(productComparator);

    }

    @Override
    public void execute() {
        makeProductSortingList().forEach(p-> System.out.println(
                "Product name: " + p.getName() +
                        "Price: "  + p.getPrice() +
                        "Rate: " + p.getRate()));

    }
    public TreeSet<Product> makeProductSortingList() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose category to sort");
        ListCategories listCategories = new ListCategories();
        listCategories.execute();
        System.out.println("All");
        System.out.print("Input category --> ");
        try {
            command = bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO
        if (command.equals("All")){
           getCategoryList()
                   .stream()
                   .forEach(i-> sortProducts.addAll(i.getProductList(i)));
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

    class RateComparator implements Comparator<Product> {

        @Override
        public int compare(Product e1, Product e2) {
            return (int) (e1.getRate() - e2.getRate());
        }
    }


    class NameComparator implements Comparator<Product> {

        @Override
        public int compare(Product e1, Product e2) {
            return e1.getName().compareTo(e2.getName());
        }
    }
    class PriceComparator implements Comparator<Product> {

        @Override
        public int compare(Product e1, Product e2) {
            return (int) (e1.getPrice() - e2.getPrice());
        }
    }