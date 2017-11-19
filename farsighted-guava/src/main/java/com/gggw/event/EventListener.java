package com.gggw.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午4:23
 **/

public class EventListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("Message:"+lastMessage);
    }

    @Subscribe
    public void listenNew(TestNewEvent event) {

        lastMessage = event.getMessage();
        System.out.println("Message new:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }
}