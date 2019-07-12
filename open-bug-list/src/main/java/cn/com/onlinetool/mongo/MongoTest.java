package cn.com.onlinetool.mongo;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author choice
 * @date 2019-07-12 15:11
 *
 */
@RestController
@RequestMapping("/mongoTest")
public class MongoTest {
    @Autowired
    MongoTemplate mongoTemplate;
    private final String collectionName = "warningNationalStandard_19_1907";

    @RequestMapping("/template")
    public String testTemplateQuery(){
        long sTime = System.currentTimeMillis();
        Query query = new Query();
        query.addCriteria(
            Criteria.where("serviceId").is("19").and("startTime").gte(1).lte(222222222222222222L)
        );
        List<DBObject> dbObjects = mongoTemplate.find(query, DBObject.class,collectionName);
        dbObjects.forEach(dbObject -> {
            dbObject.get("serviceId");
        });
        return "使用MongoTemplate.find()查询" + dbObjects.size() + "数据耗时：：：" + (System.currentTimeMillis() - sTime);
    }

    @RequestMapping("/collection")
    public String testCollectionQuery(){
        long sTime = System.currentTimeMillis();
        BasicDBObject query = new BasicDBObject();
        query.put("serviceId", "19");
        query.put("startTime", new BasicDBObject("$gte", 1).append("$lte", 222222222222222222L));
        DBCollection collection = mongoTemplate.getCollection(collectionName);
        DBCursor dbObjects = collection.find(query);
        while (dbObjects.hasNext()){
            dbObjects.next().get("serviceId");
        }
        return "使用DBCollection.find()查询" + dbObjects.count() + "数据耗时：：：" + (System.currentTimeMillis() - sTime);
    }
}
