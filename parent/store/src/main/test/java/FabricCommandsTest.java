import by.issoft.store.utils.ReflectionGetSubTypes;
import by.issoft.store.utils.commanUtils.CommandsInterface;
import by.issoft.store.utils.commanUtils.FabricCommands;
import org.junit.*;

import java.util.List;

public class FabricCommandsTest {

    private FabricCommands fabricCommands = new FabricCommands(){
        @Override
        public   List<CommandsInterface> findCommands(){
            return ReflectionGetSubTypes.findCommands("by.issoft.store.storeCommands");
        }
    };



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
        Assert.assertEquals(List.class,fabricCommands.findCommands().getClass());
        //fabricCommands.printCommandList();
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
