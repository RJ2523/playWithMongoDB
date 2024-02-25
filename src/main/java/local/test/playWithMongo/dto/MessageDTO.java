package local.test.playWithMongo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class MessageDTO implements Serializable{
    private String sender;
    private String msg;
    private Date msgRecievedAt;
}
