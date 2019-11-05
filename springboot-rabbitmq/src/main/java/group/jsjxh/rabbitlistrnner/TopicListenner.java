package group.jsjxh.rabbitlistrnner;


import group.jsjxh.bean.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = {"topic.dy","topic.yd"})
public class TopicListenner {
    private static Logger logger= LoggerFactory.getLogger(TopicListenner.class);

    @RabbitHandler
    public void getStr(String msg){
        logger.info(msg);
    }
    @RabbitHandler
    public void getBook(Book book){
        logger.info(book.toString());
    }
}
