package otts.test.work;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import otts.test.work.util.QueueNames;

/**
 * Created by alex on 05.09.2015.<br/>
 * Spring Rabbit Configuration
 */
@Configuration
@EnableRabbit
public class RabbitConfiguration {
    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory("localhost");
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setConcurrentConsumers(1);
        factory.setMaxConcurrentConsumers(10);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }

    @Bean
    public Queue alertQueue() {
        return new Queue(QueueNames.ALERT);
    }

    @Bean
    public Queue buttonClickQueue() {
        return new Queue(QueueNames.BUTTON_CLICK);
    }

    @Bean
    public Queue userVisitPageQueue() {
        return new Queue(QueueNames.USER_VISIT_PAGE);
    }
}
