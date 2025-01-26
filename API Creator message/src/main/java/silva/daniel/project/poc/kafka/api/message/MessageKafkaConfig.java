package silva.daniel.project.poc.kafka.api.message;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;

@Configuration
public class MessageKafkaConfig {

    @Bean
    public ProducerFactory<String, Message> producerFactory() {
        HashMap<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:29092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "silva.daniel.project.poc.kafka.api.message.CustomKafkaSerializer");
        return new DefaultKafkaProducerFactory<>(config);
    }

    @Bean
    public KafkaTemplate<String, Message> messageKafkaProducer() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public NewTopic topicExample() {
        // Cria um tópico chamado "example-topic" com 3 partitions e um fator de replicação 1
        return new NewTopic("consume-message", 2, (short) 1);
    }
}
