package by.issoft.domain.Category;

public class Product {

    private static  String name;
    private static  Integer price;
    private static  Integer rate;


    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Product.name = name;
    }

    public static Integer getPrice() {
        return price;
    }

    public static void setPrice(Integer price) {
        Product.price = price;
    }

    public static Integer getRate() {
        return rate;
    }

    public static void setRate(Integer rate) {
        Product.rate = rate;
    }
}
