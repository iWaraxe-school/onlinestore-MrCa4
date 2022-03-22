import by.issoft.store.utils.XmlProcessingUtil;
import org.junit.*;

import java.util.HashMap;

public class XmlProcessingUtilTest {

    private XmlProcessingUtil xmlProcessingUtil = new XmlProcessingUtil();


    @BeforeClass
    public static void globalSetUp() {
        System.out.println("Initial setup...");
        System.out.println("Code executes only once");
    }

    @Before
    public void setUp() {
        System.out.println("Current test starting.....");
    }

    @Test
    public void getXmlProcessingUtilReturnTypeTest() throws Exception {
        Assert.assertEquals(xmlProcessingUtil.getXmlConfig().getClass(), HashMap.class);
    }

    @Test
    public void getXmlProcessingKeysTest() throws Exception {
       xmlProcessingUtil.getXmlConfig().keySet()
               .forEach(i->Assert.assertEquals(i.getClass(),String.class));
    }

    @Test
    public void getXmlProcessingValuesTest() throws Exception {

        xmlProcessingUtil.getXmlConfig().values()
                .forEach(i->Assert.assertTrue(i.equals("desc")  || i.equals("asc")));
    }


    @AfterClass
    public static void tearDown() {
        System.out.println("Tests finished");
    }

    @After
    public void afterMethod() {
        System.out.println("current test end");
    }
}