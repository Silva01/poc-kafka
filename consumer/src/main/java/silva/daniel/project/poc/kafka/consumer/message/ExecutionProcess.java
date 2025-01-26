package silva.daniel.project.poc.kafka.consumer.message;

import org.springframework.stereotype.Component;

@Component
public class ExecutionProcess implements Process<ProcessExecutionProcess> {

    @Override
    public boolean isProcessType(Object type) {
        return ProcessExecutionProcess.class.equals(type.getClass());
    }

    @Override
    public void process(ProcessExecutionProcess payload) {
        System.out.println(payload.toString());
    }
}
