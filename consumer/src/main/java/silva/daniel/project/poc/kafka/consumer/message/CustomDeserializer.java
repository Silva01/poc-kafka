package silva.daniel.project.poc.kafka.consumer.message;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class CustomDeserializer implements Deserializer<Object> {

    private final ObjectMapper objectMapper;

    public CustomDeserializer() {
        this.objectMapper = new ObjectMapper();
    }


    @Override
    public Object deserialize(String topic, byte[] data) {
        final var messageWithError = new StringBuilder();
        try {
            JsonNode rootNode = objectMapper.readTree(data);
            String type = rootNode.get("type").asText();
            messageWithError.append(rootNode);
            Class<?> targetClass = Class.forName("silva.daniel.project.poc.kafka.consumer.message." + type); // Mapeia o tipo para a classe correta
            return objectMapper.treeToValue(rootNode.get("payload"), targetClass);
        } catch (Exception e) {
            return messageWithError;
        }
    }
}
