package by.issoft.domain.Category;

import domainHelpers.DomainDataValidators;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Category {

    private  String categoryName;
    private  List<Product> productList;

    public Category (String name){

        setName(name);
    }
    public Category(){

    }

    public  String getName()
    {
        return this.categoryName;
    }

    public  void setName(String name)
    {
        this.categoryName = DomainDataValidators.stringValidator(name);
    }

    //Work with products
    public Boolean addProducts(Product product){

       return this.addProducts(new  ArrayList(Arrays.asList(product)));
    }

    public Boolean addProducts(List<Product> products){
        if (productList == null){
        productList = new ArrayList<Product>();}
        productList.addAll(products);
        return true;
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
