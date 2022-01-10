package tacos.kitchen.messaging.jms.listener;

import org.springframework.stereotype.Component;
import tacos.domain.Order;

@Component
public class KitchenUI {
    public void displayOrder(Order order) {
        System.out.println(order.toString());
    }
}
