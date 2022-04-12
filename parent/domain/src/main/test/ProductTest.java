import by.issoft.domain.Category.Product;

import org.junit.*;

public class ProductTest {

    private Product product ;

    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
    }

    @Before
    public void setUp() {
        System.out.println("Code executes before each test method");
    }

    @Test
    public void testProductInstance() throws Exception {
        product = new Product("test",Float.valueOf(100),Float.valueOf(10));
        Assert.assertEquals(product.getName(), "test");
        Assert.assertEquals(product.getRate(), Float.valueOf(10));
        Assert.assertEquals(product.getPrice(), Float.valueOf(100));
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