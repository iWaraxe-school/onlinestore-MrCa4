import by.issoft.store.utils.StreamUtil;
import org.junit.*;

public class StreamUtilTest {


        private StreamUtil streamUtil = new StreamUtil();



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
        public void someTest() throws Exception {

//            xmlProcessingUtil.getXmlConfig().values()
//                    .forEach(i->Assert.assertTrue(i.equals("desc")  || i.equals("asc")));
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
