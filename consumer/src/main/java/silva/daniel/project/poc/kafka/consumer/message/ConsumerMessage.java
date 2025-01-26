package silva.daniel.project.poc.kafka.consumer.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.retry.annotation.Retry;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ConsumerMessage {

    private final HandlerMessageService handlerMessageService;
    private final DlqProducer dlqProducer;

    public ConsumerMessage(HandlerMessageService handlerMessageService, DlqProducer dlqProducer) {
        this.handlerMessageService = handlerMessageService;
        this.dlqProducer = dlqProducer;
    }

    @KafkaListener(topics = "consume-message", groupId = "CONSUMER-POC")
    @Retry(name = "kafkaRetry", fallbackMethod = "fallback")
    public void listen(@Payload ConsumerRecord<?, ?> message, Acknowledgment acknowledgment) {
        System.out.println("Received message: " + message);
        handlerMessageService.execute(message.value());
        acknowledgment.acknowledge();
    }

    public void fallback(ConsumerRecord<?, ?> message, Acknowledgment acknowledgment, RuntimeException ex) {
        System.err.println("Todas as tentativas de processamento falharam. Enviando mensagem para DLQ: " + message.value());
        dlqProducer.sendToDlq(message); // Envia a mensagem para a DLQ
        acknowledgment.acknowledge(); // Confirma o offset para evitar reprocessamento
    }
}
