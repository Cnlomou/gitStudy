package group.jsjxh.service;

import group.jsjxh.bean.Book;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;


@Service
public class BookService {

    @RabbitListener(queues = {"myqueue"})
    public void getbook(Book book){
        System.out.println(book);
    }


}
