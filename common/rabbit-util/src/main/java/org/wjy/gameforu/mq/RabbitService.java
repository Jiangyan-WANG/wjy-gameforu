package org.wjy.gameforu.mq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * encapsulation of mq sender
 */

@Service
public class RabbitService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * send message
     * @param exchange exchange machine
     * @param routingKey route key
     * @param message message Object
     * @return
     */
    public Boolean sendMessage(String exchange, String routingKey, Object message){

        rabbitTemplate.convertAndSend(exchange, routingKey, message);
        return true;
    }
}
