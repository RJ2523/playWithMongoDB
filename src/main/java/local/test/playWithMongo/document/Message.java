package local.test.playWithMongo.document;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import local.test.playWithMongo.dto.MessageDTO;

import lombok.Data;

@Document("Messages")
@Data
public class Message {
    @Id
    private String id; // if not specified mongodb will automatically generate _id
    List<MessageDTO> messages;
}
