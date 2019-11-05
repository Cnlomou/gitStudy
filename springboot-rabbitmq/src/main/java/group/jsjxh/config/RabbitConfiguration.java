package group.jsjxh.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }
    /* direct 模式*/
    @Bean(name = "hello")
    public Queue queue(){
        return  new Queue("direct.hello");
    }

    @Bean(name = "news")
    public Queue directQueue(){
        return new Queue("direct.news");
    }
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("direct");
    }

    @Bean
    public Binding directBinding(@Qualifier("hello") Queue queue, DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("direct.hello");
    }
    @Bean
    public Binding directBindingB(@Qualifier("news") Queue queue,DirectExchange directExchange){
        return BindingBuilder.bind(queue).to(directExchange).with("direct.news");
    }
    /* fanout 模式 */
    @Bean(name = "sjh")
    public Queue fanoutQueue(){
        return new Queue("fanout.sjh");
    }
    @Bean(name = "hjs")
    public Queue fanoutQueueB(){
        return new Queue("fanout.hjs");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }
    @Bean
    public Binding fanoutBinding(@Qualifier("sjh") Queue queue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }
    @Bean
    public Binding fanoutBindingB(@Qualifier("hjs") Queue queue,FanoutExchange fanoutExchange){
        return BindingBuilder.bind(queue).to(fanoutExchange);
    }

    /* topic 模式*/
    @Bean(name = "dy")
    public Queue topicQueue(){
        return new Queue("topic.dy");
    }

    @Bean(name = "yd")
    public Queue topicQueueB(){
        return new Queue("topic.yd");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topic");
    }

    @Bean
    public Binding topicBinding(@Qualifier("dy") Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("#.news");
    }
    @Bean
    public Binding topicBindingB(@Qualifier("yd") Queue queue,TopicExchange topicExchange){
        return BindingBuilder.bind(queue).to(topicExchange).with("topic.*");
    }
}
