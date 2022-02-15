package by.issoft.domain.Category.SubCategories;
import by.issoft.domain.Category.Category;
import com.github.javafaker.Faker;

public class BikeCategory extends Category {

    public BikeCategory(String name){
        super(name);
    }
    public BikeCategory(){}
    @Override
    public  String getUsableProductName(Faker faker) {
        return faker.superhero().name();
    }
}
