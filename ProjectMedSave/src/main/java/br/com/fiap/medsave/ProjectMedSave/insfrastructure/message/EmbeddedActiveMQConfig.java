package br.com.fiap.medsave.ProjectMedSave.insfrastructure.message;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class EmbeddedActiveMQConfig {

    @Bean
    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("embedded-broker");
        broker.setPersistent(false);
        broker.setUseJmx(false);
        broker.addConnector("vm://embedded-broker");
        broker.start();
        return broker;
    }

    @Bean
    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory amq = new ActiveMQConnectionFactory("vm://embedded-broker");
        return new CachingConnectionFactory(amq);
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory fac) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(fac);
        factory.setPubSubDomain(false);
        factory.setConcurrency("1-3");
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicListenerContainerFactory(ConnectionFactory fac) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(fac);
        factory.setPubSubDomain(true);
        factory.setConcurrency("1-3");
        factory.setSubscriptionDurable(false); // conexao com banco, nao ter que configurar por enquanto
        return factory;
    }

}
