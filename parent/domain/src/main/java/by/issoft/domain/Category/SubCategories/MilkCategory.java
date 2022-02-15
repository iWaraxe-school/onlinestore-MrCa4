package by.issoft.domain.Category.SubCategories;
import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;

public class MilkCategory extends Category {

    public MilkCategory(String name)
    {
        super(name);
    }
    public MilkCategory(){}
    @Override
    public  String getUsableProductName(Faker faker) {
        return faker.food().fruit();
    }
}
