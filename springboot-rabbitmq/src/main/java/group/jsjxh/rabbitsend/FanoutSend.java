package group.jsjxh.rabbitsend;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSend {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void sendStr(String exchange,String msg){
        rabbitTemplate.convertAndSend(exchange,"key",msg);
    }
    public void sendBook(String exchange,Object object){
        rabbitTemplate.convertAndSend(exchange,"key",object);
    }
}
