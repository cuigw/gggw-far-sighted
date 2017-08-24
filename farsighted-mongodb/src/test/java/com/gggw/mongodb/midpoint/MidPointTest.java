package com.gggw.mongodb.midpoint;

import com.gggw.model.MidPoint;
import com.gggw.mongodb.base.BaseTest;
import com.mongodb.AggregationOutput;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import org.junit.Test;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by cuigaowei on 2017/8/24.
 */
public class MidPointTest extends BaseTest {
    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void getMidPonit() {
        DBCollection userCollection = mongoTemplate.getCollection("mid_points");
        DBObject match = new BasicDBObject("$match",new BasicDBObject("orderId",50015162));
        DBObject unwind = new BasicDBObject("$unwind",new BasicDBObject("path","$midPointlist"));
        DBObject project = new BasicDBObject("createtime","$midPointlist.createtime");
        project.put("lg","$midPointlist.lg");
        project.put("lt","$midPointlist.lt");
        project.put("sp","$midPointlist.speed");
        project.put("ac","$midPointlist.accuracy");
        DBObject pro = new BasicDBObject("$project",project);
        DBObject ac = new BasicDBObject("ac",new BasicDBObject("$lte",100));
        DBObject sortMatch = new BasicDBObject("$match",ac);
        DBObject sort = new BasicDBObject("$sort",new BasicDBObject("createtime",1));
        List<DBObject> pipeline = Arrays.asList(match, unwind, pro, sortMatch,sort);
        AggregationOutput output = userCollection.aggregate(pipeline);
        List<MidPoint> midPointList = new ArrayList<>();
        for (DBObject result : output.results()) {
            Map map = result.toMap();
            MidPoint midPoint = new MidPoint();
            midPoint.setOrderId(Integer.parseInt(map.get("_id").toString()));
            midPoint.setLg(Double.parseDouble(map.get("lg").toString()));
            midPoint.setLt(Double.parseDouble(map.get("lt").toString()));
            midPoint.setSpeed(Double.parseDouble(map.get("sp").toString()));
            midPoint.setAccuracy(Double.parseDouble(map.get("ac").toString()));
            midPoint.setCreatetime((Date) map.get("createtime"));
            midPointList.add(midPoint);
        }
    }
}
