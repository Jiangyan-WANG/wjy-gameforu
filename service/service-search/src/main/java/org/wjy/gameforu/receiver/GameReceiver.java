package org.wjy.gameforu.receiver;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wjy.gameforu.mq.constant.MqConst;
import org.wjy.gameforu.service.GameApiService;

import java.io.IOException;

/**
 * listener for the rabbitMq message
 */
@Component
public class GameReceiver {

    @Autowired
    GameApiService gameApiService;

    /**
     * add game listener
     * durable: message persistence
     * config exchange, routing, queue
     * @param gameId
     * @param message
     * @param channel
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE_GAMES_ADD, durable = "true"),
            exchange=@Exchange(value = MqConst.EXCHANGE_GAMES),
            key = {MqConst.ROUTING_GAMES_ADD}
    ))
    public void addGame(Integer gameId, Message message, Channel channel) throws IOException {
        if(gameId != null){
            gameApiService.addGame(gameId);
        }
        // manually confirm
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }

    /**
     * remove game listener
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConst.QUEUE__GAMES_REMOVE, durable = "true"),
            exchange=@Exchange(value = MqConst.EXCHANGE_GAMES),
            key = {MqConst.ROUTING_GAMES_REMOVE}
    ))
    public void removeGame(Integer gameId, Message message, Channel channel) throws IOException {
        if(gameId != null){
            gameApiService.removeGame(gameId);
        }
        // manually confirm
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
    }
}
