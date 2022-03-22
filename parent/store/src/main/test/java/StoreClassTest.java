import by.issoft.store.Store;
import org.junit.*;

public class StoreClassTest {

    private Store store = Store.getStore();



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
    public void testGetStoreInstance() throws Exception {
        Assert.assertEquals(Store.getStore(), store);
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