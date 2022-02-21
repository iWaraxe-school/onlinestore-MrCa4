package by.issoft.domain.Category.SubCategories;
import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;

public class MilkCategory extends Category {

    public MilkCategory()
    {
        super("Milk");
    }

    @Override
    public  String getUsableProductName(Faker faker) {
        return "Milk with " + faker.food().fruit();
    }
}
