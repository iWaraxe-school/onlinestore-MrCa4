package by.issoft.store.storeCommands;

import by.issoft.store.Commands;
import by.issoft.store.Store;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ListTop5 extends Store implements Commands {
    @Override
    public void execute() {
        Sort sortCommand = null;
        try {
            sortCommand = new Sort();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        sortCommand.makeProductSortingList().stream().limit(5).forEach(p-> System.out.println(
                "Product name: " + p.getName() +
                        "Price: "  + p.getPrice() +
                        "Rate: " + p.getRate()));
    }

    @Override
    public String toString() {
        return "Top5";
    }
}
