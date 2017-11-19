package com.gggw.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午9:52
 **/

public class NewListener {

    public int lastMessage = 0;

    @Subscribe
    public void listen(TestEvent event) {
        lastMessage = event.getMessage();
        System.out.println("ttttttt:"+lastMessage);
    }

    @Subscribe
    public void listenNew(TestNewEvent event) {

        lastMessage = event.getMessage();
        System.out.println("ttttttttttt new:"+lastMessage);
    }

    public int getLastMessage() {
        return lastMessage;
    }

}
