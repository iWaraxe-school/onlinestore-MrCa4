package by.issoft.domain.Category;

public class Product {

    private   String name;
    private   Integer price;
    private   Integer rate;


    public Product(String name, Float price, Float rate){
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    @Override
    public String toString() {

        return String.format(" Product name: '%s'," +
                " Price: %.2f, " +
                "Rate: %.1f",
                this.name, this.price, this.rate);
    }


}