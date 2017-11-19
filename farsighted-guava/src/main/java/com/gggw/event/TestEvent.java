package com.gggw.event;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午4:23
 **/

public class TestEvent {

    private final int message;

    public TestEvent(int message) {
        this.message = message;
        System.out.println("event message:"+message);
    }

    public int getMessage() {
        return message;
    }
}
