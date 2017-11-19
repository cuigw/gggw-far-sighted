package com.gggw.event;

import com.google.common.eventbus.Subscribe;

/**
 * @author Cui.GaoWei
 * @create 2017-10-24 下午11:29
 **/

public class RecordLogEventListener {

    @Subscribe
    public void listen(Event event) {

        String record = event.recordInToDB();

        System.out.println(Thread.currentThread() + "本次监听的日志记录为:" + record);
    }
}
