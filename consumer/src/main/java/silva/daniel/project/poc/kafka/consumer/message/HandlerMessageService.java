package silva.daniel.project.poc.kafka.consumer.message;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class HandlerMessageService {

    private final List<Process> processes;

    public HandlerMessageService(List<Process> processes) {
        this.processes = processes;
    }

    public void execute(final Object message) {
        for (Process process : processes) {
            if (process.isProcessType(message)) {
                process.process(message);
                return;
            }
        }

        throw new IllegalArgumentException("Process not found for message: " + message);
    }
}
