import by.issoft.store.utils.RandomStorePopulator;
import org.junit.*;

import java.util.ArrayList;

public class RandomStorePopulatorTest {


    private RandomStorePopulator randomStorePopulator = new RandomStorePopulator();

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
        System.out.println("Code executes only once");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
        // store.StoreInitMethod();

    }

    @Test
    public void testPopulatorCategoriesclass() throws Exception {
      Assert.assertEquals(ArrayList.class, randomStorePopulator.getAllCategories().getClass());
       //List<Category> categoryList = randomStorePopulator.getAllCategories();

       try {
           randomStorePopulator
                   .generateProductsForCategory(randomStorePopulator.getAllCategories().get(0), null);
       }
       catch (Exception ex){
           Assert.assertFalse(false);
       }

    }


    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

    @After
    public void afterMethod() {
        System.out.println("Code executes after each test method");
    }
}