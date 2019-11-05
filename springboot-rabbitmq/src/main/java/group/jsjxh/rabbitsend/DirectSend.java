package group.jsjxh.rabbitsend;

import group.jsjxh.rabbitlistrnner.DirectListenner;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DirectSend {

    @Autowired
    RabbitTemplate rabbitTemplate;



    public void sendStr(String exchange,String msg,String routingkey){
        rabbitTemplate.convertAndSend(exchange,routingkey,msg);
    }

    public void sendObject(String exchage,Object object,String routingkey){
        rabbitTemplate.convertAndSend(exchage,routingkey,object);
    }
}
