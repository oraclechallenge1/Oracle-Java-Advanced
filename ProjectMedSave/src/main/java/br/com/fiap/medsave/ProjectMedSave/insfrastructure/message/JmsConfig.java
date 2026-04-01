package br.com.fiap.medsave.ProjectMedSave.insfrastructure.message;

import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

@Configuration
public class JmsConfig {

    @Bean
    public MappingJackson2MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public JmsTemplate queueJmsTemplate(ConnectionFactory cf, MappingJackson2MessageConverter converter) {
        JmsTemplate template = new JmsTemplate(cf);
        template.setPubSubDomain(false);
        template.setMessageConverter(converter);
        return template;
    }

    @Bean
    public JmsTemplate topicJmsTemplate(ConnectionFactory cf, MappingJackson2MessageConverter converter) {
        JmsTemplate template = new JmsTemplate(cf);
        template.setPubSubDomain(true);
        template.setMessageConverter(converter);
        return template;
    }

}
