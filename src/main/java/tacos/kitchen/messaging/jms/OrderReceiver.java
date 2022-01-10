package tacos.kitchen.messaging.jms;

import tacos.domain.Order;

import javax.jms.JMSException;

public interface OrderReceiver {
    Order receiveOrder() throws JMSException;
}
