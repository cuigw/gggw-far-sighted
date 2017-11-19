package com.gggw.event;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午9:40
 **/

public class TestNewEvent {

    private final int message;

    public TestNewEvent(int message) {
        this.message = message;
        System.out.println("event new message:"+message);
    }

    public int getMessage() {
        return message;
    }
}
