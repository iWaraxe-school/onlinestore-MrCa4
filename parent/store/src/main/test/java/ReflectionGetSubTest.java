import by.issoft.store.utils.ReflectionGetSubTypes;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;

import java.util.ArrayList;

import static by.issoft.store.utils.ReflectionGetSubTypes.*;

public class ReflectionGetSubTest {


        private ReflectionGetSubTypes reflectionGetSubTypes = new ReflectionGetSubTypes();



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
        public void testFindCommands() {

            String generatedString = RandomStringUtils.randomAscii(100);
            Assert.assertTrue(
                    (0 == findCommands("").size()) &&
                            ArrayList.class.equals(findCommands(generatedString).getClass()) &&
                            (0 != findCommands("by").size())
            );

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
