package cn.edu.nwu.iqueue.common;

import cn.edu.nwu.iqueue.model.Interviewee;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class EventCenter {

    private static Map<String,Integer> map=new HashMap<>();
    private BlockingQueue<String> interviewerQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<Interviewee> intervieweeQueue = new LinkedBlockingQueue<>();
    private AtomicInteger interviewerCount = new AtomicInteger(0);
    private AtomicInteger intervieweeCount = new AtomicInteger(0);
    private AtomicInteger lastInterviewee = new AtomicInteger();





    public void mianbieren(String uid){
        Interviewee interviewee= intervieweeQueue.poll();
        if(interviewee!=null){
            System.out.println(uid+"开始面"+interviewee.getId());
        }else{
            if(!interviewerQueue.contains(uid)){
                interviewerQueue.offer(uid);
            }
        }
    }

    public void canjiamianshi(String uid){
        if (map.get(uid) == null) {
            Interviewee interviewee = new Interviewee();
            interviewee.setUid(uid);
            int id = intervieweeCount.getAndIncrement();
            interviewee.setId(id);
            map.put(uid,id);
            String s = interviewerQueue.poll();
            if(s!=null){
                System.out.println(s+"开始面"+id);
            }else{
                if(!intervieweeQueue.contains(interviewee)){
                    intervieweeQueue.offer(interviewee);
                }
            }
        }
    }

    public int getRemain(String uid){
        return map.get(uid)-lastInterviewee.get()-1;
    }

}
