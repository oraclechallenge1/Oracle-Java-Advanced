package br.com.fiap.medsave.ProjectMedSave.insfrastructure.message;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;

@Configuration
public class EmbeddedActiveMQConfig {

    public BrokerService brokerService() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setBrokerName("embedded-broker");
        broker.setPersistent(false);
        broker.setUseJmx(false);
        broker.addConnector("vm://embedded-broker"); // isso é um processo local
        broker.start();
        return broker;
    }

    public ConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory amq = new ActiveMQConnectionFactory("vm://embedded-broker");
        return new CachingConnectionFactory(amq);
    }

}
