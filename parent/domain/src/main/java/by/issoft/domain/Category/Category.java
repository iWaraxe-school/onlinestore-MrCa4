package by.issoft.domain.Category;



import java.util.ArrayList;
import java.util.List;

public class Category {

    private  String categoryName;
    private  List<Product> productList;

    public Category (String name){

        this.name=name;
    }
    
    public void addProducts(List<Product> products){
        if (productList == null){
        productList = new ArrayList<Product>();}
        productList.addAll(products);
    }

    public void listProducts(){
        System.out.println("[================ List of products ====================]");
        if (productList != null){
            for (Product product:productList){
                System.out.println(product.toString());
            }
        }
        else{
            System.out.println("Sorry, no products in store. :(");
        }
    }


}
