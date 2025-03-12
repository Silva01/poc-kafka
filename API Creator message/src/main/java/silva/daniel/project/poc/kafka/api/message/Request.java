package silva.daniel.project.poc.kafka.api.message;

public record Request(
        String id,
        String type,
        String estrutura,
        String key
) {
}
