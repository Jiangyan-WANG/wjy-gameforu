package org.wjy.gameforu.mq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * confirm process config
 */
@Component
public class MQProducerAckConfig implements RabbitTemplate.ReturnCallback,RabbitTemplate.ConfirmCallback {

    //  for sending message
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //  run when the server loads Servlet, only run once
    @PostConstruct
    public void init(){
        rabbitTemplate.setReturnCallback(this);
        rabbitTemplate.setConfirmCallback(this);
    }

    /**
     * message send to the exchange successfully or not
     * @param correlationData   message loader
     * @param ack   confirm is sending to exchange
     * @param cause reason
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if(ack){
            System.out.println("message send successfully！");
        }else {
            System.out.println("message send failed！"+cause);
        }
    }

    /**
     * message not sent to the quere handler
     * @param message
     * @param replyCode
     * @param replyText
     * @param exchange
     * @param routingKey
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println("message body: " + new String(message.getBody()));
        System.out.println("reply code: " + replyCode);
        System.out.println("desc：" + replyText);
        System.out.println("message exchange : " + exchange);
        System.out.println("message routing : " + routingKey);
    }
}
