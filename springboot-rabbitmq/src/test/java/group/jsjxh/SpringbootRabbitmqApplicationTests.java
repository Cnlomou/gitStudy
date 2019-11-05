package group.jsjxh;

import com.rabbitmq.client.UnblockedCallback;
import group.jsjxh.bean.Book;
import group.jsjxh.rabbitsend.DirectSend;
import group.jsjxh.rabbitsend.FanoutSend;
import group.jsjxh.rabbitsend.TopicSend;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;
    @Autowired
    AmqpAdmin amqpAdmin;

    @Autowired
    DirectSend directSend;
    @Autowired
    FanoutSend fanoutSend;
    @Autowired
    TopicSend topicSend;
    @Test
    public void annotationTest(){
        Book book = new Book("三国", "罗贯中", "新华出版社");
//        directSend.sendStr("direct","hello world","direct.hello");
//        directSend.sendObject("direct",book,"direct.news");
//        fanoutSend.sendStr("fanout","fanout.key");
//        fanoutSend.sendBook("fanout",book);
        topicSend.sendStr("topic","nihao.sjh.news","我是news");
        topicSend.sendObject("topic","topic.nihao",book);

    }

    @Test
    void jsonRabbitTest(){
        Map<String,Object> maps=new HashMap<>();
        Book book=new Book();
        book.setName("水浒传");
        book.setAuthor("施耐庵");
        book.setPublisher("新华出版社");
        rabbitTemplate.convertAndSend("sjh.direct","sjh.nihao",book);
    }
    @Test
    void receiveRabbitMessage(){
        System.out.println(rabbitTemplate.receiveAndConvert("myqueue"));
    }

    @Test
    void  rabbitTest(){
        rabbitTemplate.send("sjh.direct","sjh.nihao",new Message("hello world".getBytes(),new MessageProperties()));
        String s = amqpAdmin.declareQueue(new Queue("sjh.news"));
        System.out.println(s);
    }

    @Test
    void contextLoads() {

        Person person=new Person();
        //java 1.8特性 函数接口，只要在该接口上添加一个@FunctionInteface 就能引用一个与该接口内具有相同签名的方法
        //类似与c语言中函数指针，能够传递一个函数的引用
        Supplier<?> supplier=person::getName;
    }

}
