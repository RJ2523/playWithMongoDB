package local.test.playWithMongo.model;

import java.util.Date;

import lombok.Data;

@Data
public class MessageModel{

    private String sender;
    private String recipient;
    private String msg;
    private Date msgReceivedAt;
}
