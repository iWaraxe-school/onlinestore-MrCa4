package by.issoft.domain.Category.SubCategories;
import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;

public class BikeCategory extends Category {

    public BikeCategory(){
        super("Bike");
    }

    @Override
    public  String getUsableProductName(Faker faker) {
        return "Super hero bike: " + faker.superhero().name();
    }
}
