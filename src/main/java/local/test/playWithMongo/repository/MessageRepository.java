package local.test.playWithMongo.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import local.test.playWithMongo.document.Message;
import local.test.playWithMongo.dto.MessageDTO;

@Repository
public interface MessageRepository extends MongoRepository<Message, String> {
    @Query("{id:?0}")
    Message findByUserId(String chatId);

    boolean existsById(String chatId);
}
