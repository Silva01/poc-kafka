package silva.daniel.project.poc.kafka.consumer.message;

import org.springframework.stereotype.Component;

@Component
public class ConsumeProcess implements Process<ProcessConsumeProcess> {

    @Override
    public boolean isProcessType(Object type) {
        return ProcessConsumeProcess.class.equals(type.getClass());
    }

    @Override
    public void process(ProcessConsumeProcess payload) {
        System.out.println(payload.toString());
    }
}
