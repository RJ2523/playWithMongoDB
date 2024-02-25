package local.test.playWithMongo.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import local.test.playWithMongo.converter.ObjectConverter;
import local.test.playWithMongo.document.Message;
import local.test.playWithMongo.dto.MessageDTO;
import local.test.playWithMongo.model.MessageModel;
import local.test.playWithMongo.repository.MessageRepository;

@RestController
public class MessageController {


    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private MongoTemplate mongoTemplate;


    @GetMapping("/getMessages/{userid}")
    public ResponseEntity<?> getAllMessagesForUser(@PathVariable String userid){
        Message message = messageRepository.findByUserId(userid);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // @GetMapping("/chatExist")
    // public ResponseEntity<?> doesChatExist(@RequestParam String chatId){
    //     boolean isChatPresent = messageRepository.existsById(chatId);
    //     System.out.println(isChatPresent);
    //     return new ResponseEntity<>(isChatPresent,HttpStatus.OK);
    // }

    @PostMapping("/sendMessage")
    public ResponseEntity<?> sendMessage(@RequestBody MessageModel messageModel) throws JsonProcessingException{
        Message message = new Message();

        // creating id
        if(messageModel.getSender().compareTo(messageModel.getRecipient())>0)
            message.setId(messageModel.getRecipient() + "_" + messageModel.getSender());
        else
            message.setId(messageModel.getSender() + "_" + messageModel.getRecipient());

        //find if chatId already exist
        // if yes append in else block
        boolean isChatPresent = messageRepository.existsById(message.getId());
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setSender(messageModel.getSender());
        messageDTO.setMsg(messageModel.getMsg());
        messageDTO.setMsgRecievedAt(messageModel.getMsgReceivedAt());
        if(!isChatPresent){
            message.setMessages(List.of(messageDTO));
            messageRepository.save(message);
        }else{
            // deprived query is not powerful to update records, i.e using mongoTemplate
            Query query = Query.query(Criteria.where("_id").is(message.getId()));
            Update update = new Update().addToSet("messages", new ObjectConverter().convert(messageDTO));
            mongoTemplate.updateFirst(query, update,"Messages");
        }
      
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
