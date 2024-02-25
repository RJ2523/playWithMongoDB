package local.test.playWithMongo.converter;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import local.test.playWithMongo.dto.MessageDTO;

public class ObjectConverter{


    // to remove _class while updating the document
    public DBObject convert(MessageDTO source) {
        DBObject dbObject = new BasicDBObject();
        // Extract relevant fields and populate the DBObject
        dbObject.put("sender", source.getSender());
        dbObject.put("msg", source.getMsg());
        dbObject.put("msgRecievedAt", source.getMsgRecievedAt());
        return dbObject;
    }
}
