package silva.daniel.project.poc.kafka.consumer.message;

public interface Process<T> {
    boolean isProcessType(Object type);
    void process(T payload);
}
