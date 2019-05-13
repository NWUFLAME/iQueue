package cn.edu.nwu.iqueue.controller;

import cn.edu.nwu.iqueue.common.EventCenter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class CoreController  {
    private static Map<String,EventCenter> map=new HashMap<>();
    @GetMapping("/createEvent")
    public String createEvent(){
        String uuid = UUID.randomUUID().toString();
        map.put(uuid,new EventCenter());
        return uuid;
    }
    @GetMapping("/mianbieren/{eid}/{uid}")
    public void mianbieren(@PathVariable("uid") String uid,@PathVariable("eid") String eid){
        EventCenter eventCenter = map.get(eid);
        eventCenter.mianbieren(uid);
    }

    @GetMapping("/canjiamianshi/{eid}/{uid}")
    public void canjiamianshi(@PathVariable("uid") String uid,@PathVariable("eid") String eid){
        EventCenter eventCenter = map.get(eid);
        eventCenter.canjiamianshi(uid);
    }

    @GetMapping("/qianmianjigeren/{eid}/{uid}")
    public int qianmianjigeren(@PathVariable("uid") String uid,@PathVariable("eid") String eid){
        EventCenter eventCenter = map.get(eid);
        return eventCenter.getRemain(uid);
    }

}
