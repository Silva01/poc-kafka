package silva.daniel.project.poc.kafka.api.message;

public enum MessageTypeEnum {
    CONSUME_MESSAGE("ProcessConsumeProcess"), PROCESS_MESSAGE("ProcessExecutionProcess");

    private String type;

    MessageTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
