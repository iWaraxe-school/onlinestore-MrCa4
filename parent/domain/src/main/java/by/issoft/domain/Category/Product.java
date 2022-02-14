package by.issoft.domain.Category;

import domainHelpers.DomainDataValidators;

public class Product {

    private String name;
    private Float price;
    private Float rate;

    public Product(){}

    public Product(String name, Float price, Float rate){
        setName(name);
        setPrice(price);
        setRate(rate);
    }

    @Override
    public String toString() {

        return String.format(" Product name: '%s'," +
                " Price: %.2f, " +
                "Rate: %.1f",
                this.name, this.price, this.rate);
    }


    public  String getName()
    {
        return this.name;
    }

    public void setName(String name) {
        if (0 == name.length() && name.length() > 150) {
            this.name = DomainDataValidators.stringValidator(name);
        }
        else{
            throw new RuntimeException("Too short or to long product name");
        }
    }

    public  Float getPrice()
    {
        return this.price;
    }

    public  void setPrice(Float price) {
        if (price < 0){
            throw new RuntimeException("Rate cannot be less then zero");
        }
        else {
            this.price = price;
        }
    }

    public  Float getRate() {
        return this.rate;
    }

    public  void setRate(Float rate) {
        if (rate < 0){
            throw new RuntimeException("Rate cannot be less then zero");
        }
        else {
            this.rate = rate;
        }
    }
}
