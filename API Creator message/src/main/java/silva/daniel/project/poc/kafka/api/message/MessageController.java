package silva.daniel.project.poc.kafka.api.message;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageProducer messageProducer;
    private final static String TOPIC_CONSUME_MESSAGE = "consume-message";

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping("/consume")
    @ResponseStatus(HttpStatus.CREATED)
    public void createConsumeMessage(@RequestBody Object message) {
        messageProducer.sendMessage(TOPIC_CONSUME_MESSAGE, new Message(MessageTypeEnum.CONSUME_MESSAGE.getType(), message));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCreateMessage(@RequestBody Object message) {
        messageProducer.sendMessage(TOPIC_CONSUME_MESSAGE, new Message(MessageTypeEnum.PROCESS_MESSAGE.getType(), message));
    }
}
