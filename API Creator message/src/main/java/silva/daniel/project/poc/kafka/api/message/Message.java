package silva.daniel.project.poc.kafka.api.message;

public record Message(
        String type,
        Object payload
) {
}
