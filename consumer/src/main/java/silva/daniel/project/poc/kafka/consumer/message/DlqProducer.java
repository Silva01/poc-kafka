package silva.daniel.project.poc.kafka.consumer.message;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class DlqProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public DlqProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void sendToDlq(ConsumerRecord<?, ?> message) {
        try {
            if (message.value() instanceof StringBuilder messageWithError) {
                kafkaTemplate.send("dead-letter-queue", messageWithError.toString());
            } else {
                kafkaTemplate.send("dead-letter-queue", mapper.writeValueAsString(message.value()));
            }

            System.out.println("Mensagem enviada para DLQ: " + message);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem para DLQ: " + e.getMessage());
        }
    }
}
