package silva.daniel.project.poc.kafka.consumer.message;

public class ProcessExecutionProcess {
    private String id;
    private String type;
    private String estrutura;
    private String key;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEstrutura() {
        return estrutura;
    }

    public void setEstrutura(String estrutura) {
        this.estrutura = estrutura;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "ProcessExecutionProcess{" +
                "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", estrutura='" + estrutura + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
