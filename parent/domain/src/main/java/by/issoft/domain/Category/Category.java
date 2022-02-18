package by.issoft.domain.Category;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

abstract public class Category {

    private  String name;
    private  List<Product>  productList;

    public Category (String name){
        this.name = name;
    }

    public String getName() {
        //Для получения имени категории при выводе списка всех категорий
        return name;
    }

    public void addProducts(List<Product> products) {
        if (productList == null) {
            productList = new ArrayList<>();
        }
        productList.addAll(products);

    }

    public void printAllProduct() {
        System.out.println("Current category --> " + getName());
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }


    /*
        Для генерации произвольных имен продуктов я видел два пути:
    1.Создание Enum класса где будут перечислены подкатегории и при
    генерации имен сверять текущую категорию с элементом Enum     и
    генерировать имя, например:
            switch(category){
                    case Bike:
                        return faker.bike();
                    case Milk:
                    ...
    Тогда, если бы понадобилось добавить еще один подкласс, например,
    Sport, то пришлось бы вносить изменения в Enum, Populator      и,
    возможно где то ещё.
    2. Поручить генерировать имена продуктов самой подкатегории, тогда
    при добавлении нового подкласа просто необходимо реализовать метод
    генерации имени.

    Пока что я выбрал 2 вариант. Если скажете - переделаю.
     */
    public abstract String getUsableProductName(Faker faker);
}
