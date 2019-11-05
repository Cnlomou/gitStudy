package group.jsjxh.rabbitsend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSend {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public  void sendStr(String exchange,String routingkey,String msg){
        rabbitTemplate.convertAndSend(exchange,routingkey,msg);
    }
    public void sendObject(String exchange,String routingkey,Object object){
        rabbitTemplate.convertAndSend(exchange,routingkey,object);
    }
}
