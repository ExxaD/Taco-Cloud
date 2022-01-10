package tacos.kitchen.messaging.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import tacos.domain.Order;

@Component
public class RabbitOrderReceiver {
    private RabbitTemplate rabbit;
    private MessageConverter converter;

    public RabbitOrderReceiver(RabbitTemplate rabbit, MessageConverter converter) {
        this.rabbit = rabbit;
        this.converter = converter;
    }

    public Order receiveOrder() {
        /*Message message = rabbit.receive("tacocloud.order.queue");
        return message != null
               ? (Order) converter.fromMessage(message)
               : null;*/
        /*return (Order) rabbit.receiveAndConvert("tacocloud.order.queue");*/
        return rabbit.receiveAndConvert("tacocloud.order.queue", new ParameterizedTypeReference<Order>() {});
    }
}
