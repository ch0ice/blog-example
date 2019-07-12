## 开源社区项目bug列表及解决方案
#### MongoTemplate.find()查询慢的问题
#####使用版本
* org.springframework.boot:spring-boot-starter-data-mongodb:1.5.12.RELEASE
    * org.springframework.data:spring-data-mongodb:1.10.11.RELEASE
    * org.mongodb:mongodb-driver:3.4.3
    
上面是版本对应关系，在使用 MongoTemplate.find(query,Object.class,collectionName)
时发现，查询巨慢无比，经过分析为对象序列化问题。
#####解决方案
* 修改查询方式
```java
Query query = new Query();
query.addCriteria(
    Criteria.where("serviceId")
);
List<DBObject> dbObjects = mongoTemplate.find(query, DBObject.class,collectionName);

更改为

BasicDBObject query = new BasicDBObject();
query.put("serviceId", "19");
DBCollection collection = mongoTemplate.getCollection(collectionName);
DBCursor dbObjects = collection.find(query);
```
* 更新版本
```
将
org.springframework.data:spring-data-mongodb:1.10.11.RELEASE
版本更新为1.10.12.RELEASE或更高

或者将
spring-boot-starter-data-mongodb:1.5.12.RELEASE
更新为 1.5.13或更高版本
```
 