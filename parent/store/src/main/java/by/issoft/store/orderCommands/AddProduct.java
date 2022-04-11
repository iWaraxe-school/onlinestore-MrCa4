package by.issoft.store.orderCommands;

import by.issoft.store.Order;
import by.issoft.store.utils.commandUtils.CommandsInterface;

public class AddProduct  implements CommandsInterface {



    @Override
    public void execute() {

    }

    @Override
    public void execute(Object object) {
        Order order = (Order) object;
//        order.storeCommands.exec("List Products");
//        System.out.print("Write the product that should be added to the cart ->");
//        order.cart.put(order.productList
//                .stream()
//                .parallel()
//                .filter(x -> x.getName().equals(getInputData()))
//                .findFirst().orElse(null),1);

                 order.cart.put(order.productList
                .stream()
                .parallel()
                .findAny().orElse(null),1);
        System.out.println(order.cart.keySet());
    }

    @Override
    public String toString() {
        return "Add";
    }
}
