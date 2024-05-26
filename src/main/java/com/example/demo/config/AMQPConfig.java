package com.example.demo.config;

//import ch.qos.logback.classic.pattern.MessageConverter;
import com.example.demo.constants.TextConstants;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.Queue;
@Configuration
public class AMQPConfig {

    @Bean
    public Queue confirmAccountCreation()
    {
        return new Queue(TextConstants.AccountConfirmQueue);
    }

    @Bean
    public Queue confirmAccountDeletion()
    {
        return new Queue(TextConstants.DeleteAccountConfirmQueue);
    }

    @Bean
    public Queue confirmOrderFinalisation() {return new Queue(TextConstants.FinaliseOrderConfirmQueue);}
    @Bean
    public MessageConverter jsonMessageConverter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory)
    {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

}
