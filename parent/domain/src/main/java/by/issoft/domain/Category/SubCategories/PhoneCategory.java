package by.issoft.domain.Category.SubCategories;

import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;


public class PhoneCategory extends Category {

    public PhoneCategory(String name){
        super(name);
    }
    public PhoneCategory() {}



    @Override
    public  String getUsableProductName(Faker faker) {
        return faker.company().name();
    }
}
