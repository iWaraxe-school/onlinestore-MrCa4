package by.issoft.domain.Category.SubCategories;

import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;


public class PhoneCategory extends Category {

    public PhoneCategory()
    {
        super("Phone");
    }

    @Override
    public  String getUsableProductName(Faker faker) {
        return "Nopia/Model: " + faker.number().digits(2);
    }
}
